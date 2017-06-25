package br.com.hackfest.quemequem.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackfest.quemequem.entity.FolhaPessoal;
import br.com.hackfest.quemequem.enums.Esfera;
import br.com.hackfest.quemequem.repository.FolhaPessoalRepository;

@Service
public class FolhaPessoalService {

	@Autowired
	FolhaPessoalRepository folhaPessoalRepository;
		
	@Autowired
	OpcaoConsultaFolhaService opcaoConsultaFolhaService;

	
	public List<FolhaPessoal> listarTodos() {
		return folhaPessoalRepository.findAll();
	}
	
	public List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, Esfera esfera, String gestao, String nome, String orgao, Integer itensPorPagina, Integer paginaInicial, 
			Integer quantidadeDePaginas) {
		
		return folhaPessoalRepository.listarComFiltros(ano, mes, esfera, gestao, nome, orgao, (paginaInicial - 1) * itensPorPagina, itensPorPagina * quantidadeDePaginas);
		
	}
	
		
	public int quantidadeDeRegistrosUltimaPagina(long quantidadeDeRegistros, int itensPorPagina) {
		return (int) (quantidadeDeRegistros % itensPorPagina);

	}
		
	
	public void importarDadosEsferaFederal(String pathArquivo){
		//TODO 
	}
	
	
	public void importarDadosEsferaEstadual(String pathArquivo) throws IOException{
					
		File file = new File(pathArquivo);
		long fileLength = file.length();
		long totalLido = 0l;
		int progresso = 0;
		int progressoAtual = 0;
			
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linha = br.readLine();
		FolhaPessoal folhaPessoal = null;
		while((linha = br.readLine()) != null) { 	
			String[] dados = linha.split("\\|"); 
			totalLido += linha.getBytes().length;
			progresso = (int) ((totalLido * 100) / fileLength);
				
			if (progresso > progressoAtual){
				System.out.println(progresso+"%");
				progressoAtual = progresso;
			}
		

			folhaPessoal = new FolhaPessoal(Esfera.ESTADUAL, dados[0], dados[1], dados[2], dados[5], Integer.parseInt(dados[6].substring(0, 2)), 
					Integer.parseInt(dados[6].substring(2, 6)), Float.parseFloat(dados[8])) ;
				
			folhaPessoalRepository.save(folhaPessoal);
				

		}
			
		System.out.println("100%");
			
		br.close();
			
		System.out.println("Atualizando opções....");
			
		opcaoConsultaFolhaService.inserirOpcoes(Esfera.ESTADUAL);
			
		System.out.println("Finalizado");

					
		
		
	}
	
	public void importarDadosEsferaMunicipal(String pathArquivo) throws IOException{
		
			
		File file = new File(pathArquivo);
		long fileLength = file.length();
		long totalLido = 0l;
		int progresso = 0;
		int progressoAtual = 0;
			
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linha = br.readLine();
		FolhaPessoal folhaPessoal = null;
		while((linha = br.readLine()) != null) { 	
			String[] dados = linha.split("\\|"); 
			totalLido += linha.getBytes().length;
			progresso = (int) ((totalLido * 100) / fileLength);
				
			if (progresso > progressoAtual){
				System.out.println(progresso+"%");
				progressoAtual = progresso;
			}
		

			folhaPessoal = new FolhaPessoal(Esfera.MUNICIPAL, dados[1], (dados.length > 8) ? dados[8] : dados[1], dados[2], dados[6], 
				Integer.parseInt(dados[5].substring(0, 2)), Integer.parseInt(dados[5].substring(2, 6)), Float.parseFloat(dados[7])) ;
				
			folhaPessoalRepository.save(folhaPessoal);
				

		}
			
		System.out.println("100%");
			
		br.close();
			
		System.out.println("Atualizando opções....");
			
		opcaoConsultaFolhaService.inserirOpcoes(Esfera.MUNICIPAL);
			
		System.out.println("Finalizado");
				
		
	}
	
	public void importarDadosEsferaFederal(String pathArquivo, int ano, int mes){
		//TODO 
	}
	
	
	public void importarDadosEsferaEstadual(String pathArquivo, int ano, int mes) throws IOException{
					
		File file = new File(pathArquivo);
		long fileLength = file.length();
		long totalLido = 0l;
		int progresso = 0;
		int progressoAtual = 0;
			
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linha = br.readLine();
		FolhaPessoal folhaPessoal = null;
		while((linha = br.readLine()) != null) { 	
			String[] dados = linha.split("\\|"); 
			totalLido += linha.getBytes().length;
			progresso = (int) ((totalLido * 100) / fileLength);
				
			if (progresso > progressoAtual){
				System.out.println(progresso+"%");
				progressoAtual = progresso;
			}
				
			int anoRef = Integer.parseInt(dados[6].substring(2, 6));
			int mesRef = Integer.parseInt(dados[6].substring(0, 2));
				
			if (anoRef == ano && mesRef == mes) {
				
				folhaPessoal = new FolhaPessoal(Esfera.ESTADUAL, dados[0], dados[1], dados[2], dados[5], mesRef, anoRef, 
						Float.parseFloat(dados[8])) ;
					
				folhaPessoalRepository.save(folhaPessoal);
				
			}
				

		}
			
		System.out.println("100%");
			
		br.close();
			
		System.out.println("Atualizando opções....");
			
		opcaoConsultaFolhaService.inserirOpcoes(Esfera.ESTADUAL, ano, mes);
			
		System.out.println("Finalizado");

		
	}
	
	public void importarDadosEsferaMunicipal(String pathArquivo, int ano, int mes) throws IOException{
					
		File file = new File(pathArquivo);
		long fileLength = file.length();
		long totalLido = 0l;
		int progresso = 0;
		int progressoAtual = 0;
			
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linha = br.readLine();
		FolhaPessoal folhaPessoal = null;
		while((linha = br.readLine()) != null) { 	
			String[] dados = linha.split("\\|"); 
			totalLido += linha.getBytes().length;
			progresso = (int) ((totalLido * 100) / fileLength);
				
			if (progresso > progressoAtual){
				System.out.println(progresso+"%");
				progressoAtual = progresso;
			}
		
			int anoRef = Integer.parseInt(dados[5].substring(2, 6));
			int mesRef = Integer.parseInt(dados[5].substring(0, 2));
				
			if (anoRef == ano && mesRef == mes) {

				folhaPessoal = new FolhaPessoal(Esfera.MUNICIPAL, dados[1], (dados.length > 8) ? dados[8] : dados[1], dados[2], dados[6], 
						mesRef, anoRef, Float.parseFloat(dados[7])) ;
				
				folhaPessoalRepository.save(folhaPessoal);
					
			}
				

		}
			
		System.out.println("100%");
			
		br.close();
		
		System.out.println("Atualizando opções....");
			
		opcaoConsultaFolhaService.inserirOpcoes(Esfera.MUNICIPAL, ano, mes);
			
		System.out.println("Finalizado");
		
		
	}
	
	
}
