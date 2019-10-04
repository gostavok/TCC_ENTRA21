appTextilsoft.controller("compraDetalheController", function($scope, $http,
		$routeParams) {

	$scope.compraDetalhe = {};
	$scope.compraDetalhe.valorTotal = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/compras/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.compraDetalhe = response.data;
			$scope.compraDetalhe.dataVenc = new Date($scope.compraDetalhe.dataVenc);
			console.log('success - compraDetalheController');
	

		}, function(response) {
			console.log('error- compraDetalheController');
		
		});
		
		$scope.updateCompra = function() {			
			metodo = 'PUT';		

			 databrasileira = $scope.compraDetalhe.dataCompra;
			 split = databrasileira.toString().split('/');
			 novadata = split[2] + "-" +split[1]+"-"+split[0];
			$scope.compraDetalhe.dataCompra = new Date(novadata);
			
			 databrasileira = $scope.compraDetalhe.dataVenc;
			 split = databrasileira.toString().split('/');
			 novadata = split[2] + "-" +split[1]+"-"+split[0];
			$scope.compraDetalhe.dataVenc = new Date(novadata);
			
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
		
		$scope.calculaTotal = function() {
			
			var total = $scope.compraDetalhe.qtdCompra * $scope.compraDetalhe.produtoFornecedor.valorProdForn;
			$scope.compraDetalhe.valorTotal = total.toFixed(2);
		}

});