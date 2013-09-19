
package br.arq.utils;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;


public class RequestUtils {
	public static String getParameter(String param, HttpServletRequest request) {
		return request.getParameter(param);
	}
	public static Integer getParameterInt(String param, Integer defaultValue, HttpServletRequest request) {
		String paramValue = getParameter(param, request);
		if (ValidatorUtil.isEmpty(paramValue))
			return defaultValue;
		else
			paramValue = paramValue.trim();

		try {
			return new Integer(paramValue);
		} catch(NumberFormatException e) {
			return defaultValue;
		}
	}

	public static final int getIntParameter(HttpServletRequest req, String param, int defaultValue) {
		String value = req.getParameter(param);
		int result = defaultValue;

		try {
			result = Integer.parseInt(value);
		} catch(Exception e) {	}
		return result;
	}

	public static final int getIntParameter(HttpServletRequest req, String param) {
		return RequestUtils.getIntParameter(req, param, 0);
	}
	
	public static Object getFieldValue(Object object, Field field) {
		try {
			boolean changedAccessibility = false;
			if (!field.isAccessible()) {
				field.setAccessible(true);
				changedAccessibility = true;
			}

			Object value = field.get(object);

			if (changedAccessibility) {
				field.setAccessible(false);
			}

			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível pegar o valor do atributo " + field.getName() + ": " + e.getMessage());
		}

	}


}
