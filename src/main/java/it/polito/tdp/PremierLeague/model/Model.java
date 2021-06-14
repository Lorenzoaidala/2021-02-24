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
		
		
	}
}
