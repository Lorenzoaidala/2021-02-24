package it.polito.tdp.PremierLeague.model;

public class Adiacenza {
//Questa è la classe che modella l'arco
	
	private Player player_1;
	private Player player_2;
	private Double peso;
	public Adiacenza(Player player_1, Player player_2, Double peso) {
		super();
		this.player_1 = player_1;
		this.player_2 = player_2;
		this.peso = peso;
	}
	public Player getPlayer_1() {
		return player_1;
	}
	public void setPlayer_1(Player player_1) {
		this.player_1 = player_1;
	}
	public Player getPlayer_2() {
		return player_2;
	}
	public void setPlayer_2(Player player_2) {
		this.player_2 = player_2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Adiacenza [player_1=" + player_1 + ", player_2=" + player_2 + ", peso=" + peso + "]";
	}
	
}
