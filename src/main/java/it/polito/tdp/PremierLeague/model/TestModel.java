package it.polito.tdp.PremierLeague.model;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		PremierLeagueDAO dao = new PremierLeagueDAO();
		Match m = dao.listAllMatches().get(0);
		
		model.creaGrafo(m);

	}

}
