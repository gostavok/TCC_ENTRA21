appTextilsoft.controller("corDetalheController", function($scope, $http,
		$routeParams) {

	$scope.corDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/cores/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.corDetalhe = response.data;
			
			console.log('success - corDetalheController');
	

		}, function(response) {
			console.log('error- corDetalheController');
		
		});
		
		$scope.updateCor = function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.corDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteCor = function(idCor) {

			$http({
				method : 'DELETE',
				url : url + idCor
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraCor = function(corDetalhe) {
			$scope.idCor = angular.copy(corDetalhe.idCor);
		}

});