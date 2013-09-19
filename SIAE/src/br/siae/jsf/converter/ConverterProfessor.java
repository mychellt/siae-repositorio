package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.arq.utils.ArqCache;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Professor;


@FacesConverter(value = "converterProfessor")
public class ConverterProfessor implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor professor = new Professor();
      	if( ValidatorUtil.isNotEmpty(value)){
      		professor = ArqCache.getProfessorById( Integer.parseInt(value) ) ;
      	}
        return professor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if( obj instanceof Professor ){
			Professor professor = (Professor) obj;
    		return String.valueOf( professor.getId() );
    	}
    	return String.valueOf(0);
	}

}
