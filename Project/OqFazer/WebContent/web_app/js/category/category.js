var app = angular.module('fatec');

app.controller('CategoryController', function($scope, $http, $timeout) {

	var urlPath = "http://localhost:8085/OqFazer/Category!";
	TelaHelper.tela = 'category';
	$scope.categories = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.category = {};

	$scope.loadCategories = function() {
		$http.get(urlPath + 'searchAll.action', {
			cache : false
		}).success(function(response) {
			buildList(response);
		});
	};

	$scope.insert = function() {
		var data = {
			context : {
				category : $scope.category
			}
		};

		var data1 = JSON.stringify(data);
		jQuery.ajax({
			url : urlPath + 'insert.action',
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,
			success : function(response) {
				$scope.cancelModal();
				buildList(response);
			}
		});
	};

	$scope.deleta = function(id) {
		var data = {
			context : {
				category : {
					id : id
				}
			}
		};

		var data1 = JSON.stringify(data);
		jQuery.ajax({
			url : urlPath + 'delete.action',
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : false,
			success : function(response) {
				$scope.id = null;
				buildList(response);
			}
		});
	}

	$scope.openModal = function(id) {
		if (id) {
			var data = {
				context : {
					category : {
						id : id
					}
				}
			};

			var data1 = JSON.stringify(data);
			jQuery.ajax({
				url : urlPath + 'update.action',
				data : data1,
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : false,
				success : function(response) {
					$scope.category = response.context.category;
				}
			});
		}
		jQuery('#modalForm').modal('show');
	};

	$scope.cancelModal = function() {
		$scope.category = {};
		closeModal();
	};

	function buildList(response) {
		$scope.categories = response.context.categories;
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

});