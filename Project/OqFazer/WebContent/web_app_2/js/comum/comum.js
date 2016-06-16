window.publication =  angular.module('oqfazer', ['ui.bootstrap','LocalStorageModule','ngRoute','OqFazerController']);

publication.config(function($routeProvider){
	
	var pathAdministrator = '/OqFazer/web_app_2/html/administrator/';
	var pathApp = '/OqFazer/web_app_2/html/app/';
	$routeProvider
	  
    .when('/events', {templateUrl: pathAdministrator + 'event.view.html', controller: 'EventController'}) //adm
    
    .when('/categories', {templateUrl: pathAdministrator + 'category.view.html', controller: 'CategoryController'}) //adm
    
    .when('/users', {templateUrl: pathAdministrator + 'user.view.html', controller: 'UserController'}) //adm
    
    .when('/regions', {templateUrl: pathAdministrator + 'region.view.html', controller: 'RegionController'}) //adm
    
    .when('/eventList', {templateUrl: pathApp + 'eventList.view.html', controller: 'EventController'}) // all events
    
    .when('/myCount', {templateUrl: pathApp + 'myCount.view.html', controller: 'UserController'}) //desc user
    
    .when('/descriptionEvent', {templateUrl: pathApp + 'descriptionEvent.view.html', controller: 'EventController'}) // desc event
    
    .when('/myEvents', {templateUrl: pathApp + 'myEvents.view.html', controller: 'MyEventsController'}) // set with event by owner
    
    .when('/myParticipations', {templateUrl: pathApp + 'myParticipationEvent.view.html', controller: 'ParticipationController'}) //set with list participation
    
    .when('/newEvent', {templateUrl: pathApp + 'descriptionEvent.view.html', controller: 'EventController'}) // set with event by owner
    
	.when('/login', {templateUrl: 'login.html', controller: 'LoginController'}) //login
	
	.when('/home', {templateUrl: 'index.html', controller: 'OqFazerController'}) //home
	
	.otherwise({redirectTo: '/eventList'});	  
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