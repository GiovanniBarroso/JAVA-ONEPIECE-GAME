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

    private void createBound() {
        bounds = new Rectangle(x, y, width, height);
    }

    // Getters y setters para las propiedades del botón

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
