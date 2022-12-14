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
public class MonedaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String simbolo;
	private Integer flgActivo;
	private Integer idUsuarioModif;

}
