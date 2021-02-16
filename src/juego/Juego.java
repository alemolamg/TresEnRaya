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
		
		tablero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	//TODO implementar lector coordenadas.
				super.mouseClicked(e);
				System.out.println("Pulsado en coordenada x: " +e.getX() + "y: " + e.getY() );
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
	
	public void buscaCuadros(int coordX, int coordY) {	//TODO	terminar método
		int x = 0, y = 0;
		
		if(coordX <= (tablero.getWidth()/3) )
			x = 0;
		else
			if (coordX <= (tablero.getWidth() * 2/3) )
				x = 1;
			else
				x = 2;
		
		if(coordY <= (tablero.getHeight()/3) )
			y = 0;
		else
			if (coordY <= (tablero.getHeight() * 2/3) )
				y = 1;
			else
				y = 2;
		
		// añadimos el valor a la matriz.
		this.matriz
		
	}
	
	/**
	 * Jugada del jugador.
	 * @param jugador (int)	Jugador que realiza la jugada.
	 * @param arraySup
	 * @param arrayMed
	 * @param arrayInf
	 */
//	public static void jugadaJugador (int jugador,int matriz[][]) {
//		int x, y;
//		boolean seAniade = true;
//		//mostrarTablero(matriz); 
//		do {
//			System.out.println("\n¿En qué coordenadas quieres añadir tu ficha?");
//			System.out.print("\n X= "); x = leerDentroJuego();	
//			System.out.print(" Y= "); y = leerDentroJuego();	
//			seAniade = aniadirFicha(jugador, x, y, matriz);
//			if(!seAniade)
//				System.out.println("Posición ocupada, vuelve a intentarlo.");
//				
//		}while(!seAniade);
//		
//	}
	
	
	
//	public static boolean calcularFinal(int matriz[][]) {
//		int jugador1 = 1, jugador2 = 2;
//		boolean terminado = false ;
//		
//		//Primero miramos para ganar el jugador1
//		
//		if (ganaJugador(jugador1, matriz)) { 
//			System.out.println("Ha ganado el jugador " + jugador1 + "\n La partida ha terminado");
//			terminado = true;
//		}
//		
//		// Verificamos la victoria del jugador dos
//		if (ganaJugador(jugador2, matriz)) { 
//			System.out.println("Ha ganado el jugador " + jugador2 + "\n La partida ha terminado");
//			terminado = true;
//		}
//		
//		return terminado;
//		
//	}
	
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
