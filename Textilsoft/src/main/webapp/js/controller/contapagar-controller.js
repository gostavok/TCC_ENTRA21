appTextilsoft.controller("contaPagarController", function($scope, $http) {

	$scope.listaContaPagar = [];
	$scope.contaPagar = {};	
	$scope.idexcluir = 0;	
	
	
	
	var url = 'http://localhost:8080/Textilsoft/rest/';

	
	
	
	$scope.listarContasPagar = function() {
		
		
		$http({
			method : 'GET',
			url : url + 'contaspagar/'
		}).then(function(response) {		
			
			$scope.listaContaPagar = response.data;
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarContaPagar = function() {
		var metodo = 'POST';
		 databrasileira = $scope.contaPagar.dataVencimento;
		 split = databrasileira.toString().split('/');
		 novadata = split[2] + "-" +split[1]+"-"+split[0];
		$scope.contaPagar.dataVencimento = new Date(novadata);
		
		
		$http({
			
			method : metodo,
			url : url + 'contaspagar/',
			data : $scope.contaPagar
			 
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.contaPagar = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteContaPagar = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'contaspagar/' + id
		}).then(function(response) {
			var pos = $scope.listaContaPagar.indexOf(id);
			$scope.listaContaPagar.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarContaPagar = function(contaPagar) {
		$scope.contaPagar = angular.copy(contaPagar);
	}
	
	$scope.procuraID = function(contaPagar) {
		$scope.idexcluir = angular.copy(contaPagar.idContaPagar);
	}

	$scope.cancelarAlteracaoContaPagar = function(contaPagar) {
		$scope.contaPagar = {};
	};

});