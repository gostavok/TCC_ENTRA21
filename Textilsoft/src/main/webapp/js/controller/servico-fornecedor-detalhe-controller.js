appTextilsoft.controller("servicoFornecedorDetalheController", function($scope, $http,
		$routeParams) {

	$scope.servicoFornecedorDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/servicosfornecedores/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.servicoFornecedorDetalhe = response.data;
			
			console.log('success - servicoFornecedorDetalheController');
	

		}, function(response) {
			console.log('error- servicoFornecedorDetalheController');
		
		});
		
		$scope.updateServicoFornecedor = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.servicoFornecedorDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteServicoFornecedor = function(idServicoFornecedor) {

			$http({
				method : 'DELETE',
				url : url + idServicoFornecedor
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraServicoFornecedor = function(servicoFornecedor) {
			$scope.idservicofornecedor = angular.copy(servicoFornecedor.idServForn);
		}

});