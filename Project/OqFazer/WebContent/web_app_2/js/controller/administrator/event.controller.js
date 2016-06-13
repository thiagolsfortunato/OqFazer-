OqFazerController.controller('EventController', function($scope,$http,$timeout,$sce, EventService, UserService, CategoryService, RegionService) {

	var urlPath = "http://localhost:8085/OqFazer/Event!";
	
	TelaHelper.tela = 'event';
	$scope.events = [];
	$scope.event = {};
	$scope.users = [];
	$scope.user = {};
	$scope.regions = [];
	$scope.region = {};
	$scope.categories = [];
	$scope.category = {};	
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.loadEvents();
	}
	
	$(document).ready(function () {
		$('.datepicker').datepicker({
			format: 'dd/mm/yyyy',                
		    language: 'pt-BR'	 
		});
	});
		
	function toDate(dateStr) {
		var parts = dateStr.split("-");
		var p2 = parts[2].split("T");
		return p2[0]+"/"+parts[1]+"/"+parts[0];
	}
	
	$scope.loadEvents = function() {
		EventService.searchAll().then(function (response){
			buildListEvent(response);
		});		
	};
	
	$scope.loadUsers = function() {
		UserService.searchAll().then(function (response){
			buildListUser(response);
		});		
	};
	
	$scope.loadCategories = function() {
		CategoryService.searchAll().then(function (response){
			buildListCategory(response);
		});		
	};
	
	$scope.loadRegions = function() {
		RegionService.searchAll().then(function (response){
			buildListRegion(response);
		});		
	};
	
	$scope.openModalEvent = function(id, flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.event = null;
			$scope.loadUsers();
			$scope.loadCategories();
			$scope.loadRegions();
		}
		jQuery('#modalFormEvent').modal('show');
	};
	
	$scope.insert = function() {
		var data = {context : {
			event : $scope.event
		}};
		
		EventService.insert(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
			$scope.cancelModalEvent();
		})
	};
	
	$scope.deleta = function(id) {
		var data = {context : {event : {id : id}}};
		
		EventService.deleta(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
		})
	};
	
	
	$scope.update = function(id) {
		var data = {context : {event : {id : id}}};
		
		EventService.update(data).then(function(response){
			$scope.event = response.context.event;	
		})
	};

	$scope.cancelModalEvent = function() {
		$scope.event = {};
		closeModalEvent();
	};

	function buildListEvent(response) {
		$scope.events = response.data.context.events;
		console.log($scope.events);
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function buildListUser(response) {
		$scope.users = response.data.context.users;
		console.log($scope.users);
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function buildListCategory(response) {
		$scope.categories = response.data.context.categories;
		console.log($scope.categories);
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function buildListRegion(response) {
		$scope.regions = response.data.context.regions;
		console.log($scope.regions);
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function closeModalEvent() {
		jQuery('#modalFormEvent').modal('hide');
	};
	
	setTimeout(function() {
		$scope.loadEvents();
	}, 0);
	
	init();
	
});
