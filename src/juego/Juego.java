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
	public int turno = 1;
			
				
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
				jugada(e.getX(), e.getY());
				
			}
		});
			 
	}
	
	public void jugada (int coordX, int coordY) {
		// Pasamos las coordenadas a una posición de la matriz
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
		
		if(aniadirFicha(x, y)) { // Añadimos la jugada a la matriz
			pintaJugada(x, y);	
			if(calcularFinal())
				this.cerrarAplicacion();
		}
		
	}
	
	
	private void pintaJugada(int x, int y) {
		tablero.repaint();
	}
	
	private void creaCuadros() {
		for (int i = 0; i < 3; i++) { 
			for (int j = 0; j < 3; j++) { 
				this.cuadros.add(new Cuadro (i,j,getTablero().getWidth(), getTablero().getHeight()));
			}
		}
	}
	
	
	/**
	 * Método que busca un cuadro dada la posición en la matriz
	 * @param coordX
	 * @param coordY
	 * @return
	 */
	public Cuadro buscaCuadros(int x, int y) {
		for (Cuadro cu : cuadros) {
			if(cu.getPosX() == x && cu.getPosY() == y )
				return cu;
		}
		
		System.out.println("Error en buscarCuadro");
		return null;	
	}
	
	
	public boolean aniadirFicha(int x, int y) {
		if (matriz[x][y] == 0) {
			this.matriz[x][y] = this.turno;
			cambioTurno();
		} else
			return false;

		return true;
	}
				
	
	private void cambioTurno() {
		if (this.turno == JUGADOR1)
			this.turno = JUGADOR2;
		else
			this.turno = JUGADOR1;
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
	
	/**
	 * Al cerrar la aplicación preguntaremos al usuario si está seguro de que desea salir.
	 */
	private void cerrarAplicacion(int Jugador) {
		String [] opciones ={"Fin de la partida"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"¡Ha ganado el jugador " + Jugador + "!","Salir de la aplicación",
		JOptionPane.OK_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Fin de la partida");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	/**
	 * Al cerrar la aplicación preguntaremos al usuario si está seguro de que desea salir.
	 */
	private void cerrarAplicacionEmpate() {
		String [] opciones ={"Fin de la partida"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"¡Hemos empatado!","Salir de la aplicación",
		JOptionPane.OK_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Fin de la partida");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	

	/**
	 * Calcula el final de juego, y si ha acabado.
	 * @param matriz
	 * @return
	 */
	public boolean calcularFinal() {
//		int jugador1 = 1, jugador2 = 2;
		boolean terminado = false ;
		
		//Primero miramos para ganar el jugador1
		
		if (ganaJugador(JUGADOR1)) { 
			System.out.println("Ha ganado el jugador " + JUGADOR1 + "\n La partida ha terminado");
			terminado = true;
			cerrarAplicacion(JUGADOR1);
		}
		
		// Verificamos la victoria del jugador dos
		if (ganaJugador(JUGADOR2)) { 
			System.out.println("Ha ganado el jugador " + JUGADOR2 + "\n La partida ha terminado");
			terminado = true;
			cerrarAplicacion(JUGADOR2);
		}
		
		if(empate()) {
			terminado = true;
			System.out.println("Juego acaba en empate");
			cerrarAplicacionEmpate();
		}
		
		return terminado;
		
	}
	
	private boolean empate() {
		// gestionamos empate.
		boolean empate = true;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (this.matriz[i][j] == 0) {
					empate = false;
					break;
				}
			}
		}
		return empate;
	}

	public static void main(String[] args) {
		Juego.getInstance();

	}
	
	public boolean ganaJugador (int jugador) {
		boolean victoria = false;
	
		//verificamos todas las victorias horizontales
		if(ganarHorizontal(jugador, matriz[0]))
			return true;
		if(ganarHorizontal(jugador, matriz[1]))
			return true;
		if(ganarHorizontal(jugador, matriz[2]))
			return true;
		
		//verificamos todas las victorias verticales
		for (int i = 0; i < matriz[0].length; i++) {
			if (ganarVertical(jugador, i))	// Si hemos ganado devolvemos true, si no verificamos la siguiente condición.
				return true;
		}
		
		//Verificamos diagonal
		if(ganarDiagonal(jugador))
			return true;
		
		//Verificamos diagonal Inversa
		if(ganarDiagonalInv(jugador))
			return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param jugador
	 * @param arrayVer
	 * @return
	 */
	private boolean ganarHorizontal(int jugador, int arrayVer[]) {	//TODO revisar
//		boolean horizontal = true;
		for(int i = 0; i < arrayVer.length; i++) {	// revisamos la fila superior en horizontal.
			if(arrayVer[i] != jugador) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param jugador
	 * @param arraySup
	 * @param arrayMed
	 * @param arrayInf
	 * @return
	 */
	private boolean ganarDiagonal(int jugador) {
		if(matriz[0][0] == jugador)
			if(matriz[1][1] == jugador)
				if (matriz[2][2] == jugador)
					return true;
		return false;
	}
	
	/**
	 * 
	 * @param jugador
	 * @param arraySup
	 * @param arrayMed
	 * @param arrayInf
	 * @return
	 */
	private boolean ganarDiagonalInv(int jugador) {
		if(matriz[0][2] == jugador)
			if(matriz[1][1] == jugador)
				if (matriz[2][0] == jugador)
					return true;
		return false;
	}
	
	
	
	/**
	 * Calcula si se gana de manera vertical.
	 * @param jugador
	 * @param pos
	 * @param arraySup
	 * @param arrayMed
	 * @param arrayInf
	 * @return	true si gana, false si no.
	 */
	private boolean ganarVertical(int jugador,int pos) {
		if(matriz[0][pos] == jugador)
			if(matriz[1][pos] == jugador)
				if (matriz[2][pos] == jugador)
					return true;
		return false;
	}
	
	
	

}
