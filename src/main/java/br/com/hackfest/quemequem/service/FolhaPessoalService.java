package br.com.hackfest.quemequem.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, String esfera, String gestao, String nome, String orgao, Integer itensPorPagina, Integer paginaInicial, 
			Integer quantidadeDePaginas) {
		
		return folhaPessoalRepository.listarComFiltros(ano, mes, esfera, gestao, nome, orgao, (paginaInicial - 1) * itensPorPagina, itensPorPagina * quantidadeDePaginas);
		
	}
	
		
	public int quantidadeDeRegistrosUltimaPagina(long quantidadeDeRegistros, int itensPorPagina) {
		return (int) (quantidadeDeRegistros % itensPorPagina);

	}
	
	
	public void importarDadosEsferaEstadual(){
		
		try {
			
			File file = new File("C:/Users/Tiago/Documents/TCE-PB-SAGRES-Folha_Pessoal_Esfera_Estadual.txt");
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
		

			folhaPessoal = new FolhaPessoal("Estadual", dados[0], dados[1], dados[2], dados[5], Integer.parseInt(dados[6].substring(0, 2)), 
						Integer.parseInt(dados[6].substring(2, 6)), Float.parseFloat(dados[8])) ;
				
				folhaPessoalRepository.save(folhaPessoal);
				

			}
			
			System.out.println("100%");
			
			br.close();
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
