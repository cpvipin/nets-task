training.service('apiService', function($http, $window) {
	var urlBase = $window.location.origin + '/rest';

	this.getGreeting = function() {
		return $http.get(urlBase + '/greeting');
	};

	this.getData = function() {
		return $http.get(urlBase + '/ssbData');

	}

});