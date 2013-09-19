package br.arq.utils;

import java.util.ArrayList;
import java.util.Collection;

import br.arq.dao.Persistent;

public class CollectionUtils {
	public static <T> ArrayList<T> toArrayList(Collection<T> col) {
		ArrayList<T> list = new ArrayList<T>();
		if (col != null) {
			list.addAll(col);
		}
		return list;
	}
	
	public static <T> ArrayList<Long> extractIds(Collection<Persistent> col) {
		ArrayList<Long> lista = new ArrayList<Long>();
		if(col!=null){
			for(Persistent obj: col){
				lista.add(new Long(obj.getId()));
			}
		}
		return lista;
	}
}
