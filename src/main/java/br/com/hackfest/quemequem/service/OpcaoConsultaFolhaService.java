package br.com.hackfest.quemequem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackfest.quemequem.enums.Esfera;
import br.com.hackfest.quemequem.repository.OpcaoConsultaFolhaRepository;

@Service
public class OpcaoConsultaFolhaService {
	
	@Autowired
	private OpcaoConsultaFolhaRepository opcaoConsultaFolhaRepository;
	
	
    public List<Integer> obterAnos(Esfera esfera){
    	return opcaoConsultaFolhaRepository.findDistinctAnoReferenciaByEsferaEqualsOrderByAnoReferenciaDesc(esfera);
    }
	
	public List<String> obterGestoes(Esfera esfera, Integer ano){
		return opcaoConsultaFolhaRepository.findDistinctGestaoByEsferaEqualsAndAnoReferenciaEqualsOrderByGestao(esfera, ano);
	}

	public List<String> obterOrgaos(Esfera esfera, Integer ano, String gestao){
		return opcaoConsultaFolhaRepository.findDistinctOrgaoByEsferaEqualsAndAnoReferenciaEqualsAndGestaoEqualsOrderByOrgao(esfera, ano, gestao);
	}
	
    public void inserirOpcoes(Esfera esfera){
    	opcaoConsultaFolhaRepository.inserirOpcoes(esfera);
    }
	
	public void inserirOpcoes(Esfera esfera, int ano, int mes){
		opcaoConsultaFolhaRepository.inserirOpcoes(esfera, ano, mes);
	}

}
