appTextilsoft.controller("materialController", function($scope, $http) {

	$scope.listaMaterial = [];
	$scope.material = {};
	$scope.idMaterial = 0;
	var url = 'http://localhost:8080/Textilsoft/rest/';

	$scope.listarMateriais = function() {
	
		
		$http({
			method : 'GET',
			url : url + 'materiais/'
		}).then(function(response) {
            $scope.listaMaterial = response.data;
			
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarMateriais = function() {
		var metodo = 'POST';
	
		$http({
			method : metodo,
			url : url + 'materiais/',
			data : $scope.material
		}).then(function(response) {		
			alert("efetuado com sucesso")
		}, function(response) {
			console.log('error do salvar');	
		});
	};

	$scope.deleteMaterial = function(id) {

		$http({
			method : 'DELETE',
			url : url + 'materiais/' + id
		}).then(function(response) {
			var pos = 0;
			$scope.listaMaterial.filter(function(i, idx) {
			    if(i.idMaterial == id)
			    	pos = idx; 				   
			});	
			$scope.listaMaterial.splice(pos,1);	
			
		}, function(response) {
			alert("Existe produto usando esse material")
			console.log('error ao excluir');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.alterarMaterial = function(material) {
		$scope.material = angular.copy(material);
	}
	
	$scope.procuraMaterial = function(material) {
		$scope.idMaterial = angular.copy(material.idMaterial);
	}

	$scope.cancelarAlteracaoMaterial = function(material) {
		$scope.material = {};
	};

});