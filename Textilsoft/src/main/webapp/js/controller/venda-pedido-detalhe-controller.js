appTextilsoft.controller("vendaPedidoDetalheController", function($scope,
		$http, $routeParams) {

	$scope.listaVenda = [];
	$scope.vendapedidos = [];
	$scope.venda = {};
	$scope.vendapedido = {};
	$scope.vendapedido.venda = {};
	$scope.vendapedido.pedido = {};
	$scope.pesquisa = "";

	var url = 'http://localhost:8080/Textilsoft/rest/vendaspedidos/';

	$scope.listarPedido = function(id) {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/Textilsoft/rest/pedidos/' + id
		}).then(
				function(response) {
					$scope.vendapedido.pedido = response.data;
					$scope.vendapedido.pedido.dataPedido = new Date(
							$scope.vendapedido.pedido.dataPedido);
					console.log($scope.vendapedido.pedido);
					console.log($scope.vendapedido.pedido.statusPedido);
				}, function(response) {
					console.log('error');
					console.log(response.data);
					console.log(response.status);
				});
	};

	$scope.listarVenda = function(id) {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/Textilsoft/rest/vendas/' + id
		}).then(function(response) {
			$scope.vendapedido.venda = response.data;

		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$http.get('http://localhost:8080/Textilsoft/rest/vendas/'+ $routeParams.id).then(function(response) {
				$scope.vendapedido.venda = response.data;
				console.log(response.data);

				console.log('success - vendaPedidoController');

			}, function(response) {
				console.log('error- vendaPedidoController');

			});

	$http.get(
			'http://localhost:8080/Textilsoft/rest/vendaspedidos/'
					+ $routeParams.id).then(function(response) {
		$scope.vendapedidos = response.data;
		console.log(response.data);

		console.log('success - vendaPedidoController');

	}, function(response) {
		console.log('error- vendaPedidoController');

	});

	$scope.formatNumber = function(i) {
		return Math.round(i * 100) / 100;
	}

	$scope.salvarVendaPedido = function() {

		$http({
			method : 'POST',
			url : url,
			data : $scope.vendapedido
		}).then(
				function(response) {

					var atualValor = $scope.vendapedido.venda.valorTotal;
					var valorPedido = $scope.vendapedido.pedido.valorTotalPedido;
					var novoValor = $scope.formatNumber(atualValor)
							+ $scope.formatNumber(valorPedido);
					$scope.vendapedido.venda.valorTotal = $scope
							.formatNumber(novoValor);
					$scope.vendapedido.pedido = {};

					$http({
						method : 'PUT',
						url : url,
						data : $scope.vendapedido.venda
					}).then(function(response) {
						console.log('atualizado');
					}, function(response) {
						console.log('error do PUT');
					});
				}, function(response) {
					console.log('error do POST');
				})
	};

	$scope.deletarvendapedido = function(vendapedido) {
		var pos = 0;
				
		$http({
			method : 'DELETE',
			url : url + vendapedido.venda.idVenda+'/'+ vendapedido.pedido.idPedido+'/'
		}).then(function(response) {
			
			var atualValor = vendapedido.venda.valorTotal;
			var valorPedido = vendapedido.pedido.valorTotalPedido;
			var novoValor = $scope.formatNumber(atualValor) - $scope.formatNumber(valorPedido);
			$scope.vendapedido.venda.valorTotal = $scope.formatNumber(novoValor);
						
			var pos = 0;			
			$scope.vendapedidos.filter(function(i, idx) {
			    if(i.pedido.idPedido == vendapedido.pedido.idPedido)			    
			    	pos = idx;			   
			});	
			
			$scope.vendapedidos.splice(pos,1);
						
			$http({
				method : 'PUT',
				url : url,
				data : $scope.vendapedido.venda
			}).then(function(response) {
				console.log('atualizado');
			}, function(response) {
				console.log('error do PUT');
			});
			
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
		
		
	};

	$scope.procuraVendaPedido = function(vendapedido) {
		$scope.vendapedido = angular.copy(vendapedido);
	}

});