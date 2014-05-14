package br.siae.jsf;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.arq.seguranca.Permissao;

public class PermissaoDataModel extends ListDataModel<Permissao> implements SelectableDataModel<Permissao>{

	@Override
	public Permissao getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<Permissao> permissoes = (List<Permissao>) getWrappedData();
		for( Permissao permissao : permissoes ) {
			if( permissao.getId() == Integer.parseInt(rowKey) ) {
				return permissao;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Permissao permissao) {
		return permissao.getId();
	}

}
