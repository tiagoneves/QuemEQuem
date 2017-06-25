package br.com.hackfest.quemequem;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.hackfest.quemequem.service.FolhaPessoalService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		/*FolhaPessoalService folhaService = context.getBean(FolhaPessoalService.class);
		
		try {
			folhaService.importarDadosEsferaMunicipal("C:/Users/Tiago/Documents/TCE-PB-SAGRES-Folha_Pessoal_Esfera_Municipal.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

	}

}
