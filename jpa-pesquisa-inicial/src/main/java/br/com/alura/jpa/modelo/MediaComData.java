package br.com.alura.jpa.modelo;

public class MediaComData {
	private Double valor;
	private Integer dia;
	private Integer mes;
	public Double getValor() {
		return valor;
	}
	public Integer getDia() {
		return dia;
	}
	public MediaComData(Double valor, Integer dia, Integer mes) {
		this.valor = valor;
		this.dia = dia;
		this.mes = mes;
	}
	public Integer getMes() {
		return mes;
	}
	
	

}
