var app = angular.module('fatec');

app.controller('UserController',['$scope','$htpp','$timeout','$sce','UserService', 
                                 function($scope,$htpp,$timeout,$sce, userService) {
	
	TelaHelper.tela = 'user';
	$scope.user = {};
	$scope.users = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.buildList = _buildList;

	
	function init(){
		$scope.loadUsers();
	}
	
	$scope.loadUsers = function() {
		userService.userSearchAll().then(function (response){
			$scope.buildList(response);
		});
	};

	
	$scope.insert = function() {
		var data = {context : {
			user : $scope.user
		}};
		
		userService.userInsert(data),then(function(response){
			$scope.user = null;
			$scope.loadUsers();
		})
	};
	
	$scope.deleta = function(id) {
		var data = {context : {
			user : {id : id}
		}};
		
		userService.userDelete(data),then(function(response){
			$scope.user = null;
			$scope.loadUsers();
		})
	};
	
	$scope.update = function(id) {
		var data = {context : {
			user : {id : id}
		}};
		
		userService.upate(data), then(function(response){
			$scope.region = response.context.region;
		})
	};
	
	$scope.openModal = function(id, flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.user = null;
			$scope.loadCities();
		}
		jQuery('#modalForm').modal('show');
	};

	$scope.cancelModal = function() {
		$scope.user = {};
		closeModal();
	};

	function _buildList(response) {
		$scope.users = response.context.users;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function closeModal() {
		jQuery('#modalForm').modal('hide');
	};
	
	setTimeout(function() {
		$scope.loadUsers();
	}, 0);
	
	init()
}]);