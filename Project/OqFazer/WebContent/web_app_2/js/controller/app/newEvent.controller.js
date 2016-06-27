OqFazerController.controller("NewEventController",  function($scope, EventService, LoginService, UserService, CategoryService, RegionService) {
	
	$scope.event = {};
	$scope.user = {};
	$scope.regionSelected = {};
	$scope.categoriesEvent = [];
	$scope.data;
	$scope.insertCategory = _insertCategory;
	$scope.deleteCategory = _deleteCategory;
	
	function init(){
		$scope.loadEvents();
		$scope.user = LoginService.sendUser;
		$scope.data = new Date();
		$scope.event = null;
		$scope.loadCategories();
		$scope.loadRegions();
	}
	
	$scope.insert = function() {
		$scope.event.owner = $scope.user;
		$scope.event.region = $scope.regionSelected;
		$scope.event.categories = angular.copy($scope.categoriesEvent);
		$scope.event.event_date = $scope.data;
		
		var data = {context : {event : $scope.event}};
		
		EventService.insert(data).then(function(response){
			$scope.event = null;
			$("#agregar").datepicker('setDate', "");
			document.location()
		})
	};

	$scope.loadEvents = function() {
		EventService.searchAll().then(function (response){
			buildListEvent(response);
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
	
	$(document).ready(function () {
		$('.datepicker').datepicker({
			format: 'dd/mm/yyyy',                
            language: 'pt-BR'	 
		});
	});
	
	$(".datepicker").blur("click", function() {
		$scope.data = $("#agregar").datepicker("getDate");
	});
	
	function searchUser(id){
		UserService.update(id).then(function(response){
			LoginService.sendUser = response.data.context.user;
		});
	}
	
	function buildListEvent(response) {
		$scope.events = response.data.context.events;
		for(event in $scope.events){
			$scope.events[event].event_date = toDate($scope.events[event].event_date);
		}
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
	
	init();
	
});