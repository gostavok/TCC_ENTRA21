appTextilsoft.controller("contaReceberController", function($scope, $http,
		$routeParams) {

	$scope.contaReceber = [];
	$scope.contaReceber.Pago = {};
	$scope.contaReceber.Pendente = {};
	$scope.contaReceber.Atrasado = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/contasreceber/';
	
	$scope.listarStatus = function() {		
		$http({
			method : 'GET',
			url : url + 'status/'
		}).then(function(response) {		
			
			$scope.contaReceber = response.data;
			$scope.contaReceber.Pago = $scope.contaReceber[0];
			$scope.contaReceber.Pendente = $scope.contaReceber[1];
			$scope.contaReceber.Atrasado = $scope.contaReceber[2];
			
		}, function(response) {

			console.log(response.data);
			console.log($scope.contaReceber);

		});
	};
		
		

});