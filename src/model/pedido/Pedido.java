package model.pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.cliente.Cliente;
import model.produto.Produto;

public class Pedido {

    private String numero;
    private LocalDate data;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double valorTotal;

    public Pedido(Cliente cliente) {
        this.numero = "";
        this.data = LocalDate.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }
    

    public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    
    public double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


	public void adicionarItemPedido(Produto produto, int quantidade) {
        // Verifica se o produto já existe na lista de itens
        for (ItemPedido item : itens) {
            if (item.getProduto().equals(produto)) {
                // Atualiza a quantidade do item
                item.setQuantidade(item.getQuantidade() + quantidade);
                valorTotal += produto.getPreco() * quantidade;
                return;
            }
        }
        
        // Se o produto não existe na lista, cria um novo item
        ItemPedido novoItem = new ItemPedido(produto, quantidade);
        itens.add(novoItem);
        valorTotal += novoItem.getSubtotal();
    }


    public void removerItemPedido(Produto produto) {
        ItemPedido itemRemover = null;
        for (ItemPedido item : itens) {
            if (item.getProduto().equals(produto)) {
                itemRemover = item;
                break;
            }
        }
        if (itemRemover != null) {
            itens.remove(itemRemover);
            valorTotal -= itemRemover.getSubtotal();
        }
    }
    
   

}


