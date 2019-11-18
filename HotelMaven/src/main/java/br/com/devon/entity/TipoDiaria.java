package br.com.devon.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TIPO_DIARIA")
@XmlRootElement
public class TipoDiaria implements Serializable {
	
	private static final long serialVersionUID = 2388702564339512655L;
	
	@Id
	@Column(name = "ID_TIPO_DIARIA", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoDiaria;
	@Column(name = "TIPO")
	private String tipo;
	@Column(name = "VALOR")
	private double valor;
	@Column(name = "VALOR_AUTOMOVEL")
	private double valorAutomovel;
	@OneToMany(mappedBy = "tipoDiaria", fetch = FetchType.EAGER)
	private List<Diaria> diaria;
	
	public TipoDiaria() {
	}
	
	public TipoDiaria(Integer idTipoDiaria) {
		setIdTipoDiaria(idTipoDiaria);
	}
	
	public Integer getIdTipoDiaria() {
		return idTipoDiaria;
	}
	public void setIdTipoDiaria(Integer idTipoDiaria) {
		this.idTipoDiaria = idTipoDiaria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValorAutomovel() {
		return valorAutomovel;
	}
	public void setValorAutomovel(double valorAutomovel) {
		this.valorAutomovel = valorAutomovel;
	}

	public List<Diaria> getDiaria() {
		return diaria;
	}

	public void setDiaria(List<Diaria> diaria) {
		this.diaria = diaria;
	}

}
