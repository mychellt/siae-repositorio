package br.arq.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.beans.factory.annotation.Autowired;



public class ReflectionUtils extends org.springframework.util.ReflectionUtils {
	
	public static <T> T instantiateClass(Class<T> classe) {
		try {
			return classe.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível criar uma nova instância da classe '" + classe.getName() + "'.");
		}
	}
	
	public static void copyProperties(final Object orig, final Object dest) {
		org.springframework.util.ReflectionUtils.doWithFields(dest.getClass(), new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				setFieldValue(dest, field, getFieldValue(orig, field));
			}
		}, new FieldFilter() {
			public boolean matches(Field field) {
				return !(Modifier.isStatic(field.getModifiers()) ||
						Modifier.isFinal(field.getModifiers())) && !hasAnnotation(field, Autowired.class);
			}
		});
	}
	
	public static Field getExpressionField(Class<?> classe, String expression) {
		String[] partes = expression.split("\\.");
		Class<?> tipoParte = classe;
		Field atributoParte = null;
		
		for (String parte : partes) {
			atributoParte = getField(tipoParte, parte);
			tipoParte = atributoParte.getType();
		}
		
		return atributoParte;
	}
	
	public static Field getField(Class<?> classe, String fieldName) {
		while (classe != null) {
			Field[] fields = classe.getDeclaredFields();

			if (fields != null && fields.length > 0) {
				for (Field f : fields) {
					if (fieldName.equals(f.getName()))
						return f;
				}
			}
			classe = classe.getSuperclass();
		}
		return null;
	}
	public static boolean hasAnnotation(AnnotatedElement element, Class<?> annotation) {
		return getAnnotation(element, annotation) != null;
	}
	public static Annotation getAnnotation(AnnotatedElement element, Class<?> annotation) {
		Annotation[] annotations = element.getDeclaredAnnotations();
		if (annotations != null && annotations.length > 0) {
			for (Annotation a : annotations) {
				if (annotation.equals(a.annotationType())) {
					return a;
				}
			}
		}
		return null;
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
	public static void setFieldValue(Object object, Field field, Object value) {

		try {
			boolean changedAccessibility = false;
			if (!field.isAccessible()) {
				field.setAccessible(true);
				changedAccessibility = true;
			}
			field.set(object, value);
			if (changedAccessibility) {
				field.setAccessible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível alterar o valor do atributo " + field.getName() + ": " + e.getMessage());
		}
	}

}
