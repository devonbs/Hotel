package br.com.devon.dto;

import java.io.Serializable;

import br.com.devon.entity.Hospede;

public class HospedagemDTO implements Serializable{

	private static final long serialVersionUID = -5081636077952439373L;
	
	private Integer idHospedagem;
	private Hospede hospede;
	private String dataEntrada;
	private String dataSaida;
	private boolean adicionalVeiculo;
	
	public HospedagemDTO() {
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
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public boolean isAdicionalVeiculo() {
		return adicionalVeiculo;
	}
	public void setAdicionalVeiculo(boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}
	

}
