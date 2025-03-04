package br.com.caelum.livraria.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.livraria.dao.LivroDao;

// http://localhost:8080/livraria/LivrariaWS?wsdl  -> este endereço vai mostrar a descrição do serviço
// esse tipo de webservice gerado automaticamente pelo JWS é dotipo SOAP/XML
//Utilizar a ferramenta SOAPUI para realizar as consultas soap externamente como se fosse um postman
//@Webresult é para organizar o xml da resposta
//Webparam modifica o <arg0> no XML de requisição para um nomeado conforme declarado, neste caso é o titulo

import br.com.caelum.livraria.modelo.Livro;

@WebService // JAX-WS - vai informar ao EJB que utilizará o este padrão
@Stateless
public class LivrariaWS {
	
	@Inject
	LivroDao dao;
	
	@WebResult(name="autores")
	public List<Livro> getLivrosPeloNome(@WebParam(name="titulo") String nome){
		System.out.println("LivrariaWS: procurando pelo nome " + nome);
		
		return dao.livrosPeloNome(nome);
	}

}
