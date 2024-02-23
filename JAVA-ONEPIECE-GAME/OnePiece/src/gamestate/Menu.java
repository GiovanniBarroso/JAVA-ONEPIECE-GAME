package gamestate;

import java.awt.Color;
import ui.MenuButton.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.Constants;
import utilz.LoadSave;

public class Menu extends State implements Statemethods{

	private static final int BUTTON_MARGIN = 20; 
	private MenuButton[] buttons = new MenuButton[3];
	private BufferedImage backgroundImg;
	private int menuX,menuY,menuWidth,menuHeight;

	public Menu(Game game) {
		super(game);

		loadButtons();
		loadBackground();
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int)(backgroundImg.getWidth()* Game.SCALE);
		menuHeight = (int)(backgroundImg.getHeight()* Game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int)(45 * Game.SCALE);

	}

	//BOTONES VERTICALES
	private void loadButtons() {
		buttons[0]= new MenuButton(Game.GAME_WIDTH/2,(int)(150*Game.SCALE), 0, Gamestate.PLAYING);
		buttons[1]= new MenuButton(Game.GAME_WIDTH/2,(int)(220*Game.SCALE), 1, Gamestate.OPTIONS);
		buttons[2]= new MenuButton(Game.GAME_WIDTH/2,(int)(290*Game.SCALE), 2, Gamestate.QUIT);

	}

	//BOTONES HORIZONTALES
	//		private void loadButtons() {
	//		    // Coordenada X del primer botón
	//		    int buttonXPos = Game.GAME_WIDTH / 2 - ((Constants.UI.Buttons.B_WIDTH_DEFAULT + BUTTON_MARGIN) * 2) / 2;
	//		    buttons[0] = new MenuButton(buttonXPos, (int)(150*Game.SCALE), 0, Gamestate.PLAYING);
	//		    buttons[1] = new MenuButton(buttonXPos + Constants.UI.Buttons.B_WIDTH_DEFAULT + BUTTON_MARGIN, (int)(220*Game.SCALE), 1, Gamestate.OPTIONS);
	//		    buttons[2] = new MenuButton(buttonXPos + 2 * (Constants.UI.Buttons.B_WIDTH_DEFAULT + BUTTON_MARGIN), (int)(290*Game.SCALE), 2, Gamestate.QUIT);
	//		}


	@Override
	public void update() {
		for(MenuButton mb : buttons) {
			mb.update();
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
		for(MenuButton mb : buttons) {
			mb.draw(g);
		}
	}

	@Override
	public void mousseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moussePressed(MouseEvent e) {
		for(MenuButton mb : buttons) {
			if(IsIn(e, mb)) {
				mb.setMousePressed(true);
				break;
			}
		}

	}

	@Override
	public void mousseReleased(MouseEvent e) {
		for(MenuButton mb : buttons) {
			if(IsIn(e, mb)) {
				if(mb.isMousePressed()) {
					mb.applyGamestate();
				}
				break;

			}
		}
		resetButtons();
	}

	private void resetButtons() {
		for(MenuButton mb : buttons) {
			mb.resetBools();
		}

	}

	@Override
	public void mousseMoved(MouseEvent e) {
		for(MenuButton mb : buttons) {
			mb.setMouseOver(false);
		}
		for(MenuButton mb : buttons) {
			if(IsIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}
		}
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ESCAPE) {
			Gamestate.state= Gamestate.PLAYING;

		}

	}

	@Override
	public void KeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}



}
