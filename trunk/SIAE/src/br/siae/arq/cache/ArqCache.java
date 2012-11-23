package br.siae.arq.cache;

import java.util.List;

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
import br.siae.dominio.rh.Professor;

public class ArqCache {
	public static List<Sexo> sexos;
	public static List<Estado> estados;
	public static List<Municipio> municipios;
	public static List<Pais> paises;
	public static List<Logradouro> logradouros;
	public static List<Turno> turnos;
	public static List<Nivel> niveis;
	public static List<Serie> series;
	public static List<NivelFormacao> niveisFormacao;
	public static List<Instituicao> instituicoes;
	public static List<NivelFuncional> niveisFuncionais;
	public static List<Categoria> categorias;
	public static List<Professor> professores;
	
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

	public static List<Turno> getTurnos() {
		return turnos;
	}

	public static void setTurnos(List<Turno> turnos) {
		ArqCache.turnos = turnos;
	}

	public static List<Nivel> getNiveis() {
		return niveis;
	}

	public static void setNiveis(List<Nivel> niveis) {
		ArqCache.niveis = niveis;
	}

	public static List<Serie> getSeries() {
		return series;
	}

	public static void setSeries(List<Serie> series) {
		ArqCache.series = series;
	}

	public static List<NivelFormacao> getNiveisFormacao() {
		return niveisFormacao;
	}

	public static void setNiveisFormacao(List<NivelFormacao> niveisFormacao) {
		ArqCache.niveisFormacao = niveisFormacao;
	}

	public static List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public static void setInstituicoes(List<Instituicao> instituicoes) {
		ArqCache.instituicoes = instituicoes;
	}

	public static List<NivelFuncional> getNiveisFuncionais() {
		return niveisFuncionais;
	}

	public static void setNiveisFuncionais(
			List<NivelFuncional> niveisFuncionais) {
		ArqCache.niveisFuncionais = niveisFuncionais;
	}

	public static List<Categoria> getCategorias() {
		return categorias;
	}

	public static void setCategorias(List<Categoria> categorias) {
		ArqCache.categorias = categorias;
	}
	
	public static Categoria getCategoriaById( long id ) {
		if( id == 0 ) return null;
		Categoria c = new Categoria();
		c.setId(id);
		int index = categorias.indexOf(c);
		return categorias.get(index);
	}
	
	public static NivelFuncional getNivelFuncionalById( long id ) {
		if( id == 0 ) return null;
		NivelFuncional  c = new NivelFuncional();
		c.setId(id);
		int index = niveisFuncionais.indexOf(c);
		return niveisFuncionais.get(index);
	}
	
	public static Instituicao getInstituicaoById( long id ) {
		if( id == 0 ) return null;
		Instituicao  c = new Instituicao();
		c.setId(id);
		int index = instituicoes.indexOf(c);
		return instituicoes.get(index);
	}
	
	public static Nivel getNivelById( long id ) {
		if( id == 0 ) return null;
		Nivel  c = new Nivel();
		c.setId(id);
		int index = niveis.indexOf(c);
		return niveis.get(index);
	}
	
	public static Professor getProfessorById( long id ) {
		if( id == 0 ) return null;
		Professor  c = new Professor();
		c.setId(id);
		int index = professores.indexOf(c);
		return professores.get(index);
	}

	public static List<Professor> getProfessores() {
		return professores;
	}

	public static void setProfessores(List<Professor> professores) {
		ArqCache.professores = professores;
	}

}
