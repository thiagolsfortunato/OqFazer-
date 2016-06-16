OqFazerController.controller('EventController', function($scope,$http,$timeout,$sce, EventService, UserService, CategoryService, RegionService) {

	var urlPath = "http://localhost:8085/OqFazer/Event!";
	
	TelaHelper.tela = 'event';
	$scope.data;
	$scope.events = [];
	$scope.categoriesEvent = [];
	$scope.event = {};
	$scope.users = [];
	$scope.user = {};
	$scope.regions = [];
	$scope.region = {};
	$scope.categories = [];
	$scope.category = {};	
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	$scope.insertCategory = _insertCategory;
	$scope.deleteCategory = _deleteCategory;
	
	function init(){
		$scope.loadEvents();
	}
	
	$(document).ready(function () {
		$('.datepicker').datepicker({
			format: 'dd/mm/yyyy',                
            language: 'pt-BR'	 
		});
	});
	
	$(".datepicker").blur("click", function() {
		$scope.data = $("#agregar").datepicker("getDate");
	});
		
	function toDate(dateStr) {
		var parts = dateStr.split("-");
		var p2 = parts[2].split("T");
		return p2[0]+"/"+parts[1]+"/"+parts[0];
	}
	
	function _insertCategory(category) {
		var found = 0;
		if($scope.categoriesEvent == null) $scope.categoriesEvent.push(category);
		for (c in $scope.categoriesEvent) {
			if(category.name == $scope.categoriesEvent[c].name){
				found++;
			}
		}
		if(found == 0){
			$scope.categoriesEvent.push(category)
		}
	}
	
	function _deleteCategory(index){
		$scope.categoriesEvent.splice(index,1);
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
			$scope.data = new Date();
			$scope.event = null;
			$scope.loadUsers();
			$scope.loadCategories();
			$scope.loadRegions();
		}
		jQuery('#modalFormEvent').modal('show');
	};
	
	$scope.insert = function() {
		$scope.event.owner = $scope.userSelected;
		$scope.event.region = $scope.regionSelected;
		$scope.event.categories = angular.copy($scope.categoriesEvent);
		$scope.event.event_date = $scope.data;
		console.log($scope.event);
		var data = {context : {event : $scope.event}};
		
		EventService.insert(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
			$scope.cancelModalEvent();
			$("#agregar").datepicker('setDate', "");
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
			$scope.data = $scope.event.event_date;
			var date = toDate($scope.event.event_date);
			$("#agregar").datepicker('setDate', date);
			
			$scope.categoriesEvent = $scope.event.categories;
			$scope.userSelected = $scope.event.owner;
			$scope.regionSelected = $scope.event.region;
		})
	};

	$scope.cancelModalEvent = function() {
		$scope.event = {};
		closeModalEvent();
	};

	function buildListEvent(response) {
		$scope.events = response.data.context.events;
		console.log($scope.events);
		for(event in $scope.events){
			$scope.events[event].event_date = toDate($scope.events[event].event_date);
		}
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function buildListUser(response) {
		$scope.users = response.data.context.users;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function buildListCategory(response) {
		$scope.categories = response.data.context.categories;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function buildListRegion(response) {
		$scope.regions = response.data.context.regions;
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
