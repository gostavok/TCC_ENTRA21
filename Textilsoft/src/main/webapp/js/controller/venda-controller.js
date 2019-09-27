appTextilsoft.controller("vendaController", function($scope, $http) {

	$scope.listaVenda = [];
	$scope.venda = {};
	$scope.dataEmissao = "";

	$scope.idvenda = 0;
	$scope.pesquisa= "";
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarFornecedor = function(id) {
		$http({
			method : 'GET',
			url : url + 'fornecedores/'+id
		}).then(function(response) {
			$scope.venda.fornecedor = response.data; 

			var today = new Date();
			var dia = today.getDate();
			var mes = today.getMonth()+1;
			var ano = today.getFullYear();
			
			$scope.dataEmissao = dia + "/" + mes + "/" + ano;
						
		}, function(response) {
			console.log('error');
			console.log(response.data);''
			console.log(response.status);
		});
    };
    
    $scope.listarVendaPedido = function(idVenda) {
		$http({
			method : 'GET',
			url : url + 'vendas/pedidos/'+idVenda
		}).then(function(response) {
			console.log(response.data);
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.listarVenda = function() {
		
		$http({
			method : 'GET',
			url : url + 'vendas/'
		}).then(function(response) {
			$scope.listaVenda = response.data;
			console.log(response.data)
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarVenda = function() {

		$http({
			method : 'POST',
			url : url + 'vendas/',
			data : $scope.venda
		}).then(function(response) {		
			alert("efetuado com sucesso")
			console.log($scope.venda.dataEntregaVenda);
			$scope.venda = {};
		}, function(response) {
			console.log('error do salvar');	
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.deleteVenda = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'vendas/' + id
		}).then(function(response) {
			var pos = $scope.listaVenda.indexOf(id);
			$scope.listaVenda.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarVenda = function(venda) {
		$scope.venda = angular.copy(venda);
	}
	
	$scope.procuraVenda = function(venda) {
		$scope.idvenda = angular.copy(venda.idVenda);
	}

	$scope.cancelarAlteracaoVenda = function(venda) {
		$scope.venda = {};
	};
	
	$scope.calculoTotal = function(){
		$scope.venda.valorTotalVenda = $scope.venda.servicoFornecedor.valorServForn * $scope.venda.qtdServico;	
	};

});