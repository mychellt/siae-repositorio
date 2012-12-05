package br.siae.arq.utils;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtils {
	public static <T> ArrayList<T> toArrayList(Collection<T> col) {
		ArrayList<T> list = new ArrayList<T>();
		if (col != null) {
			list.addAll(col);
		}
		return list;
	}
}
