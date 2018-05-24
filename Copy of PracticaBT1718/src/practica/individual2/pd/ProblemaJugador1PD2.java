package practica.individual2.pd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.google.common.collect.Lists;
import practica.individual2.bt.Jugador;
import practica.individual2.bt.ProblemaJugador;
import us.lsi.common.Preconditions;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

public class ProblemaJugador1PD2 implements ProblemaPD<SolucionJugador, Integer> {
	
	//Individuales
	private Integer index;
	private Integer presupuestoRestante;
	private List<Jugador> elegidos;
	//Compartidas
	private List<Jugador> players;
	private Integer n;
	private Integer cantidadSuplentes;
	
	//Creates
	public static ProblemaJugador1PD2 create() {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		return new ProblemaJugador1PD2();

	}

	public static ProblemaJugador1PD2 create(Integer presupuestoRestante,List<Jugador> seleccionados, Integer i) {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		return new ProblemaJugador1PD2(presupuestoRestante, seleccionados, i);

	}
	
	//Constructores
	private ProblemaJugador1PD2 (){
		this.index=0;
		this.presupuestoRestante = ProblemaJugador.presupuesto;
		this.elegidos = Lists.newArrayList();
		
		this.players = ProblemaJugador.jugadores;
		this.n = players.size()-1;
		this.cantidadSuplentes = ProblemaJugador.numeroSuplentes;
	}
	
	private ProblemaJugador1PD2 (Integer presupuesto,List<Jugador> seleccionados, Integer i) {
		this.index = i;
		this.presupuestoRestante = presupuesto; // HACER COPIA
		this.elegidos = seleccionados;
		
		this.players = ProblemaJugador.jugadores;
		this.n = players.size();
		this.cantidadSuplentes = ProblemaJugador.numeroSuplentes;

	}

	
	@Override
	public ProblemaPD.Tipo getTipo() {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		return Tipo.Max;
	
	}
	
	@Override
	public boolean esCasoBase() {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.

		return index == n;
	
	}

	@Override
	//4
	public Sp<Integer> getSolucionParcialCasoBase() {
		//Sp<Alternativa>
		Integer pivots = 0;
		Integer aleros = 0;
		Integer base = 0;
		for(Jugador jugador: elegidos){
			if(jugador.getPosicion1().equals("Pivot")||jugador.getPosicion2().equals("Pivot")) pivots++;
			if(jugador.getPosicion1().equals("Alero")||jugador.getPosicion2().equals("Alero")) aleros++;
			if(jugador.getPosicion1().equals("Base")||jugador.getPosicion2().equals("Base")) base++;
		}
		if (elegidos.size()!=cantidadSuplentes || pivots<2 || aleros<3 || base!=1) {
			return null;
		}
		return Sp.create(null, 0.);
	
	}
	
	

	@Override
	//6
	public Sp<Integer> getSolucionParcial(List<Sp<Integer>> ls) {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		//Contiene una lista con todas las soluciones, una por alternativa que haya
		return ls.stream().max(Comparator.naturalOrder()).orElse(null);
	
	}

	
	
	@Override
	//1
	public List<Integer> getAlternativas() {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		//Alternativas contratar o no al jugador ??��??
		List<Integer> ls = Lists.newArrayList();
		ls.add(0);
		//HACER LA COMPROBACION DE QUE TENGA EL DINERO index o index + 1
		if(presupuestoRestante-players.get(index).getCache()>=0)
		ls.add(1);
		return ls;
		
	}

	@Override
	public int getNumeroSubProblemas(Integer a) {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		//Solo un subproblema ??��??
		return 1;
		
	}

	@Override
	//3
	 //Tiene sentido aqu� esto??��?? solo hay un subproblema //////////////////////////////////////////
	public ProblemaPD<SolucionJugador, Integer> getSubProblema(Integer alternativa, int np) {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		// HACER COPIA DE ELEGIDOS ver si es 1 la alternativa y si es 1 actualizar presupuesto y lista de elegidos y pasarselo por abajo el create
		List<Jugador> copia = new ArrayList<Jugador>(elegidos);
		Integer copiaPresupuesto = presupuestoRestante;
		if(alternativa == 1){
			copia.add(players.get(index));
			copiaPresupuesto -= players.get(index).getCache();
		}
		return ProblemaJugador1PD2.create(copiaPresupuesto, copia, index+1);
	
	}

	@Override///////////////Varias veces si hubiese varios subproblemas
	//5 
	public Sp<Integer> getSolucionParcialPorAlternativa (Integer a, List<Sp<Integer>> ls) {
		//TODO ALUMNOS: EL CUERPO DEL M�TODO.
		//Al solamente haber un subproblema solo habr� una solucion parcial en la lista??��??
		int valor = 0;
		if (a.equals(1)){
			valor = players.get(index).getTirosLargos() + players.get(index).getTirosCortos();
	    }
		return Sp.create(a, ls.get(0).propiedad + valor);
		
	}

	
	
	@Override
	public SolucionJugador getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		Preconditions.checkState(esCasoBase());
		SolucionJugador r;
		r= SolucionJugador.create(); 
		return r;	
	}
		
	
	@Override////////////////////////////////////////////////////////
	public SolucionJugador getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, List<SolucionJugador> ls) {

		SolucionJugador r1 = ls.get(0);
		//SolucionJugador r2 = ls.get(1);
		//get elegidos en solucion jugador 1 + addAll elegidos en solucion jugador 2?
		//List<Jugador> sumaElegidos = new ArrayList<Jugador>();
		//sumaElegidos.addAll(r1.jugadores);
		if (sp.alternativa==1) {
			r1.jugadores.add(players.get(index));
		   //r1.add(players.get(index));
		}
		return r1;
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
		//TODO ALUMNOS: EL CUERPO DEL M�TODO. ///////////////////////////////////////
		return n-(index+1);
		
	}

	//Hashcode y equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elegidos == null) ? 0 : elegidos.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((presupuestoRestante == null) ? 0 : presupuestoRestante.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaJugador1PD2 other = (ProblemaJugador1PD2) obj;
		if (elegidos == null) {
			if (other.elegidos != null)
				return false;
		} else if (!elegidos.equals(other.elegidos))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (presupuestoRestante == null) {
			if (other.presupuestoRestante != null)
				return false;
		} else if (!presupuestoRestante.equals(other.presupuestoRestante))
			return false;
		return true;
	}



	/*@Override
	public String toString() {
		return "(" + i + "," + j + ")";
	}*/

}