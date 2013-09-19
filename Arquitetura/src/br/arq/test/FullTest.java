package br.arq.test;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses( { PessoaTest.class, PermissaoTest.class, UsuarioTest.class })  
public class FullTest extends TestCase{
	
}
