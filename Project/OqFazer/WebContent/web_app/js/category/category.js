var app = angular.module('fatec');

app.controller('CategoryController', ['$scope','$http','$timeout','$sce','CategoryService', 
                                      function($scope,$http,$timeout,$sce, categoryService) {

	TelaHelper.tela = 'category';
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.category = {};
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
		$scope.category.categorySelected = null;
		var data = {context : {category : $scope.category}};
		console.log(data);
		categoryService.insert(data).then(function(response){
			$scope.category = null;
			$scope.loadCategories();
			$scope.cancelModal();
		})
	};

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
		$scope.categories = response.data.context.categories;
		$scope.currentPage = 1;
		$scope.$applyAsync();
		
		for(cat in $scope.categories){
			if($scope.categories[cat].parentDTO != 0){
				_searchById($scope.categories[cat].parentDTO);
			}			
		}		
	}
	
	function _searchById(id){
		var data = {context : {category : {id : id}}};
		categoryService.searchById(data).then(function(response){
			$scope.test.push(response.context.category);
		})
	}
	
	function closeModal() {
		jQuery('#modalForm').modal('hide');
	}

	setTimeout(function() {
		$scope.loadCategories();		
	}, 0);

}]);