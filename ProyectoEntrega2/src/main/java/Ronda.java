import java.util.ArrayList;

//clase para la ronda

public class Ronda {
	
	// variables numero y partidos
	private String numero;
	private ArrayList<Partido> partidos = new ArrayList<Partido>();
	
	// un constructor para la Ronda. toma un numero y unos partidos.
	public Ronda(String unNumero) 
	{
		numero = unNumero;
	}
	
	public void agregarPartido(Partido unPartido)
	{
		partidos.add(unPartido);
	}
	
	// metodo para los puntos
	public int puntos(Participante unParticipante) 
	{
		int puntos = 0;
		puntos += unParticipante.puntosDeRonda(partidos);
		
		return puntos;
	}
	
	// getters
	public String numero() 
	{
		return numero;
	}
	
	public ArrayList<Partido> partidos()
	{
		return partidos;
	}
	
	public void mostrarDatos() 
	{
		System.out.println("- Datos de ronda: -");
		System.out.println("Nro: " + numero);
		System.out.println("Partidos de la ronda: ");
		for(int i = 0; i < partidos.size(); i++) 
		{
			partidos.get(i).mostrarDatos();
		}
	}
}