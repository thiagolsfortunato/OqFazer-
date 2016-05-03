var app = angular.module('fatec');

app.controller('EventController', function($scope, $http, $timeout) {

	var urlPath = "http://localhost:8085/OqFazer/Event!";
	TelaHelper.tela = 'event';
	$scope.grupos = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.events = {id: "1",
			  		name: "Event 1",
			  		description: "Descricao Event 1",
			  		event_date: "02/05/2016",
			  		local: "sao jose",
			  		region: "vale do paraiba",
			  		owner: "Thiago"};

	$scope.loadEvents = function() {
		$http.get(urlPath + 'searchAll.action', {
			cache : false
		}).success(function(response) {
	    	buildList(response);
		});
	};

	$scope.insert = function() {
		var data = {contexto : {
			event : $scope.event
		}};
		
		var data1 = JSON.stringify(data);
		jQuery.ajax({
		    url: urlPath + 'insert.action',
		    data: data1,
		    dataType: 'json',
		    contentType: 'application/json',
		    type: 'POST',
		    async: true,
		    success: function (response) {
		        $scope.cancelModal();
		    	buildList(response);
		    }
		});
	};
	
	$scope.deleta = function(id) {
		var data = {contexto : {
			event : {id : id}
		}};
		
		var data1 = JSON.stringify(data);
		jQuery.ajax({
		    url: urlPath + 'delete.action',
		    data: data1,
		    dataType: 'json',
		    contentType: 'application/json',
		    type: 'POST',
		    async: false,
		    success: function (response) {
		    	$scope.id = null;
		    	buildLista(response);
		    }
		});
	}
	
	$scope.openModal = function(id) {
		if (id) {
			var data = {contexto : {
				event : {id : id}
			}};

			var data1 = JSON.stringify(data);
			jQuery.ajax({
			    url: urlPath + 'update.action',
			    data: data1,
			    dataType: 'json',
			    contentType: 'application/json',
			    type: 'POST',
			    async: false,
			    success: function (response) {
			        $scope.event = response.context.eventsDTO;
			    }
			});
		}
		jQuery('#modalForm').modal('show');
	};

	$scope.cancelModal = function() {
		$scope.event = {};
		closeModal();
	};

	function buildList(response) {
		$scope.event = response.context.eventsDTO;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function closeModal() {
		jQuery('#modalForm').modal('hide');
	};
	
	setTimeout(function() {
		$scope.loadEvents();
	}, 0);
	
});