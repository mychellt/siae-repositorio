package br.siae.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.dominio.Municipio;
import br.siae.arq.utils.ValidatorUtil;

@FacesConverter(value = "converterMunicipio")
public class ConverterMunicipio implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Municipio municipio = new Municipio();
      	if( ValidatorUtil.isNotEmpty(value)){
      		municipio.setId( Integer.parseInt(value));
      	}
        return municipio;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Municipio ){
			Municipio municipio = (Municipio) obj;
    		return String.valueOf( municipio.getId() );
    	}
    	return String.valueOf(0);
	}
}
