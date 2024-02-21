package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.Loadsave;
import vista.Juego_Luffy;
import vista.Marco;

public class LevelManager {

	private Juego_Luffy panel1;
	private BufferedImage [] levelSprite;
	private Level level1;
	public LevelManager(Juego_Luffy panel1) {
		this.panel1=panel1;
		//		levelSprite=Loadsave.GetSpriteAtlas(Loadsave.level);
		importOutsideSprite();
		level1=new Level(Loadsave.GetLevelData());
	}

	private void importOutsideSprite() {
		BufferedImage imagen = Loadsave.GetSpriteAtlas(Loadsave.level);
		levelSprite= new BufferedImage [48];
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 12; i++) {
				int index=j*12 + i;
				levelSprite[index]= imagen.getSubimage(i*32, j*32, 32, 32);
			}			
		}

	}

	public void draw(Graphics g) {
		for (int j = 0; j < Marco.TILES_IN_HEIGHT; j++) {
			for (int i = 0; i < Marco.TILES_IN_WIDTH; i++) {
				int index= level1.getSpriteIndex(i,j);
				g.drawImage(levelSprite[index], Marco.TILES_SIZE*i,Marco.TILES_SIZE*j ,Marco.TILES_SIZE,Marco.TILES_SIZE,null);
			}
			
		}
	
	}
	public void update() {

	}
}
