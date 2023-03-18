package proyectoIntegrador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Proyecto {
										// El throws IOException es necesario para poder usar los metodos de Files.
	public static void main(String[] args) throws IOException {
		
		int i = 0;																	// Int i para los loops
		
		String archivoResultados = args[0];											// Tomamos el archivo de resultados como primer argumento
		String archivoPronosticos = args[1];										// Tomamos el archivo de pronosticos como segundo argumento
		
		File resultadoscsv = new File(archivoResultados);							// creamos el File resultadoscsv con el archivoResultados tomado anteriormente
		File pronosticoscsv = new File(archivoPronosticos);							// creamos el File pronosticoscsv con el archivoPronosticos tomado anteriormente
		
		Scanner lectoraResultados = new Scanner(resultadoscsv);						// Creamos 2 Scanners en los Files. 1 para resultadoscsv, y otro para pronosticoscsv
		Scanner lectoraPronosticos = new Scanner(pronosticoscsv);	
		
		Set<Equipo> equipos = new HashSet<>();										// Creamos un set para los equipos. En un set no puede haber elementos repetidos, lo que tiene sentido, ya que no se pueden repetir equipos.
		ArrayList<Partido> partidos = new ArrayList<Partido>();						// Creamos un arraylist para los partidos y otro para los pronosticos
		ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
		
		crearEquiposYPartidos(lectoraResultados, equipos, partidos);				// Llamamos al metodo crearEquiposYPartidos. le pasamos la lectoraResultados, el set vacios de equipos y el arraylist de partidos
		
		mostrarPartidos(partidos);													// Llamamos al metodo mostrarPartidos para ver que se hayan creado bien.
		
		mostrarEquipos(equipos);													// Llamamos al metodo mostrarEquipos para ver que se hayan creado bien.

		
		crearPronosticos(lectoraPronosticos, pronosticos, equipos, partidos);			// Llamamos al metodo crearPronosticos. Le pasamos la lectoraPronosticos, el arraylist vacio de los pronosticos y los equipos y partidos previamente creados.
		
		mostrarPronosticos(pronosticos);												// Llamamos al metodo mostrarPronosticos para ver que se hayan creado bien.
		
		
		int puntajeParticipante = 0;													// variable para los puntos del participante
		
		for(i = 0; i < pronosticos.size(); i++) 
		{
			puntajeParticipante = pronosticos.get(i).puntos();							// acumulamos todos los puntos del participante
		}
		
		System.out.println("El puntaje del participante es: " + puntajeParticipante);	// mostramos los punots
	}
	
	
	// Metodo para crear los equipos y los partidos
	// debe recibir: Un Scanner que haya sido abierto en el archivo de Resultados, un set de equipos vacio, y un arraylist de partidos vacio.
	public static void crearEquiposYPartidos(Scanner unaLectoraResultados, Set<Equipo> unosEquipos, ArrayList<Partido> unosPartidos) 
	{
		String lineaResultado;																	// Un String para tomar la linea leida
		String[] valoresResultado;																// un String[] para tomar los valores de esa linea																	.
		Equipo unEquipo, otroEquipo;															// definimos los equipos que cargaremos en el loop
		Partido unPartido;																		// definimos el partido que cargaremos en el loop
		while(unaLectoraResultados.hasNextLine()) 												// Si existe una linea por leer, el while sigue.
		{
			lineaResultado = unaLectoraResultados.nextLine();									// tomamos una linea del archivo 
			valoresResultado = lineaResultado.split(",");										// separamos esa linea por las "," y tomamos un array de Strings
			
			unEquipo = new Equipo(valoresResultado[0], "Descripcion generica");					// Creamos los equipos. En las posiciones 0 y 3 se encuentran los nombres.
			otroEquipo = new Equipo(valoresResultado[3], "Descripcion generica");
			unosEquipos.add(unEquipo);															// Usamos el metodo add(algo) para agregar los equipos creados al set
			unosEquipos.add(otroEquipo);
			
			// Para crear los partidos, le pasamos los equipos previamente creados, y los goles de los equipos estan en las posiciones 1 y 2, pero como strings, por lo que habra que parsearlos a integer.
			unPartido = new Partido(unEquipo, otroEquipo, Integer.parseInt(valoresResultado[1]),Integer.parseInt(valoresResultado[2]));
			unosPartidos.add(unPartido);														// Usamos de vuelta el metodo add para añadir un valor, esta vez al arraylist
		}
	}
	
	
	// Metodo para crear los pronosticos
	// debe recibir: Un Scanner que haya sido abierto en el archivo de pronosticos, un arraylist de pronosticos vacios y los equipos y partidos creados previamente.
	public static void crearPronosticos(Scanner unaLectoraPronosticos, ArrayList<Pronostico> unosPronosticos, Set<Equipo> unosEquipos, ArrayList<Partido> unosPartidos) 
	{
		String lineaPronostico;																	// Un String para tomar la linea leida
		String[] valoresPronostico;																// un String[] para tomar los valores de esa linea
		int i = 0;																				// 1 int para ir moviendonos a traves de los partidos
		Pronostico unPronostico;																// declaramos el pronostico que crearemos en el loop
		while(unaLectoraPronosticos.hasNextLine()) 												// Si existe una linea por leer, el while sigue.
		{
			lineaPronostico = unaLectoraPronosticos.nextLine();									// tomamos una linea del archivo
			valoresPronostico = lineaPronostico.split(",");										// separamos esa linea por las "," y tomamos un array de strings.
			
			// Si hay una "x" en la posicion 1, quiere decir que el primer equipo del partido, es el que pronostico que ganaria
			if(valoresPronostico[1].compareToIgnoreCase("x") == 0) 								
			{
				// Para obtener el equipo del pronostico, usamos el metodo buscarEquipo, y le pasamos el nombre del equipo, que el participante cree que va a ganar
				//en este caso, como la x esta en la posicion 1, quiere decir que cree que ganara el primer equipo, sino seria el segundo.
				unPronostico = new Pronostico(unosPartidos.get(i), buscarEquipo(unosEquipos, valoresPronostico[0]), ResultadoEnum.GANADOR);
				unosPronosticos.add(unPronostico);
			}
			// Si la "x" esta en la posicion 2, quiere decir que es un empate, por lo que ponemos un equipo con nombre "EMPATE".
			else if(valoresPronostico[2].compareToIgnoreCase("x") == 0)						
			{
				unPronostico = new Pronostico(unosPartidos.get(i), new Equipo("EMPATE", "-"), ResultadoEnum.EMPATE);
				unosPronosticos.add(unPronostico);
			}
			// Si la "x" esta en la posicion 3, quiere decir que el equipo[j+1], es decir el segundo, es el pronosticado como ganador.
			else 
			{
				unPronostico = new Pronostico(unosPartidos.get(i), buscarEquipo(unosEquipos, valoresPronostico[4]), ResultadoEnum.GANADOR);
				unosPronosticos.add(unPronostico);
			}
			i++;																				// Incrementamos i en 1													
		}
	}
	
	// Metodo para buscar un equipo dentro del set de equipos
	// recibe el un set de equipos y el String que representa al nombre del equipo
	public static Equipo buscarEquipo(Set<Equipo> unosEquipos, String unEquipo) 
	{
		int i = 0;																			// int i para el loop
		for(i = 0; i < unosEquipos.size(); i++) 											// metodo size devuelve el tamaño de un set/arraylist
		{
			if(unosEquipos.stream().toArray(Equipo[]::new)[i].nombre().equals(unEquipo)) 	// como set no tiene metodo get(), convertimos el set a un stream, que pasamos a un array de equipos y nos fijamos si
			{																				// el nombre del equipo de la posicion i, es igual al nombre que pasaron por parametro
				break;																		// En caso de ser asi, salimos del loop con break
			}
		}
		return unosEquipos.stream().toArray(Equipo[]::new)[i];								// y despues retornamos ese valor de tipo Equipo, ya que es el que buscabamos
	}
	
	// Metodos generales para mostrar los valores por pantalla:
	
	public static void mostrarPartidos(ArrayList<Partido> unosPartidos) 
	{
		for(int i = 0; i < unosPartidos.size(); i++) 
		{
			System.out.println("- Partido N° " + (i+1) + " -");
			System.out.println(unosPartidos.get(i).equipo1().nombre() + ": " + unosPartidos.get(i).golesEquipo1());
			System.out.println(unosPartidos.get(i).equipo2().nombre() + ": " + unosPartidos.get(i).golesEquipo2());
		}
	}
	
	public static void mostrarPartido(Partido unPartido) 
	{
		System.out.println(unPartido.equipo1().nombre() + ": " + unPartido.golesEquipo1());
		System.out.println(unPartido.equipo2().nombre() + ": " + unPartido.golesEquipo2());
	}
	
	public static void mostrarEquipos(Set<Equipo> unosEquipos) 
	{
		for(int i = 0; i < unosEquipos.size(); i++) 
		{
			System.out.println("- Equipo N° " +(i+1) + " -");
			System.out.println(unosEquipos.stream().toArray(Equipo[]:: new)[i].nombre());
		} 
	}
	
	public static void mostrarPronosticos(ArrayList<Pronostico> unosPronosticos) 
	{
		for(int i = 0; i < unosPronosticos.size(); i++) 
		{
			System.out.println("- Pronostico N° " + (i+1) + " -");
			System.out.println("Partido pronosticado: ");
			mostrarPartido(unosPronosticos.get(i).partido());
			System.out.println("Equipo pronosticado: " + unosPronosticos.get(i).equipo().nombre());
			System.out.println("Resultado pronosticado: " + unosPronosticos.get(i).resultado());
		} 	
	}

}
