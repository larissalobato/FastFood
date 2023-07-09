package repository.pedido;

import repository.RepositoryException;

public class PedidoNaoCadastradoException extends RepositoryException {

  public PedidoNaoCadastradoException() {
    super("Pedido não cadastrado");
  }

}


