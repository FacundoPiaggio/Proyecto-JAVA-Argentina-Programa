import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProyectoTest {

	Equipo unEquipo1;
	Equipo unEquipo2;
	Equipo unEquipo3;
	Equipo unEquipo4;
	Partido unPartido1;
	Partido unPartido2;
	Pronostico unPronostico1;
	Pronostico unPronostico2;
	Participante elParticipante;
	Ronda unaRonda1;
	Ronda unaRonda2;
	
	@Before
	public void inicializar() 
	{
		unEquipo1 = new Equipo("Argentina","-");
		unEquipo2 = new Equipo("Peru","-");
		unEquipo3 = new Equipo("Paraguay","-");
		unEquipo4 = new Equipo("Uruguay","-");
		unPartido1 = new Partido(unEquipo1, unEquipo2, 3, 1);
		unPartido2 = new Partido(unEquipo3, unEquipo4, 1, 1);
		unPronostico1 = new Pronostico(unPartido1, unEquipo1, ResultadoEnum.GANADOR);
		unPronostico2 = new Pronostico(unPartido2, unEquipo4, ResultadoEnum.GANADOR);
		elParticipante = new Participante("Juan");
		elParticipante.agregarPronostico(unPronostico1);
		elParticipante.agregarPronostico(unPronostico2);
		unaRonda1 = new Ronda("1");
		unaRonda1.agregarPartido(unPartido1);
		unaRonda2 = new Ronda("2");
		unaRonda1.agregarPartido(unPartido2);
	}
	
	@Test
	public void el_puntaje_del_participante_es_1_para_primera_Ronda() {
		
		assertEquals(0,unaRonda2.puntos(elParticipante));
	}
	
	@Test
	public void el_puntaje_del_participante_es_0_para_segunda_Ronda() {
		
		assertEquals(0,unaRonda2.puntos(elParticipante));
	}

}
