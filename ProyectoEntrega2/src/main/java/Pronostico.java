//clase para los pronosticos

public class Pronostico {
	
	// variables partido, equipo y un resultado del enumerado.
	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;
	
	// un constructor para el pronostico
	public Pronostico(Partido unPartido, Equipo unEquipo, ResultadoEnum unResultado) 
	{
		partido = unPartido;
		equipo = unEquipo;
		resultado = unResultado;
	}
	
	// un metodo para los puntos.
	// si el resultado del partido para el equipo pronosticado, es igual al resultado que pronosticó, entonces retorna 1
	// sino retorna 0
	public int puntos() 
	{
		if(partido.resultado(equipo) == resultado) 
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	
	// Getters
	
	public Partido partido() 
	{
		return partido;
	}
	
	public Equipo equipo() 
	{
		return equipo;
	}
	
	public ResultadoEnum resultado() 
	{
		return resultado;
	}
	
	public void mostrarDatos() 
	{
		System.out.println("- Datos del pronostico: -");
		System.out.println("Partido del pronostico: ");
		partido.mostrarDatos();
		System.out.println("Equipo pronosticado: ");
		equipo.mostrarDatos();
		System.out.println("Resultado pronosticado: " + resultado);
	}
	
}