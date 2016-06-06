window.publication =  angular.module('oqfazer', ['ui.bootstrap','LocalStorageModule','ngRoute','OqFazerController']);

publication.config(function($routeProvider){
	
	var path = '/OqFazer/web_app_2/html/administrator/views/';
	$routeProvider
	  
    .when('/event', {templateUrl: path+'event.view.html',controller: 'EventController'})
    
    .when('/category', {templateUrl: path+'category.view.html',controller: 'CategoryController'})
    
    .when('/user', {templateUrl: path+'user.view.html',controller: 'UserController'})
    
    .when('/region', {templateUrl: path+'region.view.html',controller: 'RegionController'})
   
	.when('/login', {templateUrl: 'login.html',controller: 'LoginController'})

	.when('/home', {templateUrl: 'index.html',controller: 'OqFazerController'})

	.otherwise({redirectTo: '/'});	  
});

var StorageHelper = (function(){

	var SH = {};

	SH.setItem = function(chave, valor) {
		window.localStorage.setItem(chave, angular.toJson(valor));
	};

	SH.getItem = function(chave, valor) {
		return angular.fromJson(window.localStorage.getItem(chave));
	};

	SH.removeItem = function(chave) {
		window.localStorage.removeItem(chave);
	}

	return SH;

})();

var TelaHelper = (function(){
	
	var TH = {};
	
	TH.tela = '';

	return TH;
})();

jQuery(document).on('mouseup','.btn', function(){
    $(this).blur();
});

window.OqFazerController = angular.module('OqFazerController', []);