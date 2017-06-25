package br.com.hackfest.quemequem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.hackfest.quemequem.entity.OpcaoConsultaFolha;
import br.com.hackfest.quemequem.enums.Esfera;

@Repository
public interface OpcaoConsultaFolhaRepository extends JpaRepository<OpcaoConsultaFolha, Integer>, OpcaoConsultaFolhaRepositoryCustom {
	
	@Query("SELECT DISTINCT o.anoReferencia FROM OpcaoConsultaFolha o WHERE o.esfera = ?1 ORDER BY o.anoReferencia DESC")
	List<Integer> findDistinctAnoReferenciaByEsferaEqualsOrderByAnoReferenciaDesc(Esfera esfera);
	
	@Query("SELECT DISTINCT o.gestao FROM OpcaoConsultaFolha o WHERE o.esfera = ?1  AND o.anoReferencia = ?2 ORDER BY o.gestao")
	List<String> findDistinctGestaoByEsferaEqualsAndAnoReferenciaEqualsOrderByGestao(Esfera esfera, Integer ano);

	@Query("SELECT DISTINCT o.orgao FROM OpcaoConsultaFolha o WHERE o.esfera = ?1 AND o.anoReferencia = ?2 AND o.gestao = ?3 ORDER BY o.orgao")
	List<String> findDistinctOrgaoByEsferaEqualsAndAnoReferenciaEqualsAndGestaoEqualsOrderByOrgao(Esfera esfera, Integer ano, String gestao);

	

}
