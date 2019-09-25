appTextilsoft.controller("orcamentoDetalheController", function($scope, $http,
		$routeParams) {

	$scope.orcamentoDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/orcamentos/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.orcamentoDetalhe = response.data;
			
			console.log('success - orcamentoDetalheController');
	

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

});