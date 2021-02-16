package juego;

import java.awt.Color;
import java.awt.Graphics;

public class Cuadro {
	// Atributos
	private int x, y;
	private int posX,posY;
	private int ancho, alto;
	private boolean marcado = false;
	
	/*public Cuadro(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.posX = (Juego.getInstance().getVentana().getWidth() / 3) * x;
		this.posY = (Juego.getInstance().getVentana().getHeight() / 3) * y;
		
		this.ancho = Juego.getInstance().getVentana().getWidth() / 3;
		this.alto = Juego.getInstance().getVentana().getHeight() / 3;
	}*/
	
	public Cuadro(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		
//		if(x == 0 && y == 0) {
//			this.posX = ((ancho / 3) * x) + 1 ;
//			this.posY = ((alto / 3) * y) + 1;
//		} else {
			this.posX = ((ancho / 3) * x);
			this.posY = ((alto / 3) * y);
//		}
		
		this.ancho = ancho / 3;
		this.alto = (alto / 3);
	}

	public int getPosX() {
		return posX;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public void paint(Graphics g) {
		//System.out.println("PintoUnCuadro");
		g.setColor(Color.black);
		g.drawRect(posX, posY, ancho, alto);
		//System.out.println("Pinto en x: " + this.posX + " y: " + this.posY + " ancho: " + this.ancho + " alto: " + this.alto);
		
	}
	
	
	public void paintFicha(Graphics g, int jugador) {
		this.paint(g);
		g.setColor(Color.blue);
		g.drawOval(ancho/2, alto/2, ancho-2, ancho-2);
	}
	
	
}
