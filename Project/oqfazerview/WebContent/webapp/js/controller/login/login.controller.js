OqFazerController.controller('LoginController', function($scope,$http,$timeout,$sce, LoginService) {

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
		
		var data = {context : {user : $scope.user}};
		
		LoginService.doLogin(data).then(function(response){
			var user = response.context.user
	    	if (user == null) {
    			$scope.showMessageError = true;
    			$scope.$applyAsync();
    			return;
	    	}
	    	$scope.user = user;
	    	LoginService.sendUser = $scope.user;
	    	StorageHelper.setItem(CHAVE_STORAGE, user);
	    	$scope.isLogado = true;
	    	closeModalLogin();
	    	$scope.$applyAsync();
		});
	};
	
	$scope.getMensageApresentation = function() {
		return $sce.trustAsHtml("Olá, " + $scope.user.name);
	}
	
	$scope.doLogout	 = function() {
		var data = {context : {
			user : $scope.user
		}};
		
		LoginService.doLogin(data).then(function(response){
			StorageHelper.removeItem(CHAVE_STORAGE);
			$scope.user = {};
			$scope.isLogado = false;
			$scope.$applyAsync();
			document.location.href="/oqfazerview/webapp/html/index.html";

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

	$scope.cancelModalLogin = function() {
		$scope.user = {};
		closeModalLogin();
		$scope.isLogado = false;
	};
	
	function closeModalLogin() {
		jQuery('#modalFormLogin').modal('hide');
	};
	
	setTimeout(function() {
		$scope.isLogged();
	}, 0);
});