package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

/**
 * Clase que representa la superposición de pantalla mostrada cuando se completa el juego.
 */
public class GameCompletedOverlay {
    private Playing playing;
    private BufferedImage img;
    private MenuButton quit, credit;
    private int imgX, imgY, imgW, imgH;

    /**
     * Constructor de la clase GameCompletedOverlay.
     *
     * @param playing La instancia de Playing asociada.
     */
    public GameCompletedOverlay(Playing playing) {
        this.playing = playing;
        createImg();
        createButtons();
    }

    // Crea los botones de la superposición
    private void createButtons() {
        quit = new MenuButton(Game.GAME_WIDTH / 2, (int) (270 * Game.SCALE), 2, Gamestate.MENU);
        credit = new MenuButton(Game.GAME_WIDTH / 2, (int) (200 * Game.SCALE), 3, Gamestate.CREDITS);
    }

    // Carga la imagen para mostrar en la superposición
    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.GAME_COMPLETED);
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH / 2 - imgW / 2;
        imgY = (int) (100 * Game.SCALE);
    }

    // Dibuja la superposición en pantalla
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        credit.draw(g);
        quit.draw(g);
    }

    // Actualiza los botones
    public void update() {
        credit.update();
        quit.update();
    }

    // Verifica si el mouse está sobre un botón específico
    private boolean isIn(MenuButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Maneja el evento de mover el mouse
    public void mouseMoved(MouseEvent e) {
        credit.setMouseOver(false);
        quit.setMouseOver(false);

        if (isIn(quit, e))
            quit.setMouseOver(true);
        else if (isIn(credit, e))
            credit.setMouseOver(true);
    }

    // Maneja el evento de liberar el botón del mouse
    public void mouseReleased(MouseEvent e) {
        if (isIn(quit, e)) {
            if (quit.isMousePressed()) {
                System.exit(0);
            }
        } else if (isIn(credit, e)) {
            if (credit.isMousePressed()) {
                playing.setGameState(Gamestate.CREDITS);
                playing.getLevelManager().setLevelIndex(0);

                playing.loadStartLevel();
                playing.resetAll();
                playing.getPlayer().resetAll();
                playing.setGameCompleted(false);
            }
        }

        quit.resetBools();
        credit.resetBools();
    }

    // Maneja el evento de presionar el botón del mouse
    public void mousePressed(MouseEvent e) {
        if (isIn(quit, e))
            quit.setMousePressed(true);
        else if (isIn(credit, e))
            credit.setMousePressed(true);
    }
}
