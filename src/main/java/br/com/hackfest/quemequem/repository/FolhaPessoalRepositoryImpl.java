package br.com.hackfest.quemequem.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.hackfest.quemequem.entity.FolhaPessoal;

public class FolhaPessoalRepositoryImpl implements FolhaPessoalRepositoryCustom {

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<FolhaPessoal> listarComFiltros(Integer ano, Integer mes, 
			String poder, String nome, String orgao, Integer pagina, Integer itensPorPagina) {		
		
		String jpql = "SELECT f FROM FolhaPessoal f"+adicionarFiltros(ano, mes, poder, nome, orgao)+
				" ORDER BY f.anoReferencia DESC, f.mesReferencia DESC";
		
		TypedQuery<FolhaPessoal> query = em.createQuery(jpql, FolhaPessoal.class);
		query.setFirstResult((pagina - 1) * itensPorPagina);
		query.setMaxResults(itensPorPagina);
				
		return query.getResultList();
		
	}
	
	private String adicionarFiltros(Integer ano, Integer mes, String poder, String nome, String orgao){
		
		String where = "";
		
		if (poder != null && !poder.trim().isEmpty()) {
			where += where.isEmpty()? " WHERE " : " AND ";
			where += "f.poder = '"+poder+"'";
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
	
	public Long quantidadedDeRegistros(Integer ano, Integer mes, String poder, String nome, String orgao){
		
		String jpql = "SELECT count(f) FROM FolhaPessoal f"+adicionarFiltros(ano, mes, poder, nome, orgao);
			
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
					
		return query.getSingleResult();
		
	}
	
	
	
}

