package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.arq.utils.ArqCache;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Serie;


@FacesConverter(value = "converterSerie")
public class ConverterSerie implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Serie serie = new Serie();
      	if( ValidatorUtil.isNotEmpty(value)){
      		serie =  ArqCache.getSerieById( Integer.parseInt(value) );
      	}
        return serie;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Serie ){
    		Serie serie = (Serie) obj;
    		return String.valueOf( serie.getId() );
    	}
    	return String.valueOf(0);
	}

}
