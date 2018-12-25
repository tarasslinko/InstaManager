class HttpHelper {

    constructor($http, baseUrl) {
        this.$http = $http;
        this.baseUrl = baseUrl;
    }

    get(url) {
        this.getWithCallback(url, function (response) {
            console.log(response);
        }, function (reason) {
            console.error(reason);
        });
    }

    getData(url, data) {
        this.getDataCallback(url, function (respData) {
            console.log("respData: " + respData);
            data = respData;
            console.log("Data: " + data);
        })
    }

    getDataCallback(url, dataCallback) {
        this.getWithResponseCallback(url, function (response) {
            dataCallback(response.data);
        });
    }

    getWithResponseCallback(url, responseCallback) {
        this.getWithCallback(url, responseCallback, function (reason) {
            console.error(reason);
        });
    }

    getWithCallback(url, responseCallback, errorCallback) {
        this.$http.get(this.baseUrl + "/" + url)
            .then(responseCallback, errorCallback)
    }
}