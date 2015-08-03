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