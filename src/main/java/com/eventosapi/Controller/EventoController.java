package com.eventosapi.Controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.ModelAndView;

import com.eventosapi.Models.ConvidadoModel;
import com.eventosapi.Models.EventoModel;
import com.eventosapi.Repository.ConvidadoRepository;
import com.eventosapi.Repository.EventoRepository;



@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository evr;
	@Autowired
	private ConvidadoRepository cvr;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/form";
	}
	
	@GetMapping("/Listar")
	public List <EventoModel> Listar() {
		return evr.findAll();
	}
	

	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form( EventoModel evento){
		evr.save(evento);
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView ListaEventos() {
		ModelAndView mv= new ModelAndView("index");
		Iterable<EventoModel> eventos= evr.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable ("codigo") Long codigo) {
		EventoModel evento= evr.findByCodigo(codigo);
		ModelAndView mv= new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		return mv;
	}
	@RequestMapping(value="/{codigo}", method= RequestMethod.POST)
	public String detalhesEvento(@PathVariable ("codigo") Long codigo, ConvidadoModel convidado) {
		EventoModel evento=evr.findByCodigo(codigo);
		convidado.setEvento(evento);
		cvr.save(convidado);
		return "redirect:/{codigo}";
		
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

	@RequestMapping("/ApagarEvento")
	public void ApagarEvento(@RequestParam ("codigo") Long codigo, EventoModel evento) {
		EventoModel apagevento=evr.findByCodigo(codigo);
		evr.delete(apagevento);
	}
	
	@PostMapping("/ActualizarEvento")
	public void actualizarevento(@RequestParam ("codigo") Long codigo,@RequestBody EventoModel eventoed) {
		Optional<EventoModel> evento= Optional.ofNullable(evr.findByCodigo(codigo));
		evento.get().setNomeevento(eventoed.getNomeevento());
		evento.get().setData(eventoed.getData());
		evento.get().setLocal(eventoed.getLocal());
		evento.get().setHora(eventoed.getHora());
		evr.save(evento.get());
		
	}
	
	
}
