// Para criarmos um controlador precisamos definir a qual módulo ele fará parte.
// Executar 'angular.module('NOME_MODULO')' sem passar um array é forma de fazer GET
// do módulo.
var app = angular.module('fatec');

// Para criarmos um Controlador executamos a função 'controller' a partir do módulo
// Um controller pode possuir depedências, que são outros controllers ou serviços angular
// o padrão de um controller é:
// app.controller('NOME_CONTROLLER', ['$scope', function($scope) {}]);
app.controller('LoginController', ['$scope', '$http', '$timeout', '$sce',
                                   function($scope, $http, $timeout, $sce) {

	// por padrão defininos variáveis contantes no início do arquivo
	var CHAVE_STORAGE = 'user';
	var urlPath = "http://localhost:8085/OqFazer/Login!";

	// O $scope é um objeto público que permite o HTML ter acesso ao Controller,
	// tudo que é declarado no scope pode ser usado na tela.
	// representa o usuário logado
	$scope.user = {};
	// Flag para avisar se existe usuário logado
	$scope.isLogado = false;
	// flag para exibir mensagem de erro na tela
	$scope.showMessageError = false;
	
	// Função que verifica se o código da tela que foi aberta é a mesmo que foi
	// passado como argumento, nesse caso devolve a palavra 'active'
	$scope.isActive = function(screen) {
		return TelaHelper.screen == screen ? 'active' : '';
	};
	
	
	
	// função que realiza o login no sistema
	$scope.doLogin = function() {
		$scope.showMessageError = false;
		// primeiro criamos uma variável data que possui um atributo contexto,
		// este é um objeto que possui um atributo 'usuario' que reebe o usuario
		// que está no scope da controller.
		var data = {context : {
			user : $scope.user
		}};
			
		// JSON é um objeto nativo do JavaScript e serve para converter variaveis
		// de Object para uma String e o contrário também.
		var data1 = JSON.stringify(data);
		console.log(data1);
		// AJAX (Asynchronous JavaScript e XML) é a forma como requisições são feitas
		// em javascript, aqui usamos o ajax do jquery (http://api.jquery.com/jquery.ajax/)
		jQuery.ajax({
			// variável urlPath definida na linha 15
		    url: urlPath + 'login.action',
		    // atributo onde passamos os parâmetros da requisição 
		    data: data1,
		    // definição do tipo dos dados
		    dataType: 'json',
		    // definição do tipo de retorno
		    contentType: 'application/json',
		    // tipo da requisição
		    type: 'POST',
		    // Define se o execução deve esperar o retorno ou não
		    async: false,
		    success: function (response) {
		    	// a partir da variável 'response' é possível acessar
		    	var user = response.context.user
		    	console.log(user);
		    	if (user == null) {
	    			$scope.showMessageError = true;
	    			closeModal();
	    			return;
		    	}
		    	$scope.user = user;
		    	StorageHelper.setItem(CHAVE_STORAGE, user);
		    	$scope.isLogado = true;
		    }
		});
	};
	
	// 
	$scope.getMensageApresentation = function() {
		return $sce.trustAsHtml("Olá, " + $scope.user.name);
	}
	
	$scope.doLogout	 = function() {
		var data = {context : {
			user : $scope.user
		}};
		
		var data1 = JSON.stringify(data);
		jQuery.ajax({
		    url: urlPath + 'logout.action',
		    dataType: 'json',
		    contentType: 'application/json',
		    data: data1,
		    type: 'POST',
		    async: false,
		    success: function (response) {
				StorageHelper.removeItem(CHAVE_STORAGE);
				$scope.user = {};
				$scope.isLogado = false;
		    }
		});
	};
	
	$scope.isLogged = function () {
		var user = StorageHelper.getItem(CHAVE_STORAGE);
		if (user != null) {
			var now = new Date().getTime()
			var initSession = user.startSession;
			if (initSession + 1200000 <= now) {
				$scope.user = {};
				$scope.isLogado = false;
			} else {
				$scope.user = user;
				$scope.isLogado = true;
			}
		} else {
			$scope.user = {};
			$scope.isLogado = false;
		}
		$scope.$applyAsync();
	};
	
	$scope.openModalLogin = function() {
		jQuery('#modalFormLogin').modal('show');
	};

	$scope.cancelModal = function() {
		$scope.user = {};
		closeModal();
		$scope.isLogado = false;
	};
	
	function closeModal() {
		jQuery('#modalFormLogin').modal('hide');
	};
	
	$scope.getMensagemApresentacao = function() {
		return $sce.trustAsHtml("Olá, " + $scope.usuario.nome);
	}
	
	setTimeout(function() {
		$scope.isLogged();
	}, 0);
}]);