package br.siae.arq.seguranca;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import br.siae.arq.service.ArqService;

@Component
public class SucessoAutenticacaoListener implements ApplicationListener<AuthenticationSuccessEvent>{
	@Resource(name="arqService")
	private ArqService arqService;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		try {
			arqService.executeCaching();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
