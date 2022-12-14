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

import pe.pichincha.cambio.dto.MonedaDto;
import pe.pichincha.cambio.service.MonedaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/moneda")
public class MonedaController {

	@Autowired
	private MonedaService service;

	@GetMapping
	public Flux<MonedaDto> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Mono<MonedaDto> getOne(@PathVariable Long id) {
		return service.findById(Long.valueOf(id));
	}

	@PostMapping
	public Mono<MonedaDto> save(@RequestBody Mono<MonedaDto> req) {
		return service.save(req);
	}

	@PutMapping("/update/{id}")
	public Mono<MonedaDto> update(@RequestBody Mono<MonedaDto> req, @PathVariable Long id) {
		return service.update(req, id);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return service.deleteById(id);
	}
}
