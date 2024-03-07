package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestate;
import main.GamePanel;

/**
 * Clase que maneja la entrada del ratón.
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    /**
     * Constructor para inicializar los inputs del ratón.
     * 
     * @param gamePanel El panel principal del juego.
     */
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Maneja el evento de arrastre del ratón.
     * 
     * @param e El evento de arrastre del ratón.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        switch(Gamestate.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseDragged(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOptions().mouseDragged(e);
                break;
            default:
                break;
        }
    }

    /**
     * Maneja el evento de movimiento del ratón.
     * 
     * @param e El evento de movimiento del ratón.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOptions().mouseMoved(e);
                break;
            default:
                break;
        }
    }

    /**
     * Maneja el evento de clic del ratón.
     * 
     * @param e El evento de clic del ratón.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Gamestate.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    /**
     * Maneja el evento de presión del ratón.
     * 
     * @param e El evento de presión del ratón.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOptions().mousePressed(e);
                break;
            default:
                break;
        }
    }

    /**
     * Maneja el evento de liberación del ratón.
     * 
     * @param e El evento de liberación del ratón.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getGameOptions().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    /**
     * Maneja el evento de entrada del ratón.
     * 
     * @param e El evento de entrada del ratón.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // No se necesita implementar
    }

    /**
     * Maneja el evento de salida del ratón.
     * 
     * @param e El evento de salida del ratón.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // No se necesita implementar
    }
}
