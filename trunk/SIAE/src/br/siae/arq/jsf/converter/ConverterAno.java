package br.siae.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.dominio.Ano;
import br.siae.arq.utils.ValidatorUtil;


@FacesConverter(value = "converterAno")
public class ConverterAno implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ano ano = new Ano();
      	if( ValidatorUtil.isNotEmpty(value)){
      		ano.setId( Integer.parseInt(value));
      	}
        return ano;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Ano ){
    		Ano ano = (Ano)obj;
    		return String.valueOf( ano.getId() );
    	}
    	return String.valueOf(0);
	}
}
