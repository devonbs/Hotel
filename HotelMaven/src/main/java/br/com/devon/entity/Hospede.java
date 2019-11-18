package br.com.devon.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name= "HOSPEDE")
@XmlRootElement
public class Hospede implements Serializable {
	

	private static final long serialVersionUID = -8428154710478591344L;
	@Id
	@Column(name = "ID_HOSPEDE", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHospede;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "DOCUMENTO")
	private String documento;
	@Column(name = "TELEFONE")
	private String telefone;
	@OneToMany(mappedBy = "hospede", fetch = FetchType.EAGER)
	private List<Hospedagem> hospedagem;
	
	public Hospede() {
		setHospedagem(null);
	}
	
	public Hospede(String nome, String documento, String telefone) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
	}
	public Integer getIdHospede() {
		return idHospede;
	}
	public void setIdHospede(Integer idHospede) {
		this.idHospede = idHospede;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Hospedagem> getHospedagem() {
		return hospedagem;
	}
	public void setHospedagem(List<Hospedagem> hospedagem) {
		this.hospedagem = hospedagem;
	}

}
