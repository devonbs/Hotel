package br.com.devon.bo;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;

import br.com.devon.conexao.HibernateUtil;
import br.com.devon.dao.HotelDAO;
import br.com.devon.dto.HospedeDTO;
import br.com.devon.dto.HospedeHospedagemDTO;
import br.com.devon.dto.TotalGastoHospedeDTO;
import br.com.devon.entity.Hospede;

public class HotelBO {
	
	private HotelDAO hotelDao = new HotelDAO();
	
	private static Session sessao = HibernateUtil.getSessionFactory().openSession();
	
	public void novaHospedagem(Hospede hospede, String dataEntrada, String dataSaida, boolean adicionalVeiculo) {
		sessao.beginTransaction();
		try {
			hotelDao.novaHospedagem(sessao, hospede, dataEntrada, dataSaida, adicionalVeiculo);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
		} 
	}
	
	public void novoHospede(String nome, String documento, String telefone) throws Exception {
		sessao.beginTransaction();
		try {
			hotelDao.novoHospede(sessao, nome, documento, telefone);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			throw new Exception(e.getMessage());
		} 
	}
	
	public List<HospedeDTO> buscarHospedes() {
		sessao.beginTransaction();
		List<HospedeDTO> hospedes = hotelDao.buscarHospedes(sessao);
		sessao.getTransaction().rollback();
		return hospedes;
	}
	
	public List<TotalGastoHospedeDTO> buscarHospedesResidentes() {
		sessao.beginTransaction();
		List<TotalGastoHospedeDTO> hospedes = hotelDao.buscarHospedesResidentes(sessao);
		sessao.getTransaction().rollback();
		return hospedes;
	}
	
	public HospedeDTO buscarHospedePorNome(String nome) {
		sessao.beginTransaction();
		HospedeDTO hospede = hotelDao.buscarHospedePorNome(sessao, nome);
		sessao.getTransaction().rollback();
		return hospede;
	}
	
	public HospedeDTO buscarHospedePorDocumento(String documento) {
		sessao.beginTransaction();
		HospedeDTO hospede = hotelDao.buscarHospedePorDocumento(sessao, documento);
		sessao.getTransaction().rollback();
		return hospede;
	}
	
	public HospedeDTO buscarHospedePorTelefone(String telefone) {
		sessao.beginTransaction();
		HospedeDTO hospede = hotelDao.buscarHospedePorTelefone(sessao, telefone);
		sessao.getTransaction().rollback();
		return hospede;
	}
	
	public List<HospedeHospedagemDTO> buscarHospedagensPorHospedeNome(String nome) {
		sessao.beginTransaction();
		List<HospedeHospedagemDTO> hospede = hotelDao.buscarHospedagensPorHospedeNome(sessao, nome);
		sessao.getTransaction().rollback();
		return hospede;
	}
	
	public List<TotalGastoHospedeDTO> buscarGastosHospedes() {
		sessao.beginTransaction();
		List<TotalGastoHospedeDTO> totalGastos = hotelDao.buscarGastosHospedes(sessao);
		sessao.getTransaction().rollback();
		return totalGastos;
	}
	
	public List<TotalGastoHospedeDTO> buscarGastoTotalHospede(String nome) {
		sessao.beginTransaction();
		List<TotalGastoHospedeDTO> totalGastos = hotelDao.buscarGastoTotalHospede(sessao, nome);
		sessao.getTransaction().rollback();
		return totalGastos;
	}
	
	public List<TotalGastoHospedeDTO> buscarHospedesAntigos() {
		sessao.beginTransaction();
		List<TotalGastoHospedeDTO> totalGastos = hotelDao.buscarHospedesAntigos(sessao);
		sessao.getTransaction().rollback();
		return totalGastos;
	}
	
	

}
