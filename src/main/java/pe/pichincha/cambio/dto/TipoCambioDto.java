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
public class TipoCambioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long idMoneda;
	private Double valorCompra;
	private Double valorVenta;
	private Integer flgActivo;
	private Long idUsuarioModif;
	private String nomMoneda;
}
