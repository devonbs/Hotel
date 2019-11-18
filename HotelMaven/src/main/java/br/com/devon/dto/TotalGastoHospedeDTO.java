package br.com.devon.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TotalGastoHospedeDTO {
	
	private String nome;
	private String documento;
	private Integer idHospedagem;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	private Double valorTotal;
	private Double valor;
	private Double valorAutomovel;
	
	public TotalGastoHospedeDTO(String nome, String documento, Integer idHospedagem, LocalDateTime dataEntrada,
			LocalDateTime dataSaida, Double valorTotal, Double valor, Double valorAutomovel) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.idHospedagem = idHospedagem;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valorTotal = valorTotal;
		this.valor = valor;
		this.valorAutomovel = valorAutomovel;
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
	public Integer getIdHospedagem() {
		return idHospedagem;
	}
	public void setIdHospedagem(Integer idHospedagem) {
		this.idHospedagem = idHospedagem;
	}
	public String getDataEntrada() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return dataEntrada.format(formatter);
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return dataSaida.format(formatter);
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valor) {
		this.valorTotal = valor;
	}
	public Double getValorAutomovel() {
		return valorAutomovel;
	}
	public void setValorAutomovel(Double valorAutomovel) {
		this.valorAutomovel = valorAutomovel;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

}
