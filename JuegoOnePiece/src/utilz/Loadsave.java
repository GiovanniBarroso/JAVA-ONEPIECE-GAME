package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import vista.Marco;

public class Loadsave {

	public static final String LUFFY = "SPRITE LUFFY.png";
	public static final String ZORO="SPRITE ZORO DEF.png";
	public static final String level="outside_sprites.png";
	public static final String level_data="level_one_data_long.png";
	public static BufferedImage GetSpriteAtlas(String filename) {
		BufferedImage imagen =null;
		InputStream is = Loadsave.class.getResourceAsStream("/imagenes/"+filename);
		try {
			 imagen = ImageIO.read(is);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagen;
	}
	
	public static int [][]GetLevelData(){
		int [][]lveldata = new int [Marco.TILES_IN_HEIGHT][Marco.TILES_IN_WIDTH];
		BufferedImage imagen = GetSpriteAtlas(level_data);
		
		for (int j = 0; j < imagen.getHeight(); j++) {
			for (int i = 0; i < imagen.getWidth(); i++) {
				Color color = new Color(imagen.getRGB(i, j));
				int value= color.getRed();
				if(value>=48) {
					value=0;
				}
				lveldata[j][i]= value;
			}
					
		}
		return lveldata;
	}
}
