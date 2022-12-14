package pe.pichincha.cambio.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moneda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String nombre;
	private String simbolo;
	private Integer flgActivo;
	private Integer idUsuarioModif;

}
