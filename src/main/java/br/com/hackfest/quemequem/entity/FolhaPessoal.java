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
@Table(name="folha_pessoal")
public class FolhaPessoal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Esfera esfera;

	@Column(name="gestao")
	private String gestao;
	
	@Column(name="orgao")
	private String orgao;
	
	@Column(name="cargo")
	private String cargo;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="mes_referencia")
	private Integer mesReferencia;
	
	@Column(name="ano_referencia")
	private Integer anoReferencia;
	
	@Column(name="vantagem")
	private Float vantagem;
	
	public FolhaPessoal(){
		super();
		this.esfera = null;
		this.gestao = "";
		this.orgao = "";
		this.cargo = "";
		this.nome = "";
		this.mesReferencia = 0;
		this.anoReferencia = 0;
		this.vantagem = 0f;
	}

	public FolhaPessoal(Esfera esfera, String gestao, String orgao, String cargo, String nome, Integer mesReferencia,
			Integer anoReferencia, Float vantagem) {
		super();
		this.esfera = esfera;
		this.gestao = gestao;
		this.orgao = orgao;
		this.cargo = cargo;
		this.nome = nome;
		this.mesReferencia = mesReferencia;
		this.anoReferencia = anoReferencia;
		this.vantagem = vantagem;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Integer mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public Integer getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public Float getVantagem() {
		return vantagem;
	}

	public void setVantagem(Float vantagem) {
		this.vantagem = vantagem;
	}

	
}
