package produto.repository;

import produto.model.Produto;

public interface ProdutoRepository {

	// CRUD da Conta
	public void criarProduto(Produto produto);
	public void listarTodos();
	public void consultarProdutoPorID(int id);
	public void atualizarProduto(Produto produto);
	public void deletarProduto(int id);
	
}
