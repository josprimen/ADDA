package practicas.practica15;

import java.util.*;

import com.google.common.collect.Lists;

import us.lsi.graphs.GraphView;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

public class ProblemaBicicletasPD<V, E> implements ProblemaPD<String, ProblemaBicicletasPD.Alternativa> {

	public enum Alternativa{Yes, No};
	//Propiedades individuales
	private int i;
	private int j;
	private int k;
	private GraphView<V,E> grafo;//Esta es la compartida. GraphView lo que hace es mapear los grafos, a cada vertice le da un numerito
	
	private ProblemaBicicletasPD(int i, int j, int k, GraphView<V, E> grafo) {
		super();
		this.i = i;
		this.j = j;
		this.k = k;
		this.grafo = grafo;
	}
	
	public static <V, E> ProblemaBicicletasPD<V, E> create(int i, int j, GraphView<V, E> grafo) {
		return new ProblemaBicicletasPD<V, E>(i, j, 0, grafo);
	}
	
	public static <V, E> ProblemaBicicletasPD<V, E> create(int i, int j, int k, GraphView<V, E> grafo) {
		return new ProblemaBicicletasPD<V, E>(i, j, k, grafo);
	}
	
	@Override
	public ProblemaPD.Tipo getTipo() {
		//Es un problema de minimizar el peso (tiempo) de las aristas
		return Tipo.Min;
	}
	
	@Override
	public boolean esCasoBase() {
		//Los dos casos base que hay
		//N = grafo.getNumVertices()
		return i==j || k==grafo.getNumVertices();
	}

	@Override
	public Sp<Alternativa> getSolucionParcialCasoBase() {
		//Casos base devuelve soluciones parciales que se crean con un sp.create(alternativa, propiedad a optimizar)
		int n = grafo.getNumVertices();
		if (i==j){
			return Sp.create(Alternativa.No, 0.);
		}else if(k==n && grafo.isEdge(i, j)){
			return Sp.create(Alternativa.No, grafo.getWeight(i, j));
		}else{
			return null;
		}
	}
	
	@Override
	public List<Alternativa> getAlternativas() {
		//Lista con las alternativas que hay
		List<Alternativa> ls = Lists.newArrayList();
		ls.add(Alternativa.No);
		ls.add(Alternativa.Yes);
		return ls;
	}
	
	@Override
	public int getNumeroSubProblemas(Alternativa a) {
		//Para las alternativas No hay un subproblema y para las Si hay dos i-k y k-j
		if(a.equals(Alternativa.No)){
			return 1;
		}else{
			return 2;
		}
	}
	
	@Override
	public ProblemaPD<String, Alternativa> getSubProblema(Alternativa a, int np) {
		//TODO
		ProblemaPD<String, Alternativa> res = null;
		if(a.equals(Alternativa.No)){
			res = ProblemaBicicletasPD.create(i, j, k+1, grafo);
		}else if(np == 0){
			res = ProblemaBicicletasPD.create(i, k, k+1, grafo);
		}else{
			res = ProblemaBicicletasPD.create(k, j, k+1, grafo);
		}
		return res;
	}
	
	@Override  //Tenemos que hacerla en la practica individual
	public Sp<Alternativa> getSolucionParcialPorAlternativa(Alternativa a, List<Sp<Alternativa>> ls) {
		//Va a devolver una solucion parcial por cada alternativa
		//(No,p) ó (Si,p1+p2)
		if(a.equals(Alternativa.No)){
			return Sp.create(Alternativa.No, ls.get(0).propiedad);
		}else{
			return Sp.create(Alternativa.Yes, ls.get(0).propiedad + ls.get(1).propiedad);
		}
	}
	
	@Override //Tenemos que hacerla en la practica individual
	public Sp<Alternativa> getSolucionParcial(List<Sp<Alternativa>> ls) {
		//Coger la lista de SOLUCIONES PARCIALES y te quedas con la de menor valor en este caso
		return ls.stream()
				.min(Comparator.naturalOrder())
				.orElse(null);
	}
	
	@Override
	public String getSolucionReconstruidaCasoBase(Sp<Alternativa> sp) {
		return grafo.getVertice(i) + "<" + sp.propiedad + ">" + grafo.getVertice(j);
	}
		
	@Override
	public String getSolucionReconstruidaCasoRecursivo(Sp<Alternativa> sp, List<String> ls) {
		String r = null;
		switch(sp.alternativa){
		case No: r = ls.get(0); break;
		case Yes: r = ls.get(0) + ", " + ls.get(1); break;	
		}		
		return r;
	}
	
	/* No estamos usando filtro por eso podemos comentarlo
	@Override
	public Double getObjetivoEstimado(Alternativa a) {
		return Double.MIN_VALUE;
	}

	@Override
	public Double getObjetivo() {
		return Double.MAX_VALUE;
	}
*/
	@Override
	public int size() {
		return grafo.getNumVertices()-k;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		result = prime * result + k;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProblemaBicicletasPD))
			return false;
		ProblemaBicicletasPD<?,?> other = (ProblemaBicicletasPD<?,?>) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		if (k != other.k)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + i + "," + j + "," + k + ")";
	}	
}
