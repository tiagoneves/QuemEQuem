package br.com.hackfest.quemequem.repository;

import java.util.List;

import br.com.hackfest.quemequem.entity.FolhaPessoal;

public interface FolhaPessoalRepositoryCustom {

	List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, String esfera, String gestao, String nome, String orgao, Integer offset, Integer limit) ;

	
}
