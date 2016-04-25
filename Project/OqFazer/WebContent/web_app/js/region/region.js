var app = angular.module('fatec');

app.controller('RegionController', function($scope, $http, $timeout) {

	var urlPath = "http://localhost:8585/oqfazer/Grupo!";
	TelaHelper.tela = 'region';
	$scope.grupos = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.region = {};

	$scope.loadRegions = function() {
		$http.get(urlPath + 'searchAll.action', {
			cache : false
		}).success(function(response) {
	    	buildList(response);
		});
	};

	$scope.insert = function() {
		var data = {contexto : {
			region : $scope.region
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
			region : {id : id}
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
		    	buildList(response);
		    }
		});
	}
	
	$scope.openModal = function(id) {
		if (id) {
			var data = {contexto : {
				region : {id : id}
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
			        $scope.region = response.contexto.region;
			    }
			});
		}
		jQuery('#modalForm').modal('show');
	};

	$scope.cancelModal = function() {
		$scope.region = {};
		closeModal();
	};

	function buildLista(response) {
		$scope.region = response.contexto.regions;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function closeModal() {
		jQuery('#modalForm').modal('hide');
	};
	
	setTimeout(function() {
		$scope.loadRegions();
	}, 0);
	
});