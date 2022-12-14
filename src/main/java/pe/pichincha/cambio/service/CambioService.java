package pe.pichincha.cambio.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.pichincha.cambio.dto.CambioDto;
import pe.pichincha.cambio.repository.CambioRepository;
import pe.pichincha.cambio.util.AppUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CambioService {

	@Autowired
	private CambioRepository cambioRepository;

	public Flux<CambioDto> findAll() {
		return cambioRepository.findAll(Sort.by("id"))
				.map(AppUtil::entityToDtoCambio);
	}
	
	public Mono<CambioDto> calcularCambio(Mono<CambioDto> cambio) {
		return cambio
				.flatMap(cambioOld->{
					return cambioRepository.obtenerFracciones(cambioOld.getMonto(), cambioOld.getIdMonedaOrig(), cambioOld.getIdMonedaDest());
				})
				.map(AppUtil::dtoToEntityCambio)
				.doOnNext(p -> p.setMontoEquival(p.getFracMonedaOrig()/p.getFracMonedaDest()*p.getMonto()))
				.doOnNext(p -> p.setMontoEquival(new BigDecimal(p.getMontoEquival()).setScale(2,RoundingMode.FLOOR).doubleValue()))
				.doOnNext(p -> p.setFlgActivo(1))
				.doOnNext(p -> log.info("Calculo " + p.getMontoEquival()+" Frac Orig: "+p.getFracMonedaOrig()+" Frac Dest: "+p.getFracMonedaDest()+" Monto: "+p.getMonto()))
				.flatMap(cambioRepository::save).map(AppUtil::entityToDtoCambio);
	}
}
