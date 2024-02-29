package produto;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import produto.util.Cores;
import produto.controller.ProdutoController;
import produto.model.Cosmetico;
import produto.model.Medicamento;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner leia = new Scanner(System.in);
		ProdutoController produtos = new ProdutoController();

		int opcao, id, tipo;
		String generico, fragancia, nomeProduto;
		float preco;

		opcao = 0;

		while (true) {

			System.out.println(Cores.TEXT_GREEN + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("        	    DROGARIA JAVA PHARMA   	 	         ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Produto                        ");
			System.out.println("            2 - Listar todas os produtos             ");
			System.out.println("            3 - Consultar produto por ID             ");
			System.out.println("            4 - Atualizar produto		             ");
			System.out.println("            5 - Deletar produto                      ");
			System.out.println("            6 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Digite o número da opção desejada:                   ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}
			if (opcao == 6) {
				System.out.println("\nAtendimento Java Pharma finalizado!");
				System.exit(0);
			}
			switch (opcao) {

			case 1:
				System.out.println("\n Criar Produto");
				System.out.println("\nDigite o ID do produto: ");
				id = leia.nextInt();
				System.out.println("Digite o Nome do Produto: ");
				leia.skip("\\R?");
				nomeProduto = leia.nextLine();
				System.out.println("Digite o preço do produto: ");
				preco = leia.nextFloat();

				do {
					System.out.println("Digite o Tipo de produto (1-Medicamento ou 2-Cosmético): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				switch (tipo) {
				case 1:
					System.out.println("\nDigite o nome genérico do produto: ");
					leia.skip("\\R?");
					generico = leia.nextLine();
					produtos.criarProduto(new Medicamento(id, nomeProduto, tipo, preco, generico));
					break;
				case 2:
					System.out.println("\nDigite o tipo de fragância do produto: ");
					fragancia = leia.nextLine();
					produtos.criarProduto(new Cosmetico(id, nomeProduto, tipo, preco, fragancia));
					break;

				}

				keyPress();
				break;
			case 2:
				System.out.println("\n Listar todas os produtos");
				produtos.listarTodos();
				keyPress();
				break;
			case 3:
				System.out.println("\n Consultar produto por ID");

				System.out.println("\nDigite o ID do produto: ");
				id = leia.nextInt();
				produtos.consultarProdutoPorID(id);
				keyPress();
				break;
			case 4:
				System.out.println("\n Atualizar dados do Produto");

				System.out.println("\nDigite o ID do produto: ");
				id = leia.nextInt();

				var buscaProduto = produtos.buscarNaCollection(id);

				if (buscaProduto != null) {

					tipo = buscaProduto.getTipo();

					System.out.println("Digite o Nome do Produto: ");
					leia.skip("\\R?");
					nomeProduto = leia.nextLine();
					System.out.println("Digite o preço do produto: ");
					preco = leia.nextFloat();

					switch (tipo) {
					case 1:
						System.out.println("Digite o nome genérico do produto: ");
						leia.skip("\\R?");
						generico = leia.nextLine();
						produtos.atualizarProduto(new Medicamento(id, nomeProduto, tipo, preco, generico));
						break;
					case 2:
						System.out.println("Digite o tipo de fragância do produto: ");
						fragancia = leia.nextLine();
						produtos.atualizarProduto(new Cosmetico(id, nomeProduto, tipo, preco, fragancia));
						break;

					default: {
						System.out.println("\nTipo de produto inválido!");
					}
					}
				} else {
					System.out.println("\nO produto não foi encontrado!");
				}

				keyPress();
				break;
			case 5:
				System.out.println("\n Deletar produto");
				System.out.println("\nDigite o ID do produto: ");
				id = leia.nextInt();
				produtos.deletarProduto(id);
				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);

				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar.");
			System.in.read();

		} catch (IOException e) {

			System.out.println("\nVocê pressionou uma tecla diferente de enter!");

		}
	}
}
