package com.eventosapi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/CadastrarConvidado", method= RequestMethod.POST)
	public ConvidadoModel CadastrarConvidado(@PathVariable ("codigo") Long codigo, ConvidadoModel convidado, EventoModel evento) {
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
	
	@PostMapping("/ActualizarConvidado")
	public void ActualizarEvento(@RequestParam ("biconvidado") String biconvidado, @RequestBody ConvidadoModel convidadoed, EventoModel evento) {
		Optional<ConvidadoModel> convidado= Optional.ofNullable(cvr.findByBiconvidado(biconvidado));
		convidado.get().setNomeconvidado(convidadoed.getNomeconvidado());
		convidado.get().setMoradaconvidado(convidadoed.getMoradaconvidado());
		convidado.get().setEvento(convidadoed.getEvento());
		cvr.save(convidado.get());
		
		
	}
	
	@RequestMapping("/SearchByBi")
	public ConvidadoModel SearchByBi(@RequestParam("biconvidado") String biconvidado, ConvidadoModel convidado) {
		convidado= cvr.findByBiconvidado(biconvidado);
		return convidado;
		
	}
}
