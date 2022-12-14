package pe.pichincha.cambio.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import pe.pichincha.cambio.dto.CambioDto;
import pe.pichincha.cambio.entity.Cambio;
import reactor.core.publisher.Mono;

@Repository
public interface CambioRepository extends ReactiveSortingRepository<Cambio, Long> {

	@Query(value = "SELECT "
			+ ":monto AS MONTO,"
			+ ":idMonedaOrig AS ID_MONEDA_ORIG,"
			+ ":idMonedaDest AS ID_MONEDA_DEST,"
			+ "(SELECT TC.VALOR_COMPRA FROM TIPO_CAMBIO TC INNER JOIN MONEDA M ON M.ID=TC.ID_MONEDA WHERE TC.FLG_ACTIVO = 1 AND TC.ID IN (SELECT MAX(TC2.ID) FROM TIPO_CAMBIO TC2 WHERE TC2.ID_MONEDA = :idMonedaOrig)) AS FRAC_MONEDA_ORIG,"
			+ "(SELECT TC.VALOR_COMPRA FROM TIPO_CAMBIO TC INNER JOIN MONEDA M ON M.ID=TC.ID_MONEDA WHERE TC.FLG_ACTIVO = 1 AND TC.ID IN (SELECT MAX(TC2.ID) FROM TIPO_CAMBIO TC2 WHERE TC2.ID_MONEDA = :idMonedaDest)) AS FRAC_MONEDA_DEST")
	Mono<CambioDto> obtenerFracciones(@Param("monto") Double monto, @Param("idMonedaOrig") Long idMonedaOrig,
			@Param("idMonedaDest") Long idMonedaDest);
}