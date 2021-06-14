package it.polito.tdp.PremierLeague.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private Map <Integer,Player> idMap;
	private Graph <Player, DefaultWeightedEdge> grafo;
	private PremierLeagueDAO dao;
	
	public Model() {
		idMap= new HashMap<>();
		dao=new PremierLeagueDAO();
		dao.listAllPlayers(idMap);
	}
	
	public List<Match> getAllMatches(){
		return dao.listAllMatches();
	}
	
	public void creaGrafo(Match match) {
		grafo = new SimpleDirectedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getGiocatoriPerMatch(idMap, match));
		
		for(Adiacenza a : dao.getAdiacenze(idMap, match)) {	
				Player p1 = a.getPlayer_1();
				Player p2 = a.getPlayer_2();
				Double peso = a.getPeso();
				if(peso>0) {
					Graphs.addEdgeWithVertices(this.grafo, p1, p2, peso);
				} else if(peso<0){
					peso = peso -2*peso;
					Graphs.addEdgeWithVertices(this.grafo, p2, p1, peso);
				}
		}
		
		System.out.println(this.grafo.edgeSet());
	}
}
