package br.com.caelum.livraria.clientews;

import java.util.List;

public class TesteRequestSoapComJava {

	public static void main(String[] args) {
		
		LivrariaWS cliente = new LivrariaWSService().getLivrariaWSPort();
		
		List<Livro> livros = cliente.getLivrosPeloNome("Caminho");
		
		for(Livro livro: livros) {
			System.out.println(livro.getTitulo());
			System.out.println(livro.getAutor().getNome());
			
		}
		

	}

}
