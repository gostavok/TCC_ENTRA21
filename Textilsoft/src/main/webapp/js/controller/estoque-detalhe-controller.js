appTextilsoft.controller("estoqueDetalheController", function($scope, $http,
		$routeParams) {

	$scope.estoqueDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/estoque/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.estoqueDetalhe = response.data;
			
			console.log('success - estoqueDetalheController');
	

		}, function(response) {
			console.log('error- estoqueDetalheController');
		
		});
		
		$scope.updateFornecedor = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.estoqueDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteFornecedor = function(idEstoque) {

			$http({
				method : 'DELETE',
				url : url + idEstoque
			}).then(function(response) {
			
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.procuraFornecedor = function(estoque) {
			$scope.idestoque = angular.copy(estoque.idEstoque);
		}

});