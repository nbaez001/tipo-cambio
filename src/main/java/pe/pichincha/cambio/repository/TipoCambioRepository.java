package pe.pichincha.cambio.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import pe.pichincha.cambio.dto.TipoCambioDto;
import pe.pichincha.cambio.entity.TipoCambio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TipoCambioRepository extends ReactiveSortingRepository<TipoCambio, Long> {

	@Query(value = "SELECT TC.ID,TC.ID_MONEDA,M.NOMBRE AS NOM_MONEDA,TC.VALOR_COMPRA,TC.VALOR_VENTA,TC.FLG_ACTIVO,TC.ID_USUARIO_MODIF FROM TIPO_CAMBIO TC "
			+ "INNER JOIN MONEDA M ON M.ID=TC.ID_MONEDA WHERE TC.FLG_ACTIVO = 1")
	Flux<TipoCambioDto> findAllActive(Sort sort);

	@Query(value = "SELECT TC.ID,TC.ID_MONEDA,M.NOMBRE AS NOM_MONEDA,TC.VALOR_COMPRA,TC.VALOR_VENTA,TC.FLG_ACTIVO,TC.ID_USUARIO_MODIF FROM TIPO_CAMBIO TC "
			+ " INNER JOIN MONEDA M ON M.ID=TC.ID_MONEDA WHERE TC.FLG_ACTIVO = 1 AND TC.ID IN ("
			+ " SELECT MAX(TC2.ID) FROM TIPO_CAMBIO TC2 WHERE TC2.ID_MONEDA = :idMoneda"
			+ ")")
	Mono<TipoCambioDto> findByIdMoneda(@Param("idMoneda") Long idMoneda);

}
