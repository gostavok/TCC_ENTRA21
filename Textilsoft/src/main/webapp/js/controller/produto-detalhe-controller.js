appTextilsoft.controller("produtoDetalheController", function($scope, $http,
		$routeParams) {

	$scope.produtoDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/produtos/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.produtoDetalhe = response.data;
			
			console.log('success - produtoDetalheController');
	

		}, function(response) {
			console.log('error-produtoDetalheController');
		
		});
		
		
		$scope.updateProduto = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.produtoDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteProduto = function(idProduto) {

			$http({
				method : 'DELETE',
				url : url + idProduto
			}).then(function(response) {
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.procuraProduto = function(produto) {
			$scope.idProduto = angular.copy(produto.idProduto);
		}

});