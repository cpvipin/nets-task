training.controller('GreetingCntrl',['$scope','apiService', '$location', '$rootScope', function ($scope, apiService, $location, $rootScope) {
    
	function init(){
		$scope.retVal=null;
	    getGreeting();	
	}
	
	
	function getGreeting() {
		$rootScope.showLoader = true;
		apiService.getGreeting()
            .then(function (retVal) {
            	$rootScope.showLoader = false;
            	if(retVal.data){
            		$scope.retVal = retVal.data;
            	}
            	else{
            		$scope.retVal = 'Please try after some time.'
            	}
            },function (error) {
            	$rootScope.showLoader = false;
                $scope.status = 'Unable to load  data: ' + error.message;
            });
    }
	$scope.onDisplayData = function(){
		$location.path( "/data" );
	}
	
	init();
	
}]);