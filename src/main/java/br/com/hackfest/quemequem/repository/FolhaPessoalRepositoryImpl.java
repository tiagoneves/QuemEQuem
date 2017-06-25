package br.com.hackfest.quemequem.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.hackfest.quemequem.entity.FolhaPessoal;
import br.com.hackfest.quemequem.enums.Esfera;

public class FolhaPessoalRepositoryImpl implements FolhaPessoalRepositoryCustom {

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, 
			Esfera esfera, String gestao, String nome, String orgao, Integer offset, Integer limit) {		
		
		String jpql = "SELECT f FROM FolhaPessoal f"+adicionarFiltros(ano, mes, esfera, gestao, nome, orgao)+
				" ORDER BY f.nome, f.anoReferencia DESC, f.mesReferencia DESC";
		
		TypedQuery<FolhaPessoal> query = em.createQuery(jpql, FolhaPessoal.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
						
		return query.getResultList();
		
	}
	
	private String adicionarFiltros(Integer ano, Integer mes, Esfera esfera, String gestao, String nome, String orgao){
		
		String where = "";
		
		if (esfera != null) {
			where += where.isEmpty()? " WHERE " : " AND ";
			where += "f.esfera = '"+esfera+"'";
		}
		
		if (gestao != null && !gestao.trim().isEmpty()) {
			where += where.isEmpty()? " WHERE " : " AND ";
			where += "f.gestao = '"+gestao+"'";
		}
		
		if (orgao != null && !orgao.trim().isEmpty()) {
			where += where.isEmpty()? " WHERE " : " AND ";
			where += "f.orgao = '"+orgao+"'";
		}
		
		if (nome != null && !nome.trim().isEmpty()) {
			where += where.isEmpty()? " WHERE " : " AND ";
			where += "f.nome = '"+nome+"'";
		}
		
		if (mes != null && mes > 0 ) {
			where += where.isEmpty()? " WHERE " : " AND ";
			where += "f.mesReferencia = "+mes;
		}
		
		if (ano != null && ano > 0) {
			where += where.isEmpty()? " WHERE " : " AND "; 
			where += "f.anoReferencia = "+ano;
		}
		
		return where;
		
	}
	
	
	
}

