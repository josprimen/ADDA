package us.lsi.ag.acolorea;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import us.lsi.ag.ProblemaAGIndex;
import us.lsi.ag.agchromosomes.IndexChromosome;
import us.lsi.graphs.GraphsReader;
import us.lsi.tipos.Carretera;
import us.lsi.tipos.Ciudad;


public class ProblemaColoreaAG implements ProblemaAGIndex<Map<Ciudad,Integer>> {

	private static Graph<Ciudad,Carretera> g = null;
	private static List<Ciudad> ciudades = null;
	
	
	public ProblemaColoreaAG(String fichero) {
		ProblemaColoreaAG.g = (UndirectedGraph<Ciudad,Carretera>) new SimpleGraph<Ciudad,Carretera>(Carretera.factoria);
		ProblemaColoreaAG.g =  GraphsReader.newGraph(fichero,Ciudad.factoria, Carretera.factoria,ProblemaColoreaAG.g);
		ciudades = Lists.newArrayList(g.vertexSet());
	}

	
	@Override
	public Map<Ciudad,Integer> getSolucion(IndexChromosome chromosome) {
		// TODO
		Map<Ciudad,Integer> m = Maps.newHashMap();
		List<Integer> lc = chromosome.decode(); //Pilla el cromosoma y obtiene una lista de entero que es con lo que trabajamos
		IntStream.range(0, lc.size())
		.boxed()
		.forEach(i -> m.put(ciudades.get(i), lc.get(i)+1));//Pilla ciudad, le pone color
		return m;
	}

	@Override
	public Integer getObjectsNumber() {
		// TODO;
		return ciudades.size();
	}
	
	@Override	
    public Integer getMax(int index){
		// TODO	
		return ciudades.size()-1; //0 a x
		
	}

	@Override
	public Double fitnessFunction(IndexChromosome ls) {
		Map<Ciudad,Integer> m = getSolucion(ls);
		
		// TODO
		// CU + N * N * RKO CU es el numero de colores RKO para penalizar soluciones no validas es el numero de aristas que tienen mismo color sus extremos, N es numero de ciudades y N* N se hace para sacar una cte muy grande
	
		int CU = m.values().stream()
				.collect(Collectors.toSet())
				.size();
		int N = ciudades.size();
		int RKO = (int)g.edgeSet().stream()
		.filter(e -> m.get(g.getEdgeSource(e))== m.get(g.getEdgeTarget(e)))
		.count();
		
		return (double)(-(CU + (N*N *RKO))); // El menos es para que minimice
	}

	public Set<Set<Ciudad>> getComponentes(IndexChromosome chromosome) { //Recibo la info del comosoma y creo un conjunto con cada color y cada lista de color contiene las ciudades de ese color
		Map<Ciudad,Integer> m = getSolucion(chromosome);
			
		return m.entrySet().stream()
				   .collect(Collectors.groupingBy(e -> e.getValue(),Collectors.mapping(e->e.getKey(),Collectors.toSet())))
				   .values().stream()
				   .collect(Collectors.toSet());
	}

}
