window.publication =  angular.module('oqfazer', ['ui.bootstrap','LocalStorageModule','ngRoute','OqFazerController']);

publication.config(function($routeProvider){
	
	var path = '/OqFazer/web_app_2/html/administrator/views/';
	$routeProvider
	  
    .when('/events', {templateUrl: path+'event.view.html',controller: 'EventController'})
    
    .when('/eventList', {templateUrl: path+'eventList.view.html',controller: 'EventController'})
    
    .when('/categories', {templateUrl: path+'category.view.html',controller: 'CategoryController'})
    
    .when('/users', {templateUrl: path+'user.view.html',controller: 'UserController'})
    
    .when('/regions', {templateUrl: path+'region.view.html',controller: 'RegionController'})
   
	.when('/login', {templateUrl: 'login.html',controller: 'LoginController'})

	.when('/administrator', {templateUrl: 'administrator.html',controller: 'AdministratorController'})
	
	.when('/home', {templateUrl: 'index.html',controller: 'OqFazerController'})
	
	
	.otherwise({redirectTo: '/events'});	  
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