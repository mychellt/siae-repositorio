package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;

@FacesConverter(value = "converterNivel")
public class ConverterNivel implements Converter{

	  @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        Nivel nivel = new Nivel();
	        if( ValidatorUtil.isNotEmpty( value ) ) {
	        	nivel.setId( Long.parseLong(value) );
	        }
	        return nivel;
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	    	if( ValidatorUtil.isEmpty(value)) return "";
	        return value.toString();
	    }

}
