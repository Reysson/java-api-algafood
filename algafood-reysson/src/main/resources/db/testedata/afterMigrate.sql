
truncate  cidade cascade;
truncate  cozinha cascade;
truncate  estado cascade;
truncate  forma_pagamento cascade;
truncate  grupo cascade;
truncate  grupo_permissao cascade;
truncate  permissao cascade;
truncate  produto cascade;
truncate  restaurante cascade;
truncate  restaurante_forma_pagamento cascade;
truncate  usuario cascade;
truncate  usuario_grupo cascade;

ALTER SEQUENCE cidade_id_seq RESTART WITH 1;
ALTER SEQUENCE cozinha_id_seq RESTART WITH 1;
ALTER SEQUENCE estado_id_seq RESTART WITH 1;
ALTER SEQUENCE forma_pagamento_id_seq RESTART WITH 1;
ALTER SEQUENCE grupo_id_seq RESTART WITH 1;
ALTER SEQUENCE permissao_id_seq RESTART WITH 1;
ALTER SEQUENCE produto_id_seq RESTART WITH 1;
ALTER SEQUENCE restaurante_id_seq RESTART WITH 1;
ALTER SEQUENCE usuario_id_seq RESTART WITH 1;

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Argentina');
insert into cozinha (nome) values ('Brasileira');

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Uberlândia', 1);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Fortaleza', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro,ativo) values ('Thai Gourmet', 10, 1, localtimestamp, localtimestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro',true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao,ativo) values ('Thai Delivery', 9.50, 1, localtimestamp, localtimestamp,true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao,ativo) values ('Tuk Tuk Comida Indiana', 15, 2, localtimestamp, localtimestamp,true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao,ativo) values ('Java Steakhouse', 12, 3, localtimestamp, localtimestamp,true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao,ativo) values ('Lanchonete do Tio Sam', 11, 4, localtimestamp, localtimestamp,true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao,ativo) values ('Bar da Maria', 6, 4, localtimestamp, localtimestamp,true);

insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into usuario (nome, email, senha, data_cadastro) values
('João da Silva', 'joao.ger@algafood.com', '123', localtimestamp),
('Maria Joaquina', 'maria.vnd@algafood.com', '123', localtimestamp),
('José Souza', 'jose.aux@algafood.com', '123', localtimestamp),
('Sebastião Martins', 'sebastiao.cad@algafood.com', '123', localtimestamp);