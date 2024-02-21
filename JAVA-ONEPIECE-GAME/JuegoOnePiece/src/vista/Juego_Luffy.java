package vista;

import java.awt.*;

import javax.swing.*;

import entidades.Player;
import levels.LevelManager;


public class Juego_Luffy extends JPanel implements Runnable {

	private final int FPS_SET=110;
	private final int UPDATE=200;
	private Player player;
	private LevelManager levelManager;
	
	public final static int TILES_DEFAULT_SIZE = 200;
	public final static float SCALE =1.0f;
	public final static int TILES_IN_WIDTH =26;
	public final static int TILES_IN_HEIGHT =26;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	
	public Juego_Luffy() {
		initClases();
		this.setFocusable(true);
		this.requestFocus();
		this.setLayout(null);
		
		
		Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(tamanioPantalla);
		setBackground(Color.green);
		
		empezarBucle();
	}

	private void initClases() {
		player=new Player(299,299);
		levelManager = new LevelManager(this);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		render(g);
	}

	public Player getPlayer() {
		return player;
	}

	public void WindowFocusLost() {
		player.resetDirBoolean();
	}

	private void update() {
		player.update();
		levelManager.update();

	}
	private void render(Graphics g) {
		player.render(g);
		levelManager.draw(g);

	}
	
	@Override
	public void run() {

		/*recogemos el tiempo en nanosegundos para ajustar
	        cada fotograma y crear el bucle del juego
	        de forma depurada*/
		double FPS = 1000000000.0 / FPS_SET;
		double UPS = 1000000000.0 / UPDATE; //Tiempo entre actualizaciones

		long antes = System.nanoTime();
		int frames = 0;
		int updates = 0;
		long Ultimacomprobacion = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while(true) {
			long currentTime = System.nanoTime();

			/*Con esto hacemos que no haya retardo en el bucle del juego.
			 * Si el tiempo de actualizacion tarda más de lo debido, el tiempo
			 * extra se almacena para hacer que la siguiente actualizacion
			 * se haga antes.*/
			deltaU += (currentTime - antes) / UPS;
			deltaF += (currentTime - antes) / FPS;
			antes = currentTime;

			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if(deltaF >= 1) {
				repaint();
				frames++;
				deltaF--;
			}


			if(System.currentTimeMillis() - Ultimacomprobacion >= 1000) {
				Ultimacomprobacion = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " -- UPS: " + updates);
				frames = 0;
				updates = 0;
			}

		}
	}


	private void empezarBucle() {
		Thread gameThread = new Thread(this);
		gameThread.start();
	}




}
