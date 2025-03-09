package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

import br.com.alura.jpa.modelo.MediaComData;

public class TestaMediaDiariaDasMovimentacoes {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		List<MediaComData> MediaDasMovimentacoes = new MovimentacaoDao(em).getMediaDiariaDasmovimentacoes();
		
		for (MediaComData resultado: MediaDasMovimentacoes) {
			System.out.println("Media movimentacoes " + resultado.getDia() + " / " + resultado.getMes() + " é: " + resultado.getValor());
			
		}
		
		

	}

}
