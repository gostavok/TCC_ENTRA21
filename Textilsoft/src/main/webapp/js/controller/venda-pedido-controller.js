appTextilsoft.controller("vendaPedidoController", function($scope, $http,
    $routeParams) {

$scope.vendaPedido = {};
$scope.vendaPedido.qtdServico = "";
$scope.vendaPedido.servicoFornecedor = {};
$scope.vendaPedido.valorTotalVendaPedido = "";
$scope.vendaPedido.fornecedor = {};

$scope.listaVenda = [];
$scope.vendapedidos = [];
$scope.venda = {};
$scope.idvenda = 0;
$scope.vendapedido = {};
$scope.vendapedido.venda = {};
$scope.vendapedido.pedido = {};
$scope.pesquisa= "";

var url = 'http://localhost:8080/Textilsoft/rest/vendaspedidos/';

$scope.listarPedido = function(id) {
	$http({
		method : 'GET',
		url : 'http://localhost:8080/Textilsoft/rest/pedidos/'+id
	}).then(function(response) {
		$scope.vendaPedido.pedido = response.data; 
				
	}, function(response) {
		console.log('error');
		console.log(response.data);
		console.log(response.status);
	});
};

$scope.listarVenda = function(id) {
	$http({
		method : 'GET',
		url : 'http://localhost:8080/Textilsoft/rest/vendas/'+id
	}).then(function(response) {
		$scope.vendaPedido.venda = response.data;

	}, function(response) {
		console.log('error');
		console.log(response.data);
		console.log(response.status);
	});
};

    $http.get(url + $routeParams.id).then(function(response) {
        $scope.vendaPedido = response.data;
                
		var today = new Date();
		var dia = today.getDate();
		var mes = today.getMonth()+1;
		var ano = today.getFullYear();
					
        console.log('success - vendaPedidoController');
        console.log(response.data);

    }, function(response) {
        console.log('error- vendaPedidoController');
    
    });   
    
	
	$scope.salvarVendaPedido = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'vendaspedidos/',
			data : $scope.vendapedido
		}).then(function(response) {		

			$http({
				method : 'GET',
				url : url + 'vendaspedidos/' + $scope.vendapedido.venda.idVenda
			}).then(function(response) {					
				$scope.vendapedidos = response.data;	
				var atualValor = $scope.venda.valorTotal;
				var valorPedido = $scope.vendapedido.pedido.valorTotal;
				var novoValor = $scope.formatNumber(atualValor) + $scope.formatNumber(valorPedido);
				$scope.venda.valorTotal = $scope.formatNumber(novoValor);
				$scope.vendapedido.pedido = {};
								
				$http({
					method : 'PUT',
					url : url + 'vendas/',
					data : $scope.venda
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
	
	$scope.deletarvendapedido = function (vendapedido) {
		
		$http({
			method : 'DELETE',
			url : url + vendapedido.venda.idVenda +'/'+ vendapedido.venda.idPedido +'/'
		}).then(function(response) {
			var pos = $scope.vendapedidoss.indexOf(vendapedido.venda.idVenda);
			$scope.vendapedidos.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
		
		
	};

    $scope.procuraVendaPedido = function(vendaPedido) {
        $scope.idvendaPedido = angular.copy(venda.idVendaPedido);
    }

});