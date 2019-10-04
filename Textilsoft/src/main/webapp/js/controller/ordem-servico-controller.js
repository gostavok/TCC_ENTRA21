appTextilsoft.controller("ordemServicoController", function($scope, $http) {

	$scope.listaOrdemServico = [];
	$scope.ordemServico = {};
	$scope.dataEmissao = "";
	$scope.ordemServico.qtdServico = "";
    $scope.ordemServico.servicoFornecedor = {};
	$scope.ordemServico.valorTotalOrdemServico = "";
    $scope.ordemServico.fornecedor = {};
    
    $scope.submitted = false;
    
	var today = new Date();
	var dia = today.getDate();
	var mes = today.getMonth()+1;
	var ano = today.getFullYear();
	
	$scope.dataEmissao = dia + "/" + mes + "/" + ano;

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
			console.log(response.data);''
			console.log(response.status);
		});
    };
    
    $scope.listarServicoFornecedor = function(id) {
		$http({
			method : 'GET',
			url : url + 'servicosfornecedores/'+id
		}).then(function(response) {
			$scope.ordemServico.servicoFornecedor = response.data;
			console.log($scope.ordemServico.servicoFornecedor);
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.listarOrdemServico = function() {
		
		$http({
			method : 'GET',
			url : url + 'ordemServicos/'
		}).then(function(response) {
			$scope.listaOrdemServico = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarOrdemServico = function(value) {
		$scope.submitted = true;
		if (value){
		$http({
			method : 'POST',
			url : url + 'ordemServicos/',
			data : $scope.ordemServico
		}).then(function(response) {		
			alert("efetuado com sucesso")
			console.log($scope.ordemServico.dataEntregaOrdemServico);
			$scope.ordemServico = {};
		}, function(response) {
			console.log('error do salvar');	
			console.log(response.data);
			console.log(response.status);
		});
		}
	};

	$scope.deleteOrdemServico = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'ordemServicos/' + id
		}).then(function(response) {
			var pos = 0;
			$scope.listaOrdemServico.filter(function(i, idx) {
			    if(i.idOrdem == id)
			    	pos = idx; 				   
			});
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
	
	$scope.calculoTotal = function(){
		var total = $scope.ordemServico.servicoFornecedor.valorServForn * $scope.ordemServico.qtdServico;	
		$scope.ordemServico.valorTotalOrdemServico = total.toFixed(2);
	};

});