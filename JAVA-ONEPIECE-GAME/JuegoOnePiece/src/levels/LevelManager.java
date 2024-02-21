package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import vista.Juego_Luffy;

public class LevelManager {

	private Juego_Luffy game;
	private BufferedImage levelSprite;

	public LevelManager (Juego_Luffy game) {
		this.game = game;
		levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);

	}
	public void draw (Graphics g) {
		g.drawImage(levelSprite, 0,0,null);
	}
	
	public void update() {

	}
	
}