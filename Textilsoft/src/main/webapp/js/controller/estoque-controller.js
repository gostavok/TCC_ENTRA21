appTextilsoft.controller("estoqueController", function($scope, $http) {

	$scope.listaEstoque = [];
	$scope.estoque = {};
	$scope.estoque.produtoFornecedor = {};
	$scope.idestoque = 0;
	$scope.pesquisa= "";
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
		var parametro = "";
		if ($scope.pesquisa.length >= 1){
			parametro = "p/"+ $scope.pesquisa;
		}
	
		
		
		$http({
			method : 'GET',
			url : url + 'estoque/'+parametro
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
			var pos = 0;
			$scope.listaEstoque.filter(function(i, idx) {
			    if(i.idEstoque == id)
			    	pos = idx; 				   
			});	
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