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
public class Cambio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Double monto;
	private Long idMonedaOrig;
	private Long idMonedaDest;
	private Double montoEquival;
	private Integer flgActivo;
	private Long idUsuarioModif;
	
	@Transient
	private Double fracMonedaOrig;
	@Transient
	private Double fracMonedaDest;

}
