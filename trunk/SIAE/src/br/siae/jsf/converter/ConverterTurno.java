package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.arq.utils.ValidatorUtil;
import br.siae.dominio.comum.Turno;
import br.siae.utils.SIAECache;


@FacesConverter(value = "converterTurno")
public class ConverterTurno implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Turno turno = new Turno();
      	if( ValidatorUtil.isNotEmpty(value)){
      		turno = SIAECache.getTurnoById( Integer.parseInt(value) );
      	}
        return turno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Turno ){
    		Turno turno = (Turno) obj;
    		return String.valueOf( turno.getId() );
    	}
    	return String.valueOf(0);
	}

}
