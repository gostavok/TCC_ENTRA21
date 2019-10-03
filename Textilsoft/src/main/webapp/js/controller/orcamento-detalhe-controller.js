appTextilsoft.controller("orcamentoDetalheController", function($scope, $http,
		$routeParams) {

	$scope.orcamentoDetalhe = {};
	$scope.listaCor = [];
	$scope.listaMaterial = [];
	$scope.listaEstampa = [];

	
	var url = 'http://localhost:8080/Textilsoft/rest/orcamentos/';

		$http.get(url + $routeParams.id).then(function(response) {
			
			$scope.orcamentoDetalhe = response.data;
			
			console.log('success - orcamentoDetalheController');
			//calculo não coloca ValorTotal no campo na primeira vez			
			$scope.calculaTotal();
			
		}, function(response) {
			console.log('error- orcamentoDetalheController');
		
		});
	
		
		$scope.updateOrcamento = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.orcamentoDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteOrcamento = function(idOrcamento) {

			$http({
				method : 'DELETE',
				url : url + idOrcamento
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraOrcamento = function(orcamento) {
			$scope.idOrcamento = angular.copy(orcamento.idOrcamento);
		}

		
		$scope.calculaTotal = function() {
			$scope.orcamentoDetalhe.valorBase = 30;
			$scope.procuraValorCor($scope.orcamentoDetalhe.corOrcamento.idCor);
			$scope.procuraValorMaterial($scope.orcamentoDetalhe.materialOrcamento.idMaterial);
			$scope.procuraValorEstampa($scope.orcamentoDetalhe.estampaOrcamento.idEstampa);
			var p = ($scope.listaCor.valorCor + $scope.listaMaterial.valorMaterial + $scope.listaEstampa.valorEstampa + $scope.orcamentoDetalhe.valorBase) * $scope.orcamentoDetalhe.quantidade;
			$scope.orcamentoDetalhe.valorOrcamento = ($scope.listaCor.valorCor + $scope.listaMaterial.valorMaterial + $scope.listaEstampa.valorEstampa + $scope.orcamentoDetalhe.valorBase) * $scope.orcamentoDetalhe.quantidade;
			
		} 
		
		$scope.procuraValorCor =  function(id){
			$http({
				method : 'GET',
				url : "http://localhost:8080/Textilsoft/rest/" + 'cores/'+ id
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
				url : "http://localhost:8080/Textilsoft/rest/" + 'materiais/'+ id
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
				url : "http://localhost:8080/Textilsoft/rest/" + 'estampas/'+ id
			}).then(function(response) {
	            $scope.listaEstampa = response.data;
	            console.log($scope.listaEstampa.valorEstampa);
			}, function(response) {
				console.log('error');
				console.log(response.data);
				console.log(response.status);
			});
		};
		
		
		
		
		
		
});