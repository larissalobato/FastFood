package repository.pedido;

import java.util.ArrayList;
import java.util.List;

import model.pedido.Pedido;

public class RepositorioPedidoLista implements RepositorioPedido {

  private List<Pedido> pedidos;

  public RepositorioPedidoLista() {
    pedidos = new ArrayList<>();
  }

  @Override
  public void inserirPedido(Pedido pedido) throws PedidoJaCadastradoException {
    if (pedidos.contains(pedido)) {
      throw new PedidoJaCadastradoException();
    }
    pedidos.add(pedido);
  }

  @Override
  public void finalizarPedido(Pedido pedido) throws PedidoNaoCadastradoException {
    int index = pedidos.indexOf(pedido);
    if (index != -1) {
      Pedido pedidoFinalizado = pedidos.get(index);
      pedidos.remove(index);
      pedidos.add(index, pedidoFinalizado);
    } else {
      throw new PedidoNaoCadastradoException();
    }
  }

  @Override
  public Pedido buscarPedido(String numero) throws PedidoNaoCadastradoException {
    for (Pedido pedido : pedidos) {
      if (pedido.getNumero().equals(numero)) {
        return pedido;
      }
    }
    throw new PedidoNaoCadastradoException();
  }

  @Override
  public void cancelarPedido(Pedido pedido) throws PedidoNaoCadastradoException {
    if (!pedidos.remove(pedido)) {
      throw new PedidoNaoCadastradoException();
    }
  }

  @Override
  public List<Pedido> getAll() {
    return new ArrayList<>(pedidos);
  }

  @Override
  public List<Pedido> getAll(String cpf) {
    List<Pedido> listaPedidos = new ArrayList<>();
    for (Pedido pedido : pedidos) {
      if (pedido.getCliente().getCpf().equals(cpf)) {
        listaPedidos.add(pedido);
      }
    }
    return listaPedidos;
  }
}

