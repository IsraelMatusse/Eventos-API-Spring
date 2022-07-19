package com.eventosapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventosapi.Models.ConvidadoModel;
import com.eventosapi.Models.EventoModel;
import com.eventosapi.Repository.ConvidadoRepository;
import com.eventosapi.Repository.EventoRepository;



@RestController
@RequestMapping("Evento")
public class EventoController {
	
	@Autowired
	private EventoRepository evr;
	@Autowired
	private ConvidadoRepository cvr;
	
	@GetMapping("/Listar")
	public List <EventoModel> Listar() {
		return evr.findAll();
	}
	

	@PostMapping("/Cadastrar")
	public EventoModel cadastrar(@RequestBody EventoModel evento){
		return evr.save(evento);
	}
	
	@RequestMapping("/SearchByCod")
	public EventoModel SearchByCod(@RequestParam ("codigo") Long codigo) {
		EventoModel evento= evr.findByCodigo(codigo);
		return evento;
	}
	
	@RequestMapping("/SearchByName")
	public EventoModel SearchByName(@RequestParam ("nome") String nomeevento) {
		EventoModel evento=evr.findByNomeevento(nomeevento);
		return evento;
	}

/*	@RequestMapping("/CadastrarConvidado")
	public ConvidadoModel CadastrarConvidado(@RequestParam ("codigo") Long codigo, ConvidadoModel convidado) {
		EventoModel evento= evr.findByCodigo(codigo);
		convidado.setEvento(evento);
		return cvr.save(convidado);
	}
	*/
	@RequestMapping("/ApagarEvento")
	public void ApagarEvento(@RequestParam ("codigo") Long codigo, EventoModel evento) {
		EventoModel apagevento=evr.findByCodigo(codigo);
		evr.delete(apagevento);
	}
	
}
