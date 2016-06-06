OqFazerController.controller('EventController', function($scope,$http,$timeout,$sce, EventService) {

	var urlPath = "http://localhost:8085/OqFazer/Event!";
	
	TelaHelper.tela = 'event';
	$scope.events = [];
	$scope.event = {};
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.loadEvents();
	}
	
	$scope.loadEvents = function() {
		EventService.searchAll().then(function (response){
			buildList(response);
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
		
		EventService.insert(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
			$scope.cancelModal();
		})
	};
	
	$scope.deleta = function(id) {
		var data = {context : {
			event : {id : id}
		}};
		
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

	$scope.cancelModal = function() {
		$scope.event = {};
		closeModal();
	};

	function buildList(response) {
		console.log(response.data.context.events);
		$scope.events = response.data.context.events;
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