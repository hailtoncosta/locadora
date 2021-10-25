package com.veiculo.locadora.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Aluguel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dataAluguel;
	private String dataPrazo;
	private String valorAluguel;
	private String valorMulta;
	private String kmEntrada;
	private String kmSaida;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataAluguel() {
		return dataAluguel;
	}
	public void setDataAluguel(String dataAluguel) {
		this.dataAluguel = dataAluguel;
	}
	public String getDataPrazo() {
		return dataPrazo;
	}
	public void setDataPrazo(String dataPrazo) {
		this.dataPrazo = dataPrazo;
	}
	public String getValorAluguel() {
		return valorAluguel;
	}
	public void setValorAluguel(String valorAluguel) {
		this.valorAluguel = valorAluguel;
	}
	public String getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(String valorMulta) {
		this.valorMulta = valorMulta;
	}
	public String getKmEntrada() {
		return kmEntrada;
	}
	public void setKmEntrada(String kmEntrada) {
		this.kmEntrada = kmEntrada;
	}
	public String getKmSaida() {
		return kmSaida;
	}
	public void setKmSaida(String kmSaida) {
		this.kmSaida = kmSaida;
	}
	
}
