package br.com.caelum.livraria.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

//AutorBean é chamado dentro de uma transação existente, sendo assim qdo invocar o DAO que está como Mandatory não dará erro - existe uma diferença entre usar REQUIRED e MANDATORY

@Model
public class AutorBean {
	
	private Autor autor = new Autor();
	
	@Inject
	private AutorService service; // injeção de dependencias, o EJB vai tratar de instanciar
	
	public Autor getAutor() {
		return autor;
	}
	
	public void cadastra() {
		this.service.adiciona(autor);
		this.autor = new Autor();
	}
	
	public List<Autor> getAutores() {
		return this.service.todosAutores();
	}
}
