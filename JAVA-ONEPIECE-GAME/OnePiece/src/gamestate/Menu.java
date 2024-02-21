package gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;

public class Menu extends State implements Statemethods{

	public Menu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("MENU", Game.GAME_WIDTH/2, 200);
		
	}

	@Override
	public void mousseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moussePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			Gamestate.state= Gamestate.PLAYING;
			
		}
		
	}

	@Override
	public void KeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
