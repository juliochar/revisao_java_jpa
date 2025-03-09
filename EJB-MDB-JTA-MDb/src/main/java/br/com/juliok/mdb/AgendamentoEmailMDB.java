package br.com.juliok.mdb;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;

import br.com.juliok.entidade.AgendamentoEmail;
import br.juliok.servico.AgendamentoEmailServico;

// Classe responsável por ler as mensagens que estão na Fila lá do JMS / Queue adicionadas pelo context factory - pegar esses objetos e enviar


//criar um listener do JMS para a o EJB/JBOSS execute de acordo com as mensagens vão sendo inseridas
//Qual é a fila que ele vai escutar -> fazer isso pelo @MessageDriven

@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue ="java:/jms/queue/EmailQueue"),
									@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue")})
public class AgendamentoEmailMDB implements MessageListener{
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;

	@Override
	public void onMessage(Message message) { // vai ficar escutando a fila, quando tiver uma nova mensagem pega essa mensagem - transformar o objeto e enviar....
		
		try {	
			
			AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
			agendamentoEmailServico.enviar(agendamentoEmail);
			
			
			
		} catch (JMSException e) {
		
			throw new RuntimeException(e);
		}
		
		
	}

}
