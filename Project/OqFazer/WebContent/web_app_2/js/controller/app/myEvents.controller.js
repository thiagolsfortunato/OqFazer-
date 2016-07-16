OqFazerController.controller('MyEventsController', function($scope,$http,$timeout,$sce, EventService, UserService) {
	
	var CHAVE_STORAGE = 'user';
	$scope.myEvents = [];
	$scope.event = {};
	$scope.user = {}
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.user = StorageHelper.getItem(CHAVE_STORAGE);
		$scope.loadEvents();
	}	
	
	$scope.loadEvents = function() {
		$scope.user = StorageHelper.getItem(CHAVE_STORAGE);
		$scope.myEvents = $scope.user.myEvents;
		for(event in $scope.myEvents){
			$scope.myEvents[event].event_date = toDate($scope.myEvents[event].event_date);
		}
		$scope.currentPage = 1;
		$scope.$applyAsync();
	};
	
	$scope.deleta = function(id){
		var data = {context : {event : {id : id}}};
		
		EventService.deleta(data).then(function(response){
			$scope.event = null;
			searchUser($scope.user.id);
			$scope.loadEvents();
			
		})
	}
	
	$scope.setDescriptionEvent = function(event){
		EventService.setDescriptionEvent(event);
	}
	
	$scope.getDescriptionEvent = function(event){
		$scope.event = EventService.getDescriptionEvent();
	}
	
	function searchUser(id){
		var data = {context : {user : {id : id}}};
		UserService.update(data).then(function(response){
			StorageHelper.setItem(CHAVE_STORAGE, response.context.user);
		});
	}
	
	function toDate(dateStr) {
		var parts = dateStr.split("-");
		var p2 = parts[2].split("T");
		return p2[0]+"/"+parts[1]+"/"+parts[0];
	}
	
	init();
});
