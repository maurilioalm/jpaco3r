package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario1 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, 3L);
		usuario.setNome("Roberto");
		usuario.setEmail("roberto@gmail.com");
		
		//Merge faz o Update do objeto
		em.merge(usuario);
		
		em.getTransaction().commit();
				
		em.close();
		emf.close();

	}

}
