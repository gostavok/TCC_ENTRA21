appTextilsoft.controller("ordemServicoController", function($scope, $http) {

	$scope.listaOrdemServico = [];
	$scope.ordemServico = {};
    $scope.ordemServico.fornecedor = {};
    $scope.ordemServico.servicoFornecedor = {};
	$scope.idordem = 0;
	$scope.pesquisa= "";
	var url = 'http://localhost:8080/Textilsoft/rest/';

	
	$scope.listarFornecedor = function(id) {
		$http({
			method : 'GET',
			url : url + 'fornecedores/'+id
		}).then(function(response) {
			$scope.ordemServico.fornecedor = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
    };
    
    $scope.listarServicoFornecedor = function(id) {
		$http({
			method : 'GET',
			url : url + 'servicofornecedores/'+id
		}).then(function(response) {
			$scope.ordemServico.servicoFornecedor = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.listarOrdemServico = function() {
		var parametro = "";
		if ($scope.pesquisa.length >= 1){
			parametro = "p/"+ $scope.pesquisa;
		}
	
		
		
		$http({
			method : 'GET',
			url : url + 'ordem-servico/'+parametro
		}).then(function(response) {
			$scope.listaEstoque = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarOrdemServico = function() {
		var metodo = 'POST';
	
		 databrasileira = $scope.ordemServico.dataEntregaOrdemServico;
		 split = databrasileira.toString().split('/');
		 novadata = split[2] + "-" +split[1]+"-"+split[0];
		$scope.ordemServico.dataEntregaOrdemServico = new Date(novadata);
		
		$http({
			method : metodo,
			url : url + 'ordem-servico/',
			data : $scope.ordemServico
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.ordemServico = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteOrdemServico = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'ordem-servico/' + id
		}).then(function(response) {
			var pos = $scope.listaOrdemServico.indexOf(id);
			$scope.listaOrdemServico.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarOrdemServico = function(ordemServico) {
		$scope.ordemServico = angular.copy(ordemServico);
	}
	
	$scope.procuraOrdemServico = function(ordemServico) {
		$scope.idordem = angular.copy(ordemServico.idOrdem);
	}

	$scope.cancelarAlteracaoOrdemServico = function(ordemServico) {
		$scope.ordemServico = {};
	};

});