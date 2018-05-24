package practicas.practica16;

import java.util.List;

//import org.jgrapht.Graph;
//import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import us.lsi.common.Iterables2;
//import us.lsi.graphs.GraphsReader;

import com.google.common.collect.Lists;


public class ProblemaMatriz {

	private List<Matriz> matrices;
	
	public static ProblemaMatriz create (String fichero) {
		return new ProblemaMatriz(fichero);
	}


	private ProblemaMatriz (String fichero) {
		super();
		leeDatos(fichero);		
	}

	private void leeDatos (String fichero){
		Iterable<String> is = Iterables2.fromFile(fichero);
		matrices = Lists.newArrayList();

		for (String s : is) 
			matrices.add(Matriz.create(s));
	}

	public List<Matriz> getMatrices() {
		return matrices;
	}


	public Matriz getMatriz(Integer index) {
		return getMatrices().get(index);
	}

	public Integer getFila(Integer index) {
		return getMatrices().get(index).getNumeroFilas();
	}

	public Integer getColumna(int index) {
		return getMatrices().get(index).getNumeroColumnas();
	}

	public boolean isSolucion(SolucionMatriz s) {

		return s.getNumeroDeOperaciones() > 0
				&& s.getExpresionConParentesis() != null;
	}
	

}
