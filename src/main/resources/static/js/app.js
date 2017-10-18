var app = angular.module('app', ['ui.grid','ui.grid.pagination','ui.grid.selection']);

app.service('AppService',['$http', function ($http) {
    function search(pageNumber,size,search) {
        pageNumber = pageNumber > 0?pageNumber - 1:0;
        return $http({
        	method: 'POST',
            url: 'api/email/search?page='+pageNumber+'&size='+size,
            data: { query: search }
        });
    };

    function get(id) {
        return $http({
        	method: 'GET',
            url: 'api/email/'+id
        });
    };
    
    return {
    	search: search,
    	get: get
    };

}]);

app.controller('AppController', ['$scope','AppService', 
    function ($scope, AppService) {
	
	$scope.textSearch = "";
	
	$scope.errorMessage = "";
	
	$scope.email = {} 
	
    var paginationOptions = {
        pageNumber: 1,
        pageSize: 10,
        sort: null
    };
 
    success = function (response) {
    	$scope.errorMessage = "";
		$scope.gridOptions.data = response.data.content;
		$scope.gridOptions.totalItems = response.data.totalElements;
	};

	error = function (response) {
	    $scope.errorMessage = response.data.message;
	};

	$scope.search = function () {
		AppService.search(
				paginationOptions.pageNumber,
				paginationOptions.pageSize,
				$scope.textSearch).then(success, error);
	};
        
    $scope.gridOptions = {
        paginationPageSizes: [10],
        paginationPageSize: paginationOptions.pageSize,
        enableColumnMenus:false,
        enableRowSelection: true,
        enableFullRowSelection: true,
        enableScrollbars: false,
        multiSelect: false,
        enableSelectAll: false,
        useExternalPagination: true,
        noUnselect: true,
        useExternalSorting: false,
        enableRowHeaderSelection: false,
        selectionRowHeaderWidth: 30,
        rowHeight: 30,
        columnDefs: [
           { field: 'id', displayName: 'docid' },
           { field: 'sender', displayName: 'sender' }
        ],
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope,function(row){
                AppService.get(row.entity.id).then(function (response) {
                	$scope.errorMessage = "";
                	$scope.email = response.data;
            	}, error);
              });
            gridApi.pagination.on.paginationChanged($scope, 
              function (newPage, pageSize) {
                paginationOptions.pageNumber = newPage;
                paginationOptions.pageSize = pageSize;
                AppService.search(newPage,pageSize,$scope.textSearch).then(success, error);
             });
        }
    };
}]);