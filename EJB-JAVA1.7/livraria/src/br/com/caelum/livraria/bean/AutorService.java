package br.com.caelum.livraria.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService {
	@Inject
	AutorDao dao;
	
	//required
	public void adiciona(Autor autor) {
		dao.salva(autor);
		
		//throw new LivrariaException(); //linha comentada que foi feita para simular uma exception personalizada que trata o erro e faz o ROLLBACK impedindo gravação de dados incompletos ou incosistentes
		
	}

	public List<Autor> todosAutores() {
		
		return dao.todosAutores();
	}
	
	
	

}
