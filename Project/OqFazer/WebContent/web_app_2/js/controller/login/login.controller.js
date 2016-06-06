OqFazerController.controller('LoginController', function($scope,$http,$timeout,$sce, loginService) {

	var CHAVE_STORAGE = 'user';
	var urlPath = "http://localhost:8085/OqFazer/Login!";

	$scope.user = {};
	$scope.isLogado = false;
	$scope.showMessageError = false;
	
	$scope.isActive = function(screen) {
		return TelaHelper.screen == screen ? 'active' : '';
	};
	
	
	$scope.doLogin = function() {
		$scope.showMessageError = false;
		
		var data = {context : {
			user : $scope.user
		}};
		
		categoryService.doLogin(data).then(function(response){
			var user = response.context.user
	    	console.log(user);
	    	if (user == null) {
    			$scope.showMessageError = true;
    			return;
	    	}
	    	$scope.user = user;
	    	StorageHelper.setItem(CHAVE_STORAGE, user);
	    	$scope.isLogado = true;
	    	closeModal();
		});
	};
	
	$scope.getMensageApresentation = function() {
		return $sce.trustAsHtml("Olá, " + $scope.user.name);
	}
	
	$scope.doLogout	 = function() {
		var data = {context : {
			user : $scope.user
		}};
		
		categoryService.doLogin(data).then(function(response){
			StorageHelper.removeItem(CHAVE_STORAGE);
			$scope.user = {};
			$scope.isLogado = false;
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
});