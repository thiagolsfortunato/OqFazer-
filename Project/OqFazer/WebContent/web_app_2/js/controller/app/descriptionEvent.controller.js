OqFazerController.controller("DescriptionController",  function($scope, EventService) {
		
	$scope.event = {};
	$scope.data;
	function init(){
		loadEvent();
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