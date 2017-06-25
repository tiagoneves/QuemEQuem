package br.com.hackfest.quemequem.enums;

public enum Mes {
	
	JAN("Janeiro"),
	FEV("Fevereiro"),
	MAR("Março"),
	ABR("Abril"),
	MAI("Maio"),
	JUN("Junho"),
	JUL("Julho"),
	AGO("Agosto"),
	SET("Setembro"),
	OUT("Outubro"),
	NOV("Novembro"),
	DEZ("Dezembro");
	
	public String descricao;
	
	private Mes(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
