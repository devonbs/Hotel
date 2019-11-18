package br.com.devon.dto;

import java.io.Serializable;

public class HospedeDTO implements Serializable {
	
	private static final long serialVersionUID = -2143109593910379244L;
	private String nome;
	private String documento;
	private String telefone;
	
	public HospedeDTO(String nome, String documento, String telefone) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
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

}
