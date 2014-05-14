package br.arq.utils;

import java.text.Normalizer;

/**
 * Classe que contém diversos métodos auxiliares para processamento de Strings.
 * </br>
 * @author Mychell Teixeira
 *
 */
public class StringUtils{
	/**
	 * Método que remove todos os acentos de uma dada string.
	 * <br/>
	 * @param str String contendo algum tipo de acento.
	 * @return String sem acento.
	 */
	public static String noAccents(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = str.replaceAll("[^\\p{ASCII}]", "");
	    return str;
	}
	
	/**
	 * Método que recebe uma string e retorna o valor em long da mesma.
	 * <br/>
	 * @param s String cujo valor em long será extraído. 
	 * @return Valor long da string.
	 */
	public final static Long extractLong(String s) {
		if (s == null || s.trim().equals("")) {
			return null;
		}
		if (org.apache.commons.lang.StringUtils.isNumeric(s)) {
			return Long.parseLong(s);
		}
		StringBuffer integer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (org.apache.commons.lang.StringUtils.isNumeric(s.charAt(i)+"")) {
				integer.append(s.charAt(i));
			}
		}
		if (integer.length() > 0 && integer.length() <= String.valueOf(Long.MAX_VALUE).length()) {
			return new Long(integer.toString());
		} else {
			return null;
		}
	}
}
