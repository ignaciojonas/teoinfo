import java.util.Vector;


public class Main {

	public static void main(String[] args) {
		Vector<String> mensaje = new Vector<String>();
		mensaje.add("S1");
		mensaje.add("S2");
		mensaje.add("S3");
		mensaje.add("S1");
		mensaje.add("S2");
		mensaje.add("S4");
		mensaje.add("S1");
		mensaje.add("S2");
		mensaje.add("S3");
		mensaje.add("S2");
		mensaje.add("S2");
		mensaje.add("S4");
		mensaje.add("S1");
		mensaje.add("S3");
		mensaje.add("S4");
		mensaje.add("S1");
		mensaje.add("S2");
		mensaje.add("S2");
		mensaje.add("S4");
		mensaje.add("S1");
		System.out.println("Mensaje sin original: " + mensaje.toString());
		String mensajeComprimido = LZW.comprimir(mensaje);
		System.out.println("Mensaje Comprimido: " + mensajeComprimido);
		
		Vector<String> simbolos = new Vector<String>();
		simbolos.add("S1");
		simbolos.add("S2");
		simbolos.add("S3");
		simbolos.add("S4");
		LZW.initDiccionarioDec(simbolos);
		
		mensaje = new Vector<String>();
		String[] comprimido = mensajeComprimido.split(",");
		for (String codigo : comprimido) {
			mensaje.add(codigo);
		}
		System.out.println("Mensaje Comprimido: " + mensaje.toString());
		System.out.println("Mensaje Descomprimido: " + LZW.descomprimir(mensaje));
		System.out.println("Caso Especial: Simbolos a, b " );
		simbolos = new Vector<String>();
		simbolos.add("a");
		simbolos.add("b");
		LZW.initDiccionarioDec(simbolos);
		mensaje = new Vector<String>();
		mensaje.add("1");
		mensaje.add("1");
		mensaje.add("2");
		mensaje.add("4");
		mensaje.add("6");
		mensaje.add("3");
		System.out.println("Mensaje Comprimido" + mensaje.toString());
		System.out.println("Mensaje Descomprimido" + LZW.descomprimir(mensaje));
		
		
	}

}
