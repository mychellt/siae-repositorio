package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Disciplina;

@FacesConverter(value = "converterDisciplina")
public class ConverterDisciplina implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Disciplina disciplina = new Disciplina();
      	if( ValidatorUtil.isNotEmpty(value)){
      		disciplina.setId( Integer.parseInt(value) );
      	}
        return disciplina;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if( obj instanceof Disciplina ){
			Disciplina disciplina = (Disciplina) obj;
    		return String.valueOf( disciplina.getId() );
    	}
    	return String.valueOf(0);
	}

}
