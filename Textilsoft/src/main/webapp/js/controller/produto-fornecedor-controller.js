appTextilsoft.controller("produtoFornecedorController", function($scope, $http) {

	$scope.listaProdutoFornecedor = [];
	$scope.produtoFornecedor = {};
	$scope.produtoFornecedor.fornecedor = {};
	$scope.idprodutoFornecedor = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	
	
	$scope.listarNomeFornecedor = function(id) {
		$http({
			method : 'GET',
			url : url + 'fornecedores/'+id
		}).then(function(response) {
			$scope.produtoFornecedor.fornecedor = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	
	
	
	
	$scope.listarProdutosFornecedor = function() {
		$http({
			method : 'GET',
			url : url + 'produtosfornecedores/'
		}).then(function(response) {
			
			
			$scope.listaProdutoFornecedor = response.data;
			
	
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarProdutoFornecedor = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'produtosfornecedores/',
			data : $scope.produtoFornecedor
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.produtoFornecedor = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteProdutoFornecedor = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'produtosfornecedores/' + id
		}).then(function(response) {
			var pos = 0;
			$scope.listaProdutoFornecedor.filter(function(i, idx) {
			    if(i.idProdForn == id)
			    	pos = idx; 				   
			});
			$scope.listaProdutoFornecedor.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};


	$scope.procuraProdutoFornecedor = function(produtofornecedor) {
		$scope.idprodutofornecedor = angular.copy(produtofornecedor.idProdForn);
	}
	
	
	
	

});