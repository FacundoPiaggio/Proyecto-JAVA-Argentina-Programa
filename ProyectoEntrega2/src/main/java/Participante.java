
import java.util.ArrayList;

public class Participante {
	
	private String nombre;
	private ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
	
	public Participante(String unNombre) 
	{
		nombre = unNombre;
	}
	
	public void agregarPronostico(Pronostico unPronostico) 
	{
		pronosticos.add(unPronostico);
	}
	
	public int puntosTotales() 
	{
		int puntos = 0;
		for(Pronostico unPronostico : pronosticos) 
		{
			puntos += unPronostico.puntos();
		}
		return puntos;
	}
	
	public int puntosDeRonda(ArrayList<Partido> partidosDeRonda) 
	{
		int puntos = 0;
		for(Pronostico unPronostico: pronosticos) 
		{
			if(partidosDeRonda.contains(unPronostico.partido())) 
			{
				puntos += unPronostico.puntos();
			}
		}
		return puntos;
	}
	
	
	// Getters
	public String nombre() 
	{
		return nombre;
	}
	
	public ArrayList<Pronostico> pronosticos()
	{
		return pronosticos;
	}
	
	public void mostrarDatos() 
	{
		System.out.println("- Datos del participante: ");
		System.out.println("Nombre: " + nombre);
		System.out.println("Pronosticos del participante: ");
		for(int i = 0; i < pronosticos.size(); i++) 
		{
			pronosticos.get(i).mostrarDatos();
		}
	}
}
