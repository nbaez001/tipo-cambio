package pe.pichincha.cambio.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Long idMoneda;
	private Double valorCompra;
	private Double valorVenta;
	private Integer flgActivo;
	private Long idUsuarioModif;

	@Transient
	private String nomMoneda;
}
