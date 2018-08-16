var instaManagerApp = angular.module("instaManagerApp", []);

instaManagerApp.controller("instaManagerController", function ($scope, $http) {
    $scope.host = window.location;
    console.log("host: " + $scope.host);

    let loginHelper = new HttpHelper($http, "login");

    loginHelper.get("setBaseUrl?url=" + $scope.host);

    $scope.loginUrl = "";
    loginHelper.getDataCallback("getAuthUrl", function (data) {
        $scope.loginUrl = data;
    });

});