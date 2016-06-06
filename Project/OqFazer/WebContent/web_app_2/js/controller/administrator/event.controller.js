OqFazerController.controller('EventController', function($scope,$http,$timeout,$sce, eventService) {

	var urlPath = "http://localhost:8085/OqFazer/Event!";
	
	TelaHelper.tela = 'event';
	$scope.events = [];
	$scope.event = {};
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	$scope.buildList = _buildList;
	
	function init(){
		$scope.loadEvents();
	}
	
	$scope.loadEvents = function() {
		eventService.searchAll().then(function (response){
			$scope.buildList(response);
		});		
	};

	$scope.openModal = function(id, flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.user = null;
		}
		jQuery('#modalForm').modal('show');
	};
	
	$scope.insert = function() {
		var data = {context : {
			event : $scope.event
		}};
		
		eventService.insert(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
			$scope.cancelModal();
		})
	};
	
	$scope.deleta = function(id) {
		var data = {context : {
			event : {id : id}
		}};
		
		eventService.deleta(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
		})
	};
	
	
	$scope.update = function(id) {
		var data = {context : {event : {id : id}}};
		
		eventService.update(data).then(function(response){
			$scope.event = response.context.event;
		})
	};

	$scope.cancelModal = function() {
		$scope.event = {};
		closeModal();
	};

	function buildList(response) {
		console.log(response.context.events);
		$scope.events = response.context.events;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function closeModal() {
		jQuery('#modalForm').modal('hide');
	};
	
	setTimeout(function() {
		$scope.loadEvents();
	}, 0);
	
	init();
	
});