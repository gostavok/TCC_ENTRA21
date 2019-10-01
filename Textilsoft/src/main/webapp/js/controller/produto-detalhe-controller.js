appTextilsoft.controller("produtoDetalheController", function($scope, $http,
		$routeParams) {

	$scope.produtoDetalhe = {};
	$scope.listaCorDetalhe = [];
	$scope.listaMaterialDetalhe = [];
	$scope.listaEstampaDetalhe = [];
	
	var url = 'http://localhost:8080/Textilsoft/rest/produtos/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.produtoDetalhe = response.data;
			
			console.log('success - produtoDetalheController');
	

		}, function(response) {
			console.log('error-produtoDetalheController');
		
		});
		
		
		$scope.updateProduto = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.produtoDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteProduto = function(idProduto) {

			$http({
				method : 'DELETE',
				url : url + idProduto
			}).then(function(response) {
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.procuraProduto = function(produto) {
			$scope.idProduto = angular.copy(produto.idProduto);
		}

		$scope.procuraValorCor =  function(id){
			$http({
				method : 'GET',
				url : url + 'cores/'+ id
			}).then(function(response) {
	            $scope.listaCorDetalhe = response.data;
	            console.log($scope.listaCorDetalhe.valorCor);
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
	            $scope.listaMaterialDetalhe = response.data;
	            console.log($scope.listaMaterialDetalhe.valorMaterial);
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
	            $scope.listaEstampaDetalhe = response.data;
	            console.log($scope.listaEstampaDetalhe.valorEstampa);
			}, function(response) {
				console.log('error');
				console.log(response.data);
				console.log(response.status);
			});
		};
		
		
		$scope.calculaTotal =  function(){
			$scope.procuraValorCor($scope.produtoDetalhe.corProduto.idCor);
			$scope.procuraValorMaterial($scope.produtoDetalhe.materialProduto.idMaterial);
			$scope.procuraValorEstampa($scope.produtoDetalhe.estampaProduto.idEstampa);
			
			$scope.produtoDetalhe.valorProduto = $scope.listaCorDetalhe.valorCor + $scope.listaMaterialDetalhe.valorMaterial + $scope.listaEstampaDetalhe.valorEstampa;
			
		}
		
});