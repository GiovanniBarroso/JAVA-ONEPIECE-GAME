package ui;

import java.awt.Rectangle;

/**
 * Clase que representa un botón en la pantalla de pausa.
 */
public class PauseButton {

    protected int x, y, width, height;
    protected Rectangle bounds;

    /**
     * Crea un nuevo botón de pausa con la posición y dimensiones especificadas.
     * 
     * @param x      la coordenada X del botón
     * @param y      la coordenada Y del botón
     * @param width  el ancho del botón
     * @param height el alto del botón
     */
    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBound();
    }

    /**
     * Crea el límite del botón.
     */
    private void createBound() {
        bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Obtiene la coordenada X del botón.
     * 
     * @return la coordenada X
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la coordenada X del botón.
     * 
     * @param x la coordenada X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada Y del botón.
     * 
     * @return la coordenada Y
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la coordenada Y del botón.
     * 
     * @param y la coordenada Y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtiene el ancho del botón.
     * 
     * @return el ancho del botón
     */
    public int getWidth() {
        return width;
    }

    /**
     * Establece el ancho del botón.
     * 
     * @param width el ancho del botón
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Obtiene el alto del botón.
     * 
     * @return el alto del botón
     */
    public int getHeight() {
        return height;
    }

    /**
     * Establece el alto del botón.
     * 
     * @param height el alto del botón
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Obtiene los límites del botón.
     * 
     * @return los límites del botón
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Establece los límites del botón.
     * 
     * @param bounds los límites del botón
     */
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
