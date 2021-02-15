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
import javax.swing.JOptionPane;


public class Juego {
	public static int JUGADOR1 = 1, JUGADOR2 = 2;
	private static JFrame ventana = null;
	private Tablero tablero = null;
	List<Cuadro> cuadros = new ArrayList<Cuadro>();
	private static Juego instance = null;
	int matriz[][] = new int [3][3];
			
				
	public JFrame getVentana() {
		return ventana;
	}
	
	public List<Cuadro> getCuadros() {
		return cuadros;
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
		
	public Tablero getTablero() {
		return tablero;
	}
	
	public Juego() {
		int ventanaAncho = 700, ventanaAlto = 705;
		ventana = new JFrame("3 en Raya");
		ventana.setBounds(0, 0, ventanaAncho, ventanaAlto);
		
		tablero = new Tablero();
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla)
		// al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());

		ventana.getContentPane().add(tablero, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
		ventana.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
		ventana.setVisible(true);
		
		creaCuadros();

		// Control del evento de cierre de ventana
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {  
		  @Override public void windowClosing(WindowEvent e) { cerrarAplicacion(); }
		  });
		
		ventana.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	//TODO implementar lector coordenadas.
				
				super.mouseClicked(e);
			}
		});
		
		
		 
	}
	
	
	private void creaCuadros() {
		
		for (int i = 0; i < 3; i++) { 
			for (int j = 0; j < 3; j++) { 
				this.cuadros.add(new Cuadro (i,j,getTablero().getWidth(), getTablero().getHeight()));
			}
		}
	}
	
	/**
	 * Al cerrar la aplicación preguntaremos al usuario si está seguro de que desea salir.
	 */
	private void cerrarAplicacion() {
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"¿Desea cerrar la aplicación?","Salir de la aplicación",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	

	public static void main(String[] args) {
		Juego.getInstance();

	}
	
	
	

}
