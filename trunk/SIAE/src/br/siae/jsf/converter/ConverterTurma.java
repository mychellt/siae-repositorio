package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Turma;


@FacesConverter(value = "converterTurma")
public class ConverterTurma implements Converter {
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	  	Turma turma = new Turma();
      	if( ValidatorUtil.isNotEmpty(value)){
      		turma.setId( Integer.parseInt(value));
      	}
        return turma;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if( value instanceof Turma ){
    		Turma turma = (Turma) value;
    		return String.valueOf( turma.getId() );
    	}
    	return String.valueOf(0);
    }
}
