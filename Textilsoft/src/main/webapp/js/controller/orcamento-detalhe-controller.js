appTextilsoft.controller("orcamentoDetalheController", function($scope, $http,
		$routeParams) {

	$scope.orcamentoDetalhe = {};	
	$scope.valorCor = 0;
	$scope.valorMaterial = 0;
	$scope.valorEstampa = 0;

	
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
			var base =  $scope.orcamentoDetalhe.valorBase;			
			var valorCor = $scope.orcamentoDetalhe.corOrcamento.valorCor;			
			var valorMaterial = $scope.orcamentoDetalhe.materialOrcamento.valorMaterial;	
			var valorEstampa = $scope.orcamentoDetalhe.estampaOrcamento.valorEstampa;
			
			base = (base + valorCor + valorMaterial + valorEstampa) * $scope.orcamentoDetalhe.quantidade; 
			
			$scope.orcamentoDetalhe.valorOrcamento = base;
			
		} 
		
		$scope.procuraValorCor =  function(id){
			$http({
				method : 'GET',
				url : "http://localhost:8080/Textilsoft/rest/" + 'cores/'+ id
			}).then(function(response) {
	            $scope.valorCor = response.data;
	          
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
	            $scope.valorMaterial = response.data;
	           
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
	            $scope.valorEstampa = response.data;
	           
			}, function(response) {
				console.log('error');
				console.log(response.data);
				console.log(response.status);
			});
		};
		
		
		
		
		
		
});