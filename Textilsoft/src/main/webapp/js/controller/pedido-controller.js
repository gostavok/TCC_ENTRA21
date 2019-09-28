appTextilsoft.controller("pedidoController", function($scope, $http) {

	$scope.listaPedido = [];
	$scope.pedidoprodutos = [];
	$scope.pedido = {};
	$scope.pedido.cliente = {};
	$scope.idpedido = 0;
	$scope.pedidoproduto = {};
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

	$scope.salvarPedido = function() {
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
			url : url + 'pedidos/' + id			
		}).then(function(response) {
			var pos = $scope.listaPedido.indexOf(id);
			$scope.listaPedido.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
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
		
		$http({
			method : 'DELETE',
			url : url + 'pedidosprodutos/' + produtopedido.pedido.idPedido+'/'+produtopedido.produto.idProduto+'/'
		}).then(function(response) {
			var pos = $scope.pedidoprodutos.indexOf(produtopedido.pedido.idPedido);
			$scope.pedidoprodutos.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
		
		
	};
	
	

});





