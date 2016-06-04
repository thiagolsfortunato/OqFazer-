
publication.config(function($routeProvider){
	
	var path = '/OqFazer/web_app_2/html/';
	$routeProvider
	  
    .when('/event', {templateUrl: 'game.view.html',controller: 'EventController'})
    
    .when('/category', {templateUrl: 'category.view.html',controller: 'CategoryController'})
    
    .when('/user', {templateUrl: 'user.view.html',controller: 'UserControllerList'})
    
    .when('/region', {templateUrl: 'region.view.html',controller: 'RegionController'})
   
	.when('/login', {templateUrl: path + 'index.html',controller: 'LoginController'})

	.when('/home', {templateUrl: path + 'index.html',controller: 'OqFazerController'})

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
