package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

import static utilz.Constants.UI.VolumeButtons.*;

/**
 * Clase que representa un botón de volumen en la interfaz de usuario.
 */
public class VolumeButton extends PauseButton {

    private BufferedImage imgs[];
    private BufferedImage slider;
    private int index = 0;

    private boolean mouseOver, mousePressed;
    private int buttonX, minX, maxX;
    private float floatValue = 0f;

    /**
     * Constructor de la clase VolumeButton.
     * 
     * @param x      Coordenada x del botón en la ventana.
     * @param y      Coordenada y del botón en la ventana.
     * @param width  Ancho del botón.
     * @param height Alto del botón.
     */
    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUMEN_WIDTH, height);
        bounds.x -= VOLUMEN_WIDTH / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        minX = x + VOLUMEN_WIDTH / 2;
        maxX = x + width;
        loadImgs();
    }

    /**
     * Carga las imágenes del botón de volumen desde el archivo de sprites.
     */
    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * VOLUMEN_DEFAULT_WIDTH, 0, VOLUMEN_DEFAULT_WIDTH, VOLUMEN_DEFAULT_HEIGHT);
            slider = temp.getSubimage(3 * VOLUMEN_DEFAULT_WIDTH, 0, SLIDER_WIDTH_DEFAULT, VOLUMEN_DEFAULT_HEIGHT);
        }
    }

    /**
     * Actualiza el estado del botón de volumen.
     */
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    /**
     * Dibuja el botón de volumen en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el botón de volumen.
     */
    public void draw(Graphics g) {
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(imgs[index], buttonX - VOLUMEN_WIDTH / 2, y, VOLUMEN_WIDTH, height, null);
    }

    /**
     * Cambia la coordenada x del botón.
     * 
     * @param i La nueva coordenada x del botón.
     */
    public void changeX(int i) {
        if (i < minX)
            buttonX = minX;
        else if (i > maxX)
            buttonX = maxX;
        else
            buttonX = i;
        updateFloatValue();
        bounds.x = buttonX - VOLUMEN_WIDTH / 2;
    }

    /**
     * Actualiza el valor de punto flotante del botón de volumen.
     */
    private void updateFloatValue() {
        float range = maxX - minX;
        float value = buttonX - minX;
        floatValue = value / range;
    }

    /**
     * Reinicia los estados de mouseOver y mousePressed del botón de volumen.
     */
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Indica si el ratón está sobre el botón de volumen.
     * 
     * @return true si el ratón está sobre el botón de volumen, false de lo contrario.
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Establece si el ratón está sobre el botón de volumen.
     * 
     * @param mouseOver true si el ratón está sobre el botón de volumen, false de lo contrario.
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Indica si el botón de volumen está siendo presionado por el ratón.
     * 
     * @return true si el botón de volumen está siendo presionado, false de lo contrario.
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Establece si el botón de volumen está siendo presionado por el ratón.
     * 
     * @param mousePressed true si el botón de volumen está siendo presionado, false de lo contrario.
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * Obtiene el valor de punto flotante del botón de volumen.
     * 
     * @return El valor de punto flotante del botón de volumen.
     */
    public float getFloatValue() {
        return floatValue;
    }
}
