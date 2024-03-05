package objects;

import main.Game;
import static utilz.HelpMethods.CanCannonSeePlayer;
public class Cannon extends GameObject {

	private int tileY;
	private int shootTimer = 0; // Temporizador de disparo
	private int shootDelay = 150; // Retraso entre disparos (en frames)
	public Cannon(int x, int y, int objType) {
		super(x, y, objType);
		tileY = y / Game.TILES_SIZE;
		initHitbox(40, 26);
//		hitbox.x -= (int) (1 * Game.SCALE);
		hitbox.y += (int) (6 * Game.SCALE);
	}

	public void update() {
	    if (doAnimation)
	        updateAnimationTick();
	  
	    // Actualizar el temporizador de disparo
	    shootTimer++;
	}

	public int getTileY() {
		return tileY;
	}
	
	// Dentro de la clase Cannon

	public int getShootTimer() {
	    return shootTimer;
	}

	public int getShootDelay() {
	    return shootDelay;
	}

	public void resetShootTimer() {
	    shootTimer = 0;
	    
	}


}
