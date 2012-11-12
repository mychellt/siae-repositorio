package br.siae.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.dominio.Estado;
import br.siae.arq.utils.ValidatorUtil;

@FacesConverter(value = "converterEstado")
public class ConverterEstado implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estado estado = new Estado();
      	if( ValidatorUtil.isNotEmpty(value)){
      		estado.setId( Integer.parseInt(value));
      	}
        return estado;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Estado ){
			Estado estado = (Estado) obj;
    		return String.valueOf( estado.getId() );
    	}
    	return String.valueOf(0);
	}
}
