var instaManagerApp = angular.module("instaManagerApp", []);

instaManagerApp.controller("instaManagerController", function ($scope, $http) {
    $scope.host = window.location;
    console.log("host: " + $scope.host);

    let loginHelper = new HttpHelper($http, "login");

    $scope.logined = false;
    loginHelper.getDataCallback("logined", function (data) {
        console.log("Logined: " + data);
        $scope.logined = data;

        if ($scope.logined) {
            console.log("load data");
            $scope.getSelf();
            $scope.getSelfMedia();
            $scope.getSelfFollow();
        }
    });

    $scope.loginUrl = "";
    loginHelper.getDataCallback("setBaseUrl?url=" + $scope.host, function () {
        loginHelper.getDataCallback("getAuthUrl", function (data) {
            $scope.loginUrl = data;
        });
    });

    let mediaHelper = new HttpHelper($http, "media");

    $scope.selfInfo = {};
    $scope.getSelf = function () {
        console.log("getSelf");
        mediaHelper.getDataCallback("getSelf", function (data) {
            $scope.selfInfo = data;
        })
    };

    $scope.selfMedia = {};
    $scope.getSelfMedia = function () {
        console.log("getSelfMedia");
        mediaHelper.getDataCallback("getSelfMedia", function (data) {
            $scope.selfMedia = data;
        })
    };
    $scope.selfFollow = {};
    $scope.getSelfFollow = function () {
        console.log("getSelfFollow");
        mediaHelper.getDataCallback("getSelfFollow", function (data) {
            $scope.selfFollow = data;
        })
    };


});