package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import effects.Rain;
import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import objects.ObjectManager;
import ui.ControlesOverlay;
import ui.GameCompletedOverlay;
import ui.GameOverOverlay;
import ui.LevelCompletedOverlay;
import ui.PauseOverlay;
import utilz.LoadSave;
import static utilz.Constants.Environment.*;

/**
 * La clase `Playing` representa el estado del juego mientras se está jugando.
 * Extiende la clase `State` e implementa la interfaz `Statemethods`.
 */
public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private EnemyManager enemyManager;
	private ObjectManager objectManager;
	private PauseOverlay pauseOverlay;
	private ControlesOverlay controlesOverlay;
	private GameOverOverlay gameOverOverlay;
	private GameCompletedOverlay gameCompletedOverlay;
	private LevelCompletedOverlay levelCompletedOverlay;
	private boolean paused = false;
	private boolean controles = false;
	private Rain rain;

	private int xLvlOffset;
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
	private int maxLvlOffsetX;

	private BufferedImage backgroundImg, bigCloud, smallCloud, shipImgs[];
	private int[] smallCloudsPos;
	private Random rnd = new Random();

	private boolean gameOver;
	private boolean lvlCompleted;
	private boolean gameCompleted;
	private boolean drawRain;
	private boolean drawShip = true;

	private int shipAni, shipTick, shipDir = 1;
	private float shipHeightDelta, shipHeightChange = 0.05f * Game.SCALE;

	/**
	 * Constructor de la clase `Playing`.
	 * 
	 * @param game El objeto `Game` que controla el juego.
	 */
	public Playing(Game game) {
		super(game);
		initClasses();

		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
		bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
		smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
		smallCloudsPos = new int[8];
		for (int i = 0; i < smallCloudsPos.length; i++)
			smallCloudsPos[i] = (int) (90 * Game.SCALE) + rnd.nextInt((int) (100 * Game.SCALE));
		shipImgs = new BufferedImage[4];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SHIP);
		for (int i = 0; i < shipImgs.length; i++)
			shipImgs[i] = temp.getSubimage(i * 78, 0, 78, 72);

		calcLvlOffset();
		loadStartLevel();
		setDrawRainBoolean();
	}

	/**
	 * Carga el siguiente nivel del juego.
	 */
	public void loadNextLevel() {
		resetAll();
		levelManager.loadNextLevel();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
		drawShip = false;
	}

	/**
	 * Carga el inicio del nivel actual. Carga los enemigos y objetos del nivel, y
	 * posiciona al jugador en el punto de inicio del nivel.
	 */
	public void loadStartLevel() {
		enemyManager.loadEnemies(levelManager.getCurrentLevel()); // Carga los enemigos del nivel actual
		objectManager.loadObjects(levelManager.getCurrentLevel()); // Carga los objetos del nivel actual

		// Obtener las coordenadas del spawn del nivel actual
		Point spawnPoint = levelManager.getCurrentLevel().getPlayerSpawn();

		int spawnX = (int) spawnPoint.getX();
		int spawnY = (int) spawnPoint.getY();
		if (levelManager.getLlvlIndex() == 0) {
			spawnY = (int) spawnPoint.getY(); // Establece la posición Y del jugador al spawn del nivel actual
		}

		// Establecer la posición del jugador al spawn del nivel actual
		player.setX(spawnX);
		player.setY(spawnY);
	}

	/**
	 * Calcula el desplazamiento máximo del nivel en el eje X.
	 */
	private void calcLvlOffset() {
		maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
	}

	/**
	 * Inicializa las clases necesarias para el juego. Crea instancias de
	 * LevelManager, EnemyManager, ObjectManager y Player, y carga los datos del
	 * nivel actual.
	 */
	private void initClasses() {
		levelManager = new LevelManager(game); // Crea una instancia de LevelManager
		enemyManager = new EnemyManager(this); // Crea una instancia de EnemyManager
		objectManager = new ObjectManager(this); // Crea una instancia de ObjectManager

		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE), this); // Crea una instancia de
																								// Player
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData()); // Carga los datos del nivel actual en el
																			// jugador
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn()); // Establece el punto de spawn del jugador

		pauseOverlay = new PauseOverlay(this); // Crea una instancia de PauseOverlay
		controlesOverlay = new ControlesOverlay(this); // Crea una instancia de ControlesOverlay
		gameOverOverlay = new GameOverOverlay(this); // Crea una instancia de GameOverOverlay
		levelCompletedOverlay = new LevelCompletedOverlay(this); // Crea una instancia de LevelCompletedOverlay
		gameCompletedOverlay = new GameCompletedOverlay(this); // Crea una instancia de GameCompletedOverlay
		rain = new Rain(); // Crea una instancia de Rain
	}

	/**
	 * Actualiza el estado del juego.
	 */
	@Override
	public void update() {
		if (paused) {
			pauseOverlay.update();
			return;
		}
		if (controles) {
			controlesOverlay.update();
		} else if (gameCompleted)
			gameCompletedOverlay.update();
		else if (lvlCompleted) {
			levelCompletedOverlay.update();

		}

		else if (!gameOver) {
			levelManager.update();
			objectManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			player.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			checkCloseToBorder();
		}
		if (gameOver) {
			gameOverOverlay.update();
		}
		if (drawRain) {
			rain.update(xLvlOffset);
			levelManager.update();
		}
		checkCloseToBorder();
		if (drawShip)
			updateShipAni();
	}

	private void updateShipAni() {
		shipTick++;
		if (shipTick >= 35) {
			shipTick = 0;
			shipAni++;
			if (shipAni >= 4)
				shipAni = 0;
		}

		shipHeightDelta += shipHeightChange * shipDir;
		shipHeightDelta = Math.max(Math.min(10 * Game.SCALE, shipHeightDelta), 0);

		if (shipHeightDelta == 0)
			shipDir = 1;
		else if (shipHeightDelta == 10 * Game.SCALE)
			shipDir = -1;
	}

	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;

		if (diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if (xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;
	}

	/**
	 * Dibuja el estado del juego en pantalla.
	 * 
	 * @param g El objeto `Graphics` utilizado para dibujar.
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

		drawClouds(g);
		if (drawRain)
			rain.draw(g, xLvlOffset);

		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		enemyManager.draw(g, xLvlOffset);
		objectManager.draw(g, xLvlOffset);
		objectManager.drawBackgroundTrees(g, xLvlOffset);

		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}

		if (controles) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			controlesOverlay.draw(g);
		} else if (gameOver)
			gameOverOverlay.draw(g);
		else if (lvlCompleted)
			levelCompletedOverlay.draw(g);
		else if (gameCompleted)
			gameCompletedOverlay.draw(g);
		if (drawShip)
			g.drawImage(shipImgs[shipAni], (int) (100 * Game.SCALE) - xLvlOffset,
					(int) ((288 * Game.SCALE) + shipHeightDelta), (int) (78 * Game.SCALE), (int) (72 * Game.SCALE),
					null);
	}

	private void drawClouds(Graphics g) {
		for (int i = 0; i < 3; i++)
			g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int) (xLvlOffset * 0.3), (int) (204 * Game.SCALE),
					BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);

		for (int i = 0; i < smallCloudsPos.length; i++)
			g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - (int) (xLvlOffset * 0.7), smallCloudsPos[i],
					SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
	}

	/**
	 * Reinicia todos los valores para comenzar un nuevo nivel.
	 */

	public void resetAll() {
		gameOver = false;
		paused = false;
		controles = false;
		lvlCompleted = false;
		player.resetAll();
		enemyManager.resetAllEnemies();
		objectManager.resetAllObjects();
		drawRain = false;
		if (getLevelManager().getLlvlIndex() == 0) {
			drawShip = true;
		}

		setDrawRainBoolean();
	}

	private void setDrawRainBoolean() {
		// Este método hace que llueva el 20% del tiempo cuando se carga un nivel.
		if (rnd.nextFloat() >= 0.8f)
			drawRain = true;
	}

	/**
	 * Establece el estado de juego como "game over".
	 * 
	 * @param gameOver El estado de juego "game over".
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Verifica si se ha golpeado un objeto.
	 * 
	 * @param attackBox La caja de colisión del ataque.
	 */
	public void checkObjectHit(Rectangle2D.Float attackBox) {
		objectManager.checkObjectHit(attackBox);
	}

	/**
	 * Verifica si se ha golpeado a un enemigo.
	 * 
	 * @param attackBox La caja de colisión del ataque.
	 */
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);
	}

	/**
	 * Verifica si se ha tocado una poción.
	 * 
	 * @param hitbox La caja de colisión del jugador.
	 */
	public void checkPotionTouched(Rectangle2D.Float hitbox) {
		objectManager.checkObjectTouched(hitbox);
	}

	/**
	 * Verifica si se ha tocado una trampa.
	 * 
	 * @param p El jugador.
	 */
	public void checkSpikesTouched(Player p) {
		objectManager.checkSpikesTouched(p);
	}

	/**
	 * Maneja el evento de clic del mouse.
	 * 
	 * @param e El evento de clic del mouse.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!gameOver) {
			if (e.getButton() == MouseEvent.BUTTON1)
				player.setAttacking(true);

		}
	}

	/**
	 * Maneja el evento de tecla presionada.
	 * 
	 * @param e El evento de tecla presionada.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (gameOver)
			gameOverOverlay.keyPressed(e);
		else
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(true);
				break;
			case KeyEvent.VK_ESCAPE:
				paused = true;
				break;
			case KeyEvent.VK_H:
				controles = true;
				break;
			case KeyEvent.VK_K:
				getEnemyManager().muerteGlobal();
				break;
			case KeyEvent.VK_X:
				player.powerAttack();
				break;
			}
	}

	/**
	 * Maneja el evento de tecla liberada.
	 * 
	 * @param e El evento de tecla liberada.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (!gameOver)
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(false);
				break;
			}
	}

	/**
	 * Maneja el evento de arrastre del mouse.
	 * 
	 * @param e El evento de arrastre del mouse.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseOverlay.mouseDragged(e);
			if (controles)
				controlesOverlay.mouseDragged(e);
		}

	}

	/**
	 * Maneja el evento de presión del mouse.
	 * 
	 * @param e El evento de presión del mouse.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseOverlay.mousePressed(e);
			if (controles)
				controlesOverlay.mousePressed(e);
			else if (lvlCompleted)
				levelCompletedOverlay.mousePressed(e);
			else if (gameCompleted)
				gameCompletedOverlay.mousePressed(e);
		} else {
			gameOverOverlay.mousePressed(e);
		}

		// ATACAR
		if (e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			player.setAttacking2(true);
		}

	}

	/**
	 * Maneja el evento de liberación del mouse.
	 * 
	 * @param e El evento de liberación del mouse.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseOverlay.mouseReleased(e);
			if (controles)
				controlesOverlay.mouseReleased(e);
			else if (lvlCompleted)
				levelCompletedOverlay.mouseReleased(e);
			else if (gameCompleted)
				gameCompletedOverlay.mouseReleased(e);
		} else {
			gameOverOverlay.mouseReleased(e);
		}
	}

	/**
	 * Maneja el evento de movimiento del mouse.
	 * 
	 * @param e El evento de movimiento del mouse.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseOverlay.mouseMoved(e);
			if (controles)
				controlesOverlay.mouseMoved(e);
			else if (lvlCompleted)
				levelCompletedOverlay.mouseMoved(e);
			else if (gameCompleted)
				gameCompletedOverlay.mouseMoved(e);
		} else {
			gameOverOverlay.mouseMoved(e);
		}
	}

	/**
	 * Establece el estado de nivel completado.
	 * 
	 * @param levelCompleted El estado de nivel completado.
	 */
	public void setLevelCompleted(boolean levelCompleted) {
		game.getAudioPlayer().lvlCompleted();
		if (levelManager.getLlvlIndex() + 1 >= levelManager.getAmountOfLevels()) {
			// No more levels
			gameCompleted = true;
			levelManager.setLevelIndex(0);
			levelManager.loadNextLevel();
			resetAll();
			return;
		}
		this.lvlCompleted = levelCompleted;
	}

	/**
	 * Establece el máximo desplazamiento del nivel.
	 * 
	 * @param lvlOffset El desplazamiento máximo del nivel.
	 */
	public void setMaxLvlOffset(int lvlOffset) {
		this.maxLvlOffsetX = lvlOffset;
	}

	/**
	 * Reanuda el juego.
	 */
	public void unpauseGame() {
		paused = false;
		controles = false;
		if (getLevelManager().getLlvlIndex() == 0) {
			isDrawShip(true);
		}

	}

	/**
	 * Maneja la pérdida de enfoque de la ventana del juego.
	 */
	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public ObjectManager getObjectManager() {
		return objectManager;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public boolean isDrawShip(boolean b) {
		return drawShip = b;
	}

	public void setDrawShip(boolean drawShip) {
		this.drawShip = drawShip;
	}

	public boolean isGameCompleted() {
		return gameCompleted;
	}

	public void setGameCompleted(boolean gameCompleted) {
		this.gameCompleted = gameCompleted;
	}
}
