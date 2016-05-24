var app = angular.module('fatec');

app.controller('CategoryController', ['$scope','$http','$timeout','$sce','CategoryService', 
                                      function($scope,$http,$timeout,$sce, categoryService) {

	TelaHelper.tela = 'category';
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.category = {};
	$scope.categories = [];
	$scope.buildList = _buildList;

	function init(){
		$scope.loadCategories();
	}
	
	$scope.loadCategories = function() {
		categoryService.searchAll().then(function (response){
			$scope.buildList(response);
		});
	};

	$scope.openModal = function(id, flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.category = {};
		}
		jQuery('#modalForm').modal('show');
	};
		
	$scope.insert = function() {
		var data = {
			context : {
				category : $scope.category
			}
		};

		categoryService.insert(data).then(function(response){
			$scope.category = null;
			$scope.loadCategories();
			$scope.cancelModal();
		})
	};

	$scope.deleta = function(id) {
		var data = {
			context : {
				category : {
					id : id
				}
			}
		};

		categoryService.deleta(data).then(function(response){
			$scope.category = null;
			$scope.loadCategory();
		})
	};

	
	$scope.cancelModal = function() {
		$scope.category = {};
		closeModal();
	};

	function _buildList(response) {
		console.log(response);
		$scope.categories = response.data.context.categories;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}

	function closeModal() {
		jQuery('#modalForm').modal('hide');
	}
	;

	setTimeout(function() {
		$scope.loadCategories();
	}, 0);

}]);