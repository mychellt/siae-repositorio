package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.NivelFuncional;

@FacesConverter(value = "converterNivelFuncional")
public class ConverterNivelFuncional implements Converter{
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	  	NivelFuncional nivelFuncional = new NivelFuncional();
      	if( ValidatorUtil.isNotEmpty(value)){
      		nivelFuncional = ArqCache.getNivelFuncionalById( Integer.parseInt(value) );
      	}
        return nivelFuncional;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if( value instanceof NivelFuncional ){
    		NivelFuncional nivelFuncional = (NivelFuncional) value;
    		return String.valueOf( nivelFuncional.getId() );
    	}
    	return String.valueOf(0);
    }
}