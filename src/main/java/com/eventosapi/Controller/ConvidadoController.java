package com.eventosapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventosapi.Models.ConvidadoModel;
import com.eventosapi.Models.EventoModel;
import com.eventosapi.Repository.ConvidadoRepository;

@RestController
@RequestMapping("/Convidado")
public class ConvidadoController {

	@Autowired
	private ConvidadoRepository cvr;
	private EventoModel em;
	
	@PostMapping("/CadastrarConvidado")
	public ConvidadoModel CadastrarConvidado(@RequestParam ("codigo") Long codigo,  @RequestBody ConvidadoModel convidado, EventoModel evento) {
		convidado.setEvento(evento);
		return cvr.save(convidado);
		
	}
	
	@GetMapping("/ListarConvidados")
	public List <ConvidadoModel> ListarConvidados(ConvidadoModel convidado) {
		return cvr.findAll();
		
	}
	
	@RequestMapping("/SearchByEvent")
	public Iterable<ConvidadoModel> searchbyevent(@RequestParam ("codigo") Long codigo, ConvidadoModel convidado,  EventoModel evento) {
		Iterable<ConvidadoModel>convidado1= cvr.findByEvento(evento);
		return  convidado1;
	
	}
	@RequestMapping("/ApagarConvidado")
	public void ApagarConvidado(@RequestParam ("biconvidado") String biconvidado, ConvidadoModel convidado) {
		ConvidadoModel apagconvid=cvr.findByBiconvidado(biconvidado);
		cvr.delete(apagconvid);
		
	}
}
