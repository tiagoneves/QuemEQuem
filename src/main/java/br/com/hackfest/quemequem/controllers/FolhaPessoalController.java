package br.com.hackfest.quemequem.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackfest.quemequem.entity.FolhaPessoal;
import br.com.hackfest.quemequem.service.FolhaPessoalService;

@RestController
@RequestMapping("/folha_pessoal")
public class FolhaPessoalController {

	private FolhaPessoalService folhaPessoalService;

	@Autowired
	public FolhaPessoalController(FolhaPessoalService folhaPessoalService) {
		this.folhaPessoalService = folhaPessoalService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<FolhaPessoal>> buscarTodosOrgaos() {

		Collection<FolhaPessoal> pessoal = folhaPessoalService.listarTodos();

		if (pessoal == null || pessoal.isEmpty()) {
			return new ResponseEntity<Collection<FolhaPessoal>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Collection<FolhaPessoal>>(pessoal, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{ano}/{mes}/{poder}/{nome}/{orgao}", method = RequestMethod.GET)
	public ResponseEntity<Collection<FolhaPessoal>> listarComParametros(@PathVariable("ano") Integer ano, 
			@PathVariable("mes") Integer mes, @PathVariable("poder") 
			String poder, @PathVariable("nome") String nome, 
			@PathVariable("orgao") String orgao) {
		
		Collection<FolhaPessoal> pessoal = folhaPessoalService.listarComFiltros(ano, mes, poder, nome, orgao);

		if (pessoal == null || pessoal.isEmpty()) {
			return new ResponseEntity<Collection<FolhaPessoal>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Collection<FolhaPessoal>>(pessoal, HttpStatus.OK);
	
	}	

}