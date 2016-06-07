window.publication =  angular.module('oqfazer', ['ui.bootstrap','LocalStorageModule','ngRoute','OqFazerController']);

publication.config(function($routeProvider){
	
	var path = '/OqFazer/web_app_2/html/administrator/views/';
	$routeProvider
	  
    .when('/events', {templateUrl: path+'event.view.html',controller: 'EventController'}) //adm
    
    .when('/categories', {templateUrl: path+'category.view.html',controller: 'CategoryController'}) //adm
    
    .when('/users', {templateUrl: path+'user.view.html',controller: 'UserController'}) //adm
    
    .when('/regions', {templateUrl: path+'region.view.html',controller: 'RegionController'}) //adm
    
    .when('/eventList', {templateUrl: path+'app/eventList.view.html',controller: 'EventController'}) // all events
    
    .when('/myCount', {templateUrl: path+'app/myCount.view.html',controller: 'UserController'}) //desc user
    
    .when('/descriptionEvent', {templateUrl: path+'app/descriptionEvent.view.html',controller: 'EventController'}) // desc event
    
    .when('/myEvents', {templateUrl: path+'app/eventList.view.html',controller: 'UserController'}) // set with event by owner
    
    .when('/myParticipation', {templateUrl: path+'app/eventList.view.html',controller: 'UserController'}) //set with list participation
    
	.when('/login', {templateUrl: 'login.html',controller: 'LoginController'}) //login
	
	.when('/home', {templateUrl: 'index.html',controller: 'OqFazerController'}) //home
	
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