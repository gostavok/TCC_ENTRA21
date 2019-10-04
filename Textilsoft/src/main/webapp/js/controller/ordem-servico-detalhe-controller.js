appTextilsoft.controller("ordemServicoDetalheController", function($scope, $http,
    $routeParams) {

$scope.ordemServicoDetalhe = {};
$scope.ordemServicoDetalhe.qtdServico = "";
$scope.ordemServicoDetalhe.servicoFornecedor = {};
$scope.ordemServicoDetalhe.valorTotalOrdemServico = "";
$scope.ordemServicoDetalhe.fornecedor = {};

var url = 'http://localhost:8080/Textilsoft/rest/ordemServicos/';

$scope.listarFornecedor = function(id) {
	$http({
		method : 'GET',
		url : 'http://localhost:8080/Textilsoft/rest/fornecedores/'+id
	}).then(function(response) {
		$scope.ordemServicoDetalhe.fornecedor = response.data; 

		var today = new Date();
		var dia = today.getDate();
		var mes = today.getMonth()+1;
		var ano = today.getFullYear();
		
		$scope.dataEmissao = dia + "/" + mes + "/" + ano;
					
	}, function(response) {
		console.log('error');
		console.log(response.data);''
		console.log(response.status);
	});
};

$scope.listarServicoFornecedor = function(id) {
	$http({
		method : 'GET',
		url : 'http://localhost:8080/Textilsoft/rest/servicosfornecedores/'+id
	}).then(function(response) {
		$scope.ordemServicoDetalhe.servicoFornecedor = response.data;

	}, function(response) {
		console.log('error');
		console.log(response.data);
		console.log(response.status);
	});
};

    $http.get(url + $routeParams.id).then(function(response) {
        $scope.ordemServicoDetalhe = response.data;
        console.log($scope.ordemServicoDetalhe.dataEntregaOrdemServico);
        
		var today = new Date();
		var dia = today.getDate();
		var mes = today.getMonth()+1;
		var ano = today.getFullYear();
				
		$scope.ordemServicoDetalhe.dataEntregaOrdemServico = new Date($scope.ordemServicoDetalhe.dataEntregaOrdemServico);
 		$scope.ordemServicoDetalhe.dataAberturaOrdemServico = new Date($scope.ordemServicoDetalhe.dataAberturaOrdemServico);
		
        console.log('success - ordemServicoDetalheController');
        console.log(response.data);

    }, function(response) {
        console.log('error- ordemServicoDetalheController');
    
    });
    
    $scope.updateOrdemServico = function() {			
                
        $http({
            method : 'PUT',
            url : url,
            data : $scope.ordemServicoDetalhe
        }).then(function(response) {        	
        	alert("salvo");
            history.go(-1);
        }, function(response) {
            console.log('error do salvar');		
        });
    };
    
    $scope.deleteOrdemServico = function(idOrdem) {

        $http({
            method : 'DELETE',
            url : url + idOrdem
        }).then(function(response) {
        
            history.go(-1);
        }, function(response) {
            console.log('error do salvar');		
        });
    };

    $scope.procuraOrdemServico = function(ordemServicoDetalhe) {
        $scope.idordemServico = angular.copy(ordemServicoDetalhe.idOrdem);
    }

	$scope.calculoTotal = function(){
		var total = $scope.ordemServicoDetalhe.servicoFornecedor.valorServForn * $scope.ordemServicoDetalhe.qtdServico;
		$scope.ordemServicoDetalhe.valorTotalOrdemServico = total.toFixed(2);
	};
    
});