package br.siae.arq.jsf.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterCPF")
public class ConverterCPF implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		StringBuilder builder = new StringBuilder(value);
		boolean invalido = false;
		int i = 0;
		while (i < builder.length() && !invalido) {
			char c = builder.charAt(i);
			if (Character.isDigit(c)) {
				i++;
			}
			else if (Character.isDefined('.')) {
				builder.deleteCharAt(i);
			}
			else if (Character.isDefined('-')) {
				builder.deleteCharAt(i);
			}
			else {
				invalido = true;
			}
		}

		if (invalido) {
			FacesMessage message = new FacesMessage("Ocorreu um erro de conversão. ", "CPF inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(message);
		}
		return builder.toString();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		String v = obj.toString();
		StringBuilder builder = new StringBuilder();
		int tam = v.length();
		for (int i = 0; i < tam; i++) {
			if (i == 3 || i == 6)
				builder.append(".");
			else if (i == 9)
				builder.append("-");
			if (i < 11)
				builder.append(v.charAt(i));
			else
				break;
		}
		return builder.toString();
	}
	
	public static String formate(long cpf ) {
		String v =  String.valueOf(cpf);
		StringBuilder builder = new StringBuilder();
		int tam = v.length();
		for (int i = 0; i < tam; i++) {
			if (i == 3 || i == 6)
				builder.append(".");
			else if (i == 9)
				builder.append("-");
			if (i < 11)
				builder.append(v.charAt(i));
			else
				break;
		}
		return builder.toString();
	}
}
