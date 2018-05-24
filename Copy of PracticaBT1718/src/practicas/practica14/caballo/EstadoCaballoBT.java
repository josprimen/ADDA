package practicas.practica14.caballo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import us.lsi.bt.EstadoBT;

//TODO ALUMNOS: OTRAS IMPORTACIONES.



public class EstadoCaballoBT implements EstadoBT<SolucionCaballoBT, Integer> {

	//TODO ALUMNOS: ATRIBUTOS DE LA CLASE
	//individuales
	private Integer posx = null;//Posicion actual
	private Integer posy = null;
	private Table<Integer, Integer, Integer> tablero; //es como un mapa con dos claves y un valor
	private Integer casillasProcesadas;//para saber cuando parar (cuando hayamos hecho tantos mov como casillas
	
	//Esto NO son individuales, es unas que nos vamos a crear para en vez de estar llamando de continuo a problemacaballo
	//para obtener las individuales, las almacenamos aqui y nos ahoramos el ProblemaCaballo.get...
	private Integer[] movx = null;
	private Integer[] movy = null;
	private int tam;

	
	public static EstadoCaballoBT create() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Create vale para ocultar el constructor
		return new EstadoCaballoBT();
	}
	
	private EstadoCaballoBT() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		posx = ProblemaCaballo.getCasillaInicialX();
		posy = ProblemaCaballo.getCasillaInicialY();
		casillasProcesadas = 1;
		tablero = HashBasedTable.create();
		
		movx = ProblemaCaballo.getMovimientosX();
		movy = ProblemaCaballo.getMovimientosY();
		tam = ProblemaCaballo.getLadoTablero();
		
		tablero.put(posx, posy, casillasProcesadas);
		
	}


	@Override
	public EstadoCaballoBT avanza (Integer movimiento) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//no tiene mucho sentido un return, el año pasado era vacio, esto lo unico que tiene que hacer es sus movidas
		posx = posx + movx[movimiento];
		posy = posy + movy[movimiento];
		casillasProcesadas++;
		tablero.put(posx, posy, casillasProcesadas);
		return this;
	}

	
	@Override
	public EstadoCaballoBT retrocede(Integer movimiento) {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//no tiene mucho sentido un return, el año pasado era vacio, esto lo unico que tiene que hacer es sus movidas
		tablero.remove(posx, posy);
		casillasProcesadas--;
		posx = posx - movx[movimiento];
		posy = posy - movy[movimiento];
		return this;
	}

	@Override
	public List<Integer> getAlternativas() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//aqui tenemos que decir los movimientos posibles que no se salga y que no esté pillada la casilla
		return IntStream.rangeClosed(0, 7)
				.filter(m -> 0<=posx + movx[m] && posx + movx[m]< tam)
				.filter(m -> 0<=posy + movy[m] && posy + movy[m]< tam)
				.filter(m -> tablero.get(posx + movx[m], posy + movy[m])==null)
				.boxed()
				.collect(Collectors.toList());
	}

	@Override
	public SolucionCaballoBT getSolucion() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Esto copia la solucion y es importante hacerlo
		return SolucionCaballoBT.create(tablero);
		
	}

	
	@Override
	public int size() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Lo que queda para terminar
		return tam*tam - casillasProcesadas;
	}
	
	@Override
	public Tipo getTipo () {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Si es de maximizacion, minimizacion, que devuelva todas las soluciones (todos los tableros) o alguna
		return Tipo.AlgunasSoluciones;
	}
	
	@Override
	public EstadoCaballoBT getEstadoInicial() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		return new EstadoCaballoBT();
	}
	
	@Override
	public boolean esCasoBase() {
		//TODO ALUMNOS: EL CUERPO DEL MÉTODO
		//Es caso base si ha terminado
		Boolean res = false;
		if(casillasProcesadas == tam*tam)res = true;
		return res;
	}

}
