appTextilsoft.controller("vendaController", function($scope, $http) {

	$scope.listaVenda = [];
	$scope.venda = {};
	$scope.dataEmissao = "";
	$scope.idvenda = 0;
	$scope.pesquisa= "";
	$scope.contaReceber = {};
	$scope.contaReceber.venda = {};
	$scope.contaReceber.statusContaReceber = "Pendente";
	$scope.exibeId = {};
	$scope.exibeId.idVenda = {};
	$scope.addPedido = {};
	
	var url = 'http://localhost:8080/Textilsoft/rest/';
	
	var today = new Date();
	var dia = today.getDate();
	var mes = today.getMonth()+1;
	var ano = today.getFullYear();
	
	$scope.dataEmissao = dia + "/" + mes + "/" + ano;
	
	$scope.dataEmissao = new Date($scope.dataEmissao);
	
	console.log($scope.dataEmissao)

//------------------TESTE BOTÂO PEDIDOS-----------	
	
	$http.get('http://localhost:8080/Textilsoft/rest/vendas/ultimo').then(function(response) {
       $scope.addPedido = response.data;
       $scope.exibeId.idVenda = $scope.addPedido.idVenda + 1;
       					
        console.log('success - exibeId');
        
    }, function(response) {
        console.log('error- exibeId');
    
    });
	
//------------------TESTE BOTÂO PEDIDOS-----------	
	
	$scope.salvarContaReceber = function() {
		var metodo = 'POST';
		$scope.contaReceber.statusContaReceber = "Pendente";
		
		console.log($scope.contaReceber);
		
		$http({
			method : metodo,
			url : url + 'contasreceber/',
			data : $scope.contaReceber
		}).then(function(response) {				
			
		}, function(response) {
			console.log(response.data);
			console.log('error do salvar');	
		});
	};

	$scope.listarFornecedor = function(id) {
		$http({
			method : 'GET',
			url : url + 'fornecedores/'+id
		}).then(function(response) {
			$scope.venda.fornecedor = response.data; 			
						
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
    };
    
    $scope.listarVendaPedido = function(idVenda) {
		$http({
			method : 'GET',
			url : url + 'vendas/pedidos/'+idVenda
		}).then(function(response) {
			console.log(response.data);
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};
	
	$scope.listarVenda = function() {
		
		$http({
			method : 'GET',
			url : url + 'vendas/'
		}).then(function(response) {
			$scope.listaVenda = response.data;
			console.log(response.data)
		}, function(response) {
			console.log('error');
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.salvarVenda = function() {

		$http({
			method : 'POST',
			url : url + 'vendas/',
			data : $scope.venda
		}).then(function(response) {		
			alert("efetuado com sucesso")			
			console.log($scope.venda.dataEntregaVenda);
			
			
			$http({
				method : 'GET',
				url : url + 'vendas/ultimo'
			}).then(function(response) {		
			//	alert("get com sucesso")
				var aux = response.data		
				$scope.venda.idVenda =  aux.idVenda;
				$scope.addPedido = aux;
				$scope.exibeId.idVenda = aux.idVenda + 1;
				
				$scope.contaReceber.venda = $scope.venda;
				
				$scope.salvarContaReceber();
				$scope.venda = {};
			}, function(response) {
				console.log('error do get');	
			});
			
			
			
			
		}, function(response) {
			console.log('error do salvar');	
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.deleteVenda = function(id) {
		
	$http({
			method : 'DELETE',
			url : url + 'contasreceber/deletar/' + id			
	}).then(function(response) {	
		$http({
			method : 'DELETE',
			url : url + 'vendaspedidos/d' + id			
		}).then(function(response) {
			
			
			$http({
				method : 'DELETE',
				url : url + 'vendas/' + id			
			}).then(function(response) {
				var pos = 0;			
				
				$scope.listaVenda.filter(function(i, idx) {
				    if(i.idVenda == id)			    
				    	pos = idx;			   
				});			
				$scope.listaVenda.splice(pos,1);	
				
			}, function(response) {
				console.log('error do salvar');
				console.log(response.data);
				console.log(response.status);
			});
		});
	});
	};

	$scope.alterarVenda = function(venda) {
		$scope.venda = angular.copy(venda);
	}
	
	$scope.procuraVenda = function(venda) {
		$scope.idvenda = angular.copy(venda.idVenda);
	}

	$scope.cancelarAlteracaoVenda = function(venda) {
		$scope.venda = {};
	};
	
});