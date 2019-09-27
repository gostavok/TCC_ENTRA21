appTextilsoft.controller("compraController", function($scope, $http) {

	$scope.listaCompra = [];
	$scope.compra = {};
	$scope.idexcluir = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarCompras = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'compras/'
		}).then(function(response) {		
			
			$scope.listaCompra = response.data;	
			
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarCompra = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'compras/',
			data : $scope.compra
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.compra = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteCompra = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'compras/' + id
		}).then(function(response) {
			var pos = $scope.listaCompra.indexOf(id);
			$scope.listaCompra.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarCompra = function(compra) {
		$scope.compra = angular.copy(compra);
	}
	
	$scope.procuraCompra = function(compra) {
		$scope.idexcluir = angular.copy(compra.idCompra);
	}

	$scope.cancelarAlteracaoCompra = function(compra) {
		$scope.compra = {};
	};

});