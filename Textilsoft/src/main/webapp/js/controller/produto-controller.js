appTextilsoft.controller("produtoController", function($scope, $http) {
    $scope.listaProduto = [];
   $scope.produto = {};
   
   var url = 'http://localhost:8080/Textilsoft/rest/';
   $scope.listarProdutos  = function(){
       var metodo = 'GET';
       $http({
           method : metodo,
            url : url + 'produtos/'
        }).then(function(response) {
            $scope.listaProduto = response.data;
        }, function(response) {
            console.log('error');
            console.log(response.data);
            console.log(response.status);
       });
   };
   $scope.salvarProduto = function() {
        var metodo = 'POST';
    
        $http({
            method : metodo,
            url : url + 'produtos/',
            data : $scope.produto
        }).then(function(response) {        
            alert("efetuado com sucesso")
            $scope.produto = {};
        }, function(response) {
            console.log('error do salvar');
            console.log($scope.produto);
        });
    };
   $scope.deleteProduto = function(id) {
       var metodo = 'DELETE';
        $http({
            method : metodo,
            url : url + 'produtos/' + id
        }).then(function(response) {
            var pos = $scope.listaProduto.indexOf(id);
            $scope.listaProduto.splice(pos,1);    
        }, function(response) {
            console.log('error do salvar');
            console.log(response.data);
            console.log(response.status);
        });
    };
    
    $scope.alterarproduto = function(produto) {
		$scope.produto = angular.copy(produto);
	}
	
	$scope.procuraProduto = function(produto) {
		$scope.idProduto = angular.copy(produto.idProduto);
	}

	$scope.cancelarAlteracaoProduto = function(produto) {
		$scope.produto = {};
	};
    
    
});