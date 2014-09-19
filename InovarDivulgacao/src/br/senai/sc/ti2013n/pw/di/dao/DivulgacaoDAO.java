package br.senai.sc.ti2013n.pw.di.dao;

import javax.persistence.EntityManager;

import br.senai.sc.ti2013n.pw.di.entity.Divulgacao;
import br.senai.sc.ti2013n.pw.di.util.JpaUtil;

public class DivulgacaoDAO {

	private static EntityManager entityManager;

	public static void main(String[] args) {
		JpaUtil.iniciarPersistenceUnit();
		entityManager = JpaUtil.getEntityManager();

		cadastrarDivulgacao();
		excluirDivulgacao();
		editarDivulgacao();
		listarDivulgacao();

		entityManager.close();
		JpaUtil.fecharPersistenceUnit();
	}

	private static void cadastrarDivulgacao() {
		Divulgacao divulgacao = new Divulgacao();
		divulgacao.setNomeDivulgacao("Nome");
		divulgacao.setDescricaoDivulgacao("Descrição");
		divulgacao.setCategoriaDivulgacao("Categoria");
		divulgacao.setLocalDivulgacao("Local");
		divulgacao.setSiteDivulgacao("Site");
		divulgacao.setTelefoneDivulgacao("Telefone");

		entityManager.getTransaction().begin();
		entityManager.persist(divulgacao);
		entityManager.getTransaction().commit();
	}

	private static Divulgacao listarDivulgacao() {
		Divulgacao divulgacao = null;
		try {
			divulgacao = entityManager.find(Divulgacao.class, 1L);
		} finally {
			entityManager.close();
		}
		return divulgacao;
	}

	private static void excluirDivulgacao() {
		entityManager.getTransaction().begin();
		Divulgacao divulgacao = entityManager.find(Divulgacao.class, 1L);
		entityManager.remove(divulgacao);
		entityManager.getTransaction().commit();
	}

	private static void editarDivulgacao() {
		entityManager.getTransaction().begin();
		Divulgacao divulgacao = entityManager.find(Divulgacao.class, 1L);
		divulgacao.setNomeDivulgacao("Nome 01");
		divulgacao.setDescricaoDivulgacao("Descrição 01");
		divulgacao.setCategoriaDivulgacao("Categoria 01");
		divulgacao.setLocalDivulgacao("Local 01");
		divulgacao.setSiteDivulgacao("Site 01");
		divulgacao.setTelefoneDivulgacao("Telefone 01");
		entityManager.merge(divulgacao);
		entityManager.getTransaction().commit();

	}
}
