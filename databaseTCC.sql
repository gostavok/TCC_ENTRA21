create database tcc;

use tcc;

#----------------------------------------Cadastro--------------------------------------------

create table cliente (
	id_cliente int(11) primary key auto_increment,
    nm_cliente varchar(250) not null,
    cnpj_cliente varchar(30) not null,
    end_cliente varchar(50) not null,
    cep_cliente int(11) not null,
    bairro_cliente varchar(30) not null,
    cidade_cliente varchar(30) not null, 
    estado_cliente varchar(30) not null,
    comp_cliente varchar(50) not null,
    tel_cliente int(11) not null,
    tel2_cliente int(11),
    email_cliente varchar(30),
    dt_cad_cliente DATE not null
);

create table fornecedor (
	id_fornecedor int(11) primary key auto_increment,
    nm_fornecedor varchar(250) not null,
    cnpj_fornecedor varchar(30) not null,
    end_fornecedor varchar(50) not null,
    cep_fornecedor int(11) not null,
    bairro_fornecedor varchar(50) not null,
    cidade_fornecedor varchar(50) not null,
    estado_fornecedor varchar(50) not null,
    comp_fornecedor varchar(50) not null,
    tel_fornecedor int(11) not null,
    tel2_fornecedor int(11) not null,
    email_fornecedor varchar(30),
    dt_cad_fornecedor DATE not null,
    tipo_fornecedor enum('Produto','Serviço')
);

create table produto_fornecedor (
	id_prod_forn int(11) primary key auto_increment,
    nm_prod_forn varchar(250) not null,
    desc_prod_forn varchar(250) not null,
    valor_prod_forn decimal(10,2) not null,
    und_medida_prod_forn enum('Unidade','Comprimento','Peso')
);

create table fornecedor_produto (
	id_prod_forn int(11) not null,
    id_fornecedor int(11) not null,
    foreign key(id_prod_forn) references produto_fornecedor(id_prod_forn),
    foreign key(id_fornecedor) references fornecedor(id_fornecedor)
);

create table servico_fornecedor (
	id_serv_forn int(11) primary key auto_increment,
    nm_serv_forn varchar(250) not null,
    desc_serv_forn varchar(250) not null,
    valor_serv_forn decimal(10,2) not null
);

create table fornecedor_servico (
	id_serv_forn int(11) not null,
    id_fornecedor int(11) not null,
    foreign key (id_serv_forn) references servico_fornecedor (id_serv_forn),
    foreign key(id_fornecedor) references fornecedor (id_fornecedor)
);

create table produto (
	id_produto int(11) primary key auto_increment,
    desc_produto varchar(250) not null,
    cor_produto int(11) not null,
    material_produto int(11) not null,
    estampa_produto int(11) not null,
    valor_produto decimal(10,2) not null,
    foreign key (cor_produto) references cor (id_cor),
    foreign key (material_produto) references material (id_material),
    foreign key (estampa_produto) references estampa (id_estampa)
);

create table cor(
	id_cor int(11) primary key auto_increment,
    nm_cor varchar(20) not null,
    valor_cor decimal(10,2) not null
);

create table material(
	id_material int(11) primary key auto_increment,
    nm_material varchar(20) not null,
    valor_material decimal(10,2) not null
);

create table estampa(
	id_estampa int(11) primary key auto_increment,
    codigo_estampa varchar(20) not null,
    valor_estampa decimal(10,2) not null
);

#------------------------------Estoque---------------------------------------------------







