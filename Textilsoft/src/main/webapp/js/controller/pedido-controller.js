appTextilsoft.controller("pedidoController", function($scope, $http) {

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
	$scope.pesquisa= "";
	var url = 'http://localhost:8080/Textilsoft/rest/';
	
	$scope.disabledInput = false;
	
	

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
				var novoValor = $scope.pedido.valorTotal + $scope.pedidoproduto.produto.valorProduto;
				$scope.pedido.valorTotal = novoValor;
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
	
	$scope.listarPedidos = function() {
		var parametro = "";
		if ($scope.pesquisa.length >= 1){
			parametro = "p/"+ $scope.pesquisa;
		}
	
		
		
		$http({
			method : 'GET',
			url : url + 'pedidos/'+parametro
		}).then(function(response) {
			$scope.listaPedido = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.criarPedido = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'pedidos/',
			data : $scope.pedido
		}).then(function(response) {		
			$scope.disabledInput = true;
			
			$http({
				method : 'GET',
				url : url + 'pedidos/ultimo'
			}).then(function(response) {		
			//	alert("get com sucesso")
				var pedidoaux = response.data
				$scope.pedido.idPedido = pedidoaux.idPedido;
				$scope.pedidoproduto.pedido.idPedido = pedidoaux.idPedido;
				mostrar();
			}, function(response) {
				console.log('error do get');	
			});
		}, function(response) {
			console.log('error do salvar');	
		});
		
		
		
		
		
	};

	$scope.deletePedido = function(id) {
		
		
		$http({
			method : 'DELETE',
			url : url + 'pedidosprodutos/' + id			
		}).then(function(response) {
		
			
			$http({
				method : 'DELETE',
				url : url + 'pedidos/' + id			
			}).then(function(response) {
				var pos = $scope.listaPedido.indexOf(id);
				$scope.listaPedido.splice(pos,1);	
				
			}, function(response) {
				console.log('error do salvar');
				console.log(response.data);
				console.log(response.status);
			});
		});
		
	};

	$scope.alterarPedido = function(pedido) {
		$scope.pedido = angular.copy(pedido);
	}
	
	$scope.procuraPedido = function(pedido) {
		$scope.idpedido = angular.copy(pedido.idPedido);
	}

	$scope.cancelarAlteracaoPedido = function(pedido) {
		$scope.pedido = {};
	};
	
	
	
	function mostrar(){
		
		document.getElementById('produtos').style.display = 'block';
		
	}
	
	
	$scope.deletarprodutopedido = function (produtopedido) {
		
		var pos = 0;
		
		
		$http({
			method : 'DELETE',
			url : url + 'pedidosprodutos/' + produtopedido.pedido.idPedido+'/'+ produtopedido.produto.idProduto+'/'
		}).then(function(response) {
			
			var novoValor =  $scope.pedido.valorTotal - produtopedido.produto.valorProduto;
			$scope.pedido.valorTotal = novoValor;
			$scope.pedido.qtdProd = $scope.pedido.qtdProd -1;
			
			
			var pos = 0;			
			$scope.pedidoprodutos.filter(function(i, idx) {
			    if(i.idProduto == produtopedido.produto.idProduto)			    
			    	pos = idx;			   
			});	
			
			$scope.pedidoprodutos.splice(pos,1);
			
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





