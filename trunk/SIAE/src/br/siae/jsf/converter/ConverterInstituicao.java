package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.comum.Instituicao;

@FacesConverter(value = "converterInstituicao")
public class ConverterInstituicao implements Converter{
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	  	Instituicao instituicao = new Instituicao();
      	if( ValidatorUtil.isNotEmpty(value)){
      		instituicao.setId( Integer.parseInt(value));
      	}
        return instituicao;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if( value instanceof Instituicao ){
    		Instituicao instituicao = (Instituicao) value;
    		return String.valueOf( instituicao.getId() );
    	}
    	return String.valueOf(0);
    }
}
