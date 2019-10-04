appTextilsoft.controller("estampaDetalheController", function($scope, $http,
		$routeParams) {

	$scope.estampaDetalhe = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/estampas/';

		$http.get(url + $routeParams.id).then(function(response) {
			$scope.estampaDetalhe = response.data;
			
			console.log('success - estampaDetalheController');
	

		}, function(response) {
			console.log('error- estampaDetalheController');
		
		});
		
		$scope.updateEstampa= function() {			
			metodo = 'PUT';		

			$http({
				method : metodo,
				url : url,
				data : $scope.estampaDetalhe
			}).then(function(response) {
				alert("salvo");
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		
		$scope.deleteEstampa= function(idEstampa) {

			$http({
				method : 'DELETE',
				url : url + idEstampa
			}).then(function(response) {
				
				history.go(-1);
			}, function(response) {
				console.log('error do salvar');		
			});
		};
		$scope.procuraEstampa= function(estampa) {
			$scope.idEstampa= angular.copy(estampa.idEstampa);
		}

});