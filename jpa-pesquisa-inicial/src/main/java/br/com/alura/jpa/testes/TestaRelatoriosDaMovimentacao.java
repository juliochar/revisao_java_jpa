package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Conta;

public class TestaRelatoriosDaMovimentacao {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select c from Conta c left join fetch c.movimentacoes"; // usando join fetch ajuda a resolver o problema da consulta N+1 - o fetch vai trazer as movimentacoes já na primeira consulta e faz o Hibernate fazer o join
		TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
		
		List<Conta> contas = query.getResultList();
		
		//Lazy loading - carrega os dados conforme precisa... somente qdo precisar dos dados ele faz outra query
		//Eager - ao contrário do Lazy,e ele carrega antecipadamente qdo tem um relacionamento
		//n+1 consultas - para da conta ele faz vários selects para buscar a s movimentações do relacionamento - usar o join fetch para resolver as consultas individuais por cada registro
		
		
		for(Conta conta: contas) {
			System.out.println("Titutlar: " + conta.getTitular());
			System.out.println("Agencia: " + conta.getAgencia());
			System.out.println("Numero: " + conta.getNumero());
			System.out.println("Movimentacoes: " + conta.getMovimentacoes());
					
			
		}
		
		
		
		
	}

}
