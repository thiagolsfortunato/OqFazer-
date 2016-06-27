OqFazerController.controller("DescriptionController",  function($scope, EventService, LoginService) {
		
	$scope.event = {};
	$scope.data;
	$scope.user = {};
	
	function init(){
		loadEvent();
		$scope.user = LoginService.sendUser;
	}
	
	init();
	
	$(document).ready(function () {
		$('.datepicker').datepicker({
			format: 'dd/mm/yyyy',                
            language: 'pt-BR'	 
		});
	});
	
	function loadEvent(){
		var event = EventService.getDescriptionEvent();
		if(Object.keys(event).length !== 0){		
			$scope.event = event;
			$scope.data = $scope.event.event_date;
			$("#agregar").datepicker('setDate', $scope.data);
		}else{
			document.location.href="/OqFazer/web_app_2/html/index.html";
		}
	}	
})