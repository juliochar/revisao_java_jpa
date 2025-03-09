package br.com.alura.jpa.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titular;
	private Integer agencia;
	private Integer numero;
	private Double saldo;
	@OneToMany(mappedBy = "conta", fetch = FetchType.EAGER)  // relacionamento já mapeado na entidade Conta - se faz necessário pq a JPA acaba criando outro relacionamento e nao consegue encontrar corretamente o relacionamento 1-N
	private List<Movimentacao> movimentacoes; // relacionamento bidirecional - já estruturado na outra tabela

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}




	public List<Movimentacao> getMovimentacoes() {
				
		return movimentacoes;
	}
}
