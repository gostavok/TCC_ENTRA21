appTextilsoft.controller("contaReceberController", function($scope, $http,
		$routeParams) {
	$scope.listaContaReceber = [];
	$scope.contaReceber = {};
	$scope.contaReceber.Pago = 0;
	$scope.contaReceber.Pendente = 0;
	$scope.contaReceber.Atrasado = 0;	
	$scope.pago = 0;
	$scope.pendente = 0;
	$scope.atrasado = 0;
	$scope.excluir = 0;
	
	var url = 'http://localhost:8080/Textilsoft/rest/contasreceber/';
	

	
	$scope.listarStatus = function() {	
	
		$http({
			method : 'GET',
			url : url + 'statusobj/'
		}).then(function(response) {		
			
			$scope.contaReceber = response.data;	
			
			console.log($scope.contaReceber);
			$scope.contaReceber.filter(function(i, idx) {
			    if(i.status === "Pago") $scope.contaReceber.Pago = i.quantidade;
			    if(i.status === "Pendente") $scope.contaReceber.Pendente = i.quantidade;
			  
			    if(i.status === "Atrasado") $scope.contaReceber.Atrasado = i.quantidade;
			});						
		
				
			
		}, function(response) {

			console.log(response.data);
			console.log($scope.contaReceber);

		});
	};
	
	$scope.pegarValorPago = function() {		
		$http({
			method : 'GET',
			url : url + 'pago/'
		}).then(function(response) {		
			
			$scope.pago = response.data;		
			
		}, function(response) {

			console.log(response.data);
	

		});
	};
	$scope.pegarValorPendente = function() {		
		$http({
			method : 'GET',
			url : url + 'pendente/'
		}).then(function(response) {		
			
			$scope.pendente = response.data;
			
			
		}, function(response) {

			console.log(response.data);
	

		});
	};
	$scope.pegarValorAtrasado = function() {		
		$http({
			method : 'GET',
			url : url + 'atrasado/'
		}).then(function(response) {		
			
			$scope.atrasado = response.data;
		
			
		}, function(response) {

			console.log(response.data);
	

		});
	};
		
	$scope.listarPorStatus = function(status) {		
		$http({
			method : 'GET',
			url : url + 'status/' + status
		}).then(function(response) {		
			
			$scope.listaContaReceber = response.data;
			
		}, function(response) {

			console.log(response.data);
			console.log($scope.contaReceber);

		});
	};
	
	$scope.chamarMetodos = function(){
		
			
			$scope.listarPorStatus("Atrasado");
			$scope.pegarValorPago();
			$scope.pegarValorPendente();
			$scope.pegarValorAtrasado();
			$scope.listarStatus();
		}	
	
	$scope.listarValores = function(){
		
		$scope.pegarValorPago();
		$scope.pegarValorPendente();
		$scope.pegarValorAtrasado();
		$scope.listarStatus();
	}	
	
	$scope.salvarID = function(id){
			$scope.excluir = id;		
	}
	
		
	$scope.deletar = function(id) {

			$http({
				method : 'DELETE',
				url : url + id
			}).then(function(response) {			
				var pos = 0;
				$scope.listaContaReceber.filter(function(i, idx) {
				    if(i.idContaReceber == id)
				    	pos = idx; 				   
				});				
				 $scope.listaContaReceber.splice(pos, 1);			
				 $scope.listarValores();
				
			}, function(response) {
				console.log('error do salvar');
				console.log(response.data);
				console.log(response.status);
			});
	};
});


	