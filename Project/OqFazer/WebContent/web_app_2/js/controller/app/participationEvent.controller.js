OqFazerController.controller('ParticipationController', function($scope,$http,$timeout,$sce, LoginService) {
	
	$scope.participationEvents = [];
	$scope.user = {};
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5;
	
	function init(){
		$scope.user = LoginService.sendUser;
		$scope.loadEvents();		
	}	
	
	$scope.loadEvents = function() {
		$scope.participationEvents = $scope.user.participationEvents;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	};
	
	init();
});