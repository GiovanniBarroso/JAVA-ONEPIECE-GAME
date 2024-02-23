package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float widht,float heihgt,int [][]lvlData) {
		if(!IsSolid(x, y, lvlData)) {
			if(!IsSolid(x+widht, y+heihgt, lvlData)) {
				if(!IsSolid(x+widht, y, lvlData)) {
					if(!IsSolid(x, y+heihgt, lvlData)) {
						return true;
					}
				}
			}
		}
		return false;

	}
	private static boolean IsSolid(float x, float y, int [][] lvlData) {

		if(x<0||x>=Game.GAME_WIDTH) 
			return true;
		if(y<0||y>=Game.GAME_HEIGHT)
			return true;

		float xIndex = x/Game.TILES_SIZE;
		float yIndex=y/Game.TILES_SIZE;

		int value = lvlData[(int)yIndex][(int)xIndex];

		if(value>=48||value<0||value !=11) {
			return true;

		}
		return false;

	}
	
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		
		int currentTile= (int)(hitbox.x/Game.TILES_SIZE);
		if(xSpeed>0) {
			//right
			int tileXPos=currentTile*Game.TILES_SIZE;
			int xOffset= (int)(Game.TILES_SIZE-hitbox.width);
			//MENOS UNO PARA QUE NO SE SALGA
			return tileXPos+ xOffset -1;
		}else {
			//left
			return currentTile*Game.TILES_SIZE;
			
		}
	}
	public static float GetEntityyPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile= (int)(hitbox.y/Game.TILES_SIZE);
		
		if(airSpeed>0) {
			//callendo o tocando suelo
		
		int tileYPos=currentTile*Game.TILES_SIZE+63;//mas 63 para que no se quede  flotando
			int yOffset=(int)(Game.TILES_SIZE-hitbox.height);
			return tileYPos+yOffset;
		}else {
			//saltando
			return currentTile*Game.TILES_SIZE;
		}
	}
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox,int [][] lvlData) {
		//comprobar el pixel de abajo derecha e izquierda
		if(!IsSolid(hitbox.x, hitbox.y+hitbox.height, lvlData)) {
			if(!IsSolid(hitbox.x+hitbox.width, hitbox.y+hitbox.height, lvlData)){
			return false;	
			}
		}
		return true;
	}
}