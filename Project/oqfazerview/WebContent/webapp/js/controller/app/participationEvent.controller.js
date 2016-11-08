OqFazerController.controller('ParticipationController', function($scope,$http,$timeout,$sce, EventService, ParticipationService, UserService) {
	
	var CHAVE_STORAGE = 'user';
	$scope.participationEvents = [];
	$scope.participation = {};
	$scope.user = {};
	$scope.event = {};
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.user = StorageHelper.getItem(CHAVE_STORAGE);
		$scope.loadEvents();
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}	
	
	$scope.loadEvents = function() {
		$scope.user = StorageHelper.getItem(CHAVE_STORAGE);
		$scope.participationEvents = $scope.user.participationEvents;
		for(event in $scope.participationEvents){
			$scope.participationEvents[event].event_date = toDate($scope.participationEvents[event].event_date);
		}
		$scope.currentPage = 1;
		$scope.$applyAsync();
	};
	
	$scope.setDescriptionEvent = function(event){
		EventService.setDescriptionEvent(event);
	}
	
	$scope.getDescriptionEvent = function(event){
		$scope.event = EventService.getDescriptionEvent();
	}
	
	$scope.removeParticipation = function(eventId){
		$scope.participation.userId = $scope.user.id;
		$scope.participation.eventId = eventId;
		
		var participation = {context : {participation : $scope.participation}};
		
		ParticipationService.remove(participation).then(function(response){
			searchUser($scope.user.id);
			$scope.loadEvents();
		});
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