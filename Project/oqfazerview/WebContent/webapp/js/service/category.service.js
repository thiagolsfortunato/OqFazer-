publication.factory('CategoryService', ['$http',function ($http) {

	var urlPath = "http://localhost:8085/OqFazer/Category!";
	
    return {
        insert : _categoryInsert,
        update : _categoryUpdate,
        deleta : _categoryDelete,
        searchAll : _categorySearchAll,
        searchById : _searchById
    };
    
    function _categorySearchAll(){
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
				    async: false,
				    success: function (response) {
				        return promess;
				    }
				});
    	return promess;
    }    
    
    function _categoryInsert(category){
    	var promess;
    	var data = JSON.stringify(category);
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
    
    function _categoryDelete(id){
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
    
    function _categoryUpdate(id){
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