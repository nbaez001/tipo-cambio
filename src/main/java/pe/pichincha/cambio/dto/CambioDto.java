package pe.pichincha.cambio.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CambioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Double monto;
	private Long idMonedaOrig;
	private Long idMonedaDest;
	private Double montoEquival;
	private Integer flgActivo;
	private Long idUsuarioModif;

	private Double fracMonedaOrig;
	private Double fracMonedaDest;
}
