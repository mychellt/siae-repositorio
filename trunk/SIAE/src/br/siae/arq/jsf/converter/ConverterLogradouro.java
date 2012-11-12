package br.siae.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.dominio.Logradouro;
import br.siae.arq.utils.ValidatorUtil;


@FacesConverter(value = "converterLogradouro")
public class ConverterLogradouro implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Logradouro logradouro = new Logradouro();
      	if( ValidatorUtil.isNotEmpty(value)){
      		logradouro.setId( Integer.parseInt(value));
      	}
        return logradouro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Logradouro ){
			Logradouro logradouro = (Logradouro) obj;
    		return String.valueOf( logradouro.getId() );
    	}
    	return String.valueOf(0);
	}

}
