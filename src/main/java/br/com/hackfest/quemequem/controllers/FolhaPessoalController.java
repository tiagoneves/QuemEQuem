package br.com.hackfest.quemequem.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	/*@RequestMapping(value = "/pagina", method = RequestMethod.GET)
	public ResponseEntity<Collection<FolhaPessoal>> obterPagina(@RequestParam("ano") Integer ano, 
			@RequestParam("mes") Integer mes, @RequestParam("poder") 
			String poder, @RequestParam("nome") String nome, 
			@RequestParam("orgao") String orgao, @RequestParam("pagina") Integer pagina, 
			@RequestParam("qtditens") Integer itensPorPagina) {
		
		Collection<FolhaPessoal> pessoal = folhaPessoalService.listarComFiltros(ano, mes, poder, nome, orgao, pagina, itensPorPagina);

		if (pessoal == null || pessoal.isEmpty()) {
			return new ResponseEntity<Collection<FolhaPessoal>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Collection<FolhaPessoal>>(pessoal, HttpStatus.OK);
	
	}*/
	
	class ResultadoConsulta {
		
		Integer quantidadeDePaginas;
		
		Collection<FolhaPessoal> pessoal;
		
		ResultadoConsulta(){
			quantidadeDePaginas = 0;
			pessoal = new ArrayList<FolhaPessoal>();
		}

		public Integer getQuantidadeDePaginas() {
			return quantidadeDePaginas;
		}

		public void setQuantidadeDePaginas(Integer quantidadeDePaginas) {
			this.quantidadeDePaginas = quantidadeDePaginas;
		}

		public Collection<FolhaPessoal> getPessoal() {
			return pessoal;
		}

		public void setPessoal(Collection<FolhaPessoal> pessoal) {
			this.pessoal = pessoal;
		}
		
	}
	
	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	public ResponseEntity<ResultadoConsulta> consultar(@RequestParam("ano") Integer ano, 
			@RequestParam("mes") Integer mes, @RequestParam("esfera") 
			String esfera, @RequestParam("gestao") String gestao, @RequestParam("nome") String nome, 
			@RequestParam("orgao") String orgao, @RequestParam("itensporpagina") Integer itensPorPagina,
			@RequestParam("primeirapagina") Integer primeiraPagina, 
			@RequestParam("quantidadedepaginas") Integer qtdPaginas) {
		
		ResultadoConsulta resultado = new ResultadoConsulta();
		
		resultado.pessoal = folhaPessoalService.listarComFiltros(ano, mes, esfera, gestao, nome, orgao, itensPorPagina, primeiraPagina, qtdPaginas);
		
		int qtdRegistros = resultado.pessoal.size();
		
		resultado.quantidadeDePaginas = qtdRegistros / itensPorPagina;
		
		if (qtdRegistros % itensPorPagina > 0)
			resultado.quantidadeDePaginas++;
		
		return new ResponseEntity<ResultadoConsulta>(resultado, HttpStatus.OK);
	
	}
	

}