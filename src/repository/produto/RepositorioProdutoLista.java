package repository.produto;

import java.util.ArrayList;
import java.util.List;

import model.produto.Produto;

public class RepositorioProdutoLista implements RepositorioProduto {
    
    List<Produto> produtos;
    

    public RepositorioProdutoLista() {
        produtos = new ArrayList<>();
    }
    
    @Override
    public Produto inserirProduto(Produto produto) throws ProdutoJaCadastradoException {
        try {
            buscarProduto(produto.getCodigo()); // 
            throw new ProdutoJaCadastradoException();
        } catch (ProdutoNaoCadastradoException ex) {
            produtos.add(produto);
        }
        return produto;
    }
    
    @Override
    public void alterarProduto(Produto produto) {
        // Em memória, não há necessidade de atualizar objeto
    }
    
    @Override
    public void deletarProduto(Produto produto) throws ProdutoNaoCadastradoException {
        if (! produtos.remove(produto)) {
           throw new ProdutoNaoCadastradoException();
        }
    }
    
    @Override
    public Produto buscarProduto(String codigo) throws ProdutoNaoCadastradoException {
        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigo)) { 
                return produto;
            }
        }
        throw new ProdutoNaoCadastradoException();
    }

    @Override
    public List<Produto> getAll() {
        return new ArrayList<>(produtos);
    }
    
    //@Override
    public List<Produto> getAll(String codigo) {
        List<Produto> lista = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) { 
                lista.add(produto);
            }
        }
        return lista;
    }
    
}
