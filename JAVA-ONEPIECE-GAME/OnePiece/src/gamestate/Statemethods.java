package gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethods {

	public void update();
	public void draw(Graphics g);
	public void mousseClicked(MouseEvent e);
	public void moussePressed(MouseEvent e);
	public void mousseReleased(MouseEvent e);
	public void mousseMoved(MouseEvent e);
	public void KeyPressed(KeyEvent e);
	public void KeyReleased(KeyEvent e);
}

