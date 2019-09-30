appTextilsoft.controller("fornecedorController", function($scope, $http) {

	$scope.listaFornecedor = [];
	$scope.fornecedor = {};
	$scope.idfornecedor = 0;
	$scope.submitted = false;
	
	var url = 'http://localhost:8080/Textilsoft/rest/';	
	
	$scope.listarFornecedores = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'fornecedores/'
		}).then(function(response) {
			
			
			$scope.listaFornecedor = response.data;		
		
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarFornecedor = function(value) {
		$scope.submitted = true;
		var metodo = 'POST';
		if (value){
		$http({
			method : metodo,
			url : url + 'fornecedores/',
			data : $scope.fornecedor
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.fornecedor = {};
		}, function(response) {
			console.log($scope.fornecedor);
			console.log('error do salvar');	
		});
	};
	}
	$scope.deleteFornecedor = function(id) {
	
		
		$http({
			method : 'DELETE',
			url : url + 'fornecedores/' + id
		}).then(function(response) {
			var pos = 0;
			$scope.listaFornecedor.filter(function(i, idx) {
			    if(i.idFornecedor == id)
			    	pos = idx; 				   
			});				
			 $scope.listaFornecedor.splice(pos, 1);
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
	
	var today = new Date();
	var dia = today.getDate();
	var mes = today.getMonth()+1;
	var ano = today.getFullYear();
	
	$scope.dataAtual = dia + "/" + mes + "/" + ano;
});