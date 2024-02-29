package objects;

import java.awt.geom.Rectangle2D;

import main.Game;

import static utilz.Constants.Projectiles.*;
public class Projectile {

	private Rectangle2D.Float hitbox;
	private int dir;
	private boolean active=true;

	public Projectile(int x, int y, int dir) {
		int xOffSet=(int) (-3*Game.SCALE);
		int yOffSet=(int) (5*Game.SCALE);
		if(dir==1)
			xOffSet=(int) (29*Game.SCALE);
		hitbox= new Rectangle2D.Float(x+xOffSet,y+yOffSet,CANNON_BALL_WIDTH,CANNON_BALL_HEIGHT);
		this.dir=dir;
	}
	
	public void updatePos() {
		hitbox.x+=dir*SPEED;
	}
	
	public void sertPos(int x,int y) {
		hitbox.x=x;
		hitbox.y=y;
	}
	
	public Rectangle2D.Float getHitbox(){
		return hitbox;
	}
	
	public void setActive(boolean active) {
		this.active=active;
	}
	public boolean isActive() {
		return active;
	}
	}
