package br.siae.arq.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SegurancaDAO  extends GenericDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	 public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	 
	public void inativarUsuario(String login) {
		jdbcTemplate.update("UPDATE usuario SET ativo = false WHERE login = ?", new Object[] { false, login });		
	}
	
	public void ativarUsuario(String login) {
		jdbcTemplate.update("UPDATE usuario SET ativo = true WHERE login = ?", new Object[] { true, login });		
	}
}
