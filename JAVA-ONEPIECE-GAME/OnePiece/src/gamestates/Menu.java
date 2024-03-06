package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

/**
 * Clase que representa el estado del menú del juego.
 */
public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg, backgroundImgPink;
    private int menuX, menuY, menuWidth, menuHeight;

    /**
     * Constructor de la clase Menu.
     * @param game Instancia del juego.
     */
    public Menu(Game game) {
        super(game);

        loadButtons();
        loadBackground();
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
    }

    /**
     * Carga la imagen de fondo del menú.
     */
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);
    }

    /**
     * Crea y carga los botones del menú.
     */
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }

    /**
     * Actualiza el estado del menú.
     */
    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    /**
     * Dibuja el estado del menú en pantalla.
     * @param g Objeto Graphics para dibujar.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    /**
     * Método llamado cuando se hace clic con el mouse.
     * @param e Evento de mouse.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // No implementado
    }

    /**
     * Método llamado cuando se presiona el mouse.
     * @param e Evento de mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    /**
     * Método llamado cuando se libera el mouse.
     * @param e Evento de mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) { 
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();                    
                }
                if(mb.getState()==Gamestate.PLAYING)
                    game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLlvlIndex());
                break;
            }
        }
        resetButtons();
    }

    /**
     * Reinicia las variables booleanas de los botones.
     */
    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();
    }

    /**
     * Método llamado cuando se mueve el mouse.
     * @param e Evento de mouse.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    /**
     * Método llamado cuando se presiona una tecla.
     * @param e Evento de teclado.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestate.state = Gamestate.PLAYING;
    }

    /**
     * Método llamado cuando se libera una tecla.
     * @param e Evento de teclado.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // No implementado
    }
}
