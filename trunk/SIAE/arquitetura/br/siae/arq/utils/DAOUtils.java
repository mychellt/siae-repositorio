package br.siae.arq.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.WordUtils;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import br.siae.arq.dominio.Persistable;

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
	public static boolean isFKConstraintError(Exception e) {

		if (e.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
			DataAccessException translatedException = SessionFactoryUtils.convertHibernateAccessException(cve);

			if (translatedException instanceof DataIntegrityViolationException)
				return true;

			if (cve.getSQLException() != null) {
				String msg = cve.getSQLException().getMessage();
				if (msg.contains("violates foreign key constraint"))
					return true;
				else {
					if (cve.getSQLException().getNextException() != null) {
						msg = cve.getSQLException().getNextException().toString();
						if (msg.contains("violates foreign key constraint"))
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public static String createUpdateQuery( SessionFactory sf, Class<?> classe, Integer id, String campo, Object valor) throws Exception {
		AbstractEntityPersister persister = (AbstractEntityPersister) sf.getClassMetadata(classe);
		return "UPDATE " + getNomeTabela(persister, campo) + " SET " + getNomeColuna(persister, campo) + " = " + getIdValue(classe, campo, valor)
				+ " WHERE " + getPrimaryKey(sf, persister, campo) + " = " + id;
	}
	
	public static String getNomeTabela(AbstractEntityPersister persister, String campo) {
		if (persister.getPropertyTableName(campo) == null) {
			return persister.getTableName();
		} else {
			return persister.getPropertyTableName(campo);
		}
	}
	
	public static String getNomeTabela(AbstractEntityPersister persister) {
		return persister.getTableName();
	}
	
	public static String getNomeColuna(AbstractEntityPersister persister, String campo) {
		return persister.getPropertyColumnNames(campo)[0];
	}
	
	public static String getPrimaryKey(AbstractEntityPersister persister) {
		return persister.getIdentifierColumnNames()[0];
	}
	
	public static String getPrimaryKey(SessionFactory sf, AbstractEntityPersister persister, String campo) {
		String persisterTableName = persister.getTableName();
		String propertyTableName = getNomeTabela(persister, campo);
		
		if (!persisterTableName.equals(propertyTableName) && persister.getMappedSuperclass() != null) {
			AbstractEntityPersister superclassPersister = (AbstractEntityPersister) sf.getClassMetadata(persister.getMappedSuperclass());
			return superclassPersister.getIdentifierColumnNames()[0];
		} else {
			return getPrimaryKey(persister);
		}		
	}
	private static String getIdValue(Class<?> classe, String campo, Object valor) {
		if (valor == null)
			return null;
		
		@SuppressWarnings("unchecked")
		List<Class<?>> interfaces = ClassUtils.getAllInterfaces(valor.getClass());
		if (interfaces != null && (interfaces.contains(Persistable.class) ) ) {
			try {
				return PropertyUtils.getProperty(valor, "id").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// se nao for classe persistente
		Field f = ReflectionUtils.getExpressionField(classe, campo);
		if (f.getType().equals(String.class) || f.getType().equals(Character.class) || f.getType().equals(char.class))
			return "'" + StringUtils.escapeBackSlash(valor.toString()) + "'";
		else if (f.getType().equals(Date.class)) {
			String formato = "''yyyy-MM-dd HH:mm:ss''";
			if (getTipoColuna(classe, f).equalsIgnoreCase("date"))
				formato = "''yyyy-MM-dd''";
			DateFormat df = new SimpleDateFormat(formato);
			return df.format(valor);
		} else
			return valor.toString();
	}
	
	private static String getTipoColuna(Class<?> classe, Field att) {
		if (att.getType().equals(String.class))
			return "varchar(50)";
		else if (att.getType().equals(Integer.class) || att.getType().equals(int.class))
			return "integer";
		else if (att.getType().equals(Long.class) || att.getType().equals(long.class))
			return "bigint";
		else if (att.getType().equals(Short.class) || att.getType().equals(short.class))
			return "smallint";
		else if (att.getType().equals(Float.class) || att.getType().equals(float.class))
			return "float";
		else if (att.getType().equals(Boolean.class) || att.getType().equals(boolean.class))
			return "boolean";
		// datas
		if (att.getType().equals(Date.class)) {
			Temporal t = att.getAnnotation(Temporal.class);
			if (t == null)
				t = getGetter(classe, att).getAnnotation(Temporal.class);
			if (t == null)
				return "timestamp without time zone";
			else if (t.value().equals(TemporalType.TIME))
				return "time without time zone";
			else if (t.value().equals(TemporalType.DATE))
				return "date";
			else if (t.value().equals(TemporalType.TIMESTAMP))
				return "timestamp without time zone";
		}
		return "integer";
	}
	
	private static Method getGetter(Class<?> classe, Field obj) {
		
		String nomeMetodo = "get" + WordUtils.capitalize(obj.getName());
		try {
			classe.getMethod(nomeMetodo, new Class[0]);
		} catch (Exception e) {
			if (obj.getType().equals(Boolean.class) || obj.getType().equals(boolean.class))
				nomeMetodo = "is" + WordUtils.capitalize(obj.getName());
		}
		try {
			return classe.getMethod(nomeMetodo, new Class[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createUpdateQuery(SessionFactory sf, Class<?> classe, Integer id, String[] campos, Object[] valores){
		
		AbstractEntityPersister persister = (AbstractEntityPersister) sf.getClassMetadata(classe);
		StringBuilder sb = new StringBuilder("UPDATE " + getNomeTabela(persister) + " SET ");

		for(int i=0; i<campos.length; i++ ){
			sb.append(getNomeColuna(persister, campos[i]) + " = " + getIdValue(classe, campos[i], valores[i]));
			if(i < campos.length - 1){
				sb.append(", ");
			}
		}

		sb.append(" WHERE " + getPrimaryKey(persister) + " = " + id);
		return sb.toString();
	}
	
}
