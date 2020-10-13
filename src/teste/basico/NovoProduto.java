package teste.basico;

import infra.DAO;
import modelo.basico.Produto;

public class NovoProduto {

	public static void main(String[] args) {
		
		Produto produto = new Produto("Monitor", 1090.99);
		
		// Entender essa linha com calma
		DAO<Produto> dao = new DAO<>(Produto.class);
		dao.incluirCompleto(produto).fechar();
		System.out.println("ID: " + produto.getId());

	}

}
