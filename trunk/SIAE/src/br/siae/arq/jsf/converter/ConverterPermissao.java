package br.siae.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.siae.arq.dominio.Estado;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.utils.ValidatorUtil;

@FacesConverter(value = "converterPermissao")
public class ConverterPermissao implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Permissao permissao = new Permissao();
      	if( ValidatorUtil.isNotEmpty(value)){
      		permissao.setId( Integer.parseInt(value));
      	}
        return permissao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object obj) {
		if( obj instanceof Estado ){
			Permissao permissao = (Permissao) obj;
    		return String.valueOf( permissao.getId() );
    	}
    	return String.valueOf(0);
	}
}
