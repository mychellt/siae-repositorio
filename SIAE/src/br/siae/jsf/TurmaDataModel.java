package br.siae.jsf;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import br.siae.dominio.academico.Turma;

public class TurmaDataModel extends ListDataModel<Turma> implements SelectableDataModel<Turma> {

	@Override
	public Turma getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<Turma> turmas = (List<Turma>) getWrappedData();
		for( Turma turma : turmas ) {
			if( turma.getId() == Integer.parseInt(rowKey) ) {
				return turma;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Turma turma) {
		return turma.getId();
	}

}
