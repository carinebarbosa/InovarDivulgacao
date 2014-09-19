package br.senai.sc.ti2013n.pw.di.dao;

import javax.persistence.EntityManager;

import br.senai.sc.ti2013n.pw.di.entity.Pessoa;
import br.senai.sc.ti2013n.pw.di.util.JpaUtil;

public class PessoaDAO {

	private static EntityManager entityManager;

	public static void main(String[] args) {
		JpaUtil.iniciarPersistenceUnit();
		entityManager = JpaUtil.getEntityManager();

		cadastrarCliente();
		excluirCliente();
		editarCliente();
		listarCliente();

		entityManager.close();
		JpaUtil.fecharPersistenceUnit();
	}

	private static void cadastrarCliente() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Nome");
		pessoa.setSobreNome("Sobre Nome");
		pessoa.seteMail("E-Mail");
		pessoa.setTelefone("Telefone");
		entityManager.getTransaction().begin();
		entityManager.persist(pessoa);
		entityManager.getTransaction().commit();
	}

	private static Pessoa listarCliente() {
		Pessoa pessoa = null;
		try {
			pessoa = entityManager.find(Pessoa.class, 1L);
		} finally {
			entityManager.close();
		}
		return pessoa;
	}

	private static void excluirCliente() {
		entityManager.getTransaction().begin();
		Pessoa pessoa = entityManager.find(Pessoa.class, 1L);
		entityManager.remove(pessoa);
		entityManager.getTransaction().commit();
	}

	private static void editarCliente() {
		entityManager.getTransaction().begin();
		Pessoa pessoa = entityManager.find(Pessoa.class, 1L);
		pessoa.setNome("Nome 01");
		pessoa.setSobreNome("Sobre Nome 01");
		pessoa.seteMail("E-Mail 01");
		pessoa.setTelefone("Telefone 01");
		entityManager.merge(pessoa);
		entityManager.getTransaction().commit();

	}
}
