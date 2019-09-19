appTextilsoft.controller("estampaController", function($scope, $http) {

	$scope.listaEstampa = [];
	$scope.estampa = {};
	$scope.idEstampa = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarEstampas = function() {
		$http({
			method : 'GET',
			url : url + 'estampas/'
		}).then(function(response) {
            $scope.listaEstampa = response.data;
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
/*
	$scope.salvarEstampas function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'estampas/',
			data : $scope.estampa
		}).then(function(response) {		
			alert("efetuado com sucesso")
		}, function(response) {
			console.log('error do salvar');	
		});
	};
*/
	$scope.deleteEstampa = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'estampas/' + id
		}).then(function(response) {
			var pos = $scope.listaEstampa.indexOf(id);
			$scope.listaEstampa.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarEstampa = function(estampa) {
		$scope.estampa = angular.copy(estampa);
	}
	
	$scope.procuraEstampa = function(estampa) {
		$scope.idEstampa = angular.copy(estampa.idEstampa);
	}

	$scope.cancelarAlteracaoEstampa = function(estampa) {
		$scope.estampa = {};
	};

});