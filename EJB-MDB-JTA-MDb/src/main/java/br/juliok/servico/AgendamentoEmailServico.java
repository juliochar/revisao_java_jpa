package br.juliok.servico;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.juliok.dao.AgendamentoEmailDAO;
import br.com.juliok.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {
	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmailServico.class.getName()); // instanciar o logger padrão do java.util - forma tradicional
	
	@Inject
	private AgendamentoEmailDAO dao;

	public List<AgendamentoEmail> listar() {
				
		
		return dao.listar();
		
	}
	
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(false);
		
		dao.inserir(agendamentoEmail);
		
		
	}
	
	public List<AgendamentoEmail> listarPorNaoAgendado(){
		return dao.listarPorNaoAgendado();
	}
	
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
//		if(agendamentoEmail.getEmail().equals("carol@hotmail.com")) {
//			throw new RuntimeException("Não foi possível alterar");
//		} else {
//			
//			
//		}
//		
		
		agendamentoEmail.setAgendado(true);
		dao.alterar(agendamentoEmail);
		
	}
	
	public void enviar(AgendamentoEmail agendamentoEmail) {
		try {
			Thread.sleep(5000);
			LOGGER.info("O Email do(a) Usuário(a) " + agendamentoEmail.getEmail() +
					" foi enviado!");
			
		} catch(Exception e) {
			LOGGER.warning(e.getMessage());
		}
		
	}
	
}
