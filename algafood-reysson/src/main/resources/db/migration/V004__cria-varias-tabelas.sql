create table forma_pagamento (
	id SERIAL,
	descricao varchar(60),
	primary key (id)
);

create table grupo (
	id SERIAL,
	nome varchar(60),
	primary key (id)
);

create table grupo_permissao (
	grupo_id int ,
	permissao_id int ,
	primary key (grupo_id, permissao_id)
);

create table permissao (
	id SERIAL,
	descricao varchar(60),
	nome varchar(100) ,
	
	primary key (id)
);

create table produto (
	id SERIAL,
	restaurante_id int,
	nome varchar(80),
	descricao text,
	preco decimal(10,2),
	ativo smallint,
	primary key (id)
);

create table restaurante (
	id SERIAL,
	cozinha_id int ,
	nome varchar(80) ,
	taxa_frete decimal(10,2) ,
	data_atualizacao timestamp ,
	data_cadastro timestamp ,
	
	endereco_cidade_id int,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),
	
	primary key (id)
);

create table restaurante_forma_pagamento (
	restaurante_id int ,
	forma_pagamento_id int ,
	
	primary key (restaurante_id, forma_pagamento_id)
);

create table usuario (
	id SERIAL,
	nome varchar(80) ,
	email varchar(255) ,
	senha varchar(255) ,
	data_cadastro timestamp ,
	
	primary key (id)
);

create table usuario_grupo (
	usuario_id int ,
	grupo_id int ,
	primary key (usuario_id, grupo_id)
);




alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table produto add constraint fk_produto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint fk_restaurante_cozinha
foreign key (cozinha_id) references cozinha (id);

alter table restaurante add constraint fk_restaurante_cidade
foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto
foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);