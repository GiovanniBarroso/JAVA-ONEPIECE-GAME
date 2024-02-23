package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestate.Gamestate;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

	private GamePanel gamePanel;

	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		switch(Gamestate.state) {
	
		case PLAYING:
				
			
			gamePanel.getGame().getPlaying().mousseDragged(e);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mousseMoved(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mousseMoved(e);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		switch(Gamestate.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().moussePressed(e);
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().moussePressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().moussePressed(e);
			break;
		default:
			break;
		
		}
	}
		


	@Override
	public void mouseReleased(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mousseReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mousseReleased(e);
			break;
		default:
			break;
		
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
