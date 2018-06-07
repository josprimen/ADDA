package practica.individual2.bt;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import us.lsi.bt.EstadoBT;

//TODO ALUMNOS: OTRAS IMPORTACIONES.



public class EstadoJugadorBT implements EstadoBT<SolucionJugadorBT, Integer> {

	//TODO ALUMNOS: ATRIBUTOS DE LA CLASE
	//individuales
	private Integer gasto;
	private Integer index;
	private List<Jugador> elegidos; //Lista de los jugadores que hemos elegido como suplentes
	//private List<String> = posicionesCubrir
	
	//Esto NO son individuales, es unas que nos vamos a crear para en vez de estar llamando de continuo a problemaJugadores
	private List<Jugador> players;
	private Integer cantidadSuplentes;
	private Integer dinero;

	
	public static EstadoJugadorBT create() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Create vale para ocultar el constructor
		return new EstadoJugadorBT();
	}
	
	private EstadoJugadorBT() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		gasto = 0;
		elegidos = new ArrayList<Jugador>();
		index = 0;
		
		players = ProblemaJugador.jugadores;
		cantidadSuplentes = ProblemaJugador.numeroSuplentes;
		dinero = ProblemaJugador.presupuesto;		
	}


	@Override
	public EstadoJugadorBT avanza (Integer seleccion) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//no tiene mucho sentido un return, el año pasado era vacio, esto lo unico que tiene que hacer es sus movidas
		if (seleccion==1) {
		   elegidos.add(players.get(index));
		   gasto = gasto + players.get(index).getCache();
		}
		index++;
		return this;
	}

	
	@Override
	public EstadoJugadorBT retrocede(Integer seleccion) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//no tiene mucho sentido un return, el año pasado era vacio, esto lo unico que tiene que hacer es sus movidas
		index--;
		if (seleccion==1) {
		   gasto = gasto - elegidos.get(elegidos.size()-1).getCache();
		   elegidos.remove(elegidos.size()-1);
		}
		return this;
	}

	@Override
	public List<Integer> getAlternativas() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//aqui tenemos que decir los movimientos posibles que no se salga y que no esté pillada la casilla
		
		
		
		
		Integer base = 0;
		List<Jugador> comprueba = new ArrayList<Jugador>(elegidos);
		comprueba.add(players.get(index));
		for(Jugador jugador: comprueba){
			if(jugador.getPosicion1().equals("Base")||jugador.getPosicion2().equals("Base")) base++;
		}
		
		
		
		List<Integer> ls = Lists.newArrayList();
		ls.add(0);
		if ((gasto + players.get(index).getCache())<= dinero && base<2) {
		    ls.add(1);
		}
		return ls;
		/*
		List<Integer> alt = new ArrayList<Integer>();
		for(int x=0; x<players.size();x++){
			int cont = 0;
			for(int y=0; y<elegidos.size();y++){
				if(!(players.get(x).equals(elegidos.get(y)))){
					cont++;
				}
			}
			if(cont == elegidos.size() && gasto + players.get(x).getCache()<= dinero) alt.add(x);
		}
		return alt;*/
	}

	@Override
	public SolucionJugadorBT getSolucion() {
		//Nunca va a repetir jugador porque va hacia abajo diciendo si elige uno un otro y ya está
		//Se coge o no se coge el 0, se coge o no se coge el 1...
		Integer pivots = 0;
		Integer aleros = 0;
		Integer base = 0;
		for(Jugador jugador: elegidos){
			if(jugador.getPosicion1().equals("Pivot")||jugador.getPosicion2().equals("Pivot")) pivots++;
			if(jugador.getPosicion1().equals("Alero")||jugador.getPosicion2().equals("Alero")) aleros++;
			if(jugador.getPosicion1().equals("Base")||jugador.getPosicion2().equals("Base")) base++;
		}
		if (elegidos.size()!=cantidadSuplentes || gasto>dinero || pivots<2 || aleros<3 || base!=1) {
			return null;
		}
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Esto copia la solucion y es importante hacerlo
		return SolucionJugadorBT.create(elegidos);
		
	}

	
	@Override
	public int size() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Lo que queda para terminar players.size()
		return players.size()-(index+1);
		//return players.size()-index;
	}
	
	@Override
	public Tipo getTipo () {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Si es de maximizacion, minimizacion, que devuelva todas las soluciones (todos los tableros) o alguna
		return Tipo.Max;
	}
	
	@Override
	public EstadoJugadorBT getEstadoInicial() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		return new EstadoJugadorBT();
	}
	
	@Override
	public boolean esCasoBase() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Es caso base si ha terminado
		return index==players.size()-1;//
		//return elegidos.size()==cantidadSuplentes;
	}

	public Double getObjetivoEstimado(Integer a){
		Double tirosAcumulados = SolucionJugadorBT.create(elegidos).getObjetivo();
		Double acumuladorTiros = tirosAcumulados;
		acumuladorTiros += a * (players.get(index).getTirosCortos() + players.get(index).getTirosLargos());
		
		for(int i = index+1; i<players.size();i++){
			acumuladorTiros += (players.get(i).getTirosCortos() + players.get(i).getTirosLargos());
		}
		return acumuladorTiros;
	}
}
