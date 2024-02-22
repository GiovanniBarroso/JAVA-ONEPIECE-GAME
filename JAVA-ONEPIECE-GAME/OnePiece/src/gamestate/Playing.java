package gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods{
	private Player player;
	private LevelManager levelManager;
	public Playing(Game game) {
		super(game);
		initClasses();
	}


	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());

	}

	@Override
	public void update() {
		levelManager.update();
		player.update();

	}


	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);

	}


	@Override
	public void mousseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}



	}


	@Override
	public void moussePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}

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
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			Gamestate.state=Gamestate.MENU;
			player.resetDirBooleans();
			break;
		case KeyEvent.VK_C:
			player.setAttacking(true);
			break;
		}

	}


	@Override
	public void KeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;

		}

	}
	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

}
