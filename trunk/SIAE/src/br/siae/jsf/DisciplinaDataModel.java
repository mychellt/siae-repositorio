package br.siae.jsf;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.siae.dominio.academico.Disciplina;

public class DisciplinaDataModel extends ListDataModel<Disciplina> implements SelectableDataModel<Disciplina> {

	@Override
	public Disciplina getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<Disciplina> disciplinas = (List<Disciplina>) getWrappedData();
		for( Disciplina disciplina : disciplinas ) {
			if( disciplina.getId() == Integer.parseInt(rowKey) ) {
				return disciplina;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Disciplina disciplina) {
		return disciplina.getId();
	}

}
