var IndexHandler = (function(){
	
	T = {};
	
	T.testar = function() {
		new Ajax.Request('localhost:8080/oqfazer/Test!blah.action', {
			  onSuccess: function(response) {
			    debugger;
			  }
			});
	};
	
	return T;
	
})(); 