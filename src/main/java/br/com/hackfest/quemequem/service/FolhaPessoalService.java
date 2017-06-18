package br.com.hackfest.quemequem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackfest.quemequem.entity.FolhaPessoal;
import br.com.hackfest.quemequem.repository.FolhaPessoalRepository;

@Service
public class FolhaPessoalService {

	@Autowired
	FolhaPessoalRepository folhaPessoalRepository;

	
	public List<FolhaPessoal> listarTodos() {
		return folhaPessoalRepository.findAll();
	}
	
	public List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, String poder, String nome, String orgao, Integer itensPorPagina, Integer paginaInicial, 
			Integer quantidadeDePaginas) {
		
		return folhaPessoalRepository.listarComFiltros(ano, mes, poder, nome, orgao, (paginaInicial - 1) * itensPorPagina, itensPorPagina * quantidadeDePaginas);
		
	}
	
	public Long quantidadeDePaginas(Integer ano,  Integer mes, String poder,  String nome, String orgao, Integer itensPorPagina) {
		
		Long quantidadeRegistros = folhaPessoalRepository.quantidadedDeRegistros(ano, mes, poder, nome, orgao);
		
		long paginas = quantidadeRegistros.longValue() / itensPorPagina;
		
		if (quantidadeRegistros.longValue() % itensPorPagina > 0)
			paginas++;
		
		return paginas;
		
		
	}
	
	public int quantidadeDeRegistrosUltimaPagina(long quantidadeDeRegistros, int itensPorPagina) {
		return (int) (quantidadeDeRegistros % itensPorPagina);

	}
	
	public Integer quantidadeDePaginas(Integer ano,  Integer mes, String poder,  String nome, String orgao, Integer itensPorPagina, Integer primeiraPagina, Integer ultimaPagina) {
		
		
		int qtdRegistros = folhaPessoalRepository.quantidadedDeRegistros(ano, mes, poder, nome, orgao, primeiraPagina - 1, itensPorPagina * ultimaPagina);
		
		int paginas = qtdRegistros / itensPorPagina;
		
		if (qtdRegistros % itensPorPagina > 0)
			paginas++;
		
		return paginas;
		
		
	}
	
	
}
