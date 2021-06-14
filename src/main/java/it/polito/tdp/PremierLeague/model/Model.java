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
			if(a.getPeso()>=0) {
				if(this.grafo.containsVertex(a.getPlayer_1())&& this.grafo.containsVertex(a.getPlayer_2())) {
					Graphs.addEdge(this.grafo, a.getPlayer_1(), a.getPlayer_2(), a.getPeso());
				}
			} else {
				if(this.grafo.containsVertex(a.getPlayer_1())&& this.grafo.containsVertex(a.getPlayer_2())) {
					Graphs.addEdge(this.grafo, a.getPlayer_2(), a.getPlayer_1(), (-1)*a.getPeso());
				}
			}
		}
		
		System.out.println(this.grafo.edgeSet());
	}
}
