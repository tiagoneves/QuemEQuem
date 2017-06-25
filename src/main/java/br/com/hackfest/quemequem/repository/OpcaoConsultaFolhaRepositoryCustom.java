package br.com.hackfest.quemequem.repository;

import br.com.hackfest.quemequem.enums.Esfera;

public interface OpcaoConsultaFolhaRepositoryCustom {
	
	void inserirOpcoes(Esfera esfera);
	
	void inserirOpcoes(Esfera esfera, int ano, int mes);

}
