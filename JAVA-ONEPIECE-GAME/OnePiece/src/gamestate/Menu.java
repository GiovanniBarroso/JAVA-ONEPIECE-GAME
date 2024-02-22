	package gamestate;
	
	import java.awt.Color;
import ui.MenuButton.*;
	import java.awt.Graphics;
	import java.awt.event.KeyEvent;
	import java.awt.event.MouseEvent;
	
	import main.Game;
	import ui.MenuButton;
import utilz.Constants;
	
	public class Menu extends State implements Statemethods{
		private static final int BUTTON_MARGIN = 20; 
		private MenuButton[] buttons = new MenuButton[3];
		public Menu(Game game) {
			super(game);
			
			loadButtons();
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
			 g.setColor(Color.BLUE);
		        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
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
						mb.AplicarCambios();
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
