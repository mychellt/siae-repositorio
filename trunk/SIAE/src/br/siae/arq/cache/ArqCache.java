package br.siae.arq.cache;

import java.util.Collection;

import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Logradouro;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.dominio.Pais;
import br.siae.arq.dominio.Sexo;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.NivelFormacao;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.comum.Instituicao;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Categoria;
import br.siae.dominio.rh.NivelFuncional;

public class ArqCache {
	public static Collection<Sexo> sexos;
	public static Collection<Estado> estados;
	public static Collection<Municipio> municipios;
	public static Collection<Pais> paises;
	public static Collection<Logradouro> logradouros;
	public static Collection<Turno> turnos;
	public static Collection<Nivel> niveis;
	public static Collection<Serie> series;
	public static Collection<NivelFormacao> niveisFormacao;
	public static Collection<Instituicao> instituicoes;
	public static Collection<NivelFuncional> niveisFuncionais;
	public static Collection<Categoria> categorias;
	
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

	public static Collection<Turno> getTurnos() {
		return turnos;
	}

	public static void setTurnos(Collection<Turno> turnos) {
		ArqCache.turnos = turnos;
	}

	public static Collection<Nivel> getNiveis() {
		return niveis;
	}

	public static void setNiveis(Collection<Nivel> niveis) {
		ArqCache.niveis = niveis;
	}

	public static Collection<Serie> getSeries() {
		return series;
	}

	public static void setSeries(Collection<Serie> series) {
		ArqCache.series = series;
	}

	public static Collection<NivelFormacao> getNiveisFormacao() {
		return niveisFormacao;
	}

	public static void setNiveisFormacao(Collection<NivelFormacao> niveisFormacao) {
		ArqCache.niveisFormacao = niveisFormacao;
	}

	public static Collection<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public static void setInstituicoes(Collection<Instituicao> instituicoes) {
		ArqCache.instituicoes = instituicoes;
	}

	public static Collection<NivelFuncional> getNiveisFuncionais() {
		return niveisFuncionais;
	}

	public static void setNiveisFuncionais(
			Collection<NivelFuncional> niveisFuncionais) {
		ArqCache.niveisFuncionais = niveisFuncionais;
	}

	public static Collection<Categoria> getCategorias() {
		return categorias;
	}

	public static void setCategorias(Collection<Categoria> categorias) {
		ArqCache.categorias = categorias;
	}

}
