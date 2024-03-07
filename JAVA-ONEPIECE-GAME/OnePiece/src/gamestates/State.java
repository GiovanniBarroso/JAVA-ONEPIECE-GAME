package gamestates;

import java.awt.event.MouseEvent;

import audio.AudioPlayer;
import main.Game;
import ui.MenuButton;

/**
 * Clase base para los diferentes estados del juego.
 */
public class State {

    protected Game game;

    /**
     * Constructor de la clase State.
     * 
     * @param game La instancia del juego.
     */
    public State(Game game) {
        this.game = game;
    }
    
    /**
     * Verifica si el ratón está dentro del área de un botón del menú.
     * 
     * @param e  El evento del mouse.
     * @param mb El botón del menú.
     * @return   true si el ratón está dentro del área del botón, false de lo contrario.
     */
    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Obtiene la instancia del juego.
     * 
     * @return La instancia del juego.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Establece el estado del juego.
     * 
     * @param state El estado del juego.
     * @throws IllegalArgumentException Si el valor de estado es inesperado.
     */
    public void setGameState(Gamestate state) {
        switch(state) {
            case MENU,CREDITS:
                game.getAudioPlayer().playSong(AudioPlayer.MENU_1);
                break;
            case PLAYING:
                game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLlvlIndex());
                break;
            default:
                throw new IllegalArgumentException("Valor inesperado: " + state);
        }
        Gamestate.state = state;
    }
}
