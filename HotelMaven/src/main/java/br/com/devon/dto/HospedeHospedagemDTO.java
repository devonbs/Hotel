package br.com.devon.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HospedeHospedagemDTO implements Serializable{
	
	private static final long serialVersionUID = 7069533547798641621L;
	private String nome;
	private String documento;
	private String telefone;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	
	public HospedeHospedagemDTO(String nome, String documento, String telefone, LocalDateTime dataEntrada, LocalDateTime dataSaida) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
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
	

}
