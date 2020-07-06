package it.polito.tdp.newufosightings.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;

public class Model {
	
	private NewUfoSightingsDAO dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new NewUfoSightingsDAO();
	}
	
	
	public List<String> getForme(Integer anno){
		return this.dao.getForme(anno);
	}
	
	public void creaGrafo(Integer anno, String forma) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getStati());
		
		for(Arco a : dao.getArchi(anno, forma)) {
			if(!grafo.containsEdge(a.getStato1(), a.getStato2())) {
				Graphs.addEdgeWithVertices(grafo, a.getStato1(), a.getStato2(), a.getPeso());
			}
		}
	}


	public Integer nVertici() {
		return this.grafo.vertexSet().size();
	}


	public Integer nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Peso> stampaSommaPesi() {
		List<Peso> elenco = new ArrayList<Peso>();
		for(String vPartenza : grafo.vertexSet()) {
			Integer peso = 0;
			for(String vArrivo : grafo.vertexSet()) {
				if(this.grafo.containsEdge(vPartenza, vArrivo)) {
					DefaultWeightedEdge a = grafo.getEdge(vPartenza, vArrivo);
					peso += (int)grafo.getEdgeWeight(a);
				}				
			}
			elenco.add(new Peso(vPartenza, peso));
		}		
		return elenco;
	}
}
