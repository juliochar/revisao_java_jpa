package br.com.juliok.job;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.juliok.entidade.AgendamentoEmail;
import br.juliok.servico.AgendamentoEmailServico;

//Esta classe pega os agendamentos marcados como False na tabela do banco, envia o e-mail e assim q termina de enviar, marca como True no final. ao terminar o ciclo de mensagens, qdo a job executar novamente.. vai retorna uma lista vazia pq já enviou todos os agendamentos e nao possui mais falses, ou seja, nao possui mais e-mail a enviar

//Cada envio cria um novo objeto stateless - pode criar um problema de concorrencia de envio de e-mails
//pode acabar criando uma segunda instancia e tentar  enviar o e-mail 2 vezes....
//a solução é ter o controle das instanciar do EJB e mudar de Stateless para Singleton

//@Stateless   //transformado em EJB stateless com configuração padrão - o servidor de aplicação irá instanciar automaticamente - neste caso como ele possui um Schedule ele vai instanciar o objeto e chamar o metodo no schedule de acordo com os parametros definidos
@Singleton  // um EJB do tipo Singleton q retorna uma única instancia por vez mesmo usando o Schedule - o EJB3.timer detecta q já existe um timer anterior e faz um aviso alertando que pulará a execução
@TransactionManagement(TransactionManagementType.CONTAINER) // configuração padrão do EJB para JTA
public class AgendamentoEmailJob {
	
	
	//private static AgendamentoEmailJob instance;
	
//	private AgendamentoEmailJob() {
//		
//	}
	// o syncrhonized é para evitar concorrencia
	//Singleton
//	public synchronized static AgendamentoEmailJob getInstance() {
//		if(instance == null) {
//			instance = new AgendamentoEmailJob();
//		}
//		return instance;
//	}
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;
	
	//O EJB possuir um agendador chamado Schedule e pode ser configurado via atributos
//	@Schedule(hour="*", minute="*", second = "*/10") // * o asterisco significa todo minuto, todo segundo, a barra é a cada... /10 a cada dez segundos 
//	public void enviarEmail() {
//		List<AgendamentoEmail> listarPorNaoAgendado = agendamentoEmailServico.listarPorNaoAgendado();
//		
//		listarPorNaoAgendado.forEach(emailNaoAgendado -> {
//			agendamentoEmailServico.enviar(emailNaoAgendado);
//			agendamentoEmailServico.alterar(emailNaoAgendado);
//		});
//		
//	}
	
	/**
	 * no jboss cli criar a file para o JMS com o comando abaixo:
	 * jms-queue add --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue
	 * 
	 * criar uma factory da fila 
	 * 
	 */
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory") // fábrica de conexao com JMS padrão
	private JMSContext context;
	
	//a fila em si usando o JNDI da fila criado pelo console do jboss
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	//mudar a logica do enviar e-mail usando a fila - o mesmo método comentado acima, mas agora usando JMS
	@Schedule(hour="*", minute="*", second = "*/10") // * o asterisco significa todo minuto, todo segundo, a barra é a cada... /10 a cada dez segundos 
	@TransactionAttribute(TransactionAttributeType.REQUIRED) // configuração padrçao
	public void enviarEmail() {
		List<AgendamentoEmail> listarPorNaoAgendado = agendamentoEmailServico.listarPorNaoAgendado();
		
		listarPorNaoAgendado.forEach(emailNaoAgendado -> {
			context.createProducer().send(queue, emailNaoAgendado); // um producer recebendo o objeto que irá para a fila -> aqui de fato adiciona as mensagens -Objetos na fila - aparece lá no JBOSS admin console na parte de Messages com a EmailQueue adicionada
			agendamentoEmailServico.alterar(emailNaoAgendado);
		});
		
	}
	

}
