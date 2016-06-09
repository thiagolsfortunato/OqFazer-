OqFazerController.controller('EventController', function($scope,$http,$timeout,$sce, EventService) {

	var urlPath = "http://localhost:8085/OqFazer/Event!";
	
	TelaHelper.tela = 'event';
	$scope.events = [];
	$scope.event = {};
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		//$scope.event = LoginUser.sendUser.myEvents;
		//$scope.event = LoginUser.sendUser.myParticipation;
		$scope.loadEvents();
	}
	
	$scope.loadEvents = function() {
		EventService.searchAll().then(function (response){
			buildList(response);
		});		
	};

	$scope.openModalEvent = function(id, flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.user = null;
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

	$scope.cancelModalEvent = function() {
		$scope.event = {};
		closeModalEvent();
	};

	function buildList(response) {
		$scope.events = response.data.context.events;
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