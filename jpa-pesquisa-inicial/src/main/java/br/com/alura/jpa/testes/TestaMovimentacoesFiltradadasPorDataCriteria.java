package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TestaMovimentacoesFiltradadasPorDataCriteria {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(em);
		
		List<Movimentacao> movimentacoesFiltradasPorData = movimentacaoDao.getMovimentacoesFiltradaPortData(null, 1, null);
		
		for(Movimentacao mov : movimentacoesFiltradasPorData){
			System.out.println("Descricao: -> " + mov.getDescricao());
			System.out.println("Valor: -> " + mov.getValor());
			System.out.println("--------------------------------");
			
			
			
		}
		
		

	}

}
