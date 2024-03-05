package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.PlayerConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] bucaneroArr,espadachinArr,kurohigeArr;
	private ArrayList<Bucanero> bucaneros = new ArrayList<>();
	private ArrayList<Espadachin> espadachines = new ArrayList<>();
	private ArrayList<Kurohige> kurohiges = new ArrayList<>();
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		bucaneros = level.getCrabs();
		espadachines=level.getEspadachines();
		kurohiges= level.getKurohiges();
	}

	public void update(int[][] lvlData, Player player) {
		boolean isAnyActive = false;
		for (Bucanero c : bucaneros)
			if (c.isActive()) {
				c.update(lvlData, player);
				isAnyActive = true;
			}
		for (Espadachin e : espadachines)
			if (e.isActive()) {
				e.update(lvlData, player);
				isAnyActive = true;
			}
		for (Kurohige k : kurohiges)
			if (k.isActive()) {
				k.update(lvlData, player);
				isAnyActive = true;
			}
		
		if (!isAnyActive)
			playing.setLevelCompleted(true);
		
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawBucaneros(g, xLvlOffset);
		drawEspadachines(g,xLvlOffset);
		drawKurohiges(g,xLvlOffset);
	}

	private void drawKurohiges(Graphics g, int xLvlOffset) {
		for (Kurohige k : kurohiges)
			if (k.isActive()) {
				g.drawImage(kurohigeArr[k.getState()][k.getAniIndex()], (int) k.getHitbox().x - xLvlOffset - KUROHIGE_DRAWOFFSET_X + k.flipX(), (int) k.getHitbox().y - KUROHIGE_DRAWOFFSET_Y,
						KUROHIGE_WIDTH * k.flipW(), KUROHIGE_HEIGHT, null);
				k.drawHitbox(g, xLvlOffset);
				
			}
		
	}

	private void drawEspadachines(Graphics g, int xLvlOffset) {
	
		for (Espadachin e : espadachines)
			if (e.isActive()) {
				g.drawImage(espadachinArr[e.getState()][e.getAniIndex()], (int) e.getHitbox().x - xLvlOffset - ESPADACHIN_DRAWOFFSET_X + e.flipX(), (int) e.getHitbox().y - ESPADACHIN_DRAWOFFSET_Y,
						ESPADACHIN_WIDTH * e.flipW(), ESPADACHIN_HEIGHT, null);
				e.drawHitbox(g, xLvlOffset);
				
			}
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
				if (attackBox.intersects(c.getHitbox())&&playing.getPlayer().state==ATAQUE) {
					c.hurt(10);
					return;
				}else if(attackBox.intersects(c.getHitbox())&&playing.getPlayer().state==ESPECIAL) {
					c.hurt(20);
					return;
				}
		for (Espadachin e : espadachines)
			if (e.isActive())
				if (attackBox.intersects(e.getHitbox())&&playing.getPlayer().state==ATAQUE) {
					e.hurt(10);
					return;
				}else if(attackBox.intersects(e.getHitbox())&&playing.getPlayer().state==ESPECIAL) {
					e.hurt(20);
					return;
				}
		for (Kurohige k : kurohiges)
			if (k.isActive())
				if (attackBox.intersects(k.getHitbox())&&playing.getPlayer().state==ATAQUE) {
					k.hurt(10);
					return;
				}else if(attackBox.intersects(k.getHitbox())&&playing.getPlayer().state==ESPECIAL) {
					k.hurt(20);
					return;
				}
				
	}

	private void loadEnemyImgs() {
		bucaneroArr = new BufferedImage[3][6];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.BUCANERO_SPRITE);
		for (int j = 0; j < bucaneroArr.length; j++)
			for (int i = 0; i < bucaneroArr[j].length; i++)
				bucaneroArr[j][i] = temp.getSubimage(i * BUCANERO_WIDTH_DEFAULT, j * BUCANERO_HEIGHT_DEFAULT, BUCANERO_WIDTH_DEFAULT, BUCANERO_HEIGHT_DEFAULT);
		
		espadachinArr = new BufferedImage[4][4];
		BufferedImage temp2 = LoadSave.GetSpriteAtlas(LoadSave.ESPADACHIN_SPRITE);
		for (int j = 0; j < espadachinArr.length; j++)
			for (int i = 0; i < espadachinArr[j].length; i++)
				espadachinArr[j][i] = temp2.getSubimage(i * ESPADACHIN_WIDTH_DEFAULT, j * ESPADACHIN__HEIGHT_DEFAULT, ESPADACHIN_WIDTH_DEFAULT, ESPADACHIN__HEIGHT_DEFAULT);
		
		kurohigeArr = new BufferedImage[3][12];
		BufferedImage temp3 = LoadSave.GetSpriteAtlas(LoadSave.KUROHIGE_SPRITE);
		for (int j = 0; j < kurohigeArr.length; j++)
			for (int i = 0; i < kurohigeArr[j].length; i++)
				kurohigeArr[j][i] = temp3.getSubimage(i * KUROHIGE_WIDTH_DEFAULT, j * KUROHIGE__HEIGHT_DEFAULT, KUROHIGE_WIDTH_DEFAULT, KUROHIGE__HEIGHT_DEFAULT);
	}

	public void resetAllEnemies() {
		for (Bucanero c : bucaneros)
			c.resetEnemy();
		for (Espadachin e : espadachines)
			e.resetEnemy();
		for (Kurohige k : kurohiges)
			k.resetEnemy();
		
	}

}