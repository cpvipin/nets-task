'use strict';

var training = angular.module('training', [ 'ngRoute', 'ui.bootstrap' ])
		.config(function($routeProvider) {
			$routeProvider.when('/greeting', {
				controller : 'GreetingCntrl',
				templateUrl : 'js/partials/greeting.html'
			}).when('/data', {
				templateUrl : 'js/partials/data.html',
				controller : 'DataCtrl'
			}).otherwise({
				redirectTo : '/greeting'
			});
		}).run(function($rootScope) {
			$rootScope.showLoader = false;
		});
