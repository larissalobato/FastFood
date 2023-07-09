package repository.produto;

import repository.RepositoryException;

public class ProdutoNaoCadastradoException extends RepositoryException {

  public ProdutoNaoCadastradoException() {
    super("Produto não cadastrado");
  }

}

