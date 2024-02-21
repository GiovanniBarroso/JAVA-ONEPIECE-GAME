package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vista.Juego_Luffy;

import static utilz.Constantes.Direction.*;

public class ManejoTeclas implements KeyListener {
	private Juego_Luffy panel1;

	public ManejoTeclas(Juego_Luffy panel1) {
		this.panel1 = panel1;
	}

	public void keyTyped(KeyEvent e) {
		// No es necesario implementar esta función, pero debes mantenerla si implementas KeyListener.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel1.getPlayer().setUp(true);
			break;
		case KeyEvent.VK_A:
			panel1.getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S:
			panel1.getPlayer().setDown(true);
			break;
		case KeyEvent.VK_D:
			if (e.isShiftDown()) {
				panel1.getPlayer().setcorrer(true);
			} else {
				panel1.getPlayer().setRight(true);}
			break;
		case KeyEvent.VK_C:
			panel1.getPlayer().setDefending(true);
			break;


		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel1.getPlayer().setUp(false);
			break;
		case KeyEvent.VK_A:
			panel1.getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S:
			panel1.getPlayer().setDown(false);
			break;
		case KeyEvent.VK_D:
			panel1.getPlayer().setRight(false);
			panel1.getPlayer().setcorrer(false);
			break;
		case KeyEvent.VK_C:
			panel1.getPlayer().setDefending(false);
			break;
		}
	}
}