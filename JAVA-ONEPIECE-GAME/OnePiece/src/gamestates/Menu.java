package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

/**
 * La clase `Menu` representa el estado del juego correspondiente al menú principal.
 * Extiende la clase `State` e implementa la interfaz `Statemethods`.
 * 
 * Las características principales de esta clase son:
 * - Carga los botones del menú y la imagen de fondo utilizando la clase `LoadSave`.
 * - Gestiona la interacción del usuario con los botones del menú.
 * - Cambia al estado correspondiente según la opción seleccionada por el usuario.
 * - Controla la reproducción de la música del nivel cuando se selecciona la opción de jugar.
 */
public class Menu extends State implements Statemethods {
    private MenuButton[] buttons = new MenuButton[4];
    private BufferedImage backgroundImg, backgroundImgPink;
    private int menuX, menuY, menuWidth, menuHeight;

    /**
     * Constructor de la clase `Menu`.
     * 
     * @param game El objeto `Game` al que pertenece este estado.
     */
    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
    }

    /**
     * Carga la imagen de fondo del menú y establece las dimensiones del menú.
     */
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (25 * Game.SCALE);
    }

    /**
     * Carga los botones del menú y establece sus posiciones y estados correspondientes.
     */
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (130 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (200 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (270 * Game.SCALE), 3, Gamestate.CREDITS);
        buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, (int) (340 * Game.SCALE), 2, Gamestate.QUIT);
    }

    /**
     * Actualiza el estado del menú.
     * Se encarga de actualizar el estado de los botones del menú.
     */
    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    /**
     * Dibuja el menú en la pantalla.
     * Se encarga de dibujar la imagen de fondo del menú y los botones del menú.
     * 
     * @param g El objeto `Graphics` utilizado para dibujar en la pantalla.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    /**
     * Maneja el evento de presionar el mouse.
     * Se encarga de activar el estado de "presionado" de un botón si el mouse está sobre él.
     * 
     * @param e El evento de mouse que contiene la información sobre la posición del mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    /**
     * Maneja el evento de soltar el mouse.
     * Se encarga de aplicar el estado de juego correspondiente si se ha soltado el mouse sobre un botón.
     * 
     * @param e El evento de mouse que contiene la información sobre la posición del mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGamestate();
                if (mb.getState() == Gamestate.PLAYING)
                    game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLlvlIndex());
                break;
            }
        }
        resetButtons();
    }

    /**
     * Restablece el estado de todos los botones del menú.
     */
    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();
    }

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton mb : buttons)
			mb.setMouseOver(false);

		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}