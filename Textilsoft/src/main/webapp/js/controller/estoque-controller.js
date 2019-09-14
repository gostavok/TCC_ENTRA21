appTextilsoft.controller("estoqueController", function($scope, $http) {

	$scope.listaEstoque = [];
	$scope.estoque = {};
	$scope.estoque.produtoFornecedor = {};
	$scope.idestoque = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	
	$scope.listarNomeProduto = function(id) {
		$http({
			method : 'GET',
			url : url + 'produtosfornecedores/'+id
		}).then(function(response) {
			$scope.estoque.produtoFornecedor = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.listarEstoque = function() {
		$http({
			method : 'GET',
			url : url + 'estoque/'
		}).then(function(response) {
			$scope.listaEstoque = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarEstoque = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'estoque/',
			data : $scope.estoque
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.estoque = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteEstoque = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'estoque/' + id
		}).then(function(response) {
			var pos = $scope.listaEstoque.indexOf(id);
			$scope.listaEstoque.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarEstoque = function(estoque) {
		$scope.estoque = angular.copy(estoque);
	}
	
	$scope.procuraEstoque = function(estoque) {
		$scope.idestoque = angular.copy(estoque.idEstoque);
	}

	$scope.cancelarAlteracaoEstoque = function(estoque) {
		$scope.estoque = {};
	};

});