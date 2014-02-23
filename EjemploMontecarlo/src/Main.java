import org.jfree.ui.RefineryUtilities;

/**
 *
 * Una fuente puede emitir 3 símbolos {a, b, c}. El primer símbolo que emite 
 * es a y la elección del próximo símbolo a partir del anterior está dado por 
 * la siguiente matriz de pasaje:
 *     a  |  b  | c
 *  a 1/4 | 3/4 | 0
 *  b 1/2 | 1/4 | 1/2
 *  c 1/4 |  0  | 1/2
 *  
 *  1) Escribir el codigo para calcular V* por muestreo computacional.
 *  2 ) Escribir el codigo que permita calcular por muestreo computacional la 
 *  probabilidad de que la fuente emita el mismo símbolo en dos instantes 
 *  seguidos, para cada uno de los posibles símbolos. 
 * @author ijonas
 *
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Engine e = new Engine();
		double[] vStar = e.Run();
		System.out.println(vStar[0]);
		System.out.println(vStar[1]);
		System.out.println(vStar[2]);
		
		final PlotWindow demo = new PlotWindow(e);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);        

	}

}
