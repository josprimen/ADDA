package us.lsi.ag.tsp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import com.google.common.collect.Lists;

import us.lsi.ag.ProblemaAGIndex;
import us.lsi.ag.agchromosomes.IndexChromosome;
import us.lsi.graphs.GraphsReader;
import us.lsi.tipos.Carretera;
import us.lsi.tipos.Ciudad;

public class ProblemaTspAG implements ProblemaAGIndex<List<Ciudad>> {

	public static Double SARISTAS = null;
	public static Graph<Ciudad,Carretera> g = null;
	public static List<Ciudad> ciudades = null;
	
	public static ProblemaTspAG create(String fichero) {
		return new ProblemaTspAG(fichero);
	}

	private ProblemaTspAG(String fichero) { 
		ProblemaTspAG.g = (UndirectedGraph<Ciudad,Carretera>) new SimpleGraph<Ciudad,Carretera>(Carretera.factoria);
		ProblemaTspAG.g =  GraphsReader.newGraph(fichero,Ciudad.factoria, Carretera.factoria,ProblemaTspAG.g);
		ProblemaTspAG.ciudades = Lists.newArrayList(g.vertexSet());
		ProblemaTspAG.SARISTAS = g.edgeSet().stream().mapToDouble(c -> c.getCoste()).sum();
	}

	@Override
	public List<Ciudad> getSolucion(IndexChromosome chromosome) { //Hay que hacer esta
		// TODO
		return getRecorrido(chromosome.decode());
	}

	public Double getPeso(List<Ciudad> lc) { //Se asegura de que haya un camino de cada ciudad a cada ciudad
		boolean ok = IntStream.range(0, lc.size()-1)
				.allMatch(p->g.containsEdge(lc.get(p),lc.get(p+1)));
		if (!ok) return null;
		return IntStream.range(0, lc.size()-1).boxed()
			.map(p -> g.getEdge(lc.get(p),lc.get(p+1)))			
		    .mapToDouble(p -> p.getCoste())
		    .sum();
	}
	
	@Override
	public Double fitnessFunction(IndexChromosome ls) {// Esta
		
		List<Ciudad> lc  = getRecorrido(ls.decode());
		
		// TODO
		// SA la suma de los pesos de las aristas del circuito, RKO penalizacion a soluciones donde nos diga ir de una ciudad de a otra y no haya camino entre ellas, SPA numero muy grande que es el peso de todas las aristas del grafo
		//No hay que penalizar el que se repita ciudad en la que hayamos estado porque eso no va a pasar porque getMax es 1
		
		//Vamos a recorrer de 2 en 2 de una ciudad a otra
		
		return -IntStream.rangeClosed(0, lc.size()-2) //Que la arista no existe: penaliza Que si existe: le asiga peso
		.boxed()//lo recorremos con un stream
		.map(i -> g.getEdge(lc.get(i), lc.get(i+1)))//convertimos los enteros en un edge
		.mapToDouble(e -> (e==null)? SARISTAS : e.getCoste()) //Si es null devolvemos aristas y si no el peso 
		.sum();
		
		
	}	
	
	
	public List<Ciudad> getRecorrido(List<Integer> ls) { //Te lo convierte a lista de ciudades y te pone en la ultima ciudad la primera de donde salió
		List<Ciudad> la = ls.stream() //Pilla el ls que es la lista numerica de numero de ciudad a que numero de ciudad hemos ido
				.map(p -> ciudades.get(p))//y crea una lista con los nombres de esos numeros (ciudades)
				.collect(Collectors.toList());
		la.add(la.get(0));
		return la;
	}

	@Override
	public Integer getObjectsNumber() { //Y esta
	
		// TODO
		return ciudades.size();
	}

	
}
