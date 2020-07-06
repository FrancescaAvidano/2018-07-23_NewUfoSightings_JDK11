package it.polito.tdp.newufosightings.model;

public class Peso {
	String stato;
	Integer peso;
	/**
	 * @param stato
	 * @param peso
	 */
	public Peso(String stato, Integer peso) {
		super();
		this.stato = stato;
		this.peso = peso;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Peso [stato=" + stato + ", peso=" + peso + "]";
	}
	
	
}
