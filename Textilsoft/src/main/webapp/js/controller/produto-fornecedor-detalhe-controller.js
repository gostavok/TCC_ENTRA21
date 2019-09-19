appTextilsoft.controller("produtoFornecedorDetalheController", function($scope, $http,
		$routeParams) {

	$scope.produtoFornecedorDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/produtosfornecedores/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.produtoFornecedorDetalhe = response.data;
			
			console.log('success - produtoFornecedorDetalheController');
	

		}, function(response) {
			console.log('error- produtoFornecedorDetalheController');
		
		});
		
		$scope.updateProdutoFornecedor = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.produtoFornecedorDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteProdutoFornecedor = function(idProdutoFornecedor) {

			$http({
				method : 'DELETE',
				url : url + idProdutoFornecedor
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraProdutoFornecedor = function(produtoFornecedor) {
			$scope.idprodutofornecedor = angular.copy(produtoFornecedor.idProdForn);
		}

});