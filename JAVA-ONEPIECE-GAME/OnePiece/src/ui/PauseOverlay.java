package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestate.Gamestate;
import gamestate.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;
public class PauseOverlay {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX,bgY,bgW,bgH;
	private SoundButton musicButton, sfxButton;
	private UrmButtons menuB,replayB,unpauseB;
	private VolumeButtons volumButton;

	public PauseOverlay(Playing playing) {
		this.playing=playing;
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButton();
	}

	private void createVolumeButton() {
		int vx= (int)(309*Game.SCALE);
		int vY=(int)(278*Game.SCALE);
		volumButton= new VolumeButtons(vx, vY, SLIDER_WIDHT, VOLUMEN_HEIGHT);
		
	}

	private void createUrmButtons() {
		int menuX = (int) (313 * Game.SCALE);
		int replayX = (int) (387 * Game.SCALE);
		int unpauseX = (int) (462 * Game.SCALE);
		int bY = (int) (325 * Game.SCALE);

		menuB = new UrmButtons(menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new UrmButtons(replayX, bY, URM_SIZE, URM_SIZE, 1);
		unpauseB = new UrmButtons(unpauseX, bY, URM_SIZE, URM_SIZE, 0);

	}

	private void createSoundButtons() {
		int soundX = (int) (450 * Game.SCALE);
		int musicY = (int) (140 * Game.SCALE);
		int sfxY = (int) (186 * Game.SCALE);

		musicButton = new SoundButton (soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton (soundX, sfxY, SOUND_SIZE, SOUND_SIZE);

	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int)(backgroundImg.getWidth() * Game.SCALE);
		bgH = (int)(backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (25 * Game.SCALE);
	}

	public void update () {
		musicButton.update();
		sfxButton.update();

		menuB.update();
		replayB.update();
		unpauseB.update();
		volumButton.update();

	}

	public void draw (Graphics g) {
		//Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

		//Sound Buttons
		musicButton.draw(g);
		sfxButton.draw(g);

		//URMBUTTONS
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		//VOLUME BUTTON
		volumButton.draw(g);

	}

	public void mouseDragged (MouseEvent e) {

		if(volumButton.isMousePressed()) {
			volumButton.changeX(e.getX());
		}
	}


	public void mousePressed(MouseEvent e) {
		if(isIn(e,musicButton))
			musicButton.setMousePressed(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
		else if(isIn(e, menuB)) {
			menuB.setMousePressed(true);;
		}
		else if(isIn(e, replayB)) {
			replayB.setMousePressed(true);;
		}
		else if(isIn(e, unpauseB)) {
			unpauseB.setMousePressed(true);;
		}
		else if(isIn(e, volumButton)) {
			volumButton.setMousePressed(true);;
		}
	}


	public void mouseReleased(MouseEvent e) {
		if(isIn(e, musicButton)) {
			if (musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted());

		} else if (isIn(e,sfxButton)) {
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());;

		}
		else if (isIn(e,menuB)) {
			if (menuB.isMousePressed())
				Gamestate.state=Gamestate.MENU;
				playing.unpauseGame();
		}
		else if (isIn(e,replayB)) {
			if (replayB.isMousePressed())
				System.out.println("replay lvl!");		
		}
		else if (isIn(e,unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		}
		
		musicButton.resetBools();
		sfxButton.resetBools();
		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();
		volumButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		volumButton.setMouseOver(false);
		if(isIn(e, musicButton)) 
			musicButton.setMouseOver(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMouseOver(true);

		else if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if (isIn(e, volumButton))
			volumButton.setMouseOver(true);
	}

	

	private boolean isIn(MouseEvent e, PauseButton b) {
		return (b.getBounds().contains(e.getX(), e.getY()));

	}

}