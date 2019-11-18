package br.com.devon.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "HOSPEDAGEM")
@XmlRootElement
public class Hospedagem implements Serializable {
	
	private static final long serialVersionUID = 2891965966710303184L;
	
	@Id
	@Column(name = "ID_HOSPEDAGEM", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHospedagem;
	@ManyToOne
	@JoinColumn(name = "HOSPEDE_ID")
	private Hospede hospede;
	@Column(name = "DATA_ENTRADA")
	@JsonDeserialize
	private LocalDateTime dataEntrada;
	@Column(name = "DATA_SAIDA")
	@JsonDeserialize
	private LocalDateTime dataSaida;
	@Column(name = "ADICIONAL_VEICULO")
	private boolean adicionalVeiculo;
	@OneToMany(mappedBy = "hospedagem", fetch = FetchType.EAGER)
	private List<Diaria> diaria;
	
	public Hospedagem() {
	}
	
	public Hospedagem(Hospede hospede, String dataEntrada, String dataSaida) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss");
		this.hospede = hospede;
		this.dataEntrada = LocalDateTime.parse(dataEntrada, dtf);
		this.dataSaida = LocalDateTime.parse(dataSaida, dtf);
	}

	public Hospedagem(Hospede hospede, LocalDateTime dataEntrada, LocalDateTime dataSaida, boolean adicionalVeiculo) {
		super();
		this.hospede = hospede;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.adicionalVeiculo = adicionalVeiculo;
	}
	public Integer getIdHospedagem() {
		return idHospedagem;
	}
	public void setIdHospedagem(Integer idHospedagem) {
		this.idHospedagem = idHospedagem;
	}
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.dataEntrada = LocalDateTime.parse(dataEntrada, dtf);
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.dataSaida = LocalDateTime.parse(dataSaida, dtf);
	}
	public List<Diaria> getDiaria() {
		return diaria;
	}
	public void setDiaria(List<Diaria> diaria) {
		this.diaria = diaria;
	}

	public boolean isAdicionalVeiculo() {
		return adicionalVeiculo;
	}

	public void setAdicionalVeiculo(boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}
	
}
