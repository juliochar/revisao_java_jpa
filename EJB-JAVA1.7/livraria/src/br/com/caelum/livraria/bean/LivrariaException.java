package br.com.caelum.livraria.bean;

import javax.ejb.ApplicationException;


//define como uma aplication exception e desejamos caso ocorra faça o rollback em uma transação
//usando o extends RuntimeException podemos transformar a unchecked em checked e podemos remover o throws dos métodos que podem gerar exception - um controle maior sobre as exceções e o controle sobre o rollback qdo ocorrer o erro
//ApplicationException é unchecked e faz rollback
@ApplicationException(rollback=true)
public class LivrariaException extends RuntimeException {

}
