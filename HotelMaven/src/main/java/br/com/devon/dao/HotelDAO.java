package br.com.devon.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.devon.dto.HospedeDTO;
import br.com.devon.dto.HospedeHospedagemDTO;
import br.com.devon.dto.TotalGastoHospedeDTO;
import br.com.devon.entity.Diaria;
import br.com.devon.entity.Hospedagem;
import br.com.devon.entity.Hospede;
import br.com.devon.entity.TipoDiaria;
import br.com.devon.util.DataUtil;

public class HotelDAO {
	
	@SuppressWarnings("unchecked")
	public List<HospedeDTO> buscarHospedes(Session sessao) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.HospedeDTO(h.nome, h.documento, h.telefone) from Hospede h ");
		
		Query query = sessao.createQuery(hql.toString());
		List<HospedeDTO> resultado = null;
		try {
			resultado = query.getResultList();
		} catch(NoResultException e) {
			throw new NoResultException("Não foi encontrado nenhum resultado");
		} 
		return resultado;
	}
	
	public HospedeDTO buscarHospedePorNome(Session sessao, String nome) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.HospedeDTO(h.nome, h.documento, h.telefone) from Hospede h ");
		hql.append(" where h.nome = :nome ");
		
		Query query = sessao.createQuery(hql.toString());
		query.setParameter("nome", nome);
		HospedeDTO resultado = null;
		try {
			resultado = (HospedeDTO) query.getSingleResult();
		} catch(NoResultException e) {
			throw new NoResultException("Não foi encontrado nenhum resultado");
		}
		return resultado;
	}

	public HospedeDTO buscarHospedePorDocumento(Session sessao, String documento) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.HospedeDTO(h.nome, h.documento, h.telefone) from Hospede h ");
		hql.append(" where h.documento = :documento ");
		
		Query query = sessao.createQuery(hql.toString());
		query.setParameter("documento", documento);
		HospedeDTO resultado = null;
		try {
			resultado = (HospedeDTO) query.getSingleResult();
		} catch(NoResultException e) {
			throw new NoResultException("Não foi encontrado nenhum resultado");
		} 
		return resultado;
	}
	
	public HospedeDTO buscarHospedePorTelefone(Session sessao, String telefone) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.HospedeDTO(h.nome, h.documento, h.telefone) from Hospede h ");
		hql.append(" where h.telefone = :telefone ");
		
		Query query = sessao.createQuery(hql.toString());
		query.setParameter("telefone", telefone);
		HospedeDTO resultado = null;
		try {
			resultado = (HospedeDTO) query.getSingleResult();
		} catch(NoResultException e) {
			throw new NoResultException("Não foi encontrado nenhum resultado");
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<HospedeHospedagemDTO> buscarHospedagensPorHospedeNome(Session sessao, String nome) {
		EntityManager em = sessao.getEntityManagerFactory().createEntityManager();
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.HospedeHospedagemDTO(h.nome, h.documento, h.telefone, hm.dataEntrada, hm.dataSaida)");
		hql.append( " from Hospede h ");
		hql.append(" inner join h.hospedagem hm ");
		hql.append(" where h.nome = :nome ");
		
		Query query = em.createQuery(hql.toString());
		query.setParameter("nome", nome);
		return query.getResultList();
	}
	
	public void novoHospede(Session sessao, String nome, String documento, String telefone) throws Exception {
		Hospede hospede = new Hospede(nome, documento, telefone);
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select count(h) from Hospede h ");
		hql.append(" where h.nome = :nome ");
		hql.append(" and h.documento = :documento ");
		hql.append(" and h.telefone = :telefone ");
		
		Query query = sessao.createQuery(hql.toString());
		query.setParameter("nome", nome);
		query.setParameter("documento", documento);
		query.setParameter("telefone", telefone);
		Long res = (Long) query.getSingleResult();
		if(res.equals(0l)) {
			sessao.save(hospede);
		} else {
			throw new Exception("Já existe um usuário com estes dados");
		}
	}

	public void novaHospedagem(Session sessao, Hospede hospede, String dataEntrada, String dataSaida, boolean adicionalVeiculo) {
		LocalDateTime dataEnt = DataUtil.converteDataLocalDate(dataEntrada);
		LocalDateTime dataSai = DataUtil.converteDataLocalDate(dataSaida);
		
		insereHospede(sessao, hospede);
		
		Hospedagem hospedagem = new Hospedagem(hospede, dataEnt, dataSai, adicionalVeiculo);
		Long qtdDias = DataUtil.contaIntervaloDatas(dataEnt, dataSai);
		LocalDateTime ldt = dataEnt;
		List<Diaria> lDiaria = new ArrayList<Diaria>();
		for (int i = 0; i < qtdDias; i++) {
			Diaria diaria = new Diaria();
			diaria.setDataDiaria(ldt);
			diaria.setHospedagem(hospedagem);
			diaria.setTipoDiaria(new TipoDiaria(DataUtil.isFinalSemana(ldt) ? 2 : 1)); // 1 - Semanal / 2 - Final de semana
			ldt = ldt.plusDays(1);
			lDiaria.add(diaria);
			sessao.save(diaria);
		} 
		//Se hora for maior que 16:00, adiciona mais uma diária
		if(DataUtil.isAposDezesseisHoras(dataSai)) {
			Diaria diaria = new Diaria();
			diaria.setDataDiaria(dataSai);
			diaria.setHospedagem(hospedagem);
			diaria.setTipoDiaria(new TipoDiaria(DataUtil.isFinalSemana(ldt) ? 2 : 1)); // 1 - Semanal / 2 - Final de semana
			lDiaria.add(diaria);
			sessao.save(diaria);
		}
		hospedagem.setDiaria(lDiaria);
		sessao.save(hospedagem);
	}
	
	private void insereHospede(Session sessao, Hospede hospede) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select h.idHospede from Hospede h ");
		hql.append(" where h.nome = :nome ");
		hql.append(" and h.documento = :documento ");
		hql.append(" and h.telefone = :telefone ");
		
		Query query = sessao.createQuery(hql.toString());
		query.setParameter("nome", hospede.getNome());
		query.setParameter("documento", hospede.getDocumento());
		query.setParameter("telefone", hospede.getTelefone());
		Integer idHospede = (Integer) query.getSingleResult();
		if(idHospede == null) {
			sessao.save(hospede);
		} else {
			hospede.setIdHospede(idHospede);
		}
	}

	public TipoDiaria buscarTipoData(Session sessao, LocalDateTime ldt) {
		Integer tipoData = DataUtil.isFinalSemana(ldt) ? 2 : 1; // 1 - Semanal / 2 - Final de semana
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select td from TipoDiaria ");
		hql.append(" where td.idTipoDiaria = :tipoDiaria ");
		
		Query query = sessao.createQuery(hql.toString());
		query.setParameter("tipoDiaria", tipoData);
		return (TipoDiaria) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TotalGastoHospedeDTO> buscarGastosHospedes(Session sessao) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.TotalGastoHospedeDTO(h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida, sum(td.valor), sum(td.valorAutomovel) ) ");
		hql.append(" from Hospede h ");
		hql.append(" inner join h.hospedagem hm ");
		hql.append(" inner join hm.diaria d");
		hql.append(" inner join d.tipoDiaria td ");
		hql.append(" group by h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida, td.valor, td.valorAutomovel ");
		
		Query query = sessao.createQuery(hql.toString());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TotalGastoHospedeDTO> buscarGastoTotalHospede(Session sessao, String nome) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.TotalGastoHospedeDTO(h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida,  ");
		hql.append(" (select sum(inTd.valor) from TipoDiaria inTd inner join inTd.diaria inD inner join inD.hospedagem inH where inH.hospede.id = h.idHospede) as valorTotal, sum(td.valor) , sum(td.valorAutomovel) ) ");
		hql.append(" from Hospede h ");
		hql.append(" inner join h.hospedagem hm ");
		hql.append(" inner join hm.diaria d");
		hql.append(" inner join d.tipoDiaria td ");
		hql.append(" where h.nome = :nome ");
		hql.append(" group by h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida, h.idHospede ");
		
		Query query = sessao.createQuery(hql.toString());
		query.setParameter("nome", nome);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TotalGastoHospedeDTO> buscarHospedesResidentes(Session sessao) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.TotalGastoHospedeDTO(h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida,  ");
		hql.append(" (select sum(inTd.valor) from TipoDiaria inTd inner join inTd.diaria inD inner join inD.hospedagem inH where inH.hospede.id = h.idHospede) as valorTotal, sum(td.valor) , sum(td.valorAutomovel) ) ");
		hql.append(" from Hospede h ");
		hql.append(" inner join h.hospedagem hm ");
		hql.append(" inner join hm.diaria d");
		hql.append(" inner join d.tipoDiaria td ");
		hql.append(" where hm.dataSaida < now()  ");
		hql.append(" group by h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida, h.idHospede ");
		
		Query query = sessao.createQuery(hql.toString());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TotalGastoHospedeDTO> buscarHospedesAntigos(Session sessao) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.devon.dto.TotalGastoHospedeDTO(h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida,  ");
		hql.append(" (select sum(inTd.valor) from TipoDiaria inTd inner join inTd.diaria inD inner join inD.hospedagem inH where inH.hospede.id = h.idHospede) as valorTotal, sum(td.valor) , sum(td.valorAutomovel) ) ");
		hql.append(" from Hospede h ");
		hql.append(" inner join h.hospedagem hm ");
		hql.append(" inner join hm.diaria d");
		hql.append(" inner join d.tipoDiaria td ");
		hql.append(" where hm.dataSaida < now()  ");
		hql.append(" group by h.nome, h.documento, hm.idHospedagem, hm.dataEntrada, hm.dataSaida, h.idHospede ");
		
		Query query = sessao.createQuery(hql.toString());
		return query.getResultList();
	}
	
	

}
