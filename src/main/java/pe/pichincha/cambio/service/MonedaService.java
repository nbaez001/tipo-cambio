package pe.pichincha.cambio.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.pichincha.cambio.dto.MonedaDto;
import pe.pichincha.cambio.repository.MonedaRepository;
import pe.pichincha.cambio.util.AppUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MonedaService {
	
	@Autowired
	private MonedaRepository monedaRepository;

	public Flux<MonedaDto> findAll() {
		return monedaRepository.findAll(Sort.by("id", "nombre")).map(AppUtil::entityToDtoMoneda)
				.delaySequence(Duration.ofSeconds(1));
	}

	public Mono<MonedaDto> findById(Long id) {
		return monedaRepository.findById(id).map(AppUtil::entityToDtoMoneda)
				.doOnNext(p -> log.info("Moneda with id " + p.getId()));
	}

	public Mono<MonedaDto> save(Mono<MonedaDto> moneda) {
		return moneda.map(AppUtil::dtoToEntityMoneda).flatMap(monedaRepository::save).map(AppUtil::entityToDtoMoneda);
	}

	public Mono<MonedaDto> update(Mono<MonedaDto> moneda, Long id) {
		return monedaRepository.findById(id)
				.flatMap(p -> moneda.map(AppUtil::dtoToEntityMoneda).doOnNext(e -> e.setId(id)))
				.flatMap(monedaRepository::save).map(AppUtil::entityToDtoMoneda);
	}

	public Mono<Void> deleteById(Long id) {
		return monedaRepository.deleteById(id).doOnNext(c -> log.info("Moneda with id {} deleted", id));
	}
}
