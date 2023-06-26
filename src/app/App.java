package app;

import java.util.List;
import java.util.Scanner;

import facade.FastFoodException;
import facade.FastFoodFacade;
import model.cliente.Cliente;
import model.produto.Produto;
import repository.RepositoryException;

class App {

	static FastFoodFacade facade = new FastFoodFacade();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		facade = new FastFoodFacade();

		CriaDadosDeTeste();

		int opcao;
		do {
			limpaTela();
			System.out.println("MENU PRINCIPAL");
			System.out.println("==== =========");
			System.out.println();
			System.out.println("<1> Cadastro Clientes");
			System.out.println("<2> Cadastro Produtos");
			System.out.println("<3> Fazer pedido");
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
				// fazerPedido();
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
		System.out.print("Sexo (1) Mas (2) Fem: ");
		int sexo = scanner.nextInt();
		System.out.print("Telefone: ");
		String fone = scanner.next();

		Cliente cliente = new Cliente(cpf, nome, sexo, fone);

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
		System.out.printf("CPF          Nome                 Sexo Telefone\n");
		System.out.printf("============== ==================== ==== ===============\n");
		for (Cliente cliente : clientes) {
			System.out.printf("%14s ", cliente.getCpf());
			System.out.printf("%-20s ", cliente.getNome());
			System.out.printf("%-4s ", String.valueOf(cliente.getSexo()));
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
			System.out.println("Sexo: " + cliente.getSexo());
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

			System.out.println("Sexo: " + cliente.getSexo());
			System.out.print("Sexo (<enter> = Não alterar): ");
			String sexo = scanner.nextLine();
			if (!sexo.equals("")) {
				cliente.setSexo(sexo.charAt(0));
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
	
	
	
	//////////////////////////
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
	        System.out.println("Nome: " + produto.getNomeProduto());
	        System.out.println("Preço: " + produto.getPreco());
	        System.out.println();

	        System.out.print("Excluir esse produto? (s/n)?");
	        String resposta = scanner.nextLine();

	        if (resposta.equalsIgnoreCase("s")) {
	            facade.excluirProduto(produto);
	            System.out.println("Produto excluído!");
	        }
	    } catch (RepositoryException ex) { // remover | FastFoodException ex
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
	        System.out.println("Nome: " + produto.getNomeProduto());
	        System.out.print("Nome (<enter> = Não alterar): ");
	        String nome = scanner.nextLine();
	        if (!nome.equals("")) {
	        	produto.setNomeProduto(nome);
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

	    } catch (RepositoryException ex) {  //remover | FastFoodException ex
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
		        System.out.println("Nome: " + produto.getNomeProduto());
		        System.out.println("Preço: " + produto.getPreco());
		        System.out.println("---------------------------------");
		    }
		}
		
		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}
	
	/*
	private static void listarProdutos() {
		limpaTela();
		System.out.println("Lista de Produtos");
		System.out.println("=================");
		System.out.println();
		List<Produto> produtos = facade.getAllProdutos();

		if (produtos.isEmpty()) {
		    System.out.println("Nenhum produto cadastrado.");
		} else {
			System.out.printf("Código         Nome                 Preço\n");
			System.out.printf("============== ==================== ======\n");
			for (Produto produto : produtos) {
				System.out.printf("%14s ",  produto.getCodigo());
				System.out.printf("%-20s ", produto.getNomeProduto());
				System.out.printf("%15s\n", produto.getPreco());
			
		    }
		}
		
		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}*/
	
	//////////////////////////
	

	private static void CriaDadosDeTeste() {
		try {

			facade.inserirCliente(new Cliente("111.111.111-11", "João Batista", 1, "99111-1111"));

			facade.inserirCliente(new Cliente("222.222.222-22", "Paula Leite", 2, "99222-2222"));

			facade.inserirProduto(new Produto("1111", "batata frita", 4.75));
			//facade.inserirConta(new ContaPoupanca(facade.buscarCliente("222.222.222-22"), "6789-0"));

		} catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
	}
}