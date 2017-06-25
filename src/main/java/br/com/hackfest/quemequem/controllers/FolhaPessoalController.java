package br.com.hackfest.quemequem.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hackfest.quemequem.entity.FolhaPessoal;
import br.com.hackfest.quemequem.enums.Esfera;
import br.com.hackfest.quemequem.enums.Mes;
import br.com.hackfest.quemequem.service.FolhaPessoalService;
import br.com.hackfest.quemequem.service.OpcaoConsultaFolhaService;

@RestController
@RequestMapping("/folha_pessoal")
public class FolhaPessoalController {

	@Autowired
	private FolhaPessoalService folhaPessoalService;
	
	@Autowired
	private OpcaoConsultaFolhaService opcaoConsultaFolhaService;


	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<FolhaPessoal>> buscarTodosOrgaos() {

		Collection<FolhaPessoal> pessoal = folhaPessoalService.listarTodos();

		if (pessoal == null || pessoal.isEmpty()) {
			return new ResponseEntity<Collection<FolhaPessoal>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Collection<FolhaPessoal>>(pessoal, HttpStatus.OK);
	}
	
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
			Esfera esfera, @RequestParam("gestao") String gestao, @RequestParam("nome") String nome, 
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
	
	class Opcao{
		
		String value;
		
		String label;

		public Opcao(String value, String label) {
			super();
			this.value = value;
			this.label = label;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
		
		
		
	}
	
	@RequestMapping(value="/esferas", method = RequestMethod.GET)
	public ResponseEntity<List<Opcao>> obterEsferas(){
		
		List<Opcao> esferas = new ArrayList<Opcao>();
		
		Esfera[] esferasArray = Esfera.values();
				
		for (int i = 0; i < esferasArray.length; i++)
			esferas.add(new Opcao(esferasArray[i].toString(), esferasArray[i].descricao));
		
		return new ResponseEntity<List<Opcao>>(esferas, HttpStatus.OK);
				
	}
	
	@RequestMapping(value = "/anos", method = RequestMethod.GET)
	public ResponseEntity<List<Integer>> obterAnos(@RequestParam("esfera") Esfera esfera){
		
		List<Integer> anos = opcaoConsultaFolhaService.obterAnos(esfera);
		
		return new ResponseEntity<List<Integer>>(anos, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/meses", method = RequestMethod.GET)
	public ResponseEntity<List<Opcao>> obterMeses(){
		
		List<Opcao> meses = new ArrayList<Opcao>();
		
		Mes[] mesesArray = Mes.values();
		
		for (int i = 0; i < mesesArray.length; i++)
			meses.add(new Opcao((i + 1)+"", mesesArray[i].descricao));
		
		return new ResponseEntity<List<Opcao>>(meses, HttpStatus.OK);
				
	}
	
	
	@RequestMapping(value = "/gestoes", method = RequestMethod.GET)
	public ResponseEntity<List<String>> obterGestoes(@RequestParam("esfera") Esfera esfera, @RequestParam("ano") Integer ano){
		
		List<String> gestoes = opcaoConsultaFolhaService.obterGestoes(esfera, ano);
		
		return new ResponseEntity<List<String>>(gestoes, HttpStatus.OK);

	}

	@RequestMapping(value = "/orgaos", method = RequestMethod.GET)
	public ResponseEntity<List<String>> obterOrgaos(@RequestParam("esfera") Esfera esfera, @RequestParam("ano") Integer ano, 
			@RequestParam("gestao") String gestao){
		
		List<String> orgaos = opcaoConsultaFolhaService.obterOrgaos(esfera, ano, gestao);
		
		return new ResponseEntity<List<String>>(orgaos, HttpStatus.OK);
		
	}

	

}