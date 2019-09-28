appTextilsoft.controller("pedidoDetalheController", function($scope, $http,
		$routeParams) {

	$scope.pedidoDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/pedidos/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.pedidoDetalhe = response.data;
			
			console.log('success - pedidoDetalheController');
	

		}, function(response) {
			console.log('error- pedidoDetalheController');
		
		});
		
		$scope.updatePedido = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.pedidoDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		

});