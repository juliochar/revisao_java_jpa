package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TestaSomaDasMovimentacoes {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		//MovimentacaoDao dao = new MovimentacaoDao(em);
		//System.out.println("A Soma das movimentacoes é: " + dao.getSomaDasMovimentacoes());
		
		//Usando Criteria Query
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
		
		//select m from movimentacao m
		Root<Movimentacao> root = query.from(Movimentacao.class); // é o from - montagem da String por Objetos usando Criteria, nao monta por String diretamente, internamente o JPA/HIBERNATE chega na query final
		Expression<BigDecimal> sum = builder.sum(root.<BigDecimal>get("valor")); // confuso , porém ele pega o valor pelo get e tipa para BigDecimal automaticamente - depois fará a soma dinamica montando o sql interno com Criteria do JPA que faz a ponte entre OBJETO e Consultas direto no código por OBJETOS e não pode string
		
		query.select(sum); //preparado acima e aqui de fato monta a consulta
		
		
		TypedQuery<BigDecimal> typedQuery = em.createQuery(query);
		
		System.out.println("A Soma das movimentacoes: " + typedQuery.getSingleResult());
		
		
		
		
//		String jpql2 = "select avg(m.valor) from Movimentacao m";
//		
//		TypedQuery<Double> query2 = em.createQuery(jpql2,Double.class);
//		
//		Double MediaDasMovimentacoes = query2.getSingleResult();
//		
//		System.out.println("A Soma das movimentacoes é: " + MediaDasMovimentacoes);
//		

	}

}
