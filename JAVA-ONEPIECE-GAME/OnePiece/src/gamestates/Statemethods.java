package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Interfaz que define los métodos que deben ser implementados por los diferentes estados del juego.
 */
public interface Statemethods {
    
    /**
     * Actualiza el estado del juego.
     */
    public void update();

    /**
     * Dibuja el estado del juego en el lienzo.
     * 
     * @param g El objeto Graphics para dibujar.
     */
    public void draw(Graphics g);

    /**
     * Maneja el evento de hacer clic del mouse.
     * 
     * @param e El evento del mouse.
     */
    public void mouseClicked(MouseEvent e);

    /**
     * Maneja el evento de presionar el mouse.
     * 
     * @param e El evento del mouse.
     */
    public void mousePressed(MouseEvent e);

    /**
     * Maneja el evento de soltar el mouse.
     * 
     * @param e El evento del mouse.
     */
    public void mouseReleased(MouseEvent e);

    /**
     * Maneja el evento de mover el mouse.
     * 
     * @param e El evento del mouse.
     */
    public void mouseMoved(MouseEvent e);

    /**
     * Maneja el evento de presionar una tecla del teclado.
     * 
     * @param e El evento del teclado.
     */
    public void keyPressed(KeyEvent e);

    /**
     * Maneja el evento de soltar una tecla del teclado.
     * 
     * @param e El evento del teclado.
     */
    public void keyReleased(KeyEvent e);
}
