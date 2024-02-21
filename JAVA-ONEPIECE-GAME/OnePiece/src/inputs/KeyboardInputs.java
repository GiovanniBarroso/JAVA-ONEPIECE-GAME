package inputs;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestate.Gamestate;
import main.GamePanel;
import static utilz.Constants.Direction.*;

public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().KeyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().KeyReleased(e);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().KeyPressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().KeyPressed(e);
			break;
		default:
			break;
		
		}
	}
}
