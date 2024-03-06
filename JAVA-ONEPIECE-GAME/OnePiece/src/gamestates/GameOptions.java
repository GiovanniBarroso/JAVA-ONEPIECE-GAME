package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.AudioOptions;
import ui.PauseButton;
import ui.UrmButton;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

/**
 * Representa el estado de las opciones del juego.
 */
public class GameOptions extends State implements Statemethods {

    private AudioOptions audioOptions;
    private BufferedImage background, optionsBackground;
    private int bgX, bgY, bgW, bgH;
    private UrmButton menuB;

    /**
     * Crea un nuevo estado de opciones del juego.
     * @param game El juego al que pertenece este estado.
     */
    public GameOptions(Game game) {
        super(game);
        loadImgs();
        loadButtons();
        audioOptions = game.getAudioOptions();
    }

    // Carga los botones de las opciones del juego.
    private void loadButtons() {
        int menuX = (int) (387 * Game.SCALE);
        int menuY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
    }

    // Carga las imágenes de fondo de las opciones del juego.
    private void loadImgs() {
        background = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
        optionsBackground = LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_MENU);

        bgW = (int) (optionsBackground.getWidth() * Game.SCALE);
        bgH = (int) (optionsBackground.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (33 * Game.SCALE);
    }

    @Override
    public void update() {
        menuB.update();
        audioOptions.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(optionsBackground, bgX, bgY, bgW, bgH, null);

        menuB.draw(g);
        audioOptions.draw(g);
    }

    // Maneja el arrastre del mouse en las opciones de audio.
    public void mouseDragged(MouseEvent e) {
        audioOptions.mouseDragged(e);
    }

    /**
     * Maneja el evento de presionar el mouse.
     * @param e El evento del mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else
            audioOptions.mousePressed(e);
    }

    /**
     * Maneja el evento de soltar el mouse.
     * @param e El evento del mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed())
                Gamestate.state = Gamestate.MENU;
        } else
            audioOptions.mouseReleased(e);
        menuB.resetBools();
    }

    /**
     * Maneja el movimiento del mouse.
     * @param e El evento del mouse.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        if (isIn(e, menuB))
            menuB.setMouseOver(true);
        else
            audioOptions.mouseMoved(e);
    }

    /**
     * Maneja el evento de presionar una tecla.
     * @param e El evento de teclado.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            Gamestate.state = Gamestate.MENU;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    // Verifica si el mouse está dentro del área de un botón de pausa.
    private boolean isIn(MouseEvent e, PauseButton b) {
        return (b.getBounds().contains(e.getX(), e.getY()));
    }
}
