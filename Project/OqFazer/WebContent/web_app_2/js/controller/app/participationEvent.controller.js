OqFazerController.controller('ParticipationController', function($scope,$http,$timeout,$sce, EventService, UserService, LoginService) {
	
	$scope.participationEvents = [];
	$scope.event = {};
	$scope.user = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.user = LoginService.sendUser;
		console.log($scope.user);
		$scope.loadEvents();		
	}	
	
	$scope.loadEvents = function() {
		$scope.participationEvents = $scope.user.participationEvents;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	};
	
	init();
});