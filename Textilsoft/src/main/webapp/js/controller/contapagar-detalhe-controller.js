appTextilsoft.controller("contaPagarDetalheController", function($scope, $http,
		$routeParams) {

	$scope.contaPagarDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/contaspagar/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.contaPagarDetalhe = response.data;

		}, function(response) {
			console.log('error- contaPagarDetalheController');
		
		});
		
		$scope.updateContaPagar = function() {			
			metodo = 'PUT';		
			
			databrasileira = $scope.contaPagarDetalhe.dataVencimento;
			 split = databrasileira.toString().split('/');
			 novadata = split[2] + "-" +split[1]+"-"+split[0];
			$scope.contaPagarDetalhe.dataVencimento = new Date(novadata);
			
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