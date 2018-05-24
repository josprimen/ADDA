package practicas.practica14.caballo;

public class ProblemaCaballo {

	//Compartidas
	private static Integer lado;
	private static Integer iposx;
	private static Integer iposy;
	
	private static Integer[] movx = {-1, 1, -1,  1,  2, 2, -2, -2}; 
	private static Integer[] movy = { 2, 2, -2, -2, -1, 1, -1,  1};	

	public static Integer getLadoTablero() {
		return lado;
	}
	
	public static Integer getCasillaInicialX() {
		return iposx;
	}
	public static Integer getCasillaInicialY() {
		return iposy;
		
	}

	public static void setLadoTablero (int t) {
		lado= t;
	}
	
	public static void setCasillaInicialX (int x) {
		iposx= x;
	}
	
	public static void setCasillaInicialY (int y) {
		iposy= y;
	}

	
	public static Integer[] getMovimientosX() {
		return movx;
	}
	public static Integer[] getMovimientosY() {
		return movy;
		
	}

	
}
