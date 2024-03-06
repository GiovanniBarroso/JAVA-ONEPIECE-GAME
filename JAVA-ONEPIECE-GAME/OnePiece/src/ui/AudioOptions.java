package ui;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilz.Constants.UI.VolumeButtons.SLIDER_WIDHT;
import static utilz.Constants.UI.VolumeButtons.VOLUMEN_HEIGHT;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;

/**
 * Clase que gestiona las opciones de audio en la interfaz de usuario.
 */
public class AudioOptions {
    private VolumeButton volumeButton;
    private SoundButton musicButton, sfxButton;
    private Game game;

    /**
     * Crea un nuevo objeto de opciones de audio.
     * 
     * @param game la instancia del juego
     */
    public AudioOptions(Game game) {
        this.game = game;
        createSoundButtons();
        createVolumeButton();
    }

    private void createVolumeButton() {
        int vx = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vx, vY, SLIDER_WIDHT, VOLUMEN_HEIGHT);
    }

    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);

        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    /**
     * Actualiza las opciones de audio.
     */
    public void update() {
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
    }

    /**
     * Dibuja las opciones de audio.
     * 
     * @param g el contexto gráfico
     */
    public void draw(Graphics g) {
        // Botones de sonido
        musicButton.draw(g);
        sfxButton.draw(g);
        // Botón de volumen
        volumeButton.draw(g);
    }

    /**
     * Maneja el evento de arrastre del mouse.
     * 
     * @param e el evento de arrastre del mouse
     */
    public void mouseDragged(MouseEvent e) {
        float valueBefore = 0, valueAfter;
        if (volumeButton.isMousePressed()) {
            valueBefore = volumeButton.getFloatValue();
            volumeButton.changeX(e.getX());
            valueAfter = volumeButton.getFloatValue();
            if (valueBefore != valueAfter) {
                game.getAudioPlayer().setVolume(valueAfter);
            }
        }
    }

    /**
     * Maneja el evento de presionar el mouse.
     * 
     * @param e el evento de presionar el mouse
     */
    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (isIn(e, volumeButton)) {
            volumeButton.setMousePressed(true);
        }
    }

    /**
     * Maneja el evento de soltar el mouse.
     * 
     * @param e el evento de soltar el mouse
     */
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
                game.getAudioPlayer().toggleSongMute();
            }
        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
            game.getAudioPlayer().toggleEffectMute();
        }
        musicButton.resetBools();
        sfxButton.resetBools();
        volumeButton.resetBools();
    }

    /**
     * Maneja el evento de mover el mouse.
     * 
     * @param e el evento de mover el mouse
     */
    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);
        if (isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMouseOver(true);
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return (b.getBounds().contains(e.getX(), e.getY()));
    }
}
