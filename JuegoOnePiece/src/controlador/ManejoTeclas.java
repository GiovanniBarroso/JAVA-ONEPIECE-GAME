package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vista.Juego1;

import static utilz.Constantes.Direction.*;

public class ManejoTeclas implements KeyListener {
    private Juego1 panel1;

    public ManejoTeclas(Juego1 panel1) {
        this.panel1 = panel1;
    }

    public void keyTyped(KeyEvent e) {
        // No es necesario implementar esta función, pero debes mantenerla si implementas KeyListener.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                panel1.setDirection(UP);
                panel1.setMoving(true);
                break;
            case KeyEvent.VK_A:
                panel1.setDirection(LEFT);
                panel1.setMoving(true);
                break;
            case KeyEvent.VK_S:
                panel1.setDirection(DOWN);
                panel1.setMoving(true);
                break;
            case KeyEvent.VK_D:
                panel1.setDirection(RIGHT);
                panel1.setMoving(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                panel1.setMoving(false);
                break;
        }
    }
}
