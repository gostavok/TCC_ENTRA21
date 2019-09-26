appTextilsoft.controller("pedidoController", function($scope, $http) {

	$scope.listaPedido = [];
	$scope.pedido = {};
	$scope.pedido.cliente = {};
	$scope.idpedido = 0;
	$scope.pesquisa= "";
	var url = 'http://localhost:8080/Textilsoft/rest/';

	
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
			alert("efetuado com sucesso")
			$scope.pedido = {};
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

});