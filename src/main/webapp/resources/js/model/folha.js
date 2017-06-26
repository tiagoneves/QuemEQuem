
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
			 else {
				 
				 	var ano = $('#select-anos').val();
					var mes = $('#select-meses').val();
					var esfera = $('#select-esferas').val();
					var gestao = $('#select-gestoes').val();
					var nome = nomeVM.nome;
					var orgao = $('#select-orgaos').val();
					
					carregarDados(ano, mes, esfera, gestao, nome, orgao, ++pageNavVM.paginaInferior, this.paginaAtiva);
				 
			 }
			 
		 },
		 
		 decrementarPagina() {
			 
			 this.paginaAtiva--;
			 
			 if (this.paginaAtiva >= this.paginaInferior)
				 exibirPagina(this.paginaAtiva);
			 else {
				 
				 	var ano = $('#select-anos').val();
					var mes = $('#select-meses').val();
					var esfera = $('#select-esferas').val();
					var gestao = $('#select-gestoes').val();
					var nome = nomeVM.nome;
					var orgao = $('#select-orgaos').val();
					
					carregarDados(ano, mes, esfera, gestao, nome, orgao, --pageNavVM.paginaInferior, this.paginaAtiva);
				 
			 }
			 
		 }
	 
	 
		 
	 }
	 
});



var esferasVM = new Vue({
	el: "#select-esferas",
	data:{
		esferas: [],
		esferaSelecionada:''
	}, methods: {
		
		getAnos: function(){
			
			this.esferaSelecionada = $("#select-esferas").val();
			
			 $.get('/folha_pessoal/anos?esfera='+this.esferaSelecionada, function(data, status){
				 anosVM.anos = data;
			 });

			 anosVM.anoSelecionado = '';
			 mesesVM.meses = [];
			 mesesVM.mesSelecionado = '';
			 gestoesVM.gestoes = [];
			 gestoesVM.gestaoSelecionada = '';
			 orgaosVM.orgaos = [];
			 orgaosVM.orgaoSelecionado = '';
				 

		}
		
	}
});

var anosVM = new Vue({
	el: "#select-anos",
	data:{
		anos: [],
		anoSelecionado: ''
	}, methods: {
		
		getMeses: function(){
			
			this.anoSelecionado = $("#select-anos").val();
			
			 $.get('/folha_pessoal/meses?esfera='+esferasVM.esferaSelecionada+'&ano='+this.anoSelecionado, function(data, status){
				 mesesVM.meses = data;
				 mesesVM.mesSelecionado = '';
			 });
			 
			 $.get('/folha_pessoal/gestoes?esfera='+esferasVM.esferaSelecionada+'&ano='+this.anoSelecionado, function(data, status){
				 gestoesVM.gestoes = data;
				 gestoesVM.gestaoSelecionada = '';
			 });
			 
			 if (this.anoSelecionado === ''){
				 mesesVM.meses = [];
				 mesesVM.mesSelecionado = '';
				 gestoesVM.gestoes = [];
				 gestoesVM.gestaoSelecionada = '';
			 }
			 
			 orgaosVM.orgaos = [];
			 orgaosVM.orgaoSelecionado = '';
			 
		}
		
	}
});

var mesesVM = new Vue({
	el: "#select-meses",
	data:{
		meses: [],
		mesSelecionado: ''
	}
});

var gestoesVM = new Vue({
	el: "#select-gestoes",
	data: {
		gestoes: [],
		gestaoSelecionada: ''
	}, methods: {
		
		getOrgaos: function(){
			
			this.gestaoSelecionada = $("#select-gestoes").val();
			
			 $.get('/folha_pessoal/orgaos?esfera='+esferasVM.esferaSelecionada+'&ano='+anosVM.anoSelecionado
					 +'&gestao='+this.gestaoSelecionada, function(data, status){
				 orgaosVM.orgaos = data;
				 orgaosVM.orgaoSelecionado = '';
			 });
			 
			 if (this.gestaoSelecionada === ''){
				 
				 orgaosVM.orgaos = [];
				 orgaosVM.orgaoSelecionado = '';
				 
			 }
			 
		}
		
	}
});

var orgaosVM = new Vue({
	el: "#select-orgaos",
	data:{
		orgaos: [],
		orgaoSelecionado: ''
	}
});

var nomeVM = new Vue({
	el: "#input-nome",
	data:{
		nome: ''
	}
});


var pesquisaVM = new Vue({
	el: "#btn-pesquisar",
	methods:{
		
		pesquisar: function(){
			
			var ano = $('#select-anos').val();
			var mes = $('#select-meses').val();
			var esfera = $('#select-esferas').val();
			var gestao = $('#select-gestoes').val();
			var nome = nomeVM.nome;
			var orgao = $('#select-orgaos').val();
			
			carregarDados(ano, mes, esfera, gestao, nome, orgao, 1, 1);
			
		}
		
	}
});


function exibirPagina(numero){
	
	var pagina = numero - (pageNavVM.paginaInferior - 1);
	
	var beginIndex = (pagina - 1) * pageNavVM.itensPorPagina;
	
	tabelaVM.pessoal = pessoal.slice(beginIndex, beginIndex + pageNavVM.itensPorPagina);
		
}

function carregarDados(ano, mes, esfera, gestao, nome, orgao, primeiraPagina, paginaAserExibida){
	
	$('.carregando').css('display', 'block');
	$('tbody').css('color', '#aaa');
	
	$.get('/folha_pessoal/consultar?ano='+ano+'&mes='+mes+'&esfera='+esfera+'&gestao='+gestao+'&nome='+nome+'&orgao='+orgao+
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
		$('#lista-folha').css('display', 'inline-table');
		$('#page-navigation').css('display', 'block');
        
    });
	
	
	
}


$(document).ready(function(){
			
	 $.get('/folha_pessoal/esferas', function(data, status){
		 esferasVM.esferas = data;
	 });

});


