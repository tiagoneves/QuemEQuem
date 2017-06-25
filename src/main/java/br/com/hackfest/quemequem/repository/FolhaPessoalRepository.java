package br.com.hackfest.quemequem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hackfest.quemequem.entity.FolhaPessoal;

@Repository
public interface FolhaPessoalRepository extends JpaRepository<FolhaPessoal, Integer>, FolhaPessoalRepositoryCustom {

	FolhaPessoal findByNomeEquals(String nome);
	
	
		
}