window.publication =  angular.module('oqfazer', ['ngAnimate', 'ui.bootstrap','ngRoute','oqFazerController']);

angular.module('oqfazer', ['ui.bootstrap']);

publication.config(function($routeProvider){
	
	var path = '/OqFazer/web_app_2/html/';
	$routeProvider
	  
    .when('/event', {templateUrl: 'game.view.html',controller: 'eventController'})
    
    .when('/category', {templateUrl: 'category.view.html',controller: 'categoryController'})
    
    .when('/user', {templateUrl: 'user.view.html',controller: 'userControllerList'})
    
    .when('/region', {templateUrl: 'region.view.html',controller: 'regionController'})
   
	.when('/home', {templateUrl: path + 'index.html',controller: 'oqFazerController'})
	
	.otherwise({
    redirectTo: '/'
  });	  
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

window.oqFazerController = angular.module('oqFazerController', []);
