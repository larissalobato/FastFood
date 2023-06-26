package facade;

import java.util.List;

import model.cliente.Cliente;
import model.produto.Produto;
import repository.cliente.CPFJaCadastradoException;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.RepositorioCliente;
import repository.cliente.RepositorioClienteLista;
import repository.produto.ProdutoJaCadastradoException;
import repository.produto.ProdutoNaoCadastradoException;
import repository.produto.RepositorioProduto;
import repository.produto.RepositorioProdutoLista;

public class FastFoodFacade {

  private RepositorioCliente repositorioCliente;
  private RepositorioProduto repositorioProduto;

  public FastFoodFacade() {
    repositorioCliente = new RepositorioClienteLista();
    repositorioProduto = new RepositorioProdutoLista();
  }  

  public void inserirCliente(Cliente cliente) throws CPFJaCadastradoException {
      repositorioCliente.inserirCliente(cliente);
  }
    
  public void alterarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
      repositorioCliente.alterarCliente(cliente);
  }
    
  public Cliente buscarCliente(String cpf) throws ClienteNaoCadastradoException {
      return repositorioCliente.buscarCliente(cpf);
  }
    
  public void excluirCliente(Cliente cliente) throws FastFoodException, ClienteNaoCadastradoException {
    repositorioCliente.deletarCliente(cliente);
  }
    
  public List<Cliente> getAllClientes() {
      return repositorioCliente.getAll();
  }
    
  public Produto inserirProduto(Produto produto) throws ProdutoJaCadastradoException {
    repositorioProduto.inserirProduto(produto);
    return produto;
  }

  public Produto buscarProduto(String codigo) throws ProdutoNaoCadastradoException  {
    return repositorioProduto.buscarProduto(codigo);
  }

  public void alterarProduto(Produto produto) throws ProdutoNaoCadastradoException  {
      repositorioProduto.alterarProduto(produto);
  }
    
  public void excluirProduto(Produto produto) throws ProdutoNaoCadastradoException {
    repositorioProduto.deletarProduto(produto);
  }
    
  
    
  public List<Produto> getAllProdutos() {
    return repositorioProduto.getAll();
  }

  public List<Produto> getAllContas(String cpf) {
    return repositorioProduto.getAll(cpf);
  }

  public void exit() {
  }









}