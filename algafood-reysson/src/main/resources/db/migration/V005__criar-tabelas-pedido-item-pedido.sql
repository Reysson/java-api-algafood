CREATE TABLE pedido(
    id SERIAL PRIMARY KEY,
    subtotal FLOAT,
    taxa_frete FLOAT,
    valor_total FLOAT,
    data_criacao timestamp,
    data_confirmacao timestamp,
    data_cancelamento timestamp,
    data_entrega timestamp,
    endereco_cep VARCHAR(50),
    endereco_logradouro VARCHAR(50),
    endereco_numero VARCHAR(50),
    endereco_complemento VARCHAR(50),
    endereco_bairro VARCHAR(50),
    forma_pagamento_id INT REFERENCES forma_pagamento(id),
    restaurante_id INT REFERENCES restaurante(id),
    usuario_id INT REFERENCES usuario(id),
    status_pedido VARCHAR(20)

);

CREATE TABLE item_pedido (
    id SERIAL PRIMARY KEY,
    quantidade INT,
    preco_unitario DECIMAL(5,2),
    preco_total DECIMAL(5,2),
    observacao TEXT,
    pedido_id INT REFERENCES pedido(id),
    produto_id INT REFERENCES produto(id)
);

