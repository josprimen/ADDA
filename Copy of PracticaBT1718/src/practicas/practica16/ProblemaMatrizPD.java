package practicas.practica16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import us.lsi.common.Preconditions;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

public class ProblemaMatrizPD implements ProblemaPD<SolucionMatriz, Integer> {

	private int i;
	private int j;
	private ProblemaMatriz problemaOriginal;

	public static ProblemaMatrizPD create(ProblemaMatriz problema) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		return new ProblemaMatrizPD(0,problema.getMatrices().size(),problema);

	}

	
	public static ProblemaMatrizPD create(int i, int j, ProblemaMatriz problema) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		return new ProblemaMatrizPD(i,j,problema);

	}
	
	private ProblemaMatrizPD (int i, int j, ProblemaMatriz problema) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		this.i = i;
		this.j = j;
		this.problemaOriginal = problema;

	}

	
	@Override
	public ProblemaPD.Tipo getTipo() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		return Tipo.Min;
	
	}
	
	@Override
	public boolean esCasoBase() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.

		return j-i==1||j-i==2;
	
	}

	@Override
	//4
	public Sp<Integer> getSolucionParcialCasoBase() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		if(j-i==1){
			return Sp.create(null, 0.);
		}else{
			Double ops = (double) getMFila(i)*getMFila(i+1)*getMColumna(i+1);
			return Sp.create(null, ops);
		}
	
	}
	
	//Creados para evitar jodiuras, es lo de las multiplicaciones necesarias
	private Integer getMFila(int pos){
		return problemaOriginal.getFila(pos);
	}
	private Integer getMColumna(int pos){
		return problemaOriginal.getColumna(pos);
	}
	

	@Override
	//6
	public Sp<Integer> getSolucionParcial(List<Sp<Integer>> ls) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//Contiene una lista con todas las soluciones, una por alternativa que haya
		return ls.stream().min(Comparator.naturalOrder()).orElse(null);
	
	}

	
	
	@Override
	//1
	public List<Integer> getAlternativas() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		List<Integer> res = new ArrayList<Integer>();
		for(int a=i;a<j;a++){
			res.add(a);
		}
		return res;
	}

	@Override
	//2
	public int getNumeroSubProblemas(Integer a) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		return 2;
		
	}

	@Override
	//3
	public ProblemaPD<SolucionMatriz, Integer> getSubProblema(Integer a, int np) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		if(np==0){
			return ProblemaMatrizPD.create(i,a, problemaOriginal);
		}else{
			return ProblemaMatrizPD.create(a,j, problemaOriginal);
		}
		
	
	}

	@Override
	//5
	public Sp<Integer> getSolucionParcialPorAlternativa (Integer a, List<Sp<Integer>> ls) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		//La solucion parcial de cada uno de los dos subproblemas que hay, que estan en ls
		Double n1 = ls.get(0).propiedad;
		Double n2 = ls.get(1).propiedad;
		Double ops = (double) getMFila(i)*getMFila(a)*getMColumna(j-1);
		return Sp.create(a, ops+n1+n2);
		
	}

	
	
	@Override
	public SolucionMatriz getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		
		Preconditions.checkState(esCasoBase());
		
		SolucionMatriz r;
		if (j-i==1)  //Una sola matriz.
			r= SolucionMatriz.create("(" + i + ")", sp.propiedad.intValue()); 
		else  //Dos matrices.
			r= SolucionMatriz.create("(" + i + "*" + (j-1) + ")", sp.propiedad.intValue()); 
		
		return r;	
	}
		
	
	@Override
	public SolucionMatriz getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, List<SolucionMatriz> ls) {

		SolucionMatriz r1 = ls.get(0);
		SolucionMatriz r2 = ls.get(1);
		String expresion = "(" + r1.getExpresionConParentesis() + "*"
				+ r2.getExpresionConParentesis() + ")";
		return SolucionMatriz.create(expresion, sp.propiedad.intValue());
	}
	
	
	@Override
	public Double getObjetivoEstimado(Integer a) {
		return Double.MIN_VALUE;
	}

	@Override
	public Double getObjetivo() {
		return Double.MAX_VALUE;
	}

	@Override
	public int size() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO.
		return j-i;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}
		
		

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProblemaMatrizPD))
			return false;
		ProblemaMatrizPD other = (ProblemaMatrizPD) obj;
		if (problemaOriginal == null && other.problemaOriginal != null)
			return false;
		if (problemaOriginal!=null && !problemaOriginal.equals(other.problemaOriginal))
			return false;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "(" + i + "," + j + ")";
	}

}
