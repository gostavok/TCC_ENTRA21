appTextilsoft.controller("pedidoDetalheController", function($scope, $http, $routeParams) {

	$scope.listaPedido = [];
	$scope.pedidoprodutos = [];
	$scope.pedido = {};
	$scope.pedido.cliente = {};
	$scope.idpedido = 0;
	$scope.pedidoproduto = {};
	$scope.pedido.valorTotal = 0;
	$scope.pedido.qtdProd = 0;
	$scope.pedidoproduto.pedido = {};
	$scope.pedidoproduto.produto = {};
	var url = 'http://localhost:8080/Textilsoft/rest/';
		
	$scope.formatNumber = function(i) {
	    return Math.round(i * 100)/100; 
	}
	
	
	
	$http.get(url +'pedidos/' + $routeParams.id).then(function(response) {
		$scope.pedido = response.data;
		$scope.pedidoproduto.pedido = $scope.pedido;
		
		console.log('success - pedidoDetalheController');


	}, function(response) {
		console.log('error- pedidoDetalheController');
	
	});
	
	
	$http.get(url +'pedidosprodutos/' + $routeParams.id).then(function(response) {
		$scope.pedidoprodutos = response.data;
		
		console.log('success - pedidoDetalheController');


	}, function(response) {
		console.log('error- pedidoDetalheController');
	
	});
	
	$scope.updatePedido = function() {
		
		$http({
			method : 'PUT',
			url : url + 'pedidos/',
			data : $scope.pedido
		}).then(function(response) {
			console.log('Atualizado');
		} , function(response) {
			console.log('Nao atualizou');
		});
	};

	$scope.listarNomeProduto = function(id) {
		$http({
			method : 'GET',
			url : url + 'produtos/'+id
		}).then(function(response) {
			var auxilio = response.data;
			$scope.pedidoproduto.produto.descProduto = auxilio.descProduto;
			$scope.pedidoproduto.produto.valorProduto = auxilio.valorProduto;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	
	$scope.salvarProduto = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'pedidosprodutos/',
			data : $scope.pedidoproduto
		}).then(function(response) {		
			$scope.disabledInput = true;
			
			$http({
				method : 'GET',
				url : url + 'pedidosprodutos/' + $scope.pedidoproduto.pedido.idPedido
			}).then(function(response) {					
				$scope.pedidoprodutos = response.data;
				var atualValor = $scope.pedido.valorTotal;
				var valorProduto = $scope.pedidoproduto.produto.valorProduto;
				var novoValor = $scope.formatNumber(atualValor) + $scope.formatNumber(valorProduto);
				$scope.pedido.valorTotal = $scope.formatNumber(novoValor);
				$scope.pedido.qtdProd = $scope.pedido.qtdProd + 1;
				$scope.pedidoproduto.produto = {};
				
				
				$http({
					method : 'PUT',
					url : url + 'pedidos/',
					data : $scope.pedido
				}).then(function(response) {
					console.log('atualizado');
				}, function(response) {
					console.log('error do salvar');		
				});
				
				
			}, function(response) {
				console.log('error do get');	
			});
		}, function(response) {
			console.log('error do salvar');	
		})
	};
	
	
	$scope.listarNomeCliente = function(id) {
		$http({
			method : 'GET',
			url : url + 'clientes/'+id
		}).then(function(response) {
			$scope.pedido.cliente = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	
	
	
	$scope.deletarprodutopedido = function (produtopedido) {
		
		var pos = 0;
		
		
		$http({
			method : 'DELETE',
			url : url + 'pedidosprodutos/' + produtopedido.pedido.idPedido+'/'+ produtopedido.produto.idProduto+'/'
		}).then(function(response) {
			
			var atualValor = $scope.pedido.valorTotal;
			var valorProduto = produtopedido.produto.valorProduto;
			var novoValor = $scope.formatNumber(atualValor) - $scope.formatNumber(valorProduto);
			$scope.pedido.valorTotal = $scope.formatNumber(novoValor);
			
			
			var pos = 0;			
			$scope.pedidoprodutos.filter(function(i, idx) {
			    if(i.produto.idProduto == produtopedido.produto.idProduto)			    
			    	pos = idx;			   
			});	
			
			$scope.pedidoprodutos.splice(pos,1);
			
			$scope.pedido.qtdProd = $scope.pedido.qtdProd - 1;
			
			
			
			$http({
				method : 'PUT',
				url : url + 'pedidos/',
				data : $scope.pedido
			}).then(function(response) {
				console.log('atualizado');
			}, function(response) {
				console.log('error do salvar');		
			});
			
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
		
		
	};
	
	

});





