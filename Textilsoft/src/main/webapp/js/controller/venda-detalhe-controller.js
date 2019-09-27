appTextilsoft.controller("vendaDetalheController", function($scope, $http,
    $routeParams) {

$scope.vendaDetalhe = {};
$scope.vendaDetalhe.qtdServico = "";
$scope.vendaDetalhe.servicoFornecedor = {};
$scope.vendaDetalhe.valorTotalVenda = "";
$scope.vendaDetalhe.fornecedor = {};

var url = 'http://localhost:8080/Textilsoft/rest/vendas/';

$scope.listarFornecedor = function(id) {
	$http({
		method : 'GET',
		url : 'http://localhost:8080/Textilsoft/rest/fornecedores/'+id
	}).then(function(response) {
		$scope.vendaDetalhe.fornecedor = response.data; 

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
		$scope.vendaDetalhe.servicoFornecedor = response.data;

	}, function(response) {
		console.log('error');
		console.log(response.data);
		console.log(response.status);
	});
};

    $http.get(url + $routeParams.id).then(function(response) {
        $scope.vendaDetalhe = response.data;
        console.log($scope.vendaDetalhe.dataEntregaVenda);
        
		var today = new Date();
		var dia = today.getDate();
		var mes = today.getMonth()+1;
		var ano = today.getFullYear();
				
		$scope.vendaDetalhe.dataEntregaVenda = new Date($scope.vendaDetalhe.dataEntregaVenda);
 		$scope.vendaDetalhe.dataAberturaVenda = new Date($scope.vendaDetalhe.dataAberturaVenda);
		
        console.log('success - vendaDetalheController');
        console.log(response.data);

    }, function(response) {
        console.log('error- vendaDetalheController');
    
    });
    
    $scope.updateVenda = function() {			
                
        $http({
            method : 'PUT',
            url : url,
            data : $scope.vendaDetalhe
        }).then(function(response) {        	
        	alert("salvo");
            history.go(-1);
        }, function(response) {
            console.log('error do salvar');		
        });
    };
    
    $scope.deleteVenda = function(idVenda) {

        $http({
            method : 'DELETE',
            url : url + idVenda
        }).then(function(response) {
        
            history.go(-1);
        }, function(response) {
            console.log('error do salvar');		
        });
    };

    $scope.procuraVenda = function(vendaDetalhe) {
        $scope.idvenda = angular.copy(vendaDetalhe.idVenda);
    }

	$scope.calculoTotal = function(){
		$scope.vendaDetalhe.valorTotalVenda = $scope.vendaDetalhe.servicoFornecedor.valorServForn * $scope.vendaDetalhe.qtdServico;	
	};
    
});