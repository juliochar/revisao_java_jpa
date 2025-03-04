package br.com.caelum.livraria.interceptador;

import javax.interceptor.InvocationContext;
import javax.interceptor.AroundInvoke;


public class LogInterceptador {

	@AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {
		long millis = System.currentTimeMillis();
		
		//chamada do metodo do dao??
		
		
		Object o = context.proceed();
		
		String metodo = context.getMethod().getName();
		String nomeClass = context.getTarget().getClass().getSimpleName();
		
		System.out.println("NomeClasse:  " + nomeClass + " , Nome metodo: " + metodo);
		System.out.println("Tempo Gasto: " + (System.currentTimeMillis() - millis));
		
		return o;
		
		
	}
}
