appTextilsoft.controller("compraDetalheController", function($scope, $http,
		$routeParams) {

	$scope.compraDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/compras/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.compraDetalhe = response.data;
			
			console.log('success - compraDetalheController');
	

		}, function(response) {
			console.log('error- compraDetalheController');
		
		});
		
		$scope.updateCompra = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.compraDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteCompra = function(idCompra) {

			$http({
				method : 'DELETE',
				url : url + idCompra
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraCompra = function(compra) {
			$scope.idexcluir = angular.copy(compra.idCompra);
		}

});