package pe.pichincha.cambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.pichincha.cambio.dto.TipoCambioDto;
import pe.pichincha.cambio.service.TipoCambioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tipo-cambio")
public class TipoCambioController {
	@Autowired
	private TipoCambioService service;

	@GetMapping
	public Flux<TipoCambioDto> getAll() {
		return service.findAll();
	}

	@GetMapping("/{idMoneda}")
	public Mono<TipoCambioDto> get(@PathVariable Long idMoneda) {
		return service.findById(Long.valueOf(idMoneda));
	}

	@PostMapping
	public Mono<TipoCambioDto> save(@RequestBody Mono<TipoCambioDto> req) {
		return service.save(req);
	}

	@PutMapping("/update/{id}")
	public Mono<TipoCambioDto> update(@RequestBody Mono<TipoCambioDto> req, @PathVariable Long id) {
		return service.update(req, id);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return service.deleteById(id);
	}
}
