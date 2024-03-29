package br.arq.utils;
import java.util.Collection;
import java.util.Map;

import br.arq.dao.Persistent;


public class ValidatorUtil {

	public static final boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}

	public static final boolean isAllEmpty(Object... objs) {	
		if (objs == null) { return true; }
		for (Object o : objs) {
			if (isEmpty(o) == false) { return false; }
		}
		return true;
	}
	
	public static final boolean isAllNotEmpty(Object... objs) {	
		if (objs == null) { return false; }
		for (Object o : objs) {
			if (isEmpty(o) == true) { return false; }
		}
		return true;
	}	

	public static final boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String)
			return isEmpty( (String) o);
		if (o instanceof Number) {
			Number i = (Number) o;
			return (i.intValue() == 0);
		}
		if (o instanceof Persistent)
			return ((Persistent) o).getId() == 0;
		if (o instanceof Object[])
			return ((Object[]) o).length == 0;
		if (o instanceof int[])
			return ((int[]) o).length == 0;
		if (o instanceof Collection<?>)
			return ((Collection<?>) o).size() == 0;
		if (o instanceof Map<?, ?>)
			return ((Map<?, ?>) o).size() == 0;
		return false;
	}
	
	public static final boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
}