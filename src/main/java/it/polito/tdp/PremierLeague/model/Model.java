package it.polito.tdp.PremierLeague.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

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
		List<Match> result = new ArrayList<>(dao.listAllMatches());
		Collections.sort(result);
		return result;
	}

	public void creaGrafo(Match match) {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
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


	}
	public Player getGiocatoreMigliore() {
		double efficienza = (double)Integer.MIN_VALUE;
		Player result = null;
		if(this.grafo!=null) {
			for(Player p : this.grafo.vertexSet()) {
				double temp_out = 0;
				double temp_in = 0;

				for(DefaultWeightedEdge e:this.grafo.outgoingEdgesOf(p)) {
					temp_out+=this.grafo.getEdgeWeight(e);
				}
				for(DefaultWeightedEdge e:this.grafo.incomingEdgesOf(p)) {
					temp_in+=this.grafo.getEdgeWeight(e);
				}
				double risultato_temp = temp_out-temp_in;
				//p.setEfficienza(risultato_temp);
				if(risultato_temp>efficienza) {
					efficienza=risultato_temp;
					result = p;
					result.setEfficienza(efficienza);
				}
			}
		}
		return result;
	}
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
}
