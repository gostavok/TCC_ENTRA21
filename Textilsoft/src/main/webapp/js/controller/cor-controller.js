appTextilsoft.controller("corController", function($scope, $http) {

	$scope.listaCor = [];
	$scope.cor = {};
	$scope.idCor = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarCores = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'cores/'
		}).then(function(response) {
            $scope.listaCor = response.data;
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarCores = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'cores/',
			data : $scope.cor
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.cor = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteCor = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'cores/' + id
		}).then(function(response) {
			var pos = $scope.listaCor.indexOf(id);
			$scope.listaCor.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarCor = function(cor) {
		$scope.cor = angular.copy(cor);
	}
	
	$scope.procuraCor = function(cor) {
		$scope.idCor = angular.copy(cors.idCor);
	}

	$scope.cancelarAlteracaoCor = function(cor) {
		$scope.cor = {};
	};

});