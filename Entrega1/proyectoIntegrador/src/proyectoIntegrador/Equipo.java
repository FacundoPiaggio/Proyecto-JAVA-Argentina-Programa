package proyectoIntegrador;

// Clase para los equipos

public class Equipo {
	
	// las variables nombre y descripcion
	private String nombre;
	private String descripcion;
	
	// un constructor para los equipos, toma un nombre y una descripcion.
	public Equipo(String unNombre, String unaDescripcion) 
	{
		nombre = unNombre;
		descripcion = unaDescripcion;
	}
	
	//Getters
	public String nombre() 
	{
		return nombre;
	}
	
}
