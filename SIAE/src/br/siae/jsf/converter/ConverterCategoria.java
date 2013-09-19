package br.siae.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.arq.utils.ArqCache;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Categoria;

@FacesConverter(value = "converterCategoria")
public class ConverterCategoria implements Converter{
	
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	  	Categoria categoria = new Categoria();
      	if( ValidatorUtil.isNotEmpty(value)){
      		categoria = ArqCache.getCategoriaById( Integer.parseInt(value));
      	}
        return categoria;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if( value instanceof Categoria ){
    		Categoria categoria = (Categoria) value;
    		return String.valueOf( categoria.getId() );
    	}
    	return String.valueOf(0);
    }

}
