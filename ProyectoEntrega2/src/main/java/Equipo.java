
//Clase para los equipos

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
	
	public void mostrarDatos() 
	{
		System.out.println("- Datos del equipo: -");
		System.out.println("Nombre: " + nombre);
		System.out.println("Descripcion: " + descripcion);
	}
}