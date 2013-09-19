package br.arq.utils;

import java.util.List;

import br.arq.dominio.Estado;
import br.arq.dominio.Logradouro;
import br.arq.dominio.Municipio;
import br.arq.dominio.Pais;
import br.arq.dominio.Sexo;

public class ArqCache {
	public static List<Sexo> sexos;
	public static List<Estado> estados;
	public static List<Municipio> municipios;
	public static List<Pais> paises;
	public static List<Logradouro> logradouros;
	
	public static List<Sexo> getSexos() {
		return sexos;
	}

	public static void setSexos(List<Sexo> sexos) {
		ArqCache.sexos = sexos;
	}

	public static List<Estado> getEstados() {
		return estados;
	}

	public static void setEstados(List<Estado> estados) {
		ArqCache.estados = estados;
	}

	public static List<Municipio> getMunicipios() {
		return municipios;
	}

	public static void setMunicipios(List<Municipio> municipios) {
		ArqCache.municipios = municipios;
	}

	public static List<Pais> getPaises() {
		return paises;
	}

	public static void setPaises(List<Pais> paises) {
		ArqCache.paises = paises;
	}

	public static List<Logradouro> getLogradouros() {
		return logradouros;
	}

	public static void setLogradouros(List<Logradouro> logradouros) {
		ArqCache.logradouros = logradouros;
	}

}
