package br.siae.jsf;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.siae.dominio.rh.Professor;

public class ProfessorDataModel extends ListDataModel<Professor> implements SelectableDataModel<Professor> {

	@Override
	public Professor getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<Professor> professores = (List<Professor>) getWrappedData();
		for( Professor professor : professores ) {
			if( professor.getId() == Integer.parseInt(rowKey) ) {
				return professor;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Professor professor) {
		return professor.getId();
	}

}
