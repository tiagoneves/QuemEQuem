package br.com.hackfest.quemequem.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import br.com.hackfest.quemequem.enums.Esfera;

public class OpcaoConsultaFolhaRepositoryImpl implements OpcaoConsultaFolhaRepositoryCustom{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	@Transactional
	public void inserirOpcoes(Esfera esfera) {
		
		String sql = "INSERT INTO opcao_consulta_folha (ano_referencia, esfera, gestao, orgao) "
				+ "SELECT DISTINCT ano_referencia, :esfera, gestao, orgao FROM folha_pessoal where esfera = :esfera";
		
		Query query = em.createNativeQuery(sql);
		
		query.setParameter("esfera", esfera);
			
		query.executeUpdate();			
		
	}

	@Override
	@Transactional
	public void inserirOpcoes(Esfera esfera, int ano, int mes) {
		
		String sql = "INSERT INTO opcao_consulta_folha (ano_referencia, esfera, gestao, orgao) "
				+ "SELECT DISTINCT ano_referencia, :esfera, gestao, orgao FROM folha_pessoal where esfera = :esfera AND ano_referencia = :ano "
				+ "AND mes_referencia = :mes";
		
		Query query = em.createNativeQuery(sql);
		
		query.setParameter("esfera", esfera);
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		
		query.executeUpdate();
		
	}

}
