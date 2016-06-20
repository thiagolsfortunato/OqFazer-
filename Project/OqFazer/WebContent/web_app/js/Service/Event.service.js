var app = angular.module('fatec');

app.factory('EventService', ['$http',function ($http) {

	var urlPath = "http://localhost:8085/OqFazer/Event!";
	
    return {
        insert : _eventInsert,
        update : _eventUpdate,
        deleta : _eventDelete,
        searchAll : _eventSearchAll,
        searchById : _searchById
    };
    
    function _eventSearchAll(){
    	var promess;
    	promess = $http.get(urlPath + 'searchAll.action', {
    			   cache : false
				  }).success(function(response) {
					  return promess.data;
				  });
    	return promess;
    }

     function _searchById(id){
    	var promess;
    	var data = JSON.stringify(id)
    	promess = jQuery.ajax({
    				url: urlPath + 'searchById.action',
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
    
    function _eventInsert(event){
    	var promess;
    	var data = JSON.stringify(event);
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
    
    function _eventDelete(id){
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
    
    function _eventUpdate(id){
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