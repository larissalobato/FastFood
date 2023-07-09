package facade;

import java.util.List;

import model.cliente.Cliente;
import model.pedido.ItemPedido;
import model.pedido.Pedido;
import model.produto.Produto;
import repository.cliente.CPFJaCadastradoException;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.RepositorioCliente;
import repository.cliente.RepositorioClienteLista;
import repository.pedido.PedidoJaCadastradoException;
import repository.pedido.PedidoNaoCadastradoException;
import repository.pedido.RepositorioPedido;
import repository.pedido.RepositorioPedidoLista;
import repository.produto.ProdutoJaCadastradoException;
import repository.produto.ProdutoNaoCadastradoException;
import repository.produto.RepositorioProduto;
import repository.produto.RepositorioProdutoLista;

public class FastFoodFacade {

  private RepositorioCliente repositorioCliente;
  private RepositorioProduto repositorioProduto;
  private RepositorioPedido repositorioPedido;

  public FastFoodFacade() {
    repositorioCliente = new RepositorioClienteLista();
    repositorioProduto = new RepositorioProdutoLista();
    repositorioPedido = new RepositorioPedidoLista();
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

  public List<Produto> getAllProdutos(String codigo) {
    return repositorioProduto.getAll(codigo);
  }

  public Pedido criarPedido(Cliente cliente) throws PedidoJaCadastradoException {
    Pedido pedido = new Pedido(cliente);
    repositorioPedido.inserirPedido(pedido);
    return pedido;
  }

  public void adicionarItemPedido(Pedido pedido, Produto produto, int quantidade) {
    pedido.adicionarItemPedido(produto, quantidade);
  }

  public void removerItemPedido(Pedido pedido, Produto produto) {
    pedido.removerItemPedido(produto);
  }

  public void finalizarPedido(Pedido pedido) throws PedidoNaoCadastradoException {
    repositorioPedido.finalizarPedido(pedido);
  }

  public Pedido buscarPedido(String numero) throws PedidoNaoCadastradoException {
    return repositorioPedido.buscarPedido(numero);
  }

  public void cancelarPedido(Pedido pedido) throws PedidoNaoCadastradoException {
    repositorioPedido.cancelarPedido(pedido);
  }

  public List<Pedido> getAllPedidos() {
    return repositorioPedido.getAll();
  }

  public List<Pedido> getAllPedidos(String cpf) {
    return repositorioPedido.getAll(cpf);
  }

  public void exit() {
  }
}


