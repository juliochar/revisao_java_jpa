package br.com.alura.loja.modelo;

import javax.persistence.Entity;

//utilizar herança com JPA - classe Pai: Produto
//Utilizar Estratégia para trabalhar com herança no JPA - Single Table - uma tabela única com todos os dados

//Outra Estratégia é a JOINED -> cria uma tabela por classe e faz o relacionamento com o id de cada subtabela com o mesmo ID da tabela mae, necessário JOIN para realizar as consultas

@Entity
public class Livro extends Produto {
	private String autor;
	private Integer numeroDePaginas;
	
	public Livro() {
		
	}
		
	public Livro(String autor, Integer numeroDePaginas) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}


	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}
	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}
	
	

}
