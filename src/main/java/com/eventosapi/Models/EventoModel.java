package com.eventosapi.Models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="evento")
public class EventoModel implements Serializable {

	private static final Long serialversionUID=1l;
	@Id 
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long codigo;
	@Column(name="nomeevento")
	private String nomeevento;
	@Column(name="local")
	private String local;
	@Column(name="data")
	private Date data;
	@Column(name="hora")
	private String hora;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNomeevento() {
		return nomeevento;
	}
	public void setNomeevento(String nomeevento) {
		this.nomeevento = nomeevento;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public static Long getSerialversionuid() {
		return serialversionUID;
	}
	@OneToMany
	private List<ConvidadoModel> convidados;
	
}
