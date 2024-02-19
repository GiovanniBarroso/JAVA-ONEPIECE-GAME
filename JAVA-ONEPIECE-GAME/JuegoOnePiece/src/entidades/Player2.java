package entidades;

import static utilz.Constantes2.PlayerConstants.*;
import static utilz.Constantes2.PlayerConstants.getSpriteAmount;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player2 extends Entity{

	public int aniTick = 1, aniIndex = 6, aniSpeed = 15	;
	public int player_action = CAMINAR;
	public boolean moving = false,attacking=false;
	private BufferedImage[][] animaciones;
	private  boolean left,up,right,down;
	private float velocidad=3f;
	
	
	public Player2(float x, float y) {
		super(x, y);
		loadAnimations();
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getSpriteAmount(player_action)) {
				aniIndex = 0;
				attacking=false;
			}
		}
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
		
	}

	public void render(Graphics g) {
		g.drawImage(animaciones[player_action][aniIndex], (int) x, (int) y, null);
	}


	private void setAnimation() {
		if (moving) {
			player_action = CAMINAR;
		} else {
			player_action = ESPECIAL;
		}
		if(attacking) {
			player_action=ATAQUE;
		}
	}

	public void setaAttackimg(boolean attacking) {
		this.attacking=attacking;
	}
	private void updatePos() {
	
		moving=false;
		if(left&&!right) {
			x-=velocidad;
			moving=true;
		}else if(right&&!left) {
			x+=velocidad;
			moving=true;
		}
		
		if(up&&!down) {
			y-=velocidad;
			moving=true;
		}else if(down&&!up) {
			y+=velocidad;
			moving=true;
		}
		}

	private void loadAnimations() {
		InputStream is = getClass().getResourceAsStream("/imagenes/SPRITE ZORO DEF.png");
		try {
			BufferedImage imagen = ImageIO.read(is);
			animaciones = new BufferedImage[11][11];
			for (int j = 0; j < animaciones.length; j++) {
				for (int i = 0; i < animaciones[j].length; i++) {
					animaciones[j][i] = imagen.getSubimage(i * 200, j * 200, 200, 200);
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void resetDirBoolean() {
		left=false;
		right=false;
		up=false;
		down=false;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	

}
