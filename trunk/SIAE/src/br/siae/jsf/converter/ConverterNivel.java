package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.utils.SIAECache;

@FacesConverter(value = "converterNivel")
public class ConverterNivel implements Converter{

	  @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
		  	Nivel nivel = new Nivel();
	      	if( ValidatorUtil.isNotEmpty(value)){
	      		nivel = SIAECache.getNivelById( Integer.parseInt(value) );
	      	}
	        return nivel;
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	    	if( value instanceof Nivel ){
	    		Nivel nivel = (Nivel) value;
	    		return String.valueOf( nivel.getId() );
	    	}
	    	return String.valueOf(0);
	    }

}
