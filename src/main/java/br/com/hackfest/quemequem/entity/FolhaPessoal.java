package br.com.hackfest.quemequem.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="folha_pessoal")
public class FolhaPessoal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="poder")
	private String poder;
	
	@Column(name="gestao")
	private String gestao;
	
	@Column(name="orgao")
	private String orgao;
	
	@Column(name="cargo")
	private String cargo;
	
	@Column(name="regime")
	private String regime;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="mes_referencia")
	private Integer mesReferencia;
	
	@Column(name="ano_referencia")
	private Integer anoReferencia;
	
	@Column(name="vantagem")
	private BigDecimal vantagem;
	
	@Column(name="vantagens")
	private BigDecimal vantagens;

	public String getPoder() {
		return poder;
	}

	public void setPoder(String poder) {
		this.poder = poder;
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

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public BigDecimal getVantagem() {
		return vantagem;
	}

	public void setVantagem(BigDecimal vantagem) {
		this.vantagem = vantagem;
	}

	public BigDecimal getVantagens() {
		return vantagens;
	}

	public void setVantagens(BigDecimal vantagens) {
		this.vantagens = vantagens;
	} 
	
	
}
