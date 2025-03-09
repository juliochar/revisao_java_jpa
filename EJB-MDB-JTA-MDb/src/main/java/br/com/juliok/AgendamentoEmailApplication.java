package br.com.juliok;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//JAX-RS -> precisa extender Application para poder usar os recursos

@ApplicationPath("/")
public class AgendamentoEmailApplication extends Application {
	
	

}
