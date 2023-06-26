package model.produto;

public class SaldoInsuficienteException extends Exception {
    
    private Produto conta;
    private double valorSaque;
    
    public SaldoInsuficienteException(Produto conta, double valorSaque) {
        super("Não há saldo suficiente na conta");
        this.conta = conta;
        this.valorSaque = valorSaque;
    }
    
    public Produto getConta() {
        return conta;
    }
    
    public double getValorSaque(){
        return this.valorSaque;
    }
}