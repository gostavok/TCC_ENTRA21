appTextilsoft.controller("fornecedorDetalheController", function($scope, $http,
		$routeParams) {

	$scope.fornecedorDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/fornecedores/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.fornecedorDetalhe = response.data;
			
			console.log('success - fornecedorDetalheController');
	

		}, function(response) {
			console.log('error- fornecedorDetalheController');
		
		});
		
		$scope.updateFornecedor = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.fornecedorDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteFornecedor = function(idFornecedor) {

			$http({
				method : 'DELETE',
				url : url + idFornecedor
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraFornecedor = function(fornecedor) {
			$scope.idfornecedor = angular.copy(fornecedor.idFornecedor);
		}

});