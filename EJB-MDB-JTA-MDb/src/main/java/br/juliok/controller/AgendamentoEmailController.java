package br.juliok.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.juliok.entidade.AgendamentoEmail;
import br.juliok.servico.AgendamentoEmailServico;

@Path("emails")  // endpoint do JAX-WS
public class AgendamentoEmailController {
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;
	
	
	@GET  // Utilizará o Http GET 
	@Produces(value = MediaType.APPLICATION_JSON) // vai converter automaticamente para JSON
	public Response listar() {
		
		return Response.ok(agendamentoEmailServico.listar()).build();
		
		
		
	}
	
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON) // o parse do JSON para o Objeto fica implicito
	public Response inserir(AgendamentoEmail agendamentoEmail) {
		agendamentoEmailServico.inserir(agendamentoEmail);
		
		return Response.status(201).build();
	}
	
	

}
