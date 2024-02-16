package vista;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import static utilz.Constantes.Direction.*;
import static utilz.Constantes.PlayerConstants.*;

public class Juego1 extends JPanel implements Runnable {
	public float posicionX = 100;
	public float posicionY = 800;
	private BufferedImage[][] animaciones;
	private BufferedImage imagen;
	private int frames = 0;
	private long lastCheck = 0;
	public int aniTick = 1, aniIndex = 6, aniSpeed = 15;
	public int player_action = ANDAR;
	public int playerDir = 1;
	public boolean moving = false;

	public Juego1() {
		this.setFocusable(true);
		this.requestFocus();
		this.setLayout(null);
		importImg();
		loadAnimations();
		Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(tamanioPantalla);
		setBackground(Color.orange);
		setMoving(moving);
		empezarBucle();
	}

	private void loadAnimations() {
		animaciones = new BufferedImage[11][11];
		for (int j = 0; j < animaciones.length; j++) {
			for (int i = 0; i < animaciones[j].length; i++) {
				animaciones[j][i] = imagen.getSubimage(i * 200, j * 200, 200, 200);
			}
		}
	}

	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/imagenes/sprite.png");
		try {
			imagen = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getSpriteAmount(player_action)) {
				aniIndex = 0;
			}
		}
	}

	private void setAnimation() {
		if (moving) {
			player_action = ANDAR;
		} else {
			player_action = QUIETO;
		}
	}

	private void updatePos() {
		if (moving) {
			switch (playerDir) {
			case LEFT:
				posicionX -= 5;
				break;
			case UP:
				posicionY -= 5;
				break;
			case RIGHT:
				posicionX += 5;
				break;
			case DOWN:
				posicionY += 5;
				break;
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateAnimationTick();
		setAnimation();
		updatePos();

		g.drawImage(animaciones[player_action][aniIndex], (int) posicionX, (int) posicionY, null);
	}

	@Override
	public void run() {
		double fps = 1000000000.0 / 110;
		long ultimoframe = 0;
		long ahora = System.nanoTime();
		while (true) {
			ahora = System.nanoTime();
			if (ahora - ultimoframe > fps) {
				repaint();
				ultimoframe = System.nanoTime();
				ultimoframe = ahora;
				frames++;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS :" + frames);
				frames = 0;
			}
		}
	}

	private void empezarBucle() {
		Thread gameThread = new Thread(this);
		gameThread.start();
	}




}
