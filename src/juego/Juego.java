package juego;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class Juego {

	private static JFrame ventana = null;
	private Tablero tablero = null;
	List<Cuadro> cuadros = new ArrayList<Cuadro>();
	private static Juego instance = null;
	//int matriz[][] = new int [][];
			
				
	public JFrame getVentana() {
		return ventana;
	}

	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}

	public static Juego getInstance () { //Limitar a dos creacioens max
		if (instance == null) { // Si no está inicializada, se inicializa
			instance = new Juego();
		}
		return instance;
	}
		
	public Juego() {
		ventana = new JFrame("3 en Raya");
		ventana.setBounds(0, 0, 700, 680);
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla) al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		
		
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		tablero = new Tablero(cuadros);
		
		ventana.getContentPane().add(tablero, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
		ventana.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
		ventana.setVisible(true);
		
		// Creo una lista de actores que intervendrá en el juego.
		//cuadros = creaCuadros();	// Da error de bucle infinito;
				
		// Control del evento de cierre de ventana
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		/*ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
			}
		});*/
	}
	
	
	private List<Cuadro> creaCuadros() {
		List<Cuadro> creaCuadros = new ArrayList<Cuadro>();
		
		for (int i = 0; i < 3; i++) { 
			for (int j = 0; j < 3; j++) { 
				creaCuadros.add(new Cuadro (i,j));
			}
		}
		return creaCuadros;
	}

	public static void main(String[] args) {
		Juego.getInstance();

	}

}
