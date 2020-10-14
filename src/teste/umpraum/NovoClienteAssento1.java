package teste.umpraum;

import infra.DAO;
import modelo.umpraum.Assento;
import modelo.umpraum.Cliente;

public class NovoClienteAssento1 {

	public static void main(String[] args) {
		Assento assento = new Assento("8F");
		Cliente cliente = new Cliente("Carlos", assento);
		
		DAO<Object> dao = new DAO<>();
		
		dao.abrirTransaction()
			.incluir(assento)
			.incluir(cliente)
			.fecharTransaction()
			.fechar();
	}

}
