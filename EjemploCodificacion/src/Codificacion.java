import java.util.HashMap;
import java.util.Vector;


public class Codificacion {

	public static void generarBits(char num)
	{
		char mascara = 1 << 15; //Desplaza el 1, 15 lugares a izquierda (32768)
		for(int i = 0; i < 16; i++)
		{
			if((num& mascara)==32768) //si el 1� bit de num es 1 
				System.out.print("1");
			else
				System.out.print("0");
			num = (char) (num << 1);
		}
	}
	
	public static String generarCodificacion(Vector<String> mensaje, HashMap<String,String[]> codificacion )
	{
		String resultado = "";
		char buffer = 0;
		int cant_digitos = 0;
		for (String simbolo : mensaje) {
			String[] codigo = codificacion.get(simbolo);
			for (String bit : codigo) {
				
					buffer = (char) (buffer << 1);
					if(bit == "1")
					{
						buffer = (char) (buffer | 1);
					}
					cant_digitos ++;
					if(cant_digitos == 16){
						resultado += buffer;
						buffer = 0;
						cant_digitos=0;
					}
				}
		}
		
		if((cant_digitos<16) &&(cant_digitos !=0)){
			buffer = (char) (buffer <<(16-cant_digitos));
			resultado += buffer;
		}
		
		return resultado;
	}
}
