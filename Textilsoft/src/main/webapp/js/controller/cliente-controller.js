appTextilsoft.controller("clienteController", function($scope, $http) {

	$scope.listaCliente = [];
	$scope.cliente = {};
	$scope.idexcluir = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarClientes = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'clientes/'
		}).then(function(response) {		
			
			$scope.listaCliente = response.data;	
			
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarCliente = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'clientes/',
			data : $scope.cliente
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.cliente = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteCliente = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'clientes/' + id
		}).then(function(response) {			
			var pos = 0;
			$scope.listaCliente.filter(function(i, idx) {
			    if(i.idCliente == id)
			    	pos = idx; 				   
			});				
			 $scope.listaCliente.splice(pos, 1);			
	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarCliente = function(cliente) {
		$scope.cliente = angular.copy(cliente);
	}
	
	$scope.procuraCliente = function(cliente) {
		$scope.idexcluir = angular.copy(cliente.idCliente);
	}

	$scope.cancelarAlteracaoCliente = function(cliente) {
		$scope.cliente = {};
	};

});