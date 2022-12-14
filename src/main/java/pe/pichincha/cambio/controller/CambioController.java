package pe.pichincha.cambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.pichincha.cambio.dto.CambioDto;
import pe.pichincha.cambio.dto.TipoCambioDto;
import pe.pichincha.cambio.service.CambioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cambio")
public class CambioController {

	@Autowired
	private CambioService service;

	@GetMapping
	public Flux<CambioDto> getAll() {
		return service.findAll();
	}
	
	@PostMapping
	public Mono<CambioDto> calcularCambio(@RequestBody Mono<CambioDto> req) {
		return service.calcularCambio(req);
	}
}
