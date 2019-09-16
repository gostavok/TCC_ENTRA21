var appTextilsoft = angular.module("appTextilsoft", ['ngRoute']);

appTextilsoft.config(function($routeProvider, $locationProvider){
    
	$routeProvider
    .when("/fornecedores-cad",{templateUrl:'view/fornecedor-cad.html', controller:'fornecedorController'})
    .when("/fornecedores-lista",{templateUrl:'view/fornecedor.html', controller:'fornecedorController'})
    .when("/fornecedores-editar/:id",{templateUrl:'view/fornecedor-edit.html', controller:'fornecedorDetalheController'})
    .when("/fornecedores/:id",{templateUrl:'view/fornecedor-detalhe.html', controller:'fornecedorDetalheController'})
    
    .when("/estoque-cad",{templateUrl:'view/estoque-cad.html', controller:'estoqueController'})
    .when("/estoque-lista",{templateUrl:'view/estoque.html', controller:'estoqueController'})
    .when("/estoque-editar/:id",{templateUrl:'view/estoque-edit.html', controller:'estoqueDetalheController'})
    .when("/estoque/:id",{templateUrl:'view/estoque-detalhe.html', controller:'estoqueDetalheController'})

    .when("/conta-pagar-cad",{templateUrl:'view/conta-pagar-cad.html', controller:'contaPagarController'})
    .when("/conta-pagar-lista",{templateUrl:'view/conta-pagar.html', controller:'contaPagarController'})
    .when("/conta-pagar-editar/:id",{templateUrl:'view/conta-pagar-edit.html', controller:'contaPagarControllerDetalhe'})
    .when("/conta-pagar/:id",{templateUrl:'view/conta-pagar-detalhe.html', controller:'contaPagarControllerDetalhe'})

    .otherwise({redirectTo:'/'});
	
	
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
	
}); 