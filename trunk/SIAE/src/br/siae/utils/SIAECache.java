package br.siae.utils;

import java.util.List;

import br.arq.utils.ArqCache;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.comum.Instituicao;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Categoria;
import br.siae.dominio.rh.NivelFuncional;
import br.siae.dominio.rh.Professor;

public class SIAECache extends ArqCache{
	public static List<Instituicao> instituicoes;
	public static List<Categoria> categorias;
	public static List<Nivel> niveis;
	public static List<NivelFuncional> niveisFuncionais;
	public static List<Professor> professores;
	public static List<Serie> series;
	public static List<Turno> turnos;
	
	public static List<Turno> getTurnos() {
		return turnos;
	}

	public static void setTurnos(List<Turno> turnos) {
		SIAECache.turnos = turnos;
	}

	public static List<Serie> getSeries() {
		return series;
	}

	public static void setSeries(List<Serie> series) {
		SIAECache.series = series;
	}

	public static List<Professor> getProfessores() {
		return professores;
	}

	public static void setProfessores(List<Professor> professores) {
		SIAECache.professores = professores;
	}

	public static List<Nivel> getNiveis() {
		return niveis;
	}

	public static void setNiveis(List<Nivel> niveis) {
		SIAECache.niveis = niveis;
	}

	public static List<NivelFuncional> getNiveisFuncionais() {
		return niveisFuncionais;
	}

	public static void setNiveisFuncionais(List<NivelFuncional> niveisFuncionais) {
		SIAECache.niveisFuncionais = niveisFuncionais;
	}

	public static List<Categoria> getCategorias() {
		return categorias;
	}

	public static void setCategorias(List<Categoria> categorias) {
		SIAECache.categorias = categorias;
	}

	public static List<Instituicao> getInstituicoes() {
		return instituicoes;
	}
	
	public static void setInstituicoes(List<Instituicao> instituicoes) {
		SIAECache.instituicoes = instituicoes;
	}
	
	public static Instituicao getInstituicaoById(int id) {
		Instituicao instituicao = new Instituicao();
		instituicao.setId(id);
		if(instituicoes.contains(instituicao)){
			return instituicoes.get(instituicoes.indexOf(instituicao));
		}
		return null;
	}

	public static Categoria getCategoriaById(int id) {
		Categoria categoria = new Categoria();
		categoria.setId(id);
		if(categorias.contains(categoria)) {
			return categorias.get(categorias.indexOf(categoria));
		}
		return null;
	}

	public static Nivel getNivelById(int id) {
		Nivel nivel = new Nivel();
		nivel.setId(id);
		if(niveis.contains(nivel)){
			return niveis.get(niveis.indexOf(nivel));
		}
		return null;
	}

	public static NivelFuncional getNivelFuncionalById(int id) {
		NivelFuncional nivelFuncional = new NivelFuncional();
		nivelFuncional.setId(id);
		if(niveisFuncionais.contains(nivelFuncional)){
			return niveisFuncionais.get(niveisFuncionais.indexOf(nivelFuncional));
		}
		return null;
	}

	public static Professor getProfessorById(int id) {
		Professor professor = new Professor();
		professor.setId(id);
		if(professores.contains(professor)){
			return professores.get(professores.indexOf(professor));
		}
		return null;
	}

	public static Serie getSerieById(int id) {
		Serie serie = new Serie();
		serie.setId(id);
		if(series.contains(serie)){
			return series.get(series.indexOf(serie));
		}
		return null;
	}

	public static Turno getTurnoById(int id) {
		Turno turno = new Turno();
		turno.setId(id);
		if(turnos.contains(turno)){
			return turnos.get(turnos.indexOf(turno));
		}
		return null;
	}
	
}
