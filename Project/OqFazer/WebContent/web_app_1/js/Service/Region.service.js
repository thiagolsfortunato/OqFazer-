var app = angular.module('fatec');

app.factory('RegionService', ['$http',function ($http) {

	var urlPath = "http://localhost:8085/OqFazer/Region!";
	
    return {
        insert : _regionInsert,
        update : _regionUpdate,
        deleta : _regionDelete,
        searchAll : _regionSearchAll,
        searchAllCities : _citiesSearchAllCities
    };
    
    function _regionSearchAll(){
    	var promess;
    	promess = $http.get(urlPath + 'searchAll.action', {
    			   cache : false
				  }).success(function(response) {
					  return promess.data;
				  });
    	return promess;
    }
    
    function _citiesSearchAllCities(){
    	var promess;
    	promess = $http.get(urlPath + 'searchAllCities.action', {
    			   cache : false
				  }).success(function(response) {
					  return promess.data;
				  });
    	return promess;
    }
    
    function _regionInsert(user){
    	var promess;
    	var data = JSON.stringify(user);
    	promess = jQuery.ajax({
				    url: urlPath + 'insert.action',
				    data: data,
				    dataType: 'json',
				    contentType: 'application/json',
				    type: 'POST',
				    async: true,
				    success: function (response) {
				        return promess;
				    }
				});
    	return promess;
    }
    
    function _regionDelete(id){
    	var promess;
    	var data = JSON.stringify(id);
		promess = jQuery.ajax({
				    url: urlPath + 'delete.action',
				    data: data,
				    dataType: 'json',
				    contentType: 'application/json',
				    type: 'POST',
				    async: false,
				    success: function (response) {
				    	return promess;
				    }
				});
		return promess;
    }
    
    function _regionUpdate(id){
    	var promess;
    	var data = JSON.stringify(id);
    	promess = jQuery.ajax({
				    url: urlPath + 'update.action',
				    data: data,
				    dataType: 'json',
				    contentType: 'application/json',
				    type: 'POST',
				    async: false,
				    success: function (response) {
				        return promess;
				    }
				});
    	return promess;
    }
    
}]);