package juego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Tablero extends Canvas{
	// Atributos
	
	List<Cuadro> cuadros = null;
	
	public Tablero(List<Cuadro> cuadros) {
		this.cuadros = cuadros;
	}
	
	@Override
	public void paint(Graphics g) {
		this.setBackground(Color.magenta);
		
		for (Cuadro cu : cuadros) {
			cu.paint(g);
		}
	}
	
	
}
