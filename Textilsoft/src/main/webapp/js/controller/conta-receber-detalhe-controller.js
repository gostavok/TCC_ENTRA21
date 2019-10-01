appTextilsoft.controller("contaPagarDetalheController", function($scope, $http,
		$routeParams) {

	$scope.contaReceberDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/contasreceber/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.contaReceberDetalhe = response.data;
		
			$scope.contaReceberDetalhe.dataVencimento = new Date($scope.contaReceberDetalhe.dataVencimento);		

		
		}, function(response) {
			console.log('error- contaReceberDetalheController');
		
		});
		
		$scope.updateContaReceber = function() {			
			metodo = 'PUT';		

			
			
			$http({
				method : metodo,
				url : url,
				data : $scope.contaReceberDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteContaReceber = function(id) {

			$http({
				method : 'DELETE',
				url : url + id
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraID = function(contaReceberDetalhe) {
			$scope.idexcluir = angular.copy(contaReceberDetalhe.idContaReceber);
		}

});