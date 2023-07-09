package repository.pedido;

import pattern.IdGeneratorStrategy;

public class SequencialPedidoIdGeneratorStrategy implements IdGeneratorStrategy {

  private int nextNumber;

  public SequencialPedidoIdGeneratorStrategy() {
    this.nextNumber = 1;
  }
  
  public String nextId() {
    return String.valueOf(nextNumber++);
  }

}

