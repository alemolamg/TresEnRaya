package juego;

import java.awt.Color;
import java.awt.Graphics;

public class Cuadro {
	// Atributos
	private int x, y;
	private int posX,posY;
	private int ancho, alto;
	private boolean marcado = false;
	
	public Cuadro(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		
		this.posX = ((ancho / 3) * x);
		this.posY = ((alto / 3) * y);
		
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
		paintFicha(g);
	}
	
	
	public void paintFicha(Graphics g) {
			if (Juego.getInstance().matriz[this.x][this.y] == Juego.JUGADOR1) {
				this.marcado = true;
				g.setColor(Color.blue);
				g.drawOval(this.posX+10, posY+5, ancho-18, ancho-20);
			}
			
			if (Juego.getInstance().matriz[this.x][this.y] == Juego.JUGADOR2) {
				this.marcado = true;
				g.setColor(Color.red);
				g.drawRect(this.posX+15, posY+15, ancho-24, ancho-35);
			}
			
	}
	
	
	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	
}
