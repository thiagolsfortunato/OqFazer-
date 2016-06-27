OqFazerController.controller('MyEventsController', function($scope,$http,$timeout,$sce, EventService, UserService, LoginService) {
	
	var CHAVE_STORAGE = 'user';
	$scope.myEvents = [];
	$scope.event = {};
	$scope.user = {}
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.user = StorageHelper.getItem(CHAVE_STORAGE);
		console.log($scope.user);
		$scope.loadEvents();
	}	
	
	$scope.loadEvents = function() {
		$scope.myEvents = $scope.user.myEvents;
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
