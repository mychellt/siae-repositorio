package br.siae.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.dominio.Sexo;
import br.siae.arq.utils.ValidatorUtil;

@FacesConverter(value = "converterSexo")
public class ConverterSexo implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Sexo sexo = new Sexo();
      	if( ValidatorUtil.isNotEmpty(value)){
      		sexo.setId( Integer.parseInt(value));
      	}
        return sexo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Sexo ){
    		Sexo sexo = (Sexo) obj;
    		return String.valueOf( sexo.getId() );
    	}
    	return String.valueOf(0);
	}
}
