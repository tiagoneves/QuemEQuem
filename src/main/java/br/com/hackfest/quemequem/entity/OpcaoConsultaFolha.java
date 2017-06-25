package br.com.hackfest.quemequem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.hackfest.quemequem.enums.Esfera;

@Entity
@Table(name="opcao_consulta_folha")
public class OpcaoConsultaFolha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ano_referencia")
	private Integer anoReferencia;
	
	@Enumerated(EnumType.STRING)
	private Esfera esfera;

	@Column(name="gestao")
	private String gestao;
	
	@Column(name="orgao")
	private String orgao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public Esfera getEsfera() {
		return esfera;
	}

	public void setEsfera(Esfera esfera) {
		this.esfera = esfera;
	}

	public String getGestao() {
		return gestao;
	}

	public void setGestao(String gestao) {
		this.gestao = gestao;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	
	

}
