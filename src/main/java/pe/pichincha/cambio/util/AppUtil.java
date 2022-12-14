package pe.pichincha.cambio.util;

import org.springframework.beans.BeanUtils;

import pe.pichincha.cambio.dto.CambioDto;
import pe.pichincha.cambio.dto.MonedaDto;
import pe.pichincha.cambio.dto.TipoCambioDto;
import pe.pichincha.cambio.entity.Cambio;
import pe.pichincha.cambio.entity.Moneda;
import pe.pichincha.cambio.entity.TipoCambio;

public class AppUtil {
	
	public static MonedaDto entityToDtoMoneda(Moneda Moneda) {
        MonedaDto MonedaDto = new MonedaDto();
        BeanUtils.copyProperties(Moneda, MonedaDto);
        return MonedaDto;
    }

    public static Moneda dtoToEntityMoneda(MonedaDto MonedaDto) {
        Moneda Moneda = new Moneda();
        BeanUtils.copyProperties(MonedaDto, Moneda);
        return Moneda;
    }
    
    public static TipoCambioDto entityToDtoTipoCambio(TipoCambio TipoCambio) {
    	TipoCambioDto TipoCambioaDto = new TipoCambioDto();
        BeanUtils.copyProperties(TipoCambio, TipoCambioaDto);
        return TipoCambioaDto;
    }

    public static TipoCambio dtoToEntityTipoCambio(TipoCambioDto TipoCambioaDto) {
        TipoCambio TipoCambioa = new TipoCambio();
        BeanUtils.copyProperties(TipoCambioaDto, TipoCambioa);
        return TipoCambioa;
    }
    
    public static CambioDto entityToDtoCambio(Cambio Cambio) {
    	CambioDto CambioaDto = new CambioDto();
        BeanUtils.copyProperties(Cambio, CambioaDto);
        return CambioaDto;
    }

    public static Cambio dtoToEntityCambio(CambioDto CambioaDto) {
        Cambio Cambioa = new Cambio();
        BeanUtils.copyProperties(CambioaDto, Cambioa);
        return Cambioa;
    }
}
