appTextilsoft.controller("contaPagarDetalheController", function($scope, $http,
		$routeParams) {

	$scope.contaPagarDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/contaspagar/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.contaPagarDetalhe = response.data;
		
			$scope.contaPagarDetalhe.dataVencimento = new Date($scope.contaPagarDetalhe.dataVencimento);		

		
		}, function(response) {
			console.log('error- contaPagarDetalheController');
		
		});
		
		$scope.updateContaPagar = function() {			
			metodo = 'PUT';		

			$scope.contaPagarDetalhe.dataVencimento = new Date($scope.contaPagarDetalhe.dataVencimento);
			
			$http({
				method : metodo,
				url : url,
				data : $scope.contaPagarDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteContaPagar = function(id) {

			$http({
				method : 'DELETE',
				url : url + id
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraID = function(contaPagarDetalhe) {
			$scope.idexcluir = angular.copy(contaPagarDetalhe.idContaPagar);
		}

});