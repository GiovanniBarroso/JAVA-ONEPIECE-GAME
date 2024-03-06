package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

/**
 * Clase que maneja la entrada del teclado.
 */
public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    /**
     * Constructor para inicializar los inputs del teclado.
     * 
     * @param gamePanel El panel principal del juego.
     */
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se necesita implementar
    }

    @Override
	public void keyReleased(KeyEvent e) {
		switch (Gamestate.state) {
		case MENU -> gamePanel.getGame().getMenu().keyReleased(e);
		case PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);
		case CREDITS -> gamePanel.getGame().getCredits().keyReleased(e);
		default -> throw new IllegalArgumentException("Unexpected value: " + Gamestate.state);
		}
	}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOptions().keyPressed(e);
                break;
            default:
                break;
        }
    }
}
