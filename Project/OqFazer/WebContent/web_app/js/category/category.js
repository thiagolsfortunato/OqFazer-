var app = angular.module('fatec');

app.controller('CategoryController', ['$scope','$http','$timeout','$sce','CategoryService', 
                                      function($scope,$http,$timeout,$sce, categoryService) {

	TelaHelper.tela = 'category';
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.category = {};
	$scope.category.categoryParent = {};
	$scope.categories = [];
	$scope.test = [];
	$scope.categories.categoriesParent = [];
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
		$scope.category.parentDTO = $scope.category.categorySelected.id
		delete $scope.category.categorySelected;
		var data = {context : {category : $scope.category}};
		
		categoryService.insert(data).then(function(response){
			$scope.category = null;
			$scope.loadCategories();
			$scope.cancelModal();
		})
	};
	
	$scope.update = function(id){
		var data = {context : {category : {id : id}}}

		categoryService.update(data).then(function(response){
			console.log(response.context.category);
			$scope.category = response.context.category;
		});
	}

	$scope.deleta = function(id) {
		var data = {context : {category : {id : id}}};
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
		console.log(response.data.context.categories);
		$scope.categories = response.data.context.categories;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	$scope.searchById = function(id) {
		var data = {context : {category : {id : id}}};
		categoryService.searchById(data).then(function(response){
			$scope.category = response.context.category;
		})		
	}
	
	function closeModal() {
		jQuery('#modalForm').modal('hide');
	}

	setTimeout(function() {
		$scope.loadCategories();		
	}, 0);

}]);