package br.com.devon.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DIARIA")
@XmlRootElement
public class Diaria implements Serializable {
	
	private static final long serialVersionUID = 3250886879147464793L;
	
	@Id
	@Column(name = "ID_DIARIA", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDiaria;
	@ManyToOne
	@JoinColumn(name = "HOSPEDAGEM_ID")
	private Hospedagem hospedagem;
	@ManyToOne
	@JoinColumn(name = "TIPO_DIARIA_ID")
	private TipoDiaria tipoDiaria;
	@Column(name = "DATA_DIARIA")
	private LocalDateTime dataDiaria;
	
	public Diaria() {
	}
	
	public Integer getIdDiaria() {
		return idDiaria;
	}
	public void setIdDiaria(Integer idDiaria) {
		this.idDiaria = idDiaria;
	}
	public Hospedagem getHospedagem() {
		return hospedagem;
	}
	public void setHospedagem(Hospedagem hospedagem) {
		this.hospedagem = hospedagem;
	}
	public TipoDiaria getTipoDiaria() {
		return tipoDiaria;
	}
	public void setTipoDiaria(TipoDiaria tipoDiaria) {
		this.tipoDiaria = tipoDiaria;
	}
	public LocalDateTime getDataDiaria() {
		return dataDiaria;
	}
	public void setDataDiaria(LocalDateTime dataDiaria) {
		this.dataDiaria = dataDiaria;
	}
	

}
