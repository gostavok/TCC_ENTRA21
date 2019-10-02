create database textilsoft;
use textilsoft;
#----------------------------------------Cadastro--------------------------------------------


create table cliente (
	id_cliente int(11) primary key auto_increment,
    nm_cliente varchar(250) not null,
    cnpj_cliente varchar(30) not null,
    end_cliente varchar(50) not null,
    cep_cliente int(11),
    bairro_cliente varchar(30) not null,
    cidade_cliente varchar(30) not null, 
    estado_cliente varchar(30) not null,
    comp_cliente varchar(50),
    tel_cliente bigint not null,
    tel2_cliente bigint,
    email_cliente varchar(30),
    dt_cad_cliente TIMESTAMP not null
);

create table fornecedor (
	id_fornecedor int(11) primary key auto_increment,
    nm_fornecedor varchar(250) not null,
    cnpj_fornecedor varchar(30) not null,
    end_fornecedor varchar(50) not null,
    cep_fornecedor int(11),
    bairro_fornecedor varchar(50) not null,
    cidade_fornecedor varchar(50) not null,
    estado_fornecedor varchar(50) not null,
    comp_fornecedor varchar(50),
    tel_fornecedor bigint not null,
    tel2_fornecedor bigint,
    email_fornecedor varchar(30),
    dt_cad_fornecedor TIMESTAMP not null,
    tipo_fornecedor enum('Produto','Serviço')
);

create table produto_fornecedor (
	id_prod_forn int(11) primary key auto_increment,
    nm_prod_forn varchar(250) not null,
    desc_prod_forn varchar(250) not null,
    valor_prod_forn decimal(10,2) not null,
    und_medida_prod_forn enum('Quilos','Metros','Unitario'),
    id_fornecedor int(11) not null,
    foreign key (id_fornecedor) references fornecedor(id_fornecedor)
    
);


create table servico_fornecedor (
	id_serv_forn int(11) primary key auto_increment,
    nm_serv_forn varchar(250) not null,
    desc_serv_forn varchar(250) not null,
    valor_serv_forn decimal(10,2) not null,
    und_medida_serv_forn enum('Unidade','Peso'),
    id_fornecedor int(11) not null,
    foreign key (id_fornecedor) references fornecedor(id_fornecedor)
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


#------------------------------Estoque---------------------------------------------------

create table estoque (
	id_estoque int(11) primary key auto_increment,
    id_prod_forn int(11) not null,
    qtd_estoque decimal(10,3) not null,
    data_registro TIMESTAMP not null,
    foreign key (id_prod_forn) references produto_fornecedor (id_prod_forn)
);

create table compra (
	id_compra int(11) primary key auto_increment,
    id_prod_forn int(11) not null,
    qtd_compra decimal (10,3) not null,
    valor_total decimal(10,2) not null,
    data_compra TIMESTAMP not null,
    data_venc DATE not null,
    foreign key (id_prod_forn) references produto_fornecedor (id_prod_forn)	
);

#-------------------------Venda----------------------------------------------------------

create table orcamento (
	id_orcamento int(11) primary key auto_increment,
    id_cor int(11) not null,
    id_estampa int(11) not null,
    id_material int(11) not null,
    quantidade int(11) not null,
    valor_base decimal(10,2) not null,
    valor_orcamento decimal(10,2),
    foreign key (id_cor) references cor (id_cor),
    foreign key (id_estampa) references estampa (id_estampa),
    foreign key (id_material) references material (id_material)
);

create table pedido(
	id_pedido int(11) primary key auto_increment,
    id_cliente int(11) not null,
    qtd_prod int(11) not null,
    valor_total_pedido decimal(10,2) not null,
    data_pedido TIMESTAMP not null,
    status_pedido enum('Aberto','Pendente','Fechado') not null,
    foreign key (id_cliente) references cliente(id_cliente)
);

create table pedido_produto (
	id_pedido int(11) not null,
    id_produto int(11) not null,
    foreign key (id_pedido) references pedido (id_pedido),
    foreign key (id_produto) references produto (id_produto)
);

create table venda (
	id_venda int(11) primary key auto_increment,
    valor_total_venda decimal(10,2),
    data_pagamento DATE ,
    data_venda TIMESTAMP not null
);

create table venda_pedido (
	id_venda int(11) not null,
    id_pedido int(11) not null,
    foreign key (id_venda) references venda (id_venda),
    foreign key (id_pedido) references pedido (id_pedido)
);

create table ordem_servico (
	id_ordem int(11) primary key auto_increment,
    id_fornecedor int(11) not null,
    id_serv_forn int(11) not null,
    qtd_servico decimal(10,3) not null,
    status_ordem enum('Aberto','Fechado','Pendente') not null,
    data_abertura TIMESTAMP not null,
    data_entrega DATE not null,
    valor_total decimal(10,3) not null,
    foreign key (id_fornecedor) references fornecedor(id_fornecedor),
    foreign key (id_serv_forn) references servico_fornecedor (id_serv_forn)
);

create table ordem_pedido (
	id_ordem_pedido int(11) primary key auto_increment,
    id_ordem int(11) not null,
	id_pedido int(11) not null,
    id_produto int(11) not null,
    qtd decimal(10,2) not null,
    foreign key (id_ordem) references ordem_servico (id_ordem),
    foreign key (id_pedido) references pedido (id_pedido),
    foreign key (id_produto) references produto (id_produto)
);

#--------------------------Financeiro-----------------------------------

create table conta_receber (
	id_conta_receber int(11) primary key auto_increment,
    id_venda int(11) not null,
    status_conta_receber enum('Pago','Pendente', 'Atrasado') not null,
    foreign key (id_venda) references venda(id_venda)
);

create table conta_pagar (
	id_conta_pagar int(11) primary key auto_increment,
	desc_conta_pagar varchar(200) not null,
    	complemento varchar(200) not null,
	valor_conta_pagar decimal(10,2) not null,
	data_vencimento date not null,
	data_inclusao timestamp not null,
    	status_conta_pagar enum('Pago','Pendente', 'Atrasado') not null
);

