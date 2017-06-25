package br.com.hackfest.quemequem.repository;

import java.util.List;

import br.com.hackfest.quemequem.entity.FolhaPessoal;
import br.com.hackfest.quemequem.enums.Esfera;

public interface FolhaPessoalRepositoryCustom {

	List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, Esfera esfera, String gestao, String nome, String orgao, Integer offset, Integer limit) ;

}
