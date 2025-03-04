package br.com.juliok.timer;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

//Para agendar uma execução nao faz sentido ter um pool
//Singleton para ter apenas um Objeto e Startup para iniciar o agendamento assim q a aplicação subir
// @Schedule(hour="9,18") -> chama 09 e as 18 horas, atributo hour
// todo agendado é persistido automaticamente, gravando as configurações, pode-se remover isso com persistent=false

//EAR - modulo EJB  + MODULO WEB - enterprise archive -> junta a aplicação web com os EJB isolados, como esse que faz agendamento


@Singleton
@Startup
public class Agendador {
	
	@Schedule(hour="*",minute="*",second="*/10", persistent=false)
	void agendado() {
		System.out.println("verificando serviço externo periodicamente");
	}

}
