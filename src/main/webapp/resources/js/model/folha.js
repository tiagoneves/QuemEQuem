
var itensPorPagina = 15;
var tamanhoJanela = 10;


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
		 paginaSuperior: tamanhoJanela,
		 paginaAtiva: 1 
		 
	 }, computed: {
		 
		 paginasDaJanelaAtual: function(){
			 
			 var janela = [];
			 
			 for (i = this.paginaInferior; i <= this.paginaSuperior; i++)
				 janela.push(i);
			 
			 return janela;
			 
			 
		 }
		 
	 }, methods: {
		 
		 irParaPagina: function(numero){
			 
			 $.get('/folha_pessoal/pagina?ano=0&mes=0&poder=&nome=&orgao=&pagina='+numero+'&qtditens='+itensPorPagina, 
						function(data, status){
					
			        tabelaVM.pessoal = data;
			        
			        this.paginaAtiva = numero;
			   
			        
			    });
			 
		 }
		 
	 }
	 
});



/*function quantidadeJanelas(quantidadePaginas, paginasPorJanela){
	
	
	var janelas = quantidadePaginas / paginasPorJanela;
	
	var resto = quantidadePaginas % paginasPorJanela;
	
	if (resto > 0 )
		janelas++;
	
	return janelas;
	
	
}

function obterJanela(numero, quantidadeJanelas, paginasPorJanela, quantidadePaginas) {
	
	var janela = [(numero - 1) * paginasPorJanela + 1, numero * paginasPorJanela];
	
	
	if (janela[1] > quantidadePaginas)
		janela[1] = quantidadePaginas;
	
	return janela;
	
}*/



$(document).ready(function(){
		
	$.get('/folha_pessoal/consultar?ano=0&mes=0&poder=&nome=&orgao=&pagina=1&qtditens='+itensPorPagina, 
			function(data, status){
		
        tabelaVM.pessoal = data.pessoal;
        
        pageNavVM.totalPaginas = data.quantidadeDePaginas;
        
        if (pageNavVM.totalPaginas  < tamanhoJanela)
        	pageNavVM.paginaSuperior = pageNavVM.totalPaginas;
        
    });
	
	 
});


