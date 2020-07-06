package it.polito.tdp.newufosightings.model;

public class Arco {
	private String stato1;
	private String stato2;
	private Integer peso;
	/**
	 * @param stato1
	 * @param stato2
	 * @param peso
	 */
	public Arco(String stato1, String stato2, Integer peso) {
		super();
		this.stato1 = stato1;
		this.stato2 = stato2;
		this.peso = peso;
	}
	public String getStato1() {
		return stato1;
	}
	public void setStato1(String stato1) {
		this.stato1 = stato1;
	}
	public String getStato2() {
		return stato2;
	}
	public void setStato2(String stato2) {
		this.stato2 = stato2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}
