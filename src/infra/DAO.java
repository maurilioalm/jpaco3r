package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO<T> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<T> classe;
	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		} catch (Exception e) {

		}
	}

	public DAO() {
		this(null);
	}

	public DAO(Class<T> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}

	public DAO<T> abrirTransaction() {
		em.getTransaction().begin();
		return this;
	}

	public DAO<T> fecharTransaction() {
		em.getTransaction().commit();
		return this;
	}

	public DAO<T> incluir(T entidade) {
		em.persist(entidade);
		return this;
	}

	public DAO<T> incluirCompleto(T entidade) {
		return this.abrirTransaction().incluir(entidade).fecharTransaction();
	}
	
	public T obterPorId(Object id) {
		return em.find(classe, id);
	}

	public List<T> obterTodos() {
		return this.obterTodos(10, 0);
	}

	public List<T> obterTodos(int quantidade, int delocamento) {
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula");
		}
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<T> query = em.createQuery(jpql, classe);
		query.setMaxResults(quantidade);
		query.setFirstResult(delocamento);
		return query.getResultList();
	}

	public void fechar() {
		em.close();
	}
}
