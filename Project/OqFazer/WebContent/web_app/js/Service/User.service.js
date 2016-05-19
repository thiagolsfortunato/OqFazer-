var app = angular.module('fatec');

app.factory('UserService', ['$http',function ($http) {

	var urlPath = "http://localhost:8085/OqFazer/User!";
	
    return {
        userInsert : _userInsert,
        userUpdate : _userUpdate,
        userDelete : _userDelete,
        userSearchAll : _userSearchAll
    };
    
    function _userSearchAll(){
    	var promess;
    	promess = $http.get(urlPath + 'searchAll.action', {
    			   cache : false
				  }).success(function(response) {
					  return promess.data;
				  });
    	return promess;
    }
    
    function _userInsert(user){
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
    
    function _userDelete(id){
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
    
    function _userUpdate(id){
    	var promess;
    	var data = JSON.stringify(id);
    	console.log(id);
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