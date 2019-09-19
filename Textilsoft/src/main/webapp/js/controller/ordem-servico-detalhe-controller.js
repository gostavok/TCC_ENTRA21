appTextilsoft.controller("ordemServicoDetalheController", function($scope, $http,
    $routeParams) {

$scope.ordemServicoDetalhe = {};

var url = 'http://localhost:8080/Textilsoft/rest/ordems-servico/';

    $http.get(url + $routeParams.id).then(function(response) {
        $scope.ordemServicoDetalhe = response.data;
        
        console.log('success - ordemServicoDetalheController');


    }, function(response) {
        console.log('error- ordemServicoDetalheController');
    
    });
    
    $scope.updateOrdemServico = function() {			
        metodo = 'PUT';		

        $http({
            method : metodo,
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

    $scope.procuraOrdemServico = function(ordemServico) {
        $scope.idordemServico = angular.copy(ordemServico.idOrdem);
    }

});