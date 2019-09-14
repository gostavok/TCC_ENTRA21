var appTextilsoft = angular.module("appTextilsoft", ['ngRoute']);

appTextilsoft.config(function($routeProvider, $locationProvider){
    
	$routeProvider
    .when("/fornecedores-cad",{templateUrl:'view/fornecedor-cad.html', controller:'fornecedorController'})
    .when("/fornecedores-lista",{templateUrl:'view/fornecedor.html', controller:'fornecedorController'})
    .when("/fornecedores-editar/:id",{templateUrl:'view/fornecedor-edit.html', controller:'fornecedorDetalheController'})
    .when("/fornecedores/:id",{templateUrl:'view/fornecedor-detalhe.html', controller:'fornecedorDetalheController'})
    .when("/usuario",{templateUrl:'view/usuario.html', controller:'usuarioController'})
    .otherwise({redirectTo:'/'});
	
	
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
	
}); 