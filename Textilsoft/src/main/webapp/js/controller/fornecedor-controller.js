appTextilsoft.controller("fornecedorController", function($scope, $http) {

	$scope.listaFornecedor = [];
	$scope.fornecedor = {};
	$scope.idfornecedor = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarFornecedores = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'fornecedores/'
		}).then(function(response) {
			
			
			$scope.listaFornecedor = response.data;
			
			for (var i = 0; i < 1; i++) {
			
				 $scope.listaFornecedor[i].fornecedor.telFornecedor.replace((/(\d{2})(\d{2})(\d{2})/, "($1)$2-$3"));
				   
				}
			
			
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarFornecedor = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'fornecedores/',
			data : $scope.fornecedor
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.fornecedor = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteFornecedor = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'fornecedores/' + id
		}).then(function(response) {
			var pos = $scope.listaFornecedor.indexOf(id);
			$scope.listaFornecedor.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarFornecedor = function(fornecedor) {
		$scope.fornecedor = angular.copy(fornecedor);
	}
	
	$scope.procuraFornecedor = function(fornecedor) {
		$scope.idfornecedor = angular.copy(fornecedor.idFornecedor);
	}

	$scope.cancelarAlteracaoFornecedor = function(fornecedor) {
		$scope.fornecedor = {};
	};

});