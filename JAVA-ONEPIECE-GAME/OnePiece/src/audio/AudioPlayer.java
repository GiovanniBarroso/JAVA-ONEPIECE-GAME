package audio;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Esta clase representa un reproductor de audio que gestiona la reproducción de música de fondo y efectos de sonido en un juego.
 * Permite cargar archivos de audio, ajustar el volumen, reproducir canciones y efectos, así como controlar el estado de reproducción.
 * @author Giovanni/Cristian
 * @version 1.0
 */

public class AudioPlayer {

	public static int MENU_1 = 0;
	public static int LEVEL_1 = 1;
	public static int LEVEL_2 = 2;

	public static int DIE = 0;
	public static int JUMP = 1;
	public static int GAMEOVER = 2;
	public static int LVL_COMPLETED = 3;
	public static int ATTACK_ONE = 4;
	public static int ATTACK_TWO = 5;
	public static int ATTACK_THREE = 6;

	private Clip[] songs, effects;
	private int currentSongId;
	private float volume = 1f;
	private boolean songMute, effectMute;
	private Random rand = new Random();

	public AudioPlayer() {
		loadSongs();
		loadEffects();
		playSong(MENU_1);
	}

	/**
	 * Carga las canciones de fondo del juego.
	 */
	private void loadSongs() {
		String[] names = { "menu", "level1", "level2" };
		songs = new Clip[names.length];
		for (int i = 0; i < songs.length; i++)
			songs[i] = getClip(names[i]);
	}
	/**
	 * Carga los efectos de sonido del juego.
	 */
	private void loadEffects() {
		String[] effectNames = { "die", "jump", "gameover", "lvlcompleted", "attack1", "attack2", "attack3" };
		effects = new Clip[effectNames.length];
		for (int i = 0; i < effects.length; i++)
			effects[i] = getClip(effectNames[i]);

		updateEffectsVolume();

	}

	/**
	 * Obtiene un Clip de audio a partir del nombre del archivo.
	 *
	 * @param name El nombre del archivo de audio.
	 * @return Un objeto Clip que representa el archivo de audio.
	 */
	private Clip getClip(String name) {
		URL url = getClass().getResource("/cargarAudio/" + name + ".wav");
		AudioInputStream audio;

		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			return c;
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

			e.printStackTrace();
		}
		return null;

	}
	/**
	 * Establece el volumen general del reproductor de audio.
	 *
	 * @param volume El nivel de volumen deseado (0 a 1).
	 */
	public void setVolume(float volume) {
		this.volume = volume;
		updateSongVolume();
		updateEffectsVolume();
	}
	/**
	 * Detiene la reproducción de la canción actual.
	 */
	public void stopSong() {
		if (songs[currentSongId].isActive())
			songs[currentSongId].stop();
	}
	/**
	 * Selecciona la canción de fondo según el índice de nivel proporcionado.
	 *
	 * @param lvlIndex El índice del nivel actual.
	 */
	public void setLevelSong(int lvlIndex) {
		if (lvlIndex % 2 == 0)
			playSong(LEVEL_1);
		else
			playSong(LEVEL_2);
	}
	/**
	 * Método llamado cuando se completa un nivel.
	 * Detiene la reproducción de la canción actual y reproduce el efecto de nivel completado.
	 */
	public void lvlCompleted() {
		stopSong();
		playEffect(LVL_COMPLETED);
	}
	/**
	 * Reproduce un sonido de ataque aleatorio.
	 */
	public void playAttackSound() {
		int start = 4;
		start += rand.nextInt(3);
		playEffect(start);
	}
	/**
	 * Reproduce un efecto de sonido específico.
	 *
	 * @param effect El índice del efecto de sonido a reproducir.
	 */
	public void playEffect(int effect) {
		effects[effect].setMicrosecondPosition(0);
		effects[effect].start();
	}
	/**
	 * Reproduce una canción de fondo específica.
	 *
	 * @param song El índice de la canción de fondo a reproducir.
	 */
	public void playSong(int song) {
		stopSong();

		currentSongId = song;
		updateSongVolume();
		songs[currentSongId].setMicrosecondPosition(0);
		songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
	}
	/**
	 * Alterna el estado de silencio de las canciones de fondo.
	 */
	public void toggleSongMute() {
		this.songMute = !songMute;
		for (Clip c : songs) {
			BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
			booleanControl.setValue(songMute);
		}
	}
	/**
	 * Alterna el estado de silencio de los efectos de sonido y reproduce un efecto de salto si el silencio se desactiva.
	 */
	public void toggleEffectMute() {
		this.effectMute = !effectMute;
		for (Clip c : effects) {
			BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
			booleanControl.setValue(effectMute);
		}
		if (!effectMute)
			playEffect(JUMP);
	}
	/**
	 * Actualiza el volumen de la canción actual según el volumen general.
	 */
	private void updateSongVolume() {

		FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * volume) + gainControl.getMinimum();
		gainControl.setValue(gain);

	}
	/**
	 * Actualiza el volumen de todos los efectos de sonido según el volumen general.
	 */
	private void updateEffectsVolume() {
		for (Clip c : effects) {
			FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * volume) + gainControl.getMinimum();
			gainControl.setValue(gain);
		}
	}

}
