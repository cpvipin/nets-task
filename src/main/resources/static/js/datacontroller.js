training.controller('DataCtrl', [ '$scope', 'apiService', '$rootScope',
		'$location', function($scope, apiService, $rootScope, $location) {

			function init() {

				$scope.retVal = null;
				getData();
			}

			function getData() {
				$rootScope.showLoader = true;
				apiService.getData().success(function(retVal) {
					$rootScope.showLoader = false;
					$scope.retVal = retVal;
				}).error(function(error) {
					$rootScope.showLoader = false;
					$scope.status = 'Unable to load  data: ' + error.message;
				});
			}

			$scope.onGoBack = function() {
				$location.path("/greeting");
			}

			init();

		} ]);