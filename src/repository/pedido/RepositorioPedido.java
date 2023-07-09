package repository.pedido;

import java.util.List;

import model.pedido.Pedido;

public interface RepositorioPedido {

  void inserirPedido(Pedido pedido) throws PedidoJaCadastradoException;

  void finalizarPedido(Pedido pedido) throws PedidoNaoCadastradoException;

  Pedido buscarPedido(String numero) throws PedidoNaoCadastradoException;

  void cancelarPedido(Pedido pedido) throws PedidoNaoCadastradoException;

  List<Pedido> getAll();

  List<Pedido> getAll(String cpf);


}
