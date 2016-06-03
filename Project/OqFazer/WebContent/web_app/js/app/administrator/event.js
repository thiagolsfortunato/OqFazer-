var app = angular.module('fatec');

app.controller('EventController', ['$scope','$http','$timeout','$sce','EventService', 
                                      function($scope,$http,$timeout,$sce, eventService) {

	TelaHelper.tela = 'event';
	$scope.currentPage = 1;
	$scope.itemsPerPage = 10
	$scope.event = {};
	$scope.events = [];
	$scope.buildList = _buildList;
	
	function init(){
		$scope.loadEvents();
	}
	
	$scope.loadEvents = function() {
		eventService.searchAll().then(function (response){
			$scope.buildList(response);
		});	
		
	};

	$scope.insert = function() {
		var data = {context : {event : $scope.event}
		};
		eventService.insert(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
			$scope.cancelModal();
		})
	};
	
	$scope.deleta = function(id) {
		var data = {
			context : {
				event : {id : id}
		}};
		
		eventService.deleta(data).then(function(response){
			$scope.event = null;
			$scope.loadEvents();
		})
		
	}
	
	$scope.openModal = function(id, flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.event = {};
		}
		jQuery('#modalForm').modal('show');
	};

	$scope.cancelModal = function() {
		$scope.event = {};
		closeModal();
	};
	
	$scope.calendar = function() {
		jQuery("#calendario").datepicker({
			showOn: "button",
			buttonImage: "calendario.png",
			buttonImageOnly: true
		});
	}

	function _buildList(response) {
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
	
		
}]);