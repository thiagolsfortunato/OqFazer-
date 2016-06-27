OqFazerController.controller("DescriptionController",  function($scope, EventService, UserService, LoginService, ParticipationService) {
		
	var CHAVE_STORAGE = 'user';
	$scope.event = {};
	$scope.data;
	$scope.user = {};
	$scope.participation = {};
	
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
	
	$scope.insertParticipation = function(eventId){
		$scope.participation.userId = $scope.user.id;
		$scope.participation.eventId = eventId;
		var participation = {context : {participation : $scope.participation}};
		
		ParticipationService.insert(participation).then(function(response){
			searchUser($scope.user.id);
		});
	}
	
	function searchUser(id){
		var data = {context : {user : {id : id}}};
		UserService.update(data).then(function(response){
			StorageHelper.setItem(CHAVE_STORAGE, response.context.user);
		});
	}
	
})