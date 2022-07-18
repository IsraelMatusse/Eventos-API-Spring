package com.eventosapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventosapi.Models.ConvidadoModel;
import com.eventosapi.Models.EventoModel;

@Repository
public interface ConvidadoRepository extends JpaRepository <ConvidadoModel, String> {
	Iterable <ConvidadoModel> findByEvento(EventoModel evento);

}
