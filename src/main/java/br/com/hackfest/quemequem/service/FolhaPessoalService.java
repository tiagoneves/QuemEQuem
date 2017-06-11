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
	
	public List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, String poder, String nome, String orgao) {
		
		return folhaPessoalRepository.listarComFiltros(ano, mes, poder, nome, orgao);
		
	}
	
	
}
