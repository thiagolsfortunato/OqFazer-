publication.factory('ParticipationService', ['$http',function ($http) {

	var urlPath = "http://localhost:8085/OqFazer/Participation!";
	
	return{
		insert : _insert,
		remove : _remove
	}
	
	function _insert(participation){
		var promess;
    	var data = JSON.stringify(participation);
    	promess = jQuery.ajax({
				    url: urlPath + 'insert.action',
				    data: data,
				    dataType: 'json',
				    contentType: 'application/json',
				    type: 'POST',
				    async: true,
				    success: function (response) {
				    	console.log("aqui");
				        return promess;
				    }
				});
    	return promess;
	}
	
	function _remove(participation){
		var promess;
    	var data = JSON.stringify(participation);
    	promess = jQuery.ajax({
				    url: urlPath + 'remove.action',
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