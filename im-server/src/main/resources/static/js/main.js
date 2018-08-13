var instaManagerApp = angular.module("instaManagerApp", []);

instaManagerApp.controller("instaManagerController", function ($scope, $http) {
    $scope.host = window.location;
});