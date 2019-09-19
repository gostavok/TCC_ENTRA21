appTextilsoft.controller("materialDetalheController", function($scope, $http,
		$routeParams) {

	$scope.materialDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/materiais/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.materialDetalhe = response.data;
			
			console.log('success - materialDetalheController');
	

		}, function(response) {
			console.log('error- materialDetalheController');
		
		});
		
		$scope.updateMaterial= function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.materialDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteMaterial= function(idMaterial) {

			$http({
				method : 'DELETE',
				url : url + idMaterial
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraMaterial= function(material) {
			$scope.idMaterial= angular.copy(material.idMaterial);
		}

});