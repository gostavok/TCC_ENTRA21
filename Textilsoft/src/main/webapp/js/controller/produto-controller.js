appTextilsoft.controller("produtoController", function($scope, $http) {
    $scope.listaProduto = [];
	$scope.listaCor = {};
	$scope.listaMaterial = {};
	$scope.listaEstampa = {};
   $scope.produto = {};
	$scope.idExcluir = 0;
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
        	$scope.listaCor = [];
        	$scope.listaMaterial = [];
        	$scope.listaEstampa = [];
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
        	var pos = 0;
			$scope.listaProduto.filter(function(i, idx) {
			    if(i.idProduto == id)
			    	pos = idx; 				   
			});
            $scope.listaProduto.splice(pos,1);    
        }, function(response) {
            console.log('error do excluir');
            console.log(response.data);
            console.log(response.status);
        });
    };
    
    $scope.alterarproduto = function(produto) {
		$scope.produto = angular.copy(produto);
	}
	
	$scope.procuraProduto = function(id) {
		$scope.idExcluir = angular.copy(id);
	}

	$scope.cancelarAlteracaoProduto = function(produto) {
		$scope.produto = {};
	};
    
	$scope.procuraValorCor =  function(id){
		$http({
			method : 'GET',
			url : url + 'cores/'+ id
		}).then(function(response) {
            $scope.listaCor = response.data;
            console.log($scope.listaCor.valorCor);
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.procuraValorMaterial =  function(id){
		$http({
			method : 'GET',
			url : url + 'materiais/'+ id
		}).then(function(response) {
            $scope.listaMaterial = response.data;
            console.log($scope.listaMaterial.valorMaterial);
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.procuraValorEstampa =  function(id){
		$http({
			method : 'GET',
			url : url + 'estampas/'+ id
		}).then(function(response) {
            $scope.listaEstampa = response.data;
            console.log($scope.listaEstampa.valorEstampa);
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	
	$scope.calculaTotal =  function(){
		$scope.procuraValorCor($scope.produto.corProduto.idCor);
		$scope.procuraValorMaterial($scope.produto.materialProduto.idMaterial);
		$scope.procuraValorEstampa($scope.produto.estampaProduto.idEstampa);
		
		$scope.produto.valorProduto = $scope.listaCor.valorCor + $scope.listaMaterial.valorMaterial + $scope.listaEstampa.valorEstampa;
		
	}
	
	
	
	
	
});