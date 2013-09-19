package br.arq.utils;

import java.text.Normalizer;

public class StringUtils{
	public static String noAccents(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = str.replaceAll("[^\\p{ASCII}]", "");
	    return str;
	}
}
