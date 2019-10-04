/* SUGESTÕES ALTERAÇÕES ESTRUTURA TABELA
create table cliente (
	id_cliente int(11) primary key auto_increment,
    nm_cliente varchar(250) not null,
    cnpj_cliente varchar(14) not null, -- Tamanho 14 padrão NACIONAL
    end_cliente varchar(250) not null, -- Aumentar de 50 para 250
    cep_cliente int(8) not null, -- Tamanho 8 padrão NACIONAL
    bairro_cliente varchar(30) not null,
    cidade_cliente varchar(30) not null, 
    estado_cliente varchar(30) not null, -- Tamanho 2 padrão NACIONAL sigla
    comp_cliente varchar(250) not null, -- Aumentar de 50 para 250
    tel_cliente int(15) not null, -- Aumentar para 15, eventual DDD + código país
    tel2_cliente int(15), -- Aumentar para 15, eventual DDD + código país
    email_cliente varchar(30),
    dt_cad_cliente TIMESTAMP not null
);
*/

use textilsoft;
INSERT INTO textilsoft.cliente
(nm_cliente,
cnpj_cliente,
end_cliente,
cep_cliente,
bairro_cliente,
cidade_cliente,
estado_cliente,
comp_cliente,
tel_cliente,
tel2_cliente,
email_cliente)
VALUES

('Cliente 1',
'31784443000111',
'Endereço 1',
89037111,
'Bairro 1',
'Cidade 1',
'SC',
'Sala número 51',
47999999991,
47999999991,
'cliente1@email.com');
INSERT INTO textilsoft.cliente
(nm_cliente,
cnpj_cliente,
end_cliente,
cep_cliente,
bairro_cliente,
cidade_cliente,
estado_cliente,
comp_cliente,
tel_cliente,
tel2_cliente,
email_cliente)

VALUES
('Cliente 2',
'31784443000222',
'Endereço 2',
89037222,
'Bairro 2',
'Cidade 2',
'SP',
'Sala número 52',
47999999992,
47999999992,
'cliente2@email.com');

INSERT INTO tcc.cliente
(nm_cliente,
cnpj_cliente,
end_cliente,
cep_cliente,
bairro_cliente,
cidade_cliente,
estado_cliente,
comp_cliente,
tel_cliente,
tel2_cliente,
email_cliente)

VALUES
('Cliente 3',
'31784443000333',
'Endereço 3',
89037333,
'Bairro 3',
'Cidade 3',
'RS',
'Sala número 53',
47999999993,
47999999993,
'cliente3@email.com');

/*
/* SUGESTÕES ALTERAÇÕES ESTRUTURA TABELA
create table cliente (
	id_cliente int(11) primary key auto_increment,
    nm_cliente varchar(250) not null,
    cnpj_cliente varchar(14) not null, -- Tamanho 14 padrão NACIONAL
    end_cliente varchar(250) not null, -- Aumentar de 50 para 250
    cep_cliente int(8) not null, -- Tamanho 8 padrão NACIONAL
    bairro_cliente varchar(30) not null,
    cidade_cliente varchar(30) not null, 
    estado_cliente varchar(30) not null, -- Tamanho 2 padrão NACIONAL sigla
    comp_cliente varchar(250) not null, -- Aumentar de 50 para 250
    tel_cliente int(15) not null, -- Aumentar para 15, eventual DDD + código país
    tel2_cliente int(15), -- Aumentar para 15, eventual DDD + código país
    email_cliente varchar(30),
    dt_cad_cliente TIMESTAMP not null
);
*/
/*
use textilsoft;
INSERT INTO textilsoft.cliente
(nm_cliente,
cnpj_cliente,
end_cliente,
cep_cliente,
bairro_cliente,
cidade_cliente,
estado_cliente,
comp_cliente,
tel_cliente,
tel2_cliente,
email_cliente)
VALUES

('Cliente 1',
'31784443000111',
'Endereço 1',
89037111,
'Bairro 1',
'Cidade 1',
'SC',
'Sala número 51',
47999999991,
47999999991,
'cliente1@email.com');
INSERT INTO textilsoft.cliente
(nm_cliente,
cnpj_cliente,
end_cliente,
cep_cliente,
bairro_cliente,
cidade_cliente,
estado_cliente,
comp_cliente,
tel_cliente,
tel2_cliente,
email_cliente)

VALUES
('Cliente 2',
'31784443000222',
'Endereço 2',
89037222,
'Bairro 2',
'Cidade 2',
'SP',
'Sala número 52',
47999999992,
47999999992,
'cliente2@email.com');

INSERT INTO tcc.cliente
(nm_cliente,
cnpj_cliente,
end_cliente,
cep_cliente,
bairro_cliente,
cidade_cliente,
estado_cliente,
comp_cliente,
tel_cliente,
tel2_cliente,
email_cliente)

VALUES
('Cliente 3',
'31784443000333',
'Endereço 3',
89037333,
'Bairro 3',
'Cidade 3',
'RS',
'Sala número 53',
47999999993,
47999999993,
'cliente3@email.com');
*/
