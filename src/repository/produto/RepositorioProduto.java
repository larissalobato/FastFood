package repository.produto;

import java.util.List;

import model.produto.Produto;

public interface RepositorioProduto {

    Produto inserirProduto(Produto produto) throws ProdutoJaCadastradoException;

    void alterarProduto(Produto produto) throws ProdutoNaoCadastradoException;

    void deletarProduto(Produto produto) throws ProdutoNaoCadastradoException;

    Produto buscarProduto(String codigo) throws ProdutoNaoCadastradoException;

    List<Produto> getAll();

    List<Produto> getAll(String cpf);
    
}
