package pli.practica11.ejercicio4;
import us.lsi.pl.*;

public class TestNReinasPLI {

	public static void main(String[] args) {
		int nReinas = 16;
		String r = ReinasPLI.getConstraints(nReinas);
		System.out.println(r);
		
		AlgoritmoPLI a = AlgoritmoPLI.create();
		a.setConstraints(r);
		a.ejecuta();
		
		System.out.println("Sol:");
		
		for (int i = 0; i < nReinas; i++){
			for (int j = 0; j < nReinas; j++){
				double x = a.getSolucion()[i*nReinas + j];
				System.out.print(Math.round(x) + " ");
			}
			System.out.println();
		}
	}
	
}

