OqFazerController.controller('RegionController', function($scope,$http,$timeout,$sce,RegionService) {

	TelaHelper.tela = 'region';
	$scope.regions = [];
	$scope.cities = [];
	$scope.citiesRegion = [];
	$scope.currentPage = 1;
	$scope.itemsPerPage = 5
	$scope.region = {};
	$scope.insertCities = _insertCities;
	$scope.deleteCities = _deleteCities;

	
	function _insertCities(city) {
		var found = 0;
		if($scope.citiesRegion == null) $scope.citiesRegion.push(city);
		for (c in $scope.citiesRegion) {
			if(city.name == $scope.citiesRegion[c].name){
				found++;
			}
		}
		if(found == 0){
			$scope.citiesRegion.push(city)
		}
	}
	
	function _deleteCities(index){
		$scope.citiesRegion.splice(index,1);
	}
	
	function init(){
		$scope.loadRegions();
		$scope.loadCities();
	}
	
	$scope.loadRegions = function() {
		RegionService.searchAll().then(function (response){
			
			buildList(response);
		});
	};
	
	$scope.loadCities = function() {
		RegionService.searchAllCities().then(function (response){
			RegionService.sendRegions = response.data.context.regions;
			buildListCities(response);
		});
	};

	$scope.insert = function() {
		$scope.region.cities = angular.copy($scope.citiesRegion);
		var data = {
			context : {
				region : $scope.region
			}
		};
		
		RegionService.insert(data).then(function(response){
			if(response.context.region.erro != null){
				alert(response.context.region.erro);
			}			
			$scope.region = {};
			$scope.citiesRegion = null;
			$scope.loadRegions();
			$scope.cancelModalRegion();
		})
	};

	$scope.deleta = function(id) {
		var data = {
			context : {
				region : {id : id}
			}
		};

		RegionService.deleta(data).then(function(response){
			$scope.id = null;
			$scope.region = [];
			$scope.loadRegions();
		});
	}

	$scope.openModalRegion = function(id,flag) {
		if(flag == "update"){
			$scope.update(id);
		}else{
			$scope.region = {};
			$scope.cities = [];
			$scope.citiesRegion = [];
			$scope.loadCities();
		}
		jQuery('#modalFormRegion').modal('show');
	};

	$scope.update = function(id){
		if (id) {
			var data = {
				context : {
					region : {id : id}
				}
			};
			
			RegionService.update(data).then(function(response){
				if(response.context.region.erro != null){
					alert(response.context.region.erro);
				}	
				
				$scope.citiesRegion = null;
				$scope.region = response.context.region;
				$scope.citiesRegion = angular.copy($scope.region.cities);
				id = null;
			});
		}
	}

	$scope.cancelModalRegion = function() {
		$scope.region = {};
		$scope.citiesRegion = [];
		closeModalRegion();
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

	function closeModalRegion() {
		jQuery('#modalFormRegion').modal('hide');
	}

	setTimeout(function() {
		$scope.loadRegions();
		$scope.loadCities();
	}, 0);
	
});