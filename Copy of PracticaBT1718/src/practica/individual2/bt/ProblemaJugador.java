package practica.individual2.bt;

import java.util.ArrayList;
import java.util.List;
import us.lsi.stream.Stream2;

public class ProblemaJugador {

	public static List<Jugador> jugadores;
	public static Integer numeroSuplentes;
	public static Integer presupuesto;
	
	
	private ProblemaJugador(String file, String file2){
		super();
		leeJugadores(file);
		leeRestricciones(file2);
	}
	
	private static void leeJugadores(String file){	
		List<String> ls = Stream2.fromFile(file).toList();
		jugadores = new ArrayList<Jugador>();
		for(String s : ls){
			String[] fm = Stream2.fromString(s, ",").toArray((int x)->new String[x]);
			Jugador a = Jugador.create(new Integer(fm[0]),fm[1],fm[2],fm[3],new Integer(fm[4]),fm[5],new Integer(fm[6]),new Integer(fm[7]),new Integer(fm[8]));
			jugadores.add(a);
		}
	}
	
	private static void leeRestricciones(String file2){	
		List<String> ls = Stream2.fromFile(file2).toList();
		numeroSuplentes = 0;
		presupuesto = 0;
		for(String s : ls){
			String[] fm = Stream2.fromString(s, ",").toArray((int x)->new String[x]);
			numeroSuplentes = new Integer(fm[0]);
			presupuesto = new Integer(fm[1]);
		}
	}
	
	
	public static Integer getNumeroSuplentes() {
		return numeroSuplentes;
	}

	public static void setNumeroSuplentes(Integer numeroSuplentes) {
		ProblemaJugador.numeroSuplentes = numeroSuplentes;
	}

	public static Integer getPresupuesto() {
		return presupuesto;
	}

	public static void setPresupuesto(Integer presupuesto) {
		ProblemaJugador.presupuesto = presupuesto;
	}

	public static ProblemaJugador create(String file, String file2){
		return new ProblemaJugador(file, file2);
	}
	
}
