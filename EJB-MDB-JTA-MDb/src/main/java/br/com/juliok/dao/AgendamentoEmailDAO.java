package br.com.juliok.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.juliok.entidade.AgendamentoEmail;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN) // controle da transação assumido pela classe e nao pelo EJB
public class AgendamentoEmailDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Inject
	private UserTransaction userTransaction;
	
	
	
	public List<AgendamentoEmail> listar(){
		
		return entityManager.createQuery("select ae FROM AgendamentoEmail ae", AgendamentoEmail.class).getResultList();
		
		
		
	}
	
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
		
	}
	
	public List<AgendamentoEmail> listarPorNaoAgendado(){
		return entityManager.createQuery("SELECT ae from AgendamentoEmail ae where ae.agendado = false", AgendamentoEmail.class)
				.getResultList();
		
	}
	
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
		
		try {
			userTransaction.begin();
			entityManager.merge(agendamentoEmail);  // apenas um update, nao é persist .. merge - Objeto já existe
			
			userTransaction.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
		

}
