package br.siae.arq.jsf;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Pessoa;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class ConsultadorPessoa extends AbstractController{
	/** Realiza a consulta por nome.*/
	private boolean byNome;
	
	/** Realiza a consulta por CPF.*/
	private boolean byCpf;
	
	/** Realizar a consulta por RG.*/
	private boolean byRg;
	
	/** Realizar a consulta por Número do Registro de Nascimento.*/
	private boolean byRegistroNascimento;
	
	/** Realizar a consulta por nome da mãe*/
	private boolean byNomeMae;
	
	/** Realiza a consulta por nome do pai.*/
	private boolean byNomePai;
	
	/** Realiza a busca por data de nascimento */
	private boolean byDataNascimento;
	
	
	public void validarConsulta( Pessoa obj) {
		if( !isByCpf() && !isByNome() && !isByDataNascimento() && !isByNomeMae() && !isByNomePai() && !isByRegistroNascimento() && !isByRg() ){
			addMensagemErro("Selecione pelo menos uma opção de busca.");
		}
		if( isByCpf() && ValidatorUtil.isEmpty( obj.getCpf() ) ){
			addMensagemErro("Informe o CPF para realizar a consulta.");
		}
		if( isByNome() && ValidatorUtil.isEmpty( obj.getNome() ) ) {
			addMensagemErro("Informe o nome para realizar a consulta.");
		}
		if( isByNomeMae() && ValidatorUtil.isEmpty( obj.getNomeMae() ) ){
			addMensagemErro("Informe o nome da mãe para realizar a consulta.");
		}
		if( isByNomePai() && ValidatorUtil.isEmpty( obj.getNomePai() ) ){
			addMensagemErro("Informe o nome do pai para realizar a consulta.");
		}
		if( isByRg() && ValidatorUtil.isEmpty( obj.getIdentidade().getNumero() ) ) {
			addMensagemErro("Informe o nome o número do RG para realizar a consulta.");
		}
		if( isByRegistroNascimento() && ValidatorUtil.isEmpty( obj.getCertidaoNascimento().getNumero() ) ) {
			addMensagemErro("Informe o nome o número do registro de nascimento para realizar a consulta.");
		}
		if( isByDataNascimento() && ValidatorUtil.isEmpty( obj.getDataNascimento() ) ) {
			addMensagemErro("Informe a data de nascimento para realizar a consulta.");
		}
	}

	public boolean isByNome() {
		return byNome;
	}

	public void setByNome(boolean byNome) {
		this.byNome = byNome;
	}

	public boolean isByCpf() {
		return byCpf;
	}

	public void setByCpf(boolean byCpf) {
		this.byCpf = byCpf;
	}

	public boolean isByRg() {
		return byRg;
	}

	public void setByRg(boolean byRg) {
		this.byRg = byRg;
	}

	public boolean isByRegistroNascimento() {
		return byRegistroNascimento;
	}

	public void setByRegistroNascimento(boolean byRegistroNascimento) {
		this.byRegistroNascimento = byRegistroNascimento;
	}

	public boolean isByNomeMae() {
		return byNomeMae;
	}

	public void setByNomeMae(boolean byNomeMae) {
		this.byNomeMae = byNomeMae;
	}

	public boolean isByNomePai() {
		return byNomePai;
	}

	public void setByNomePai(boolean byNomePai) {
		this.byNomePai = byNomePai;
	}

	public boolean isByDataNascimento() {
		return byDataNascimento;
	}

	public void setByDataNascimento(boolean byDataNascimento) {
		this.byDataNascimento = byDataNascimento;
	}
}
