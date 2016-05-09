var app = angular.module('fatec');

app.controller('UserController', function($scope, $http, $timeout) {

	var urlPath = "http://localhost:8085/OqFazer/User!";
	TelaHelper.tela = 'user';
	$scope.users = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.user = {};

	$scope.loadUsers = function() {
		$http.get(urlPath + 'searchAll.action', {
			cache : false
		}).success(function(response) {
	    	buildList(response);
		});
	};

	$scope.insert = function() {
		var data = {context : {
			user : $scope.user
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
		var data = {context : {
			user : {id : id}
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
			var data = {context : {
				user : {id : id}
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
			        $scope.user = response.context.usersDTO;
			    }
			});
		}
		jQuery('#modalForm').modal('show');
	};

	$scope.cancelModal = function() {
		$scope.user = {};
		closeModal();
	};

	function buildLista(response) {
		$scope.user = response.context.usersDTO;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function closeModal() {
		jQuery('#modalForm').modal('hide');
	};
	
	setTimeout(function() {
		$scope.loadUsers();
	}, 0);
	
});