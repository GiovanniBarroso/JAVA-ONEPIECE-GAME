package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

/**
 * Clase que representa la pantalla de pausa en el juego.
 */
public class ControlesOverlay {

    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private AudioOptions audioOptions;
    private UrmButton  unpauseB;

    /**
     * Crea una nueva pantalla de pausa.
     * 
     * @param playing la instancia de Playing (estado de juego) asociada
     */
    public ControlesOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        audioOptions = playing.getGame().getAudioOptions();
        createUrmButtons();
    }

    private void createUrmButtons() {
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    public void update() {
        unpauseB.update();
    }

    public void draw(Graphics g) {
        // Fondo
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        // Botones URM
 
        unpauseB.draw(g);
       
        // Oculta la nave mientras está pausada
        playing.isDrawShip(false);
    }

    public void mouseDragged(MouseEvent e) {
        audioOptions.mouseDragged(e);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, unpauseB)) {
            unpauseB.setMousePressed(true);
        } 
    }

    public void mouseReleased(MouseEvent e) {
       if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed()) {
            	playing.getPlayer().resetDirBooleans();;
                playing.unpauseGame();
               
               
            }
        } 
        unpauseB.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        unpauseB.setMouseOver(false);
        if (isIn(e, unpauseB)) {
            unpauseB.setMouseOver(true);
        } 
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
