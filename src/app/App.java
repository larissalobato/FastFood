package app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import facade.FastFoodException;
import facade.FastFoodFacade;
import model.cliente.Cliente;
import model.pedido.ItemPedido;
import model.pedido.Pedido;
import model.produto.Produto;
import repository.RepositoryException;
import repository.pedido.PedidoNaoCadastradoException;

class App {

	static FastFoodFacade facade = new FastFoodFacade();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		facade = new FastFoodFacade();

		int opcao;
		do {
			limpaTela();
			System.out.println("MENU PRINCIPAL");
			System.out.println("==== =========");
			System.out.println();
			System.out.println("<1> Cadastro Clientes");
			System.out.println("<2> Cadastro Produtos");
			System.out.println("<3> Fazer Pedido");
			System.out.println("<4> Listar Pedidos");
			System.out.println("<0> Sair");
			System.out.println();
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}

			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				cadastroClientes();
				break;
			case 2:
				cadastroProdutos();
				break;
			case 3:
				fazerPedido();
				break;
			case 4: 
				listarPedidos();
				break;
			case 5: 
				//relatorioVenda();
				break;
			}
		} while (opcao != 0);

		facade.exit();
		System.out.println("Programa terminado");
	}

	private static void limpaTela() {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}

	private static void cadastroClientes() {
		int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRO CLIENTES");
			System.out.println("======== ========");
			System.out.println();
			System.out.println("<1> Incluir Cliente");
			System.out.println("<2> Alterar Cliente");
			System.out.println("<3> Excluir Cliente");
			System.out.println("<4> Listar Clientes");
			System.out.println("<0> Menu Principal");
			System.out.println();
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}

			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				incluirCliente();
				break;
			case 2:
				alterarCliente();
				break;
			case 3:
				excluirCliente();
				break;
			case 4:
				listarCliente();
				break;
			}
		} while (opcao != 0);
	}

	private static void incluirCliente() {
		limpaTela();
		System.out.println("Cadastro de Cliente");
		System.out.println("===================");
		System.out.print("CPF: ");
		String cpf = scanner.next();
		System.out.print("Nome: ");
		String nome = scanner.next();
		System.out.print("Telefone: ");
		String fone = scanner.next();

		Cliente cliente = new Cliente(cpf, nome, fone);

		try {
			facade.inserirCliente(cliente);
			System.out.println("Cliente cadastrado!");
		} catch (RepositoryException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
	}

	private static void listarCliente() {
		limpaTela();
		List<Cliente> clientes = facade.getAllClientes();
		System.out.printf("CPF          Nome                  Telefone\n");
		System.out.printf("============== ==================== ===============\n");
		for (Cliente cliente : clientes) {
			System.out.printf("%14s ", cliente.getCpf());
			System.out.printf("%-20s ", cliente.getNome());
			System.out.printf("%15s\n", cliente.getTelefone());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	private static void excluirCliente() {
		limpaTela();
		System.out.println("Excluir de Cliente");
		System.out.println("==================");
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();

		try {
			Cliente cliente = facade.buscarCliente(cpf);
			System.out.println();
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Telefone: " + cliente.getTelefone());
			System.out.println();

			System.out.print("Exclui esse cliente? (s/n)?");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("s")) {
				facade.excluirCliente(cliente);
				System.out.println("Cliente excluído!");
			}

		} catch (RepositoryException | FastFoodException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	private static void alterarCliente() {
		limpaTela();
		System.out.println("Alterar de Cliente");
		System.out.println("==================");
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();

		try {
			Cliente cliente = facade.buscarCliente(cpf);

			System.out.println();
			System.out.println("Nome: " + cliente.getNome());
			System.out.print("Nome (<enter> = Não alterar): ");
			String nome = scanner.nextLine();
			if (!nome.equals("")) {
				cliente.setNome(nome);
			}

			System.out.println("Telefone: " + cliente.getTelefone());
			System.out.print("Telefone (<enter> = Não alterar): ");
			String fone = scanner.nextLine();
			if (!fone.equals("")) {
				cliente.setTelefone(fone);
			}

			System.out.println();

			facade.alterarCliente(cliente);
			System.out.println("Cliente Alterado!");
			System.out.println();

		} catch (RepositoryException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	private static void cadastroProdutos() {
		int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRO PRODUTOS");
			System.out.println("======== ======");
			System.out.println();
			System.out.println("<1> Incluir Produto");
			System.out.println("<2> Excluir Produto");
			System.out.println("<3> Alterar Produto");
			System.out.println("<4> Listar Produtos");
			System.out.println("<0> Menu Principal");
			System.out.println();
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}

			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				incluirProduto();
				break;
			case 2:
				excluirProduto();
				break;
			case 3:
				alterarProduto();
				break;
			case 4:
				listarProdutos();
				break;
			}
		} while (opcao != 0);
	}
	
	private static void incluirProduto() {
	    limpaTela();
	    System.out.println("Cadastro de Produto");
	    System.out.println("===================");
	    System.out.print("Código: ");
	    String codigo = scanner.nextLine();
	    System.out.print("Nome: ");
	    String nome = scanner.nextLine();
	    System.out.print("Preço: ");
	    double preco = scanner.nextDouble();
	    scanner.nextLine();

	    Produto produto = new Produto(codigo, nome, preco);

	    try {
	        facade.inserirProduto(produto);
	        System.out.println("Produto cadastrado!");
	    } catch (RepositoryException ex) {
	        System.err.println(ex.getMessage());
	    }

	    System.out.println("tecle <enter> para voltar");
	    scanner.nextLine();
	}
	
	private static void excluirProduto() {
	    limpaTela();
	    System.out.println("Excluir Produto");
	    System.out.println("================");
	    System.out.print("Código do Produto: ");
	    String codigo = scanner.nextLine();

	    try {
	        Produto produto = facade.buscarProduto(codigo);
	        System.out.println();
	        System.out.println("Nome: " + produto.getNome());
	        System.out.println("Preço: " + produto.getPreco());
	        System.out.println();

	        System.out.print("Excluir esse produto? (s/n)?");
	        String resposta = scanner.nextLine();

	        if (resposta.equalsIgnoreCase("s")) {
	            facade.excluirProduto(produto);
	            System.out.println("Produto excluído!");
	        }
	    } catch (RepositoryException ex) { 
	        System.err.println(ex.getMessage());
	    }

	    System.out.println();
	    System.out.println("tecle <enter> para voltar");
	    scanner.nextLine();
	}
			
	private static void alterarProduto() {
	    limpaTela();
	    System.out.println("Alterar Produto");
	    System.out.println("================");
	    System.out.print("Código do Produto: ");
	    String codigo = scanner.nextLine();

	    try {
	        Produto produto = facade.buscarProduto(codigo);

	        System.out.println();
	        System.out.println("Nome: " + produto.getNome());
	        System.out.print("Nome (<enter> = Não alterar): ");
	        String nome = scanner.nextLine();
	        if (!nome.equals("")) {
	        	produto.setNome(nome);
	        }
	        System.out.println("Preço: " + produto.getPreco());
	        System.out.print("Preço (<enter> = Não alterar): ");
	        String precoStr = scanner.nextLine();
	        if (!precoStr.equals("")) {
	            double preco = Double.parseDouble(precoStr);
	            produto.setPreco(preco);
	        }

	        facade.alterarProduto(produto);
	        System.out.println("Produto alterado!");

	    } catch (RepositoryException ex) { 
	        System.err.println(ex.getMessage());
	    }

	    System.out.println();
	    System.out.println("tecle <enter> para voltar");
	    scanner.nextLine();
	}
			
	private static void listarProdutos() {
		limpaTela();
		System.out.println("Lista de Produtos");
		System.out.println("=================");
		System.out.println();
		List<Produto> produtos = facade.getAllProdutos();

		if (produtos.isEmpty()) {
		    System.out.println("Nenhum produto cadastrado.");
		} else {
		    for (Produto produto : produtos) {
		        System.out.println("Código: " + produto.getCodigo());
		        System.out.println("Nome: " + produto.getNome());
		        System.out.println("Preço: " + produto.getPreco());
		        System.out.println("---------------------------------");
		    }
		}
		
		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}
	
	private static void fazerPedido(){
	    limpaTela();
	    System.out.println("FAZER PEDIDO");
	    System.out.println("===== ======");
	    System.out.println();

	    System.out.print("CPF do Cliente: ");
	    String cpfCliente = scanner.nextLine();

	    try {
	        Cliente cliente = facade.buscarCliente(cpfCliente);

	        Pedido pedido = facade.criarPedido(cliente);

	   
	        int opcao;
	        do {
	            limpaTela();
	            System.out.println("MENU PEDIDO");
	            System.out.println("==== ======");
	            System.out.println();
	            System.out.println("<1> Adicionar Item");
	            System.out.println("<2> Remover Item");
	            System.out.println("<3> Alterar Pedido");
	            System.out.println("<4> Exibir Detalhes do Pedido");
	            System.out.println("<5> Concluir Pedido");
	            System.out.println("<0> Menu Principal");
	            System.out.println();
	            System.out.print("Escolha uma opção: ");

	            try {
	                opcao = Integer.valueOf(scanner.nextLine());
	            } catch (Exception e) {
	                opcao = 0;
	            }

	            switch (opcao) {
	                case 0:
	                    limpaTela();
	                    break;
	                case 1:
	                    adicionarItemPedido(pedido);
	                    break;
	                case 2:
	                    removerItemPedido(pedido);
	                    break;
	                case 3:
	                    alterarPedido(pedido);
	                    break;
	                case 4:
	                    exibirDetalhesPedido(pedido);
	                    break;
	                case 5: 
	                	concluirPedido(pedido);
	            }
	        } while (opcao != 0);

	    } catch (RepositoryException ex) {
	        System.err.println(ex.getMessage());
	    }
	}
	
	private static void adicionarItemPedido(Pedido pedido) {
	    limpaTela();
	    System.out.println("ADICIONAR ITEM");
	    System.out.println("==============");
	    System.out.println();

	    List<Produto> produtos;
	    try {
	        produtos = facade.getAllProdutos();
	        System.out.println("Produtos Disponíveis:");
	        System.out.println("---------------------");

	        for (int i = 0; i < produtos.size(); i++) {
	            Produto produto = produtos.get(i);
	            System.out.println("Item " + (i + 1));
	            System.out.println("Código: " + produto.getCodigo());
	            System.out.println("Nome: " + produto.getNome());
	            System.out.println("Preço: " + produto.getPreco());
	            System.out.println("---------------------------------");
	        }

	        System.out.print("Código do Produto: ");
	        String codigoProduto = scanner.nextLine();

	        Produto produto = facade.buscarProduto(codigoProduto);
	        if (produto != null) {
	            System.out.println();
	            System.out.println("Nome: " + produto.getNome());
	            System.out.println("Preço: " + produto.getPreco());
	            System.out.println();

	            int quantidade;
	            while (true) {
	                try {
	                    System.out.print("Quantidade: ");
	                    quantidade = scanner.nextInt();
	                    scanner.nextLine();
	                    break;
	                } catch (InputMismatchException e) {
	                    System.err.println("Quantidade inválida. Digite um valor inteiro.");
	                    scanner.nextLine();
	                }
	            }

	            ItemPedido item = new ItemPedido(produto, quantidade);
	            facade.adicionarItemPedido(pedido, item.getProduto(), item.getQuantidade());

	            System.out.println("Item adicionado ao pedido!");
	        } else {
	            System.err.println("Produto não encontrado.");
	        }
	    } catch (RepositoryException ex) {
	        System.err.println(ex.getMessage());
	    }

	    System.out.println();
	    System.out.println("Pressione <Enter> para continuar");
	    scanner.nextLine();
	}





	private static void removerItemPedido(Pedido pedido) {
	    limpaTela();
	    System.out.println("REMOVER ITEM");
	    System.out.println("============");
	    System.out.println();

	    List<ItemPedido> itens = pedido.getItens();

	    if (itens.isEmpty()) {
	        System.out.println("Nenhum item no pedido.");
	    } else {
	        for (int i = 0; i < itens.size(); i++) {
	            ItemPedido item = itens.get(i);
	            System.out.println("Item " + (i + 1));
	            System.out.println("Código: " + item.getProduto().getCodigo());
	            System.out.println("Nome: " + item.getProduto().getNome());
	            System.out.println("Preço: " + item.getProduto().getPreco());
	            System.out.println("Quantidade: " + item.getQuantidade());
	            System.out.println("---------------------------------");
	        }

	        try {
	            System.out.print("Digite o número do item a ser removido: ");
	            int numeroItem = Integer.parseInt(scanner.nextLine());

	            if (numeroItem >= 1 && numeroItem <= itens.size()) {
	                ItemPedido itemRemovido = itens.remove(numeroItem - 1);
	                System.out.println("Item removido do pedido: " + itemRemovido.getProduto().getNome());
	            } else {
	                System.out.println("Número do item inválido!");
	            }
	        } catch (NumberFormatException ex) {
	            System.err.println("Número inválido. Digite um valor inteiro.");
	        }
	    }

	    System.out.println();
	    System.out.println("Pressione <Enter> para continuar");
	    scanner.nextLine();
	}


	private static void alterarPedido(Pedido pedido) {
	    limpaTela();
	    System.out.println("ALTERAR PEDIDO");
	    System.out.println("==============");
	    System.out.println();

	    List<ItemPedido> itens = pedido.getItens();

	    if (itens.isEmpty()) {
	        System.out.println("Nenhum item no pedido.");
	    } else {
	        for (int i = 0; i < itens.size(); i++) {
	            ItemPedido item = itens.get(i);
	            System.out.println("Item " + (i + 1));
	            System.out.println("Código: " + item.getProduto().getCodigo());
	            System.out.println("Nome: " + item.getProduto().getNome());
	            System.out.println("Preço: " + item.getProduto().getPreco());
	            System.out.println("Quantidade: " + item.getQuantidade());
	            System.out.println("---------------------------------");
	        }

	        System.out.print("Digite o número do item a ser alterado: ");
	        int numeroItem = Integer.parseInt(scanner.nextLine());

	        if (numeroItem >= 1 && numeroItem <= itens.size()) {
	            ItemPedido itemAlterar = itens.get(numeroItem - 1);
	            System.out.print("Digite a nova quantidade: ");
	            int novaQuantidade = Integer.parseInt(scanner.nextLine());
	            itemAlterar.setQuantidade(novaQuantidade);
	            System.out.println("Item alterado: " + itemAlterar.getProduto().getNome() + " - Nova quantidade: " + novaQuantidade);
	        } else {
	            System.out.println("Número do item inválido!");
	        }
	    }

	    System.out.println();
	    System.out.println("Pressione <Enter> para continuar");
	    scanner.nextLine();
	}

	private static void exibirDetalhesPedido(Pedido pedido) {
	    limpaTela();
	    System.out.println("DETALHES DO PEDIDO");
	    System.out.println("==================");
	    System.out.println();

	    System.out.println("Cliente: " + pedido.getCliente().getNome());
	    System.out.println("Data: " + pedido.getData());
	    System.out.println();

	    List<ItemPedido> itens = pedido.getItens();

	    if (itens.isEmpty()) {
	        System.out.println("Nenhum item no pedido.");
	    } else {
	        for (int i = 0; i < itens.size(); i++) {
	            ItemPedido item = itens.get(i);
	            System.out.println("Item " + (i + 1));
	            System.out.println("Código: " + item.getProduto().getCodigo());
	            System.out.println("Nome: " + item.getProduto().getNome());
	            System.out.println("Preço: " + item.getProduto().getPreco());
	            System.out.println("Quantidade: " + item.getQuantidade());
	            System.out.println("---------------------------------");
	        }
	    }
	    
	    // atualiza o valor total do pedido
	    double novoValorTotal = 0.0;
	    for (ItemPedido item : itens) {
	        novoValorTotal += item.getSubtotal();
	    }
	    pedido.setValorTotal(novoValorTotal);

	    System.out.println("Valor Total: " + pedido.getValorTotal());

	    System.out.println();
	    System.out.println("Pressione <Enter> para continuar");
	    scanner.nextLine();
	}


	private static void concluirPedido(Pedido pedido) {
	    limpaTela();
	    System.out.println("CONCLUIR PEDIDO");
	    System.out.println("===============");
	    System.out.println();

	    System.out.print("Deseja realmente concluir o pedido? (s/n): ");
	    String resposta = scanner.nextLine();

	    if (resposta.equalsIgnoreCase("s")) {
	        try {
	            facade.finalizarPedido(pedido);
	            System.out.println("Pedido realizado com sucesso!");
	        } catch (PedidoNaoCadastradoException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }

	    System.out.println();
	    System.out.println("Pressione <Enter> para continuar");
	    scanner.nextLine();
	}

	
	private static void listarPedidos() {
	    limpaTela();
	    System.out.println("LISTA DE PEDIDOS");
	    System.out.println("================");
	    System.out.println();

	    List<Pedido> pedidos = facade.getAllPedidos();

	    if (pedidos.isEmpty()) {
	        System.out.println("Nenhum pedido cadastrado.");
	    } else {
	        for (Pedido pedido : pedidos) {
	            System.out.println("Número do Pedido: " + pedido.getNumero());
	            System.out.println("Data: " + pedido.getData());
	            System.out.println("Cliente: " + pedido.getCliente().getNome());
	            System.out.println("Valor Total: " + pedido.getValorTotal());
	            System.out.println("---------------------------------");
	        }
	    }

	    System.out.println();
	    System.out.println("Pressione <Enter> para voltar");
	    scanner.nextLine();
	}

	
}

