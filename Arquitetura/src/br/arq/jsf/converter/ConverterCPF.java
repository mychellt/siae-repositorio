package br.arq.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.arq.utils.StringUtils;

@FacesConverter(value = "converterCPF")
public class ConverterCPF implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().equals("")){
			value = "0";
		}

		return StringUtils.extractLong(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		return null;
	}
	
	public static final String formate(Long cpf)  {
		return null;
	}
	

}
