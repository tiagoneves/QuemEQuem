package br.com.hackfest.quemequem.enums;

public enum Esfera {
	
	ESTADUAL("Estadual"),
	FEDERAL("Federal"),
	MUNICIPAL("Municipal");
	
	public String descricao;
	
	private Esfera(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
