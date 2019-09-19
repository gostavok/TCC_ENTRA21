appTextilsoft.controller("clienteDetalheController", function($scope, $http,
		$routeParams) {

	$scope.clienteDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/clientes/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.clienteDetalhe = response.data;
			
			console.log('success - clienteDetalheController');
	

		}, function(response) {
			console.log('error- clienteDetalheController');
		
		});
		
		$scope.updateCliente = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.clienteDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteCliente = function(idCliente) {

			$http({
				method : 'DELETE',
				url : url + idCliente
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraCliente = function(cliente) {
			$scope.idexcluir = angular.copy(cliente.idCliente);
		}

});