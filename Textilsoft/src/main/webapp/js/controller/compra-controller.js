appTextilsoft.controller("compraController", function($scope, $http) {

	$scope.listaCompra = [];
	$scope.compra = {};
	$scope.fornecedor = {};
	$scope.compra.produtoFornecedor = {};
	$scope.idexcluir = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarCompras = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'compras/'
		}).then(function(response) {		
			console.log("salvo");
			$scope.listaCompra = response.data;	
			
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarCompra = function() {
		var metodo = 'POST';

		
		 databrasileira = $scope.compra.dataVenc;
		 split = databrasileira.toString().split('/');
		 novadata = split[2] + "-" +split[1]+"-"+split[0];
		$scope.compra.dataVenc = new Date(novadata);

		$http({
			method : metodo,
			url : url + 'compras/',
			data : $scope.compra	
		
			
		}).then(function(response) {		
			console.log('salvo')
			alert("efetuado com sucesso")
			$scope.compra = {};
		}, function(response) {
			console.log($scope.compra);
			console.log('error do salvar');	
		});
	};

	$scope.deleteCompra = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'compras/' + id
		}).then(function(response) {
			var pos = 0;
			$scope.listaCompra.filter(function(i, idx) {
			    if(i.idCompra == id)
			    	pos = idx; 				   
			});					
			$scope.listaCompra.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarCompra = function(compra) {
		$scope.compra = angular.copy(compra);
	}
	
	$scope.procuraCompra = function(compra) {
		$scope.idexcluir = angular.copy(compra.idCompra);
	}

	$scope.cancelarAlteracaoCompra = function(compra) {
		$scope.compra = {};
	};

	$scope.procuraFornecedores = function(id) {

		$http({
			method : 'GET',
			url : url + 'produtosfornecedores/'+id
		}).then(function(response) {
		
			$scope.compra.produtoFornecedor = response.data;		

		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
		
		
};
		
	$scope.calculaTotal = function() {
	
		$scope.compra.valorTotal = $scope.compra.qtdCompra * $scope.compra.produtoFornecedor.valorProdForn;
	}
	
});