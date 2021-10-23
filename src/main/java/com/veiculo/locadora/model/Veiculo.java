package com.veiculo.locadora.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String modelo;
	private String marca;
	private String ano;
	private String placa;
	private String alugado;
	private String batido;
	private String kmatual;
	private String valordiaria;
	private String descricao;
	private String tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getAlugado() {
		return alugado;
	}
	public void setAlugado(String alugado) {
		this.alugado = alugado;
	}
	public String getBatido() {
		return batido;
	}
	public void setBatido(String batido) {
		this.batido = batido;
	}
	public String getKmatual() {
		return kmatual;
	}
	public void setKmatual(String kmatual) {
		this.kmatual = kmatual;
	}
	public String getValordiaria() {
		return valordiaria;
	}
	public void setValordiaria(String valordiaria) {
		this.valordiaria = valordiaria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
