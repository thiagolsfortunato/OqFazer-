OqFazerController.controller('CategoryController', function($scope,$http,$timeout,$sce,CategoryService) {

	TelaHelper.tela = 'category';
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.category = {};
	$scope.category.categoryParent = {};
	$scope.categories = [];
	$scope.categories.categoriesParent = [];
	$scope.buildList = _buildList;

	function init(){
		$scope.loadCategories();
	}
	
	$scope.loadCategories = function() {
		CategoryService.searchAll().then(function (response){
			CategoryService.sendCategories = response.data.context.categories;
			$scope.buildList(response);
		});	
	};

	$scope.openModalCategory = function(id, flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.category = {};
		}
		jQuery('#modalFormCategory').modal('show');
	};
		
	$scope.insert = function() {
		var flag = true;
		if($scope.category.categorySelected != null){
			if($scope.category.id != $scope.category.categorySelected.id){
				$scope.category.parentDTO = $scope.category.categorySelected;
				delete $scope.category.categorySelected;
				
				var data = {context : {category : $scope.category}};
				CategoryService.insert(data).then(function(response){
					$scope.category = null;
					$scope.loadCategories();
					$scope.cancelModalCategory();
				})
			}else{
				alert('Select diferent category')
				return 
			}
		}else{
			CategoryService.insert(data).then(function(response){
				$scope.category = null;
				$scope.loadCategories();
				$scope.cancelModalCategory();
			})
		}		
	};
	
	function buildTree(listTree, category){
		if(category.parentDTO == null){
			return listTree;
		}else{
			listTree.push(category.id)
			buildTree(listTree, category.parenDTO);
		}
	}
	
	$scope.update = function(id){
		var data = {context : {category : {id : id}}}
		CategoryService.update(data).then(function(response){
			$scope.category.categorySelected = response.context.category.parentDTO;
			$scope.category = response.context.category;
		});
	}
	
	$scope.deleta = function(id) {
		var data = {context : {category : {id : id}}};
		CategoryService.deleta(data).then(function(response){
			$scope.category = null;
			$scope.loadCategories();
		})
	};

	
	$scope.cancelModalCategory = function() {
		$scope.category = {};
		closeModalCategory();
	};

	function _buildList(response) {
		$scope.categories = response.data.context.categories;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	$scope.searchById = function(id) {
		var data = {context : {category : {id : id}}};
		CategoryService.searchById(data).then(function(response){
			$scope.category = response.context.category;
		})		
	}
	
	function closeModalCategory() {
		jQuery('#modalFormCategory').modal('hide');
	}

	setTimeout(function() {
		$scope.loadCategories();		
	}, 0);

});