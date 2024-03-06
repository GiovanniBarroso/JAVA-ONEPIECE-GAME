package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.PauseButtons.*;

import utilz.LoadSave;

/**
 * Clase que representa un botón de sonido en la interfaz de usuario.
 */
public class SoundButton extends PauseButton {

    private BufferedImage[][] soundImgs; // Matriz de imágenes para el botón de sonido
    private boolean mouseOver, mousePressed; // Estado del ratón sobre el botón
    private boolean muted; // Estado de silencio del sonido
    private int rowIndex, colIndex; // Índices para la matriz de imágenes

    /**
     * Constructor de la clase SoundButton.
     * 
     * @param x      Coordenada x del botón en la ventana.
     * @param y      Coordenada y del botón en la ventana.
     * @param width  Ancho del botón.
     * @param height Alto del botón.
     */
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height); // Llama al constructor de la clase base (PauseButton)
        
        loadSoundImgs(); // Carga las imágenes del botón de sonido
    }

    private void loadSoundImgs() {
        // Carga las imágenes del botón de sonido desde el archivo de sprites
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3]; // Crea la matriz de imágenes
        for (int j = 0; j < soundImgs.length; j++)
            for (int i = 0; i < soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
    }

    /**
     * Actualiza el estado del botón de sonido.
     */
    public void update() {
        // Determina qué imagen debe mostrarse según el estado del botón y del sonido
        if (muted)
            rowIndex = 1;
        else
            rowIndex = 0;

        if (mouseOver)
            colIndex = 1;
        if (mousePressed)
            colIndex = 2;
    }

    /**
     * Reinicia los estados de mouseOver y mousePressed del botón de sonido.
     */
    public void resetBools() {
        // Reinicia los estados de mouseOver y mousePressed
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Dibuja el botón de sonido en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico en el que se dibuja el botón de sonido.
     */
    public void draw(Graphics g) {
        // Dibuja la imagen correspondiente según el estado del botón y del sonido
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
    }

    // Getters y setters para los estados de mouseOver, mousePressed y muted
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

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
