'use strict';

var deliveryOptmizationApp = angular.module('DeliveryOptmizationApp', ["ngRoute", "ngResource"]);

deliveryOptmizationApp.config(function ($routeProvider, $locationProvider) {
    $routeProvider
            .when('/addLogisticsNetwork',
                {
                    templateUrl: 'partial/insert_map_form.html'
                })
                .when('/shortestPath',
                {
                    templateUrl: 'partial/shortest_path.html'
                })
            .otherwise(
                {
                    templateUrl: "partial/home.html"
                });
});

deliveryOptmizationApp.controller('InserMapController', function($scope, $http){
    
    $scope.init = function() {
        $scope.newMap = {'logisticsNetwork': []};
    };
    
    $scope.registerMap = function(){
        // validating that there is at least one route in this map
        if ($scope.newMap.logisticsNetwork.length <= 0){
            alert('This map should have at least one source/destiny path.');
            return;
        }
        
        $http.put('rest/addLogisticsNetwork', $scope.newMap).then(function(response) {
            alert('Success: ' + JSON.stringify(response.data));
            
            // if successfully registered the network, clean the object
            $scope.init();
        },
        function(errorResponse){
            alert('Error: ' + JSON.stringify(errorResponse));
        });
    };
    
    $scope.addNewPath = function(){
        if (!$scope.sourcePoint || ! $scope.destinyPoint || !$scope.distance) {
            alert('Source Point Name, Destiny Point Name and Distance fields are required.');        
            return;
        }
        
        if ($scope.distance <= 0){
            alert('The distance must be greater than 0.');
            return;
        }
        
        if ($scope.sourcePoint === $scope.destinyPoint) {
            alert('The source and destiny paths must be different.');
            return;
        }
        
        $scope.newMap.logisticsNetwork.push({
            'sourceName': $scope.sourcePoint,
            'destinyName': $scope.destinyPoint,
            'distance': $scope.distance
        });
        
        $scope.sourcePoint = '';
        $scope.destinyPoint = '';
        $scope.distance = '';
    };
    
    $scope.init();
});

deliveryOptmizationApp.controller('ShortestPathFinderController', function($scope, $http){

    $scope.init = function () {
        $http.get('./rest/getAllMaps')
                .then(function (response) {
                    $scope.mapList = response.data;
                    if ($scope.mapList.length === 0)
                        alert('The webservice returned 0 maps. You need to register a map first.');
                }, function (error) {
                    alert('An error has occurred while trying to get the maps. Error: ' + JSON.stringify(error));
                });
                
        $scope.calculationResponse = null;
    };
    
    // the server returns the entites, and that may cause destiny and source to repeat as only the pair must be unique
    // so in here we remove the repeated values in order no to confuse the user
    $scope.onMapSelect = function () {       
        
        $scope.availableSources = [];
        $scope.availableDestinations = [];
        
        for (var i = 0; i < $scope.selectedMap.logisticsNetwork.length; i++)
            $scope.availableSources.push($scope.selectedMap.logisticsNetwork[i].sourceName);
        
        for (var i = 0; i < $scope.selectedMap.logisticsNetwork.length; i++)
            $scope.availableDestinations.push($scope.selectedMap.logisticsNetwork[i].destinyName);
        
        $scope.availableSources = $scope.availableSources.filter(function (item, pos, self) {
            return self.indexOf(item) == pos;
        });
        
        $scope.availableDestinations = $scope.availableDestinations.filter(function (item, pos, self) {
            return self.indexOf(item) == pos;
        });
    };
    
    $scope.calculateShortestPath = function(){
        $http.get("./rest/getShortestPath", 
                    { 
                        params: { 
                            mapName: $scope.selectedMap.name, 
                            from: $scope.selectedSourcePath, to: $scope.selectedDestinyPath, 
                            autonomy: $scope.autonomy, gasPrice: $scope.gasPrice.replace(',', '.') // try to replace the comma by dot just in case
                        }
                    })
                    .then(function(response) { 
                        $scope.calculationResponse = response.data;
                    }, function (error) {
                    alert('An error has occurred while trying to get the maps. Error: ' + JSON.stringify(error));
                });
    };
    
    $scope.init();
});