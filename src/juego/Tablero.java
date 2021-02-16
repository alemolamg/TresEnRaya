package juego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Tablero extends Canvas{
	// Atributos
	
	//public static List<Cuadro> cuadros = null;
	
	public Tablero() {
		super();
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		//g.drawRect(0, 1, 100, 100);
		
		for (Cuadro cu : Juego.getInstance().getCuadros()) {
			cu.paint(g);
		} 
	}	
	
}
