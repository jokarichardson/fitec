DROP TABLE IF EXISTS produtos;
DROP TABLE IF EXISTS garantias;
DROP TABLE IF EXISTS itens_vendas;
DROP TABLE IF EXISTS vendas;

CREATE TABLE produtos (
  id UUID PRIMARY KEY,
  nome VARCHAR(50), 
  valor DECIMAL,
  estoque_maximo INT,
  estoque_minimo INT,
  saldo_estoque INT,
  fornecedor VARCHAR(50),
  possui_garantia BOOLEAN
);

CREATE TABLE garantias (
  id UUID PRIMARY KEY, 
  nome VARCHAR(50), 
  valor DECIMAL,
  prazo INT
);

CREATE TABLE vendas (
  id UUID DEFAULT RANDOM_UUID() PRIMARY KEY, 
  valor_total DECIMAL
);

CREATE TABLE itens_vendas (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    id_venda UUID NOT NULL,
    CONSTRAINT fk_vendas FOREIGN KEY(id_venda) REFERENCES vendas(id),
    id_produto UUID NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL NOT NULL,
    valor_total DECIMAL NOT NULL,
    id_garantia UUID
);

SET @id_produto=RANDOM_UUID();
SET @id_garantia=RANDOM_UUID();
SET @id_venda=RANDOM_UUID();
SET @id_item_venda=RANDOM_UUID();

INSERT
  INTO produtos (id, nome, valor, estoque_maximo, estoque_minimo, saldo_estoque, fornecedor, possui_garantia)
VALUES (@id_produto, 'Produto 1', 10.55, 100, 10, 57, 'Fornecedor 1', true);

INSERT
  INTO garantias (id, nome, valor, prazo)
VALUES (@id_garantia, 'Garantia 1', 5.00, 1);

INSERT
  INTO vendas (id, valor_total)
VALUES (@id_venda, 153.20);

INSERT
  INTO itens_vendas (id, id_venda, id_produto, quantidade, valor_unitario, valor_total, id_garantia)
VALUES (@id_item_venda, @id_venda, @id_produto, 10, 15.32, 153.20, @id_garantia);

COMMIT;