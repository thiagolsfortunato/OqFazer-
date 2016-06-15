publication.factory('LoginService', ['$http',function ($http) {
	
	var CHAVE_STORAGE = 'user';
	var urlPath = "http://localhost:8085/OqFazer/Login!";
	
	return {
        doLogin : _doLogin,
        doLogout : _doLogout,
    };
    
    function _doLogin(user){
    	var promess;
    	var data = JSON.stringify(user);
    	promess = jQuery.ajax({
				    url: urlPath + 'login.action',
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
    
    function _doLogout(user){
    	var promess;
    	var data = JSON.stringify(user);
    	promess = jQuery.ajax({
				    url: urlPath + 'logout.action',
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
}]);
