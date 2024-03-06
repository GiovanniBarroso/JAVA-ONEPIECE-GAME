package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import utilz.Constants;

/**
 * Clase que representa un botón en el menú.
 */
public class MenuButton {

    private int xPos, yPos, rowIndex, index;
    private int xOffSetCenter = Constants.UI.Buttons.B_WIDHT / 2;
    private Gamestate state;
    private BufferedImage[] imagenesBotones;
    private boolean mouseOver = false, mousePressed = false;
    private Rectangle bounds;

    /**
     * Crea un nuevo botón de menú.
     * 
     * @param xPos     la posición X del botón
     * @param yPos     la posición Y del botón
     * @param rowIndex el índice de fila de la imagen del botón
     * @param state    el estado de juego asociado al botón
     */
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.rowIndex = rowIndex;
        this.state = state;
        loadimgs();
        initBounds();
    }

    private void initBounds() {
        // Cambiar ancho y alto de los botones
        int buttonWidth = Constants.UI.Buttons.B_WIDTH_DEFAULT * 2;
        int buttonHeight = Constants.UI.Buttons.B_HEIGHT_DEFAULT * 2;

        bounds = new Rectangle(xPos - xOffSetCenter, yPos, buttonWidth, buttonHeight);
    }

    private void loadimgs() {
        imagenesBotones = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imagenesBotones.length; i++) {
            imagenesBotones[i] = temp.getSubimage(i * Constants.UI.Buttons.B_WIDTH_DEFAULT,
                    rowIndex * Constants.UI.Buttons.B_HEIGHT_DEFAULT, Constants.UI.Buttons.B_WIDTH_DEFAULT,
                    Constants.UI.Buttons.B_HEIGHT_DEFAULT);
        }
    }

    /**
     * Dibuja el botón en el menú.
     * 
     * @param g el contexto gráfico
     */
    public void draw(Graphics g) {
        // Pintar los botones con las medidas nuevas
        g.drawImage(imagenesBotones[index], xPos - xOffSetCenter, yPos, bounds.width, bounds.height, null);
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    // Método por si queremos poner botones horizontales
    public void setXPos(int xPos) {
        this.xPos = xPos;
        bounds.x = xPos - xOffSetCenter;
    }

    public Gamestate getState() {
        return state;
    }

}
