appTextilsoft.controller("orcamentoController", function($scope, $http) {

	$scope.listaOrcamento = [];
	$scope.orcamento = {};
	$scope.idOrcamento = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarOrcamentos = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'orcamentos/'
		}).then(function(response) {
            $scope.listaOrcamento = response.data;
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarOrcamentos = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'orcamentos/',
			data : $scope.orcamento
		}).then(function(response) {		
			alert("efetuado com sucesso")
			$scope.orcamento = {};
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteOrcamentos = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'orcamentos/' + id
		}).then(function(response) {
			var pos = $scope.listaOrcamento.indexOf(id);
			$scope.listaOrcamento.splice(pos,1);	
			
		}, function(response) {
			console.log('error do salvar');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.calculaTotal = function() {
		var cor = 0;
		var material = 0;
		var estampa =  0 ;
		var base = 0;
		var quantidade = 1
		
		cor = $scope.orcamento.corOrcamento.valorCor;
		material = $scope.orcamento.materialOrcamento.valorMaterial;
		estampa =  $scope.orcamento.estampaOrcamento.valorEstampa ;
		base = $scope.orcamento.valorBase
		quantidade = $scope.orcamento.quantidade
		$scope.orcamento.valorOrcamento = ( cor + material +estampa+base) * quantidade ;
	} 
	
	
	$scope.alterarOrcamento = function(orcamento) {
		$scope.orcamento = angular.copy(orcamento);
	}
	
	$scope.procuraOrcamento = function(orcamento) {
		$scope.idOrcamento = angular.copy(orcamento.idOrcamento);
	}

	$scope.cancelarAlteracaoOrcamento = function(orcamento) {
		$scope.orcamento = {};
	};

});