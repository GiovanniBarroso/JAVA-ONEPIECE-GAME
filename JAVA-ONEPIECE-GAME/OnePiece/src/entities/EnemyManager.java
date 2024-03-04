package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] bucaneroArr;
	private ArrayList<Bucanero> bucaneros = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		bucaneros = level.getCrabs();
	}

	public void update(int[][] lvlData, Player player) {
		boolean isAnyActive = false;
		for (Bucanero c : bucaneros)
			if (c.isActive()) {
				c.update(lvlData, player);
				isAnyActive = true;
			}
		if (!isAnyActive)
			playing.setLevelCompleted(true);
		
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawBucaneros(g, xLvlOffset);
	}

	private void drawBucaneros(Graphics g, int xLvlOffset) {
		for (Bucanero c : bucaneros)
			if (c.isActive()) {
				g.drawImage(bucaneroArr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - BUCANERO_DRAWOFFSET_X + c.flipX(), (int) c.getHitbox().y - BUCANERO_DRAWOFFSET_Y,
						BUCANERO_WIDTH * c.flipW(), BUCANERO_HEIGHT, null);
				c.drawHitbox(g, xLvlOffset);
				
			}

	}

	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Bucanero c : bucaneros)
			if (c.isActive())
				if (attackBox.intersects(c.getHitbox())) {
					c.hurt(10);
					return;
				}
	}

	private void loadEnemyImgs() {
		bucaneroArr = new BufferedImage[3][6];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.BUCANERO_SPRITE);
		for (int j = 0; j < bucaneroArr.length; j++)
			for (int i = 0; i < bucaneroArr[j].length; i++)
				bucaneroArr[j][i] = temp.getSubimage(i * BUCANERO_WIDTH_DEFAULT, j * BUCANERO_HEIGHT_DEFAULT, BUCANERO_WIDTH_DEFAULT, BUCANERO_HEIGHT_DEFAULT);
	}

	public void resetAllEnemies() {
		for (Bucanero c : bucaneros)
			c.resetEnemy();
	}

}