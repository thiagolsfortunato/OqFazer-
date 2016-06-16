OqFazerController.controller('MyEventsController', function($scope,$http,$timeout,$sce, EventService, UserService) {
	
	$scope.myEvents = [];
	$scope.event = {};
	
	function init(){
		$scope.loadEvents();
	}
	
	
	$scope.loadEvents = function() {
		$scope.myEevents = UserService.myEvents;
		console.log($scope.myEevents);
		$scope.currentPage = 1;
		$scope.$applyAsync();
	};
});
