package br.com.richardson.tools;

import br.com.richardson.model.dto.ProdutoDTO;
import br.com.richardson.model.entity.Produto;

import java.util.Objects;

public class ProdutosConverter {
    public static Produto produtoDTOToProduto(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getValor(),
                produtoDTO.getEstoque_minimo(),
                produtoDTO.getEstoque_maximo(),
                produtoDTO.getSaldo_estoque(),
                produtoDTO.getFornecedor(),
                produtoDTO.getGarantia());
    }

    public static ProdutoDTO produtoToProdutoDTO(Produto produto) {
        if (Objects.isNull(produto)) {
            return null;
        }
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .estoque_minimo(produto.getEstoque_minimo())
                .estoque_maximo(produto.getEstoque_maximo())
                .saldo_estoque(produto.getSaldo_estoque())
                .fornecedor(produto.getFornecedor())
                .garantia(produto.getPossui_garantia())
                .build();
    }
}
