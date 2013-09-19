package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.NivelFormacao;

@FacesConverter(value = "converterNivelFormacao")
public class ConverterNivelFormacao implements Converter{

	  @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
		  	NivelFormacao nivel = new NivelFormacao();
	      	if( ValidatorUtil.isNotEmpty(value)){
	      		nivel.setId( Integer.parseInt(value));
	      	}
	        return nivel;
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	    	if( value instanceof NivelFormacao ){
	    		NivelFormacao nivel = (NivelFormacao) value;
	    		return String.valueOf( nivel.getId() );
	    	}
	    	return String.valueOf(0);
	    }

}
