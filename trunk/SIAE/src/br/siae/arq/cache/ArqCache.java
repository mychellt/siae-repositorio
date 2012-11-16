package br.siae.arq.cache;

import java.util.Collection;

import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Logradouro;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.dominio.Pais;
import br.siae.arq.dominio.Sexo;

public class ArqCache {
	public static Collection<Sexo> sexos;
	public static Collection<Estado> estados;
	public static Collection<Municipio> municipios;
	public static Collection<Pais> paises;
	public static Collection<Logradouro> logradouros;
	
	public static Collection<Sexo> getSexos() {
		return sexos;
	}

	public static void setSexos(Collection<Sexo> sexos) {
		ArqCache.sexos = sexos;
	}

	public static Collection<Estado> getEstados() {
		return estados;
	}

	public static void setEstados(Collection<Estado> estados) {
		ArqCache.estados = estados;
	}

	public static Collection<Municipio> getMunicipios() {
		return municipios;
	}

	public static void setMunicipios(Collection<Municipio> municipios) {
		ArqCache.municipios = municipios;
	}

	public static Collection<Pais> getPaises() {
		return paises;
	}

	public static void setPaises(Collection<Pais> paises) {
		ArqCache.paises = paises;
	}

	public static Collection<Logradouro> getLogradouros() {
		return logradouros;
	}

	public static void setLogradouros(Collection<Logradouro> logradouros) {
		ArqCache.logradouros = logradouros;
	}

}
