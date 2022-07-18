package com.eventosapi.Models;

import javax.persistence.*;

@Entity
@Table(name="convidado")
public class ConvidadoModel {
	
	@Column(name="nome_convidado")
	private String nomeconvidado;
	@Column(name="morada_convidado")
	private String moradaconvidado;
	@Id
	@Column(name="bi_convidado")
	private String biconvidado;
	@ManyToOne
	private EventoModel evento;
	public String getNomeconvidado() {
		return nomeconvidado;
	}
	public void setNomeconvidado(String nomeconvidado) {
		this.nomeconvidado = nomeconvidado;
	}
	public String getMoradaconvidado() {
		return moradaconvidado;
	}
	public void setMoradaconvidado(String moradaconvidado) {
		this.moradaconvidado = moradaconvidado;
	}
	public String getBiconvidado() {
		return biconvidado;
	}
	public void setBiconvidado(String biconvidado) {
		this.biconvidado = biconvidado;
	}
	public EventoModel getEvento() {
		return evento;
	}
	public void setEvento(EventoModel evento) {
		this.evento = evento;
	}
}
