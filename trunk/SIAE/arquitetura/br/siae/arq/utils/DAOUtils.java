package br.siae.arq.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DAOUtils {
	public static String trataAspasSimples(String original) {
		if (original != null) return original.replaceAll("'", "''");
		else return original;
	}
	public static String toMD5(String senha, String salts) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			
			if (!ValidatorUtil.isEmpty(salts)) {
				String salttmp[] = salts.split(",");
			    byte salt[] = new byte[salttmp.length];

			    for (int i = 0; i < salt.length; i++) {
			      salt[i] = Byte.parseByte(salttmp[i]);
			    }
			    
			    md.update(salt);
			}

			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			String s = hash.toString(16);

			while (s.length() < 32)
				s = "0" + s;

			return s;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
