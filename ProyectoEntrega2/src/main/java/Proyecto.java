

import java.io.File;
import java.io.IOException;
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
		ArrayList<Ronda> rondas = new ArrayList<Ronda>();
		ArrayList<Participante> participantes = new ArrayList<Participante>();

		crearEquiposPartidosYRondas(lectoraResultados, equipos, partidos, rondas);				// Llamamos al metodo crearEquiposYPartidos. le pasamos la lectoraResultados, el set vacios de equipos y el arraylist de partidos

		//mostrarPartidos(partidos);													// Llamamos al metodo mostrarPartidos para ver que se hayan creado bien.
		
		//mostrarEquipos(equipos);													// Llamamos al metodo mostrarEquipos para ver que se hayan creado bien.

		mostrarRondas(rondas);
		System.out.println();
		crearPronosticos(lectoraPronosticos, pronosticos, equipos, partidos, participantes);			// Llamamos al metodo crearPronosticos. Le pasamos la lectoraPronosticos, el arraylist vacio de los pronosticos y los equipos y partidos previamente creados.

		//mostrarPronosticos(pronosticos);												// Llamamos al metodo mostrarPronosticos para ver que se hayan creado bien.
		System.out.println();
		mostrarParticipantes(participantes);
		System.out.println();
		
		for(Participante unParticipante : participantes) 
		{
			System.out.println("El puntaje de: " + unParticipante.nombre() + " es: " + unParticipante.puntosTotales());
		}
		
	}


// Metodo para crear los equipos y los partidos
// debe recibir: Un Scanner que haya sido abierto en el archivo de Resultados, un set de equipos vacio, y un arraylist de partidos vacio.
	public static void crearEquiposPartidosYRondas(Scanner unaLectoraResultados, Set<Equipo> unosEquipos, ArrayList<Partido> unosPartidos, ArrayList<Ronda> unasRondas) 
	{
		String lineaResultado;																	// Un String para tomar la linea leida
		String[] valoresResultado;																// un String[] para tomar los valores de esa linea																	.
		Equipo unEquipo, otroEquipo;															// definimos los equipos que cargaremos en el loop
		Partido unPartido;																		// definimos el partido que cargaremos en el loop
		int nroRonda = 0;
		Ronda unaRonda = null;
		while(unaLectoraResultados.hasNextLine()) 												// Si existe una linea por leer, el while sigue.
		{
			lineaResultado = unaLectoraResultados.nextLine();									// tomamos una linea del archivo 
			valoresResultado = lineaResultado.split(",");										// separamos esa linea por las "," y tomamos un array de Strings
			
			unEquipo = new Equipo(valoresResultado[1], "Descripcion generica");					// Creamos los equipos. En las posiciones 0 y 3 se encuentran los nombres.
			otroEquipo = new Equipo(valoresResultado[4], "Descripcion generica");
			unosEquipos.add(unEquipo);															// Usamos el metodo add(algo) para agregar los equipos creados al set
			unosEquipos.add(otroEquipo);

			// Para crear los partidos, le pasamos los equipos previamente creados, y los goles de los equipos estan en las posiciones 1 y 2, pero como strings, por lo que habra que parsearlos a integer.
			unPartido = new Partido(unEquipo, otroEquipo, Integer.parseInt(valoresResultado[2]),Integer.parseInt(valoresResultado[3]));
			unosPartidos.add(unPartido);														// Usamos de vuelta el metodo add para añadir un valor, esta vez al arraylist
		
			if(nroRonda == Integer.parseInt(valoresResultado[0])) 
			{
				unaRonda.agregarPartido(unPartido);
			}
			else 
			{
				nroRonda++;
				unaRonda = new Ronda(valoresResultado[0]);
				unaRonda.agregarPartido(unPartido);
				unasRondas.add(unaRonda);
			}
		}
	}


	// Metodo para crear los pronosticos
	// debe recibir: Un Scanner que haya sido abierto en el archivo de pronosticos, un arraylist de pronosticos vacios y los equipos y partidos creados previamente.
	public static void crearPronosticos(Scanner unaLectoraPronosticos, ArrayList<Pronostico> unosPronosticos, Set<Equipo> unosEquipos, ArrayList<Partido> unosPartidos, ArrayList<Participante> unosParticipantes) 
	{
		String lineaPronostico;																	// Un String para tomar la linea leida
		String[] valoresPronostico;																// un String[] para tomar los valores de esa linea
		int i = 0;																				// 1 int para ir moviendonos a traves de los partidos
		Pronostico unPronostico;																// declaramos el pronostico que crearemos en el loop
		Participante unParticipante = null;
		while(unaLectoraPronosticos.hasNextLine()) 												// Si existe una linea por leer, el while sigue.
		{
			lineaPronostico = unaLectoraPronosticos.nextLine();									// tomamos una linea del archivo
			valoresPronostico = lineaPronostico.split(",");										// separamos esa linea por las "," y tomamos un array de strings.
			
			
			// Si hay una "x" en la posicion 1, quiere decir que el primer equipo del partido, es el que pronostico que ganaria
			if(valoresPronostico[2].compareToIgnoreCase("x") == 0) 								
			{
				// Para obtener el equipo del pronostico, usamos el metodo buscarEquipo, y le pasamos el nombre del equipo, que el participante cree que va a ganar
				//en este caso, como la x esta en la posicion 1, quiere decir que cree que ganara el primer equipo, sino seria el segundo.
				unPronostico = new Pronostico(unosPartidos.get(i), buscarEquipo(unosEquipos, valoresPronostico[1]), ResultadoEnum.GANADOR);
				unosPronosticos.add(unPronostico);
			}
			// Si la "x" esta en la posicion 2, quiere decir que es un empate, por lo que ponemos un equipo con nombre "EMPATE".
			else if(valoresPronostico[3].compareToIgnoreCase("x") == 0)						
			{
				unPronostico = new Pronostico(unosPartidos.get(i), new Equipo("EMPATE", "-"), ResultadoEnum.EMPATE);
				unosPronosticos.add(unPronostico);
			}
			// Si la "x" esta en la posicion 3, quiere decir que el equipo[j+1], es decir el segundo, es el pronosticado como ganador.
			else 
			{
				unPronostico = new Pronostico(unosPartidos.get(i), buscarEquipo(unosEquipos, valoresPronostico[5]), ResultadoEnum.GANADOR);
				unosPronosticos.add(unPronostico);
			}
			i++;																				// Incrementamos i en 1		
			
			if(i == unosPartidos.size()) 
			{
				i = 0;
			}
			
			if(unParticipante == null) 
			{
				unParticipante = new Participante(valoresPronostico[0]);
				unParticipante.agregarPronostico(unPronostico);
				unosParticipantes.add(unParticipante);
			}
			else if(unParticipante.nombre().equals(valoresPronostico[0])) 
			{
				unParticipante.agregarPronostico(unPronostico);
			}
			else 
			{
				unParticipante = new Participante(valoresPronostico[0]);
				unParticipante.agregarPronostico(unPronostico);
				unosParticipantes.add(unParticipante);
			}
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
			unosPartidos.get(i).mostrarDatos();
		}
	}

	public static void mostrarPartido(Partido unPartido) 
	{
		unPartido.mostrarDatos();
	}

	public static void mostrarEquipos(Set<Equipo> unosEquipos) 
	{
		for(int i = 0; i < unosEquipos.size(); i++) 
		{
			System.out.println("- Equipo N° " +(i+1) + " -");
			//System.out.println(unosEquipos.stream().toArray(Equipo[]:: new)[i].nombre());
			unosEquipos.stream().toArray(Equipo[]::new)[i].mostrarDatos();
			unosEquipos.stream().toList().get(i).mostrarDatos();
		} 
	}

	public static void mostrarPronosticos(ArrayList<Pronostico> unosPronosticos) 
	{
		for(int i = 0; i < unosPronosticos.size(); i++) 
		{
			System.out.println("- Pronostico N° " + (i+1) + " -");
			//System.out.println("Partido pronosticado: ");
			//mostrarPartido(unosPronosticos.get(i).partido());
			//System.out.println("Equipo pronosticado: " + unosPronosticos.get(i).equipo().nombre());
			//System.out.println("Resultado pronosticado: " + unosPronosticos.get(i).resultado());
			unosPronosticos.get(i).mostrarDatos();
		} 	
	}
	
	public static void mostrarRondas(ArrayList<Ronda> unasRondas) 
	{
		for(int i = 0; i < unasRondas.size(); i++) 
		{
			System.out.println("- Ronda N° " + Integer.parseInt(unasRondas.get(i).numero()));
			//mostrarPartidos(unasRondas.get(i).partidos());
			unasRondas.get(i).mostrarDatos();
		}
	}
	
	public static void mostrarParticipantes(ArrayList<Participante> unosParticipantes) 
	{
		for(int i = 0; i < unosParticipantes.size(); i++) 
		{
			System.out.println("- Participante N° " + (i+1));
			//System.out.println("Nombre: " + unosParticipantes.get(i).nombre());
			//mostrarPronosticos(unosParticipantes.get(i).pronosticos());
			unosParticipantes.get(i).mostrarDatos();
		} 
	}

}