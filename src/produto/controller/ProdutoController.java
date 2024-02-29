package produto.controller;

import java.util.ArrayList;
import produto.model.Produto;
import produto.repository.ProdutoRepository;

public class ProdutoController implements ProdutoRepository {

	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
	int id;

	@Override
	public void criarProduto(Produto produto) {
		boolean idExistente = listaProdutos.stream().anyMatch(p -> p.getId() == produto.getId());

		if (!idExistente) {
			listaProdutos.add(produto);
			System.out.println("\n O Produto " + produto.getId() + " foi criado com sucesso!");
		} else {
			System.out.println("\n O Produto " + produto.getId() + " já existe!");
		}
	}

	@Override
	public void listarTodos() {
		for (var produto : listaProdutos) {
			produto.visualizar();
		}
	}

	@Override
	public void consultarProdutoPorID(int id) {
		var produto = buscarNaCollection(id);

		if (produto != null)
			produto.visualizar();
		else
			System.out.println("\nO produto " + id + " não foi encontrada!");

	}

	@Override
	public void atualizarProduto(Produto produto) {
		var buscaProduto = buscarNaCollection(produto.getId());

		if (buscaProduto != null) {
			listaProdutos.set(listaProdutos.indexOf(buscaProduto), produto);
			System.out.println("\nO produto " + produto.getId() + " foi atualizada com sucesso!");
		} else
			System.out.println("\nO produto " + produto.getId() + "  não foi encontrada!");

	}

	@Override
	public void deletarProduto(int id) {
		var produto = buscarNaCollection(id);

		if (produto != null) {
			if (listaProdutos.remove(produto) == true)
				System.out.println("\nO produto " + id + " foi deletada com sucesso!");

		} else {
			System.out.println("\nO produto " + id + " não foi encontrada!");
		}
	}

	public Produto buscarNaCollection(int id) {
		for (var produto : listaProdutos) {
			if (produto.getId() == id) {
				return produto;
			}
		}
		return null;
	}

}
