package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.interceptador.LogInterceptador;
import br.com.caelum.livraria.modelo.Autor;

//Transformar em EJB -> simples adicionar @Stateless ou @Statefull - JNDI
//JTA - por padrão usa o CMT - container managed transaction

//Para trabalhar com begin e commit é necessário modificar de CONTAINER para BEAN, assim quem gerencia o commit é o código e não a API
//para usar o UserTransaction, modificar o TransactionManagementType para Bean - usar @Inject para égar o UserTransaction do EJB e utilizar os métodos tradicionais de beign e commit , mas quem faz isso não é o EntityManager e sim  o userTransaction pelo EJB

@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER) //opcional
//@TransactionManagement(TransactionManagementType.BEAN) // BMT - Bean Managed Transaction
//@Interceptors({LogInterceptador.class}) // passar o array de classes que fará a interceptação, aqui estamos passando a LogInterceptador que está declarada em outro pacote - tecnicamente qdo chamar essa classe vai ocorrer a interceptação pela classe declarda como interceptor
public class AutorDao {

	@PersistenceContext
	private EntityManager manager;
	
	//capturar o UserTransactionq está no EJB
	//@Inject
	//UserTransaction tx;

	@PostConstruct
	void aposCriacao() {
		System.out.println("CALL BACK - Autor DAO Criado");
	}

	
	// Required - busca se já tem uma transação e a gerencia
	// mandatory - Obrigatório - quem faz a chamada deve abrir uma transação 
	// Never - mão pode participar o método em uma transação
	
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED) // opcional - sempre é necessário ter uma transação - já está configurado por default - qdo utilizado BMT - BEAN - não faz sentidodefinir o AttributeTransaction
	public void salva(Autor autor) {
		System.out.println("Salvar Autor" + autor.getNome());

		
			//tx.begin();
		manager.persist(autor);
			//tx.commit();
				

		System.out.println("Autor Salvo.... -> " + autor.getNome());

//		try {
//		Thread.sleep(10000);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		
		
		
		//simular o problema de uma consulta externa a um serviço que pode falhar
		//a transação falha e faz rollback e não salva o autor por causa da exceção.
		
		//exceções uncheck - obriga o rollback
		//exceções checked - sem rollback
		
		//throw new RuntimeException("Serviço externo deu erro"); // ele faz o RollbackException por usar o modo Container e nao conseguiu dar o commit

	}

	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}

}
