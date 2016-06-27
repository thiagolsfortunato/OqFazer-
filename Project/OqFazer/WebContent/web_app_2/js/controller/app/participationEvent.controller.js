OqFazerController.controller('ParticipationController', function($scope,$http,$timeout,$sce, LoginService, EventService) {
	
	var CHAVE_STORAGE = 'user';
	$scope.participationEvents = [];
	$scope.user = {};
	$scope.event = {};
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.user = StorageHelper.getItem(CHAVE_STORAGE);
		$scope.loadEvents();		
	}	
	
	$scope.loadEvents = function() {
		$scope.participationEvents = $scope.user.participationEvents;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	};
	
	$scope.setDescriptionEvent = function(event){
		EventService.setDescriptionEvent(event);
	}
	
	$scope.getDescriptionEvent = function(event){
		$scope.event = EventService.getDescriptionEvent();
	}
	
	init();
});