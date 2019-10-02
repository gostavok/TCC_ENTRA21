appTextilsoft.controller("contaReceberController", function($scope, $http,
		$routeParams) {

	$scope.contaReceber = [];
	$scope.contaReceber.Pago = {};
	$scope.contaReceber.Pendente = {};
	$scope.contaReceber.Atrasado = {};
	$scope.pago = 0;
	$scope.pendente = 0;
	$scope.atrasado = 0;
	
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
	
	$scope.pegarValorPago = function() {		
		$http({
			method : 'GET',
			url : url + 'pago/'
		}).then(function(response) {		
			
			$scope.pago = response.data;		
			
		}, function(response) {

			console.log(response.data);
	

		});
	};
	$scope.pegarValorPendente = function() {		
		$http({
			method : 'GET',
			url : url + 'pendente/'
		}).then(function(response) {		
			
			$scope.pendente = response.data;
			
			
		}, function(response) {

			console.log(response.data);
	

		});
	};
	$scope.pegarValorAtrasado = function() {		
		$http({
			method : 'GET',
			url : url + 'atrasado/'
		}).then(function(response) {		
			
			$scope.atrasado = response.data;
		
			
		}, function(response) {

			console.log(response.data);
	

		});
	};
		
	$scope.listarPorStatus = function(status) {		
		$http({
			method : 'GET',
			url : url + 'status/' + status
		}).then(function(response) {		
			
			$scope.contaReceber = response.data;
			
		}, function(response) {

			console.log(response.data);
			console.log($scope.contaReceber);

		});
	};
		$scope.chamarMetodos = function(){
			$scope.listarStatus();
			$scope.pegarValorPago();
			$scope.pegarValorPendente();
			$scope.pegarValorAtrasado();
		}	

});