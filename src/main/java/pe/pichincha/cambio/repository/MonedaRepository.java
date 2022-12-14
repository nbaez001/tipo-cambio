package pe.pichincha.cambio.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import pe.pichincha.cambio.entity.Moneda;

@Repository
public interface MonedaRepository extends ReactiveSortingRepository<Moneda, Long> {

}