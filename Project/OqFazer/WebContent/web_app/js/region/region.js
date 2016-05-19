var app = angular.module('fatec');

app.controller('RegionController',['$scope','$http','$timeout','$sce','RegionService', 
                                 function($scope,$http,$timeout,$sce, regionService) {

	TelaHelper.tela = 'region';
	$scope.regions = [];
	$scope.cities = [];
	$scope.citiesRegion = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.region = {};
	$scope.insertCities = _insertCities;

	
	function _insertCities(city){
		$scope.citiesRegion.push(city);
	}
	
	function init(){
		$scope.loadRegions();
		$scope.loadCities();
	}
	
	$scope.loadRegions = function() {
		regionService.searchAll().then(function (response){
			buildList(response);
		});
	};
	
	$scope.loadCities = function() {
		regionService.searchAllCities().then(function (response){
			buildListCities(response);
		});
	};

	$scope.insert = function() {
		$scope.cities.push($scope.region);
		var data = {
			context : {
				region : $scope.region
			}
		};
		
		regionService.insert(data).then(function(response){
			$scope.region = null;
			$scope.loadRegions();
			$scope.cancelModal();
		})
	};

	$scope.deleta = function(id) {
		var data = {
			context : {
				region : {id : id}
			}
		};

		regionService.deleta(data).then(function(response){
			$scope.id = null;
			$scope.region = null;
			$scope.loadRegions();
		});
	}

	$scope.openModal = function(id,flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.region = null;
			$scope.cities = null;
			$scope.loadCities();
		}
		jQuery('#modalForm').modal('show');
	};

	$scope.update = function(id){
		if (id) {
			var data = {
				context : {
					region : {id : id}
				}
			};
			
			regionService.update(data).then(function(response){
				$scope.citiesRegion = null;
				$scope.region = response.context.region;
				$scope.citiesRegion = angular.copy($scope.region.cities);
				id = null;
			});
		}
	}

	$scope.cancelModal = function() {
		$scope.region = {};
		closeModal();
	};

	function buildList(response) {
		$scope.regions = response.data.context.regions;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}
	
	function buildListCities(response) {
		$scope.cities = response.data.context.cities;
		$scope.currentPage = 1;
		$scope.$applyAsync();
	}

	function closeModal() {
		jQuery('#modalForm').modal('hide');
	}

	setTimeout(function() {
		$scope.loadRegions();
		$scope.loadCities();
	}, 0);
	
}]);