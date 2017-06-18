
var pessoal = [];


var tabelaVM = new Vue({
  el: '#lista-folha',
  data: {
	pessoal: [] 
  }, 
  methods: {
	  
	  
	  
  }
});

var pageNavVM = new Vue({
	 
	el: '#page-navigation',
	 
	 data:{
		 
		 totalPaginas: 0,
		 paginaInferior: 1,
		 paginaSuperior: 0,
		 paginaAtiva: 1,
		 tamanhoJanela: 10,
		 itensPorPagina: 15
		 
	 }, computed: {
		 
		 paginasDaJanelaAtual: function(){
			 
			 var janela = [];
			 
			 for (i = this.paginaInferior; i <= this.paginaSuperior; i++)
				 janela.push(i);
			 
			 return janela;
			 
			 
		 }
		 
	 }, methods: {
		 
		 irParaPagina: function(numero){
			 
			 exibirPagina(numero);
			 
			 this.paginaAtiva = numero;
			 
		 },
		 
		 incrementarPagina() {
			 
			 this.paginaAtiva++;
			 
			 if (this.paginaAtiva <= this.paginaSuperior)
				 exibirPagina(this.paginaAtiva);
			 else
				 carregarDados(0, 0, '', '', '', ++pageNavVM.paginaInferior, this.paginaAtiva);
			 
		 },
		 
		 decrementarPagina() {
			 
			 this.paginaAtiva--;
			 
			 if (this.paginaAtiva >= this.paginaInferior)
				 exibirPagina(this.paginaAtiva);
			 else
				 carregarDados(0, 0, '', '', '', --pageNavVM.paginaInferior, this.paginaAtiva);
			 
		 }
	 
	 
		 
	 }
	 
});

function exibirPagina(numero){
	
	var pagina = numero - (pageNavVM.paginaInferior - 1);
	
	var beginIndex = (pagina - 1) * pageNavVM.itensPorPagina;
	
	tabelaVM.pessoal = pessoal.slice(beginIndex, beginIndex + pageNavVM.itensPorPagina);
		
}

function carregarDados(ano, mes, poder, nome, orgao, primeiraPagina, paginaAserExibida){
	
	$('.carregando').css('display', 'block');
	$('tbody').css('color', '#aaa');
	
	$.get('/folha_pessoal/consultar?ano='+ano+'&mes='+mes+'&poder='+poder+'&nome='+nome+'&orgao='+orgao+
			'&itensporpagina='+pageNavVM.itensPorPagina+
			'&primeirapagina='+primeiraPagina+'&quantidadedepaginas='+(pageNavVM.tamanhoJanela + 1), 
			function(data, status){
						
		pessoal = data.pessoal;
		
		pageNavVM.paginaInferior = primeiraPagina;
		
		exibirPagina(paginaAserExibida);
		        
        pageNavVM.totalPaginas = data.quantidadeDePaginas;
        
        if (pageNavVM.totalPaginas  < pageNavVM.tamanhoJanela)
        	pageNavVM.paginaSuperior = pageNavVM.totalPaginas;
        else 
        	pageNavVM.paginaSuperior = (pageNavVM.paginaInferior + pageNavVM.tamanhoJanela) - 1;
        
        $('.carregando').css('display', 'none');
		$('tbody').css('color', '#333');
        
    });
	
	
}


$(document).ready(function(){
		
	carregarDados(0, 0, '', '', '', 1, 1);

});


