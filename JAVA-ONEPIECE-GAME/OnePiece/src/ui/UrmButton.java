package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

/**
 * Clase que representa un botón de la interfaz de usuario.
 */
public class UrmButton extends PauseButton {

    private BufferedImage[] imgs; 
    private int rowIndex, index; 
    private boolean mouseOver, mousePressed; 

    /**
     * Constructor de la clase UrmButton.
     * 
     * @param x         Coordenada x del botón en la ventana.
     * @param y         Coordenada y del botón en la ventana.
     * @param width     Ancho del botón.
     * @param height    Alto del botón.
     * @param rowIndex  Índice de fila para determinar la imagen del botón.
     */
    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height); 
        this.rowIndex = rowIndex; 
        loadImgs(); 
    }

    private void loadImgs() {
     
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
    }

    /**
     * Actualiza el estado del botón.
     */
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    /**
     * Dibuja el botón en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el botón.
     */
    public void draw(Graphics g) {
        // Dibuja la imagen correspondiente en el botón
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }

    /**
     * Reinicia los estados de mouseOver y mousePressed del botón.
     */
    public void resetBools() {
        // Reinicia los estados de mouseOver y mousePressed
        mouseOver = false;
        mousePressed = false;
    }

    // Getters y setters para los estados de mouseOver y mousePressed
    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
