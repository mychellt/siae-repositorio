package br.siae.arq.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	
	private static final String PLAIN_ASCII =  
	      "AaEeIiOoUu"    // grave
	    + "AaEeIiOoUuYy"  // acute
	    + "AaEeIiOoUuYy"  // circumflex
	    + "AaEeIiOoUuYyNn"  // tilde
	    + "AaEeIiOoUuYy"  // umlaut
	    + "Aa"            // ring
	    + "Cc"            // cedilla
	    ;

	private static final String UNICODE =
	      "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"             // grave
	    + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" // acute
	    + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" // circumflex
	    + "\u00C3\u00E3EeIi��UuYy��" // tilde
	    + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" // umlaut
	    + "\u00C5\u00E5"                                                             // ring
	    + "\u00C7\u00E7"                                                             // cedilla
	    ;

	private static Map<String, String> htmlAccents;
	
	static {
		htmlAccents = new HashMap<String, String>();
		htmlAccents.put("&aacute;", "�");	htmlAccents.put("&Aacute;", "�");
		htmlAccents.put("&agrave;", "�");	htmlAccents.put("&Agrave;", "�");
		htmlAccents.put("&acirc;", "�");	htmlAccents.put("&Acirc;", "�");
		htmlAccents.put("&atilde;", "�");	htmlAccents.put("&Atilde;", "�");
		htmlAccents.put("&auml;", "�");		htmlAccents.put("&Auml;", "�");
		htmlAccents.put("&aring;", "�");	htmlAccents.put("&Aring;", "�");
		htmlAccents.put("&aelig;","�");		htmlAccents.put("&Aelig;","�");
		
		htmlAccents.put("&eacute;", "�");	htmlAccents.put("&Eacute;", "�");
		htmlAccents.put("&egrave;", "�");	htmlAccents.put("&Egrave;", "�");
		htmlAccents.put("&ecirc;", "�");	htmlAccents.put("&Ecirc;", "�");
		htmlAccents.put("&euml;", "�");		htmlAccents.put("&Euml;", "�");
		
		htmlAccents.put("&iacute;", "�");	htmlAccents.put("&Iacute;", "�");
		htmlAccents.put("&igrave;", "�");	htmlAccents.put("&Igrave;", "�");
		htmlAccents.put("&icirc;", "�");	htmlAccents.put("&Icirc;", "�");
		htmlAccents.put("&iuml;", "�");		htmlAccents.put("&Iuml;", "�");
		
		htmlAccents.put("&oacute;", "�");	htmlAccents.put("&Oacute;", "�");
		htmlAccents.put("&ograve;", "�");	htmlAccents.put("&Ograve;", "�");
		htmlAccents.put("&ocirc;", "�");	htmlAccents.put("&Ocirc;", "�");
		htmlAccents.put("&otilde;", "�");	htmlAccents.put("&Otilde;", "�");
		htmlAccents.put("&ouml;", "�");		htmlAccents.put("&Ouml;", "�");
		htmlAccents.put("&oslash;","�"); 	htmlAccents.put("&Oslash;","�");
		
		htmlAccents.put("&uacute;", "�");	htmlAccents.put("&Uacute;", "�");
		htmlAccents.put("&ugrave;", "�");	htmlAccents.put("&Ugrave;", "�");
		htmlAccents.put("&ucirc;", "�");	htmlAccents.put("&Ucirc;", "�");
		htmlAccents.put("&uuml;", "�");		htmlAccents.put("&Uuml;", "�");
				
		htmlAccents.put("&ccedil;","�"); 	htmlAccents.put("&Ccedil;","�");
	    htmlAccents.put("&szlig;","�"); 	htmlAccents.put("&nbsp;"," ");
	}

	/** Transforma HTML Entities (http://en.wikipedia.org/wiki/HTML_entities) em caracteres Unicode. */
	public static final String unescapeHTML(String source) {
		return StringEscapeUtils.unescapeHtml(source);
	}

	/** Troca todos os ' por '' */
	public static String escapeBackSlash(String oldString) {
		return oldString.replaceAll("'", "''");
	}

	/** Troca todos os ' por \' e transforma a string para uppercase */
	public static String escapeBackSlashUpper(String oldString) {
		return escapeBackSlash(oldString.toUpperCase());
	}

	/** Converte a string para ascii */
	public static String toAscii(String oldString) {
		if ( oldString == null ) {
			return oldString;
		}
		StringBuffer sb = new StringBuffer();
		int n = oldString.length();
		for (int i = 0; i < n; i++) {
			char c = oldString.charAt(i);
			int pos = UNICODE.indexOf(c);
			if (pos > -1) {
				sb.append(PLAIN_ASCII.charAt(pos));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Converte as duas strings para ASCII e compara se o conte�do � igual ignorando case sensitive. Retorna TRUE se as string forem iguais.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compareInAscii(String a, String b) {
		
		if (a == null || b == null)
			return false;
		
		return toAscii(a.trim()).equalsIgnoreCase(toAscii(b.trim()));
		
	}
	
	/**
	 * Remove espa�os em brancos repetidos no texto.
	 * 
	 * @param texto
	 * @return
	 */
	public static String removeEspacosRepetidos(String texto) {
		
		if (texto == null)
			return "";
		
		return texto.replaceAll("( )+", " ").trim();
	}
	
	/** 
	 * Converte a string para ascii, trocando c�digos de caracteres 
	 * acentuados do html (como &amp;aacute;) pela letra correspondente;
	 */
	public static String toAsciiHtml(String oldString) {
		for (Entry<String, String> entry : htmlAccents.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			oldString = oldString.replaceAll(key, value);
		}
		
		return toAscii(oldString);
	}

	/** Transforma a string para ascii e converte para uppercase */
	public static String toAsciiAndUpperCase(String oldString) {
		String toAscii = toAscii(oldString);
		if(toAscii == null) {
			return toAscii;
		} else {
			return toAscii.toUpperCase();
		}
	}

	/**
	 * Retorna <tt>true</tt> se a String for diferente de null e n�o for vazia.
	 */
	public final static boolean notEmpty(String s) {
		return !isEmpty(s);
	}

	/**
	 * Retorna <tt>true</tt> se a String for igual a null ou vazia
	 */
	public final static boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}

	/**
	 * Retorna o total de caracteres diferentes de uma String.
	 */
	public final static int getDensidade(String str) {

		str = str.toLowerCase();

		TreeSet<Character> caracteres = new TreeSet<Character>();
		for ( int a = 0; a < str.length(); a++) {
			caracteres.add(str.charAt(a));
		}

		return caracteres.size();

	}

	/**
	 * Extrai um long da string, pegando todos os n�meros e desconsiderando os caracteres da mesma
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

	/**
	 * Extrai um integer da string, pegando todos os n�meros e desconsiderando os caracteres da mesma
	 */
	public final static Integer extractInteger(String s) {
		if (s == null || s.trim().equals("")) {
			return null;
		}
		if (org.apache.commons.lang.StringUtils.isNumeric(s)) {
			return Integer.parseInt(s);
		}
		StringBuffer integer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (org.apache.commons.lang.StringUtils.isNumeric(s.charAt(i)+"")) {
				integer.append(s.charAt(i));
			}
		}
		if (integer.length() > 0) {
			return new Integer(integer.toString().trim());
		} else {
			return null;
		}
	}
	
	public final static String extractAlgarismos(String s) {
		if (s == null || s.trim().equals("")) {
			return null;
		}
		if (org.apache.commons.lang.StringUtils.isNumeric(s)) {
			return s;
		}
		StringBuffer integer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (org.apache.commons.lang.StringUtils.isNumeric(s.charAt(i)+"")) {
				integer.append(s.charAt(i));
			}
		}
		return integer.toString();
		
	}

	/**
	 * Fun��o para gerar uma sequ�ncia de caracteres aleat�rios de acordo com o tamanho informado
	 */
	public final static String generatePassword(int tamanho) {
		StringBuilder password = new StringBuilder( tamanho > 0 ? tamanho : 0 );

		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int total = chars.length();

		for(int i = 0; i < tamanho; i++) {
		    password.append( chars.charAt( ((Double) Math.floor((Math.random() * total))).intValue()) );
		}
		return password.toString();
	}

	/**
	 * Transforma a String para o encoding Latin9
	 */
	public final static String toLatin9(String s) {
		try {
			if ( s == null ) {
				return null;
			}
			return  new String(s.getBytes(), "ISO8859_15_FDIS");
		} catch (UnsupportedEncodingException e) {
			System.err.println(" ------  ERRO NA CONVERS�O PARA LATIN9 ------");
			return toAscii(s);
		}
	}

	/**
	 * Remove da string os caracteres que s�o inv�lidos no encoding UTF-8;
	 */
	public static String removeInvalidUtf8(String s) {
		if (s != null) {
			String utf8 = s;
			while(utf8.indexOf(0x00) != -1) {
				utf8 = utf8.replace((char) 0x00, ' ');
			}
			return utf8;
		}
		return s;
	}

	/**
	 * Converte caracteres de escape e caracteres especiais do html
	 * em HTML entities.
	 */
	public static String converteJavaToHtml(String texto) {

		StringBuilder textoFmt = new StringBuilder();
		
		if (texto != null) {
			for (int i = 0; i < texto.length(); i++) {
				switch (texto.charAt(i)) {
				case '\n':
					textoFmt.append("<br />");
					break;
				case '\r':
					break;
				case '\"':
					textoFmt.append("&quot;");
					break;
				case '<':
					textoFmt.append("&lt;");
					break;
				case '>':
					textoFmt.append("&gt;");
					break;
				default:
					textoFmt.append(texto.charAt(i));
					break;
				}
			}
		}
		
		return textoFmt.toString();
	}

	/**
	 * Separa uma String com tamanho grande em v�rias linhas.
	 */
	public static String divideEmLinhas(String oldString, int maximo) {
		StringBuilder sb = new StringBuilder(oldString);
		StringBuilder newString = new StringBuilder();

		while (sb.length() > 0) {
			int indice = sb.length();

			if (maximo < sb.length())  {
				indice = buscaUltimoEspacoEmBranco(sb.substring(0, maximo));
				if (indice == -1) {
					indice = maximo;
				}
			}

			newString.append(sb.substring(0, indice) + System.getProperty("line.separator"));
			sb.replace(0, indice, "");
		}

		return newString.toString();
	}

	/**
	 * Busca a posi��o do �ltimo espa�o em branco de uma String
	 */
	public static int buscaUltimoEspacoEmBranco(String str) {
		for (int i = str.length() - 1; i >= 0; i--) {
			if (str.charAt(i) == ' ') {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Retorna o n�mero de palavras de uma String.
	 */
	public static int wordCount(String s) {
		if (s != null) {
			return Pattern.compile("[\\W]+").split(s.trim()).length;
		}
		return 0;
	}

	/**
	 * Limita a quantidade de caracteres de um texto sem cortar a palavra:
	 *
	 * @author M�rio Rizzi
	 * @param 	str - texto a ser limitado
	 * 			tam - quantidade de caracteres
	 */
	public static String limitTxt(String str,Integer tam){
		String palavras[] = str.split(" ");
		str = "";
		Integer comp=0;

		for(int i=0;i<palavras.length;i++){
		   comp += palavras[i].length();
		   if(i< palavras.length){
			  comp ++;
		      if(comp<=tam) {
				str +=  palavras[i]+" ";
		      } else{
		    	if(isEmpty(str)) str=palavras[i].substring(0,tam);
		    	str +="...";
		      	break;
		      }
		   }
		}

		return str.trim();
	}

	/** 
	 * Transforma uma String em camel case em formato leg�vel por humanos, separando palavras
	 * por espa�os.
	 */
	public static String humanFormat(String oldString) {
		if (oldString == null) {
			return null;
		}
		if (oldString.length() == 0) {
			return "";
		}

		char[] toCharArray = oldString.substring(1).toCharArray();
		StringBuilder builder = new StringBuilder();
		builder.append(String.valueOf(oldString.charAt(0)).toUpperCase());
		for (char c : toCharArray) {
			if(Character.isUpperCase(c)){
				builder.append(" ");
			}
			builder.append(c);
		}
		return builder.toString();
	}

	/**
	 * Remove o caracter carriage return (\\r) da string.
	 */
	public static String removeCarriageReturn(String s) {
		if (s == null) {
			return null;
		}
		return s.trim().replaceAll("[\r\n]+", "\n");
	}

	/** Retira tags html de uma String */
	public static String stripHtmlTags(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("\\<.*?>","");
	}

	/** Retorna true se existirem letras na String */
	public static boolean hasLetter(String str) {
		return hasLetter(str, 1);
	}

	/** Retorna true se existirem n�meros na String */
	public static boolean hasNumber(String str) {
		return hasNumber(str, 1);
	}

	/** Retorna true se existirem letras em uma quantidade m�nima na String */
	public static boolean hasLetter(String str, int minQty) {
		int qtd = 0;
		for (int i = 0; i < str.length(); i++) {
			qtd += (Character.isLetter(str.charAt(i)) ? 1 : 0);
		}
		return qtd >= minQty;
	}

	/** Retorna true se existirem n�meros em uma quantidade m�nima na String */
	public static boolean hasNumber(String str, int minQty) {
		int qtd = 0;
		for (int i = 0; i < str.length(); i++) {
			qtd += (Character.isDigit(str.charAt(i)) ? 1 : 0);
		}
		return qtd >= minQty;
	}


	/**
	 * Cabe�alho para e-mail HTML.
	 */
	public static String htmlMailHead() {
		return "<html>" +
				"<head>" +
				"<style> body { font-family: Verdana; font-size: 10pt } table { font-size: 10pt } </style> " +
				"</head> " +
				"<body>";
	}

	/**
	 * Rodap� para e-mail HTML.
	 */
	public static String htmlMailTail() {
		return "</font> <br> <i> Esta mensagem � autom�tica e n�o deve ser respondida </i>" +
				"</body></html>";
	}


	/** Se o conte�do da string for qualquer uma dessas strings ser� considerado como lero lero imediatamente. */
	private static List<String> leroLeroPatterns = new ArrayList<String>();
	static{
		leroLeroPatterns.add("ser� informado oportunamente");
		leroLeroPatterns.add("a definir");
	}

	private static List<Character> vogais = new ArrayList<Character>();
	static{
		vogais.add('a');
		vogais.add('�');
		vogais.add('e');
		vogais.add('�');
		vogais.add('i');
		vogais.add('o');
		vogais.add('�');
		vogais.add('u');
		vogais.add('y');

		vogais.add('A');
		vogais.add('�');
		vogais.add('E');
		vogais.add('�');
		vogais.add('I');
		vogais.add('O');
		vogais.add('�');
		vogais.add('U');
		vogais.add('Y');

		vogais.add('�');
		vogais.add('�');
	}

	/**
	 * Este m�todo identifica se a string passada cont�m Lero-Lero!
	 * 
	 * @param text
	 * @param tamanhoMinimo tamanho m�nimo da string
	 * @param minimoCaracteres  m�nimo de caracteres diferentes que a string deve conter
	 * @param minimoPalavras  m�nimo de palavras diferentes que a string deve conter
	 * @return retorna uma String descrevendo a inconsist�ncia encontrada e,  caso n�o contenha lero-lero retorna nulo. 
	 * CASO os par�metros n�o sejam informados estes s�o os valores DEFAULT:
	 * 		tamanhoMinimo = 50;
	 * 		minimoCaracteres = 10;
	 * 		minimoPalavras = 10;
	 */
	public static String containsLeroLero(String text, Integer tamanhoMinimo, Integer minimoCaracteres, Integer minimoPalavras){

		text = text.toLowerCase();

		/** VALORES DEFAULT */
		if( tamanhoMinimo == null ) {
			tamanhoMinimo = 50;
		}
		if( minimoCaracteres == null ) {
			minimoCaracteres = 10;
		}
		if( minimoPalavras == null ) {
			minimoPalavras = 10;
		}

		for( String s : leroLeroPatterns ){
			if( toAscii( s.trim() ).equalsIgnoreCase( toAscii( text.trim() ) ) ) {
				return "O conte�do n�o pode ser '" + text.trim() + "'";
			}
		}

		//wordCount
		if( wordCount(text) < minimoPalavras){
			return "O conte�do deve possuir no m�nimo " + minimoPalavras + " palavras.";
		}
		
		HashSet<Character> caracteres = new HashSet<Character>();
		for( char c : text.toCharArray() ){
			caracteres.add(c);
		}

		/**
		 * testando tamanho minimo da string
		 */
		if( text.length() < tamanhoMinimo ){
			return "O conte�do deve possuir no m�nimo " + tamanhoMinimo + " caracteres.";
		}

		
		/** tem q ter pelo menos o :totalCaracteres informado*/
		if( caracteres.size() < minimoCaracteres ){
			return "O conte�do deve possuir no m�nimo " + minimoCaracteres + " caracteres diferentes.";
		}

		/**
		 * verificando se contem 4 vogais ou 4 consoantes consecutivos..
		 */
		int contVogais = 0;
		int contConsoantes = 0;
		for( char c : text.toCharArray() ){
			if( !Character.isLetter(c) ){
				contConsoantes = 0;
				contVogais = 0;
				continue;
			}

			if( vogais.contains( toAscii( Character.toString(c) ).charAt(0) ) ){
				contVogais++;
				contConsoantes = 0;
			}
			else{
				contConsoantes++;
				contVogais = 0;
			}

			if( contVogais > 5 ){
				//System.out.println( "contVogais:" + contVogais + "\n contConsoantes" + contConsoantes );
				//return true;
				return "O conte�do n�o pode ter 5 vogais consecutivas.";
			}
			
			if( contConsoantes > 5 )
				return "O conte�do n�o pode ter 5 consoantes consecutivas.";
		}

		return null;
	}

	/**
	 * Transforma uma lista de objetos em uma String com as descri��es
	 * dos objetos separadas por v�rgulas e o �ltimo objeto separado por "e".
	 * Exemplo: [1, 2, 3, 4] -> 1, 2, 3 e 4
	 *
	 * @param itens
	 * @return
	 */
	public static String transformaEmLista(List<?> itens) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < itens.size(); i++) {
			Object item = itens.get(i);

			if ((i > 0) && (i < itens.size() - 1)) {
				sb.append(", ");
			}
			if ((i > 0) && (i == itens.size() - 1)) {
				sb.append(" e ");
			}

			sb.append(item.toString());
		}

		return sb.toString();
	}
	
	/**
	 * Remove os s�mbolos de uma string com CPF ou CNPJ.
	 */
	public static String removeSimbolosCPFCNPJ(String cpfcnpj) {
		if (cpfcnpj == null) {
			return null;
		}
		return cpfcnpj.trim().replaceAll("[./-]*","");
	}

	/**
	 * Remove os caracteres carriage return (\\r) e line break (\\n)
	 * de uma String.
	 */
	public static String removeLineBreak(String s) {
		if (s == null) {
			return null;
		}
		return s.trim().replaceAll("[\r\n|\n|\r|\n\r]+", " ");
	}

	/**
	 * N�o deixa aparecer ponto e v�rgula na String para uso e CSV;
	 */
	public static String removePV(String src) {
		if ( src != null ) {
			return src.replace(";", " ");
		} else {
			return src;
		}
	}

	/**
	 * Remove as quebras de linhas e pontos e v�rgulas
	 */
	public static String removeLineBreakAndPV(String s) {
		if (s == null) {
			return null;
		}
		return removePV(s.trim().replaceAll("[\r\n|\n|\r|\n\r]+", " "));
	}

	/**
	 * M�todo respons�vel pela remo��o dos coment�rios introduzidos no TyneMCE.
	 */
	public static String removerComentarios(String texto){
		if(texto != null) {
			//Enquanto haver in�cios de coment�rios
			texto = removerComentario(texto, "<!--" , "-->" , 3);
			texto = removerComentario(texto, "&lt;!--" , "--&gt;" , 6);

			//remo��o dos coment�rios
			while(texto.contains("!--")){
				int inicioComentarioWord = texto.indexOf("!--");
				int fimComentarioWord = texto.indexOf(">", inicioComentarioWord);
				if(fimComentarioWord != -1) {
					if(inicioComentarioWord != -1 && inicioComentarioWord == 0)
						texto = texto.substring(fimComentarioWord+1);
					else if(fimComentarioWord <= texto.length() && inicioComentarioWord != 0)
						texto = texto.substring(0, inicioComentarioWord) + texto.substring(fimComentarioWord + 1);
				} else
					break;
			}
		}
		return texto;
	}

	/**
	 * Remove coment�rios de um texto de acordo com o padr�o de abertura e fechamento do coment�rio.
	 */
	private static String removerComentario(String texto, String tipoComAbertura, String tipoComFechamento, int index) {
		while(texto.contains(tipoComAbertura)){
			//Pegar a primeira ocorr�ncia do coment�rio
			int aberturaComentario = texto.indexOf(tipoComAbertura);

			//Pegar a segunda ocorr�ncia do coment�rio
			int segundaAbertura = -1;
			if(aberturaComentario != -1){
				segundaAbertura = texto.indexOf(tipoComAbertura, aberturaComentario + 1);
			}
			//Caso S� exista um coment�rio
			if(segundaAbertura == -1){
				int finalComentario = texto.indexOf(tipoComFechamento);
				//Se existir um final para o coment�rio
				if(finalComentario != -1 && aberturaComentario > 1){
					//N�o tem texto depois
					if(finalComentario+ index >= texto.length())
						texto = texto.substring(0, aberturaComentario);
					else
						texto = texto.substring(0, aberturaComentario) + texto.substring(finalComentario + index);
				}else if (finalComentario != -1 && aberturaComentario <= 1){
					texto = texto.substring(finalComentario + index);
				}else//Se n�o existir
					break;
			}else{//Se Existirem mais de uma abertura
				int finalComentario = texto.indexOf(tipoComFechamento);
				//Se existir um final para o coment�rio
				if(finalComentario != -1 && finalComentario > aberturaComentario){
					if(aberturaComentario > 1){
						texto = texto.substring(0, aberturaComentario) + texto.substring(finalComentario+ index);
					}else{
						texto = texto.substring(finalComentario + index);
					}
				}else//Se n�o existir
					break;
			}
		}
		return texto;
	}

	/**
	 * Retorna os n�meros do come�o de uma String passada como argumento, caso haja.
	 */
	public static Long getNumerosIniciais(String conteudo){
		Long numRetorno = 0l;
		
		if (conteudo != null){
			String retorno = conteudo.replaceAll("\\D+\\w*", "");
			
			if (!retorno.equals("")) {
				try {
					numRetorno = Long.parseLong(retorno);
				} catch (NumberFormatException e) {
					numRetorno = 0l;
				}
			}
		}
		return (numRetorno.equals(new Long(0))?null:numRetorno);
	}

	public static String[] dividirPorTamanho(String str, int tamanho) {
		List<String> partes = new ArrayList<String>();
		
		while(!isEmpty(str)) {
			partes.add(str.substring(0, tamanho));
			str = str.substring(tamanho);
		}
		
		return partes.toArray(new String[partes.size()]);
	}

	public static String primeriaMaiuscula (String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	public static String toCamelCase(String s) {
		StringBuilder sb = new StringBuilder();
		boolean primeira = true;
		for(String parte : s.split(" |_")) {
			if (primeira) {
				sb.append(parte.toLowerCase());
				primeira = false;
			} else {
				sb.append( Character.toUpperCase(parte.charAt(0)));
				sb.append(parte.substring(1).toLowerCase());
			}
		}
		return sb.toString();
		
	}

	/**
	 * Verifica se todos os caracteres de uma string s�o iguais. retorna falso se a string for vazia.
	 * @param s
	 * @return
	 */
	public static boolean todosCaracteresIguais (String s){
		if (!isEmpty(s)){
			char primeiroCaractere = s.charAt(0);
			for (int i = 1; i < s.length(); i++) {
				char caractereAtual = s.charAt(i);
				if (caractereAtual != primeiroCaractere){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Identifica se uma string � um n�mero de ponto flutuante v�lido.
	 * @param s
	 * @return
	 */
	public static boolean isDouble(String s) {
		try{
		   Double.valueOf(s);
		   return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
