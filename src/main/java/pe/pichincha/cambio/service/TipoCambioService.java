package pe.pichincha.cambio.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.pichincha.cambio.dto.TipoCambioDto;
import pe.pichincha.cambio.repository.TipoCambioRepository;
import pe.pichincha.cambio.util.AppUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TipoCambioService {

	@Autowired
	private TipoCambioRepository tipoCambioRepository;

	public Flux<TipoCambioDto> findAll() {
		return tipoCambioRepository.findAllActive(Sort.by("id", "idMoneda")).delaySequence(Duration.ofSeconds(1));
	}

	public Mono<TipoCambioDto> findById(Long idMoneda) {
		return tipoCambioRepository.findByIdMoneda(idMoneda).doOnNext(p -> log.info("TipoCambio with id " + p.getId()));
	}

	public Mono<TipoCambioDto> save(Mono<TipoCambioDto> tipoCambio) {
		return tipoCambio.map(AppUtil::dtoToEntityTipoCambio)
				.flatMap(tipoCambioRepository::save).map(AppUtil::entityToDtoTipoCambio);
	}

	public Mono<TipoCambioDto> update(Mono<TipoCambioDto> tipoCambio, Long id) {
		return tipoCambioRepository.findById(id)
				.flatMap(p -> tipoCambio.map(AppUtil::dtoToEntityTipoCambio).doOnNext(e -> e.setId(id)))
				.flatMap(tipoCambioRepository::save).map(AppUtil::entityToDtoTipoCambio);
	}

	public Mono<Void> deleteById(Long id) {
		return tipoCambioRepository.deleteById(id).doOnNext(c -> log.info("TipoCambio with id {} deleted", id));
	}
}
