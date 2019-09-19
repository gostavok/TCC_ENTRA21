appTextilsoft.controller("produtoFornecedorController", function($scope, $http) {

	$scope.listaProdutoFornecedor = [];
	$scope.produtoFornecedor = {};
	$scope.idprodutoFornecedor = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

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
			var pos = $scope.listaProdutoFornecedor.indexOf(id);
			$scope.listaProdutoFornecedor.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};



});