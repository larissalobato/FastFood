package repository.produto;

import repository.RepositoryException;

public class ProdutoJaCadastradoException extends RepositoryException {

    public ProdutoJaCadastradoException() {
        super("Produto já cadastrada");
    }
    
    
}
