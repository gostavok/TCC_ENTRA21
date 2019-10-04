appTextilsoft.controller("servicoFornecedorController", function($scope, $http) {

	$scope.listaServicoFornecedor = [];
	$scope.servicoFornecedor = {};
	$scope.servicoFornecedor.fornecedor = {};
	$scope.idservicoFornecedor = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	
	
	$scope.listarNomeFornecedor = function(id) {
		$http({
			method : 'GET',
			url : url + 'fornecedores/'+id
		}).then(function(response) {
			$scope.servicoFornecedor.fornecedor = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	
	
	
	
	$scope.listarServicosFornecedor = function() {
		$http({
			method : 'GET',
			url : url + 'servicosfornecedores/'
		}).then(function(response) {
			
			
			$scope.listaServicoFornecedor = response.data;
			
	
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarServicoFornecedor = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'servicosfornecedores/',
			data : $scope.servicoFornecedor
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.servicoFornecedor = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteServicoFornecedor = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'servicosfornecedores/' + id
		}).then(function(response) {
			var pos = 0;
			$scope.listaServicoFornecedor.filter(function(i, idx) {
			    if(i.idServForn == id)
			    	pos = idx; 				   
			});
			$scope.listaServicoFornecedor.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};


	$scope.procuraServicoFornecedor = function(servicofornecedor) {
		$scope.idservicofornecedor = angular.copy(servicofornecedor.idServForn);
	}
	
	
	
	

});