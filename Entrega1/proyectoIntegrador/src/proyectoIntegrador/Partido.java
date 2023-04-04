package proyectoIntegrador;

// Clase de los partidos

public class Partido {
	
	// Variables para los equipos y los goles respectivos
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;
	
	// un constructor para los partidos. Toma 2 equipos y los respectivos goles.
	public Partido(Equipo unEquipo, Equipo otroEquipo, int unosGoles, int otrosGoles) 
	{
		equipo1 = unEquipo;
		equipo2 = otroEquipo;
		golesEquipo1 = unosGoles;
		golesEquipo2 = otrosGoles;
	}
	
	// un metodo que retorna un resultado. pasandole un equipo.
	public ResultadoEnum resultado(Equipo unEquipo) 
	{	
		if(unEquipo.nombre().equals(equipo1.nombre())) 
		{
			if(golesEquipo1 > golesEquipo2) 
			{
				return ResultadoEnum.GANADOR;
			}
			else if(golesEquipo2 > golesEquipo1) 
			{
				return ResultadoEnum.PERDEDOR;
			}
			else 
			{
				return ResultadoEnum.EMPATE;
			}
		}
		else 
		{
			if(golesEquipo1 > golesEquipo2) 
			{
				return ResultadoEnum.PERDEDOR;
			}
			else if(golesEquipo2 > golesEquipo1) 
			{
				return ResultadoEnum.GANADOR;
			}
			else 
			{
				return ResultadoEnum.EMPATE;
			}
		}
	}
	
	// Getters:
	public Equipo equipo1() 
	{
		return equipo1;
	}
	
	public Equipo equipo2() 
	{
		return equipo2;
	}
	
	public int golesEquipo1() 
	{
		return golesEquipo1;
	}
	
	public int golesEquipo2() 
	{
		return golesEquipo2;
	}
	
	
	
}
