package br.com.hackfest.quemequem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.hackfest.quemequem.service.FolhaPessoalService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		FolhaPessoalService folhaPessoalService = context.getBean(FolhaPessoalService.class);
		
		folhaPessoalService.importarDadosEsferaEstadual();

	}

}
