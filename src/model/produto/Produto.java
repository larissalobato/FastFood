package model.produto;

//import model.cliente.Cliente;

public class Produto {
    private String codigo;
    private String nomeProduto;
    private double preco;
    
    public Produto(String codigo, String nomeProduto, double preco) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }
    

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String toString() {
        return String.format("Código: %s\nnomeProduto: %s\nPreco %.2f", 
                            this.codigo + " - " +
                            this.nomeProduto +
                            this.preco);
    }
}

	