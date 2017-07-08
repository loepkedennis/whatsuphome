/**
 * 
 */
var serviceURI = "/api/v1/projects/list";

var cmApp = angular.module("cmApp",[]);

cmApp.controller("cmStartViewController",['$scope','$http',function($scope,$http){
	
	$scope.projects = [];
	$scope.searchFilter = '';
	$scope.search = '';
	
	$scope.getAllProjects = function($scope, $http) {
		
		$http.get(serviceURI) 
		     .success(function(data) {
		    	 $scope.projects = data; })
		     .error(function(error) {
		    	 console.log(error); });		
	}
	
	$scope.applySearchFilter = function() {
		$scope.searchFilter = $scope.search;
	}
	
	$scope.getEventclass = function(eventclass){
		if(eventclass == 'Denkmal'){
			return "Denkmal";
		}
		if(eventclass == 'Geschichte_erleben'){
			return "Gemeinschaft erleben";
		}
		if(eventclass == 'Gottesdienst'){
			return "Gottesdienst";
		}
		if(eventclass == 'Einsatz_fuer_das_Leben'){
			return "Einsatz für das Leben";
		}
		return "Alle";
			
	}
	$scope.getStyleclass = function(eventclass){
		if(eventclass == 'Denkmal'){
			return "denkmal";
		}
		if(eventclass == 'Geschichte_erleben'){
			return "gemeinschaft_erleben";
		}
		if(eventclass == 'Gottesdienst'){
			return "gottesdienst";
		}
		if(eventclass == 'Einsatz_fuer_das_Leben'){
			return "einsatz_für_das-leben";
		}
		return "alle";
		
	}
	
	
		
	
	$scope.getAllProjects($scope, $http);
	
}]);


