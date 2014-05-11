import java.util.HashMap;
import java.util.Vector;


public class Main {

	public static void main(String[] args) {
		char num = 'a';
		Codificacion.generarBits(num);
		Vector<String> mensaje = new Vector<String>();
		mensaje.add("C2");
		mensaje.add("C1");
		mensaje.add("C2");
		mensaje.add("C1");
		mensaje.add("C2");
		mensaje.add("C1");
		mensaje.add("C2");
		mensaje.add("C1");
		mensaje.add("C2");
		mensaje.add("C1");
		
		
		HashMap<String, String[]> codificacion = new HashMap<String, String[]>();
		codificacion.put("C0", new String[] {"1"});
		codificacion.put("C1", new String[] {"0","0","1"});
		codificacion.put("C2", new String[] {"0","0","0"});
		codificacion.put("C3", new String[] {"0","1"});
		
		String resultado = Codificacion.generarCodificacion(mensaje, codificacion);
		for (char c : resultado.toCharArray()) {
			
			Codificacion.generarBits(c);
		}
	}

}
