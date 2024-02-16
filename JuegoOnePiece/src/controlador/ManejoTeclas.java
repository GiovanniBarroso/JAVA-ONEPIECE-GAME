package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vista.Juego1;

import static utilz.Constantes.Direction.*;
public class ManejoTeclas implements KeyListener{
	private Juego1 panel1= new Juego1();

	public void setPanel1(Juego1 panel1) {
		this.panel1 = panel1;
	}
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel1.setDirection(UP);
			panel1.setMoving(true);
			break;
		case KeyEvent.VK_A:
			panel1.setDirection(DOWN);
			break;	
		case KeyEvent.VK_S:
			panel1.setDirection(LEFT);
		case KeyEvent.VK_D:
			panel1.setDirection(RIGHT);
			break;
		}
		}

	@Override
	public void keyReleased(KeyEvent e) {

	switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_D:
		panel1.setMoving(false);
			break;
	}

}
}