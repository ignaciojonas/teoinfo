
import java.util.HashMap;
import java.util.Vector;

// Esta clase implementa los algoritmos de codificacion y decodificacion de LZW
public class LZW {
	private static HashMap<String, String> diccionarioCod = new HashMap<String, String>(); ;
	private static HashMap<String, String> diccionarioDeco = new HashMap<String, String>(); ;
	private static int i = 0;
	
	//El diccionario de codificacion tiene como Key el simbolo, y como Value el codigo.
	public static void initDiccionarioCod(Vector<String> mensaje){
		int j = 1;
		for (String simbolo : mensaje) {
			if(!diccionarioCod.containsKey(simbolo))
			{
				diccionarioCod.put(simbolo, String.valueOf(j));
				j++;
			}
		}
	}
	
	//El diccionario de decodificacion tiene como Key el codigo, y como Value el simbolo.
	public static void initDiccionarioDec(Vector<String> mensaje){
		int j = 1;
		for (String simbolo : mensaje) {
			if(!diccionarioDeco.containsKey(simbolo))
			{
				diccionarioDeco.put(String.valueOf(j),simbolo);
				j++;
			}
		}
	}
	
	//Metodo para comprimir un mensaje 
	public static String comprimir(Vector<String> mensaje)
	{
		//Inicializo el diccionario
		initDiccionarioCod(mensaje);
		//Obtengo la cantidad de codigos que tengo en el diccionario para poder seguir
		//creando a partir de ese indice
		int j = diccionarioCod.size();
		//Variables auxiliares
		i=0;
		String salida = "";
		String s = "";
		String sant = "";
		String ssig = "";
		//Obtengo el primer simbolo del mensaje
		s = getSimbolo(mensaje);
		while(s != null)
		{
			//Incremento en uno el indice del proximo codigo.
			j++;
			//Si el diccionario contiene el simbolo, trato de agregar otro.
			while(diccionarioCod.containsKey(s))
			{
				//Guardo el simbolo actual en anterior
				sant = s;
				//Obtengo el siguiente simbolo
				ssig = getSimbolo(mensaje);
				//Si no terminó el mensaje
				if(ssig != null)
				{
					//Mi simbolo nuevo es el anterior y el siguiente.
					s = sant + "-" + ssig;
				}
				else
				{
					//Si es fin del mensaje, no agrego mas simbolos
					s = null;
				}
			}
			
			//Busco la codificacion del simbolo y la agrego a la salida
			salida += diccionarioCod.get(sant) + ",";
			//Si no es el fin del mensaje
			if(s != null)
			{
				//Agrego un nuevo simbolo a mi diccionario.
				diccionarioCod.put(s, String.valueOf(j));
				//el simbolo se vuelve el siguiente.
				s = ssig;
			}
		}
		//Retorno el mensaje codificado
		return salida;
	}
	
	//Este metodo obtiene un simbolo del mensaje
	public static String getSimbolo(Vector<String> mensaje)
	{
		if(i< mensaje.size()){
		String s = mensaje.get(i);
		i++;
		return s;
		}
		return null;
	}
	
	//Metodo para descomprimir un mensaje 
	public static String descomprimir(Vector<String> mensaje)
	{
		
		//Obtengo el tamaño del diccionario con los simbolos originales.
		int j = diccionarioDeco.size();
		//Variables auxiliares
		i=0;
		String salida = "";
		String s = "";
		String sant = null;
		//Obtengo el primer codigo.
		String c = getCodigo(mensaje);
		//Mientras que no sea el fin del mensaje
		while(c != null)
		{
			//Obtengo el simbolo que se corresponde con el codigo
			s = diccionarioDeco.get(c);
			//Si no es el primer simbolo
			if(sant != null)
			{
				//Incremento en la clave del proximo codigo a agregar.
				j++;
				//Si el simbolo no estaba codificado
				if(s==null)
				{
					//lo codifico con la cadena anterior + el primer simbolo de la cadena anterior.
					sant += "-" +sant.substring(0,sant.indexOf('-'));
					//El simbolo pasa a ser el simbolo anterior.
					s = sant;
				}
				else
				{
					//Si el simbolo es compuesto
					if(s.contains("-"))
					{
						//A la cadena anterior le agrego el primer simbolo
						sant += "-" + s.substring(0,s.indexOf("-"));
					}
					else
					{
						//Si no es compuesto, agrego a la cadena anterior, el simbolo actual.
						sant += "-" + s;
					}
				}
				//Agrego el nuevo codigo, con el nuevo simbolo en el diccionario.
				diccionarioDeco.put(String.valueOf(j), sant);
			}
			//Guardo el simbolo actual en el anterior
			sant = s;
			//Agrego el simbolo descomprimido a la salida
			salida += s + ",";
			//Busco el siguiente codigo.
			c = getCodigo(mensaje);
		}
		
		//retorno la cadena descomprimida
		return salida;
	}
	
	//Metodo para obtener un codigo de un mensaje, se implementa llamando a GetSimbolo. 
	//Este metodo es un Wrapper para mayor legibilidad.
	public static String getCodigo(Vector<String> mensaje)
	{
		return getSimbolo(mensaje);
	}

}
