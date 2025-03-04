package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {
	private String nome;
	private String cpf;
		
	public DadosPessoais() {}; // jpa precisa do construtor vazio para mapear
	
	public DadosPessoais(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public String getCpf() {
		return cpf;
	}


}
