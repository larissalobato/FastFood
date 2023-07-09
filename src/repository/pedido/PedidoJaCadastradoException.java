package repository.pedido;

import repository.RepositoryException;

public class PedidoJaCadastradoException extends RepositoryException {

    public PedidoJaCadastradoException() {
        super("Pedido já cadastrado");
    }
    
    
}


