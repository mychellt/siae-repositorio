package br.siae.arq.seguranca;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class SucessoAutenticacaoListener implements ApplicationListener<AuthenticationSuccessEvent>{

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		
	}

}
