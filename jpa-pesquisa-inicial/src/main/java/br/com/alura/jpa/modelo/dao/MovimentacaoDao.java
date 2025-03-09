package br.com.alura.jpa.modelo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.MediaComData;
import br.com.alura.jpa.modelo.Movimentacao;

public class MovimentacaoDao {
	private EntityManager em;
		
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}
	
	
	public List<Movimentacao> getMovimentacoesFiltradaPortData(Integer dia, Integer mes, Integer ano){
		//Criteria queries - com parametros q podem existir ou não:
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> query = builder.createQuery(Movimentacao.class);
		
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		if(dia != null) {
			//day(m.data)
			// primeiro a função do JPQL, depois o tipo de retorno e a partir do root qual o elemento da classe será utilizado no parametro da função
			Predicate predicate = builder.equal(builder.function("day",Integer.class, root.get("data")),dia);
			predicates.add(predicate);
			
		}
		if(mes != null) {
			//month(m.data)
			Predicate predicate = builder.equal(builder.function("month",Integer.class, root.get("data")),mes);
			predicates.add(predicate);
			
		}
		
		if(ano != null) {
			//year(m.data)
			Predicate predicate = builder.equal(builder.function("year",Integer.class, root.get("data")),ano);
			predicates.add(predicate);
			
		}
		
		query.where((Predicate[]) predicates.toArray(new Predicate[0])); // confuso, mas é para evitar criar objetos extras qdo transformar em array - deve ter uma melhor maneira de transportar esse objeto
		
		TypedQuery<Movimentacao> typedQuery = em.createQuery(query);
		return typedQuery.getResultList();
				
		
		
	}

	public List<MediaComData> getMediaDiariaDasmovimentacoes(){		

		//String jpql2 = "select new br.com.alura.jpa.modelo.MediaComData(avg(m.valor),day(m.data), month(m.data)) from Movimentacao m group by day(m.data), month(m.data), year(m.data)";
		//TypedQuery<MediaComData> query2 = em.createQuery(jpql2,MediaComData.class);
		
		TypedQuery<MediaComData> query2 = em.createNamedQuery("mediaDiariaMovimentacaoes", MediaComData.class);
		
		
				
		return query2.getResultList();
		
	}
	
	public BigDecimal getSomaDasMovimentacoes() {
				
		String jpql = "select sum(m.valor) from Movimentacao m";
		
		TypedQuery<BigDecimal> query = em.createQuery(jpql,BigDecimal.class );
		
		BigDecimal somaDasMovimentacoes = query.getSingleResult();
		
		return somaDasMovimentacoes;
	}

}
