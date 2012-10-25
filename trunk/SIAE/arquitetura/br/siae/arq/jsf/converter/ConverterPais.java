package br.siae.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.dominio.Pais;
import br.siae.arq.utils.ValidatorUtil;


@FacesConverter(value = "converterPais")
public class ConverterPais implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pais pais = new Pais();
      	if( ValidatorUtil.isNotEmpty(value)){
      		pais.setId( Integer.parseInt(value));
      	}
        return pais;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Pais ){
			Pais pais = (Pais) obj;
    		return String.valueOf( pais.getId() );
    	}
    	return String.valueOf(0);
	}
}
