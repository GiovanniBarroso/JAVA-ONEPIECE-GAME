package levels;

public class Level {

	private int [][]lvldata;
	
	public Level(int [][] lvldata) {
		this.lvldata=lvldata;
	}
	public int getSpriteIndex(int x,int y){
		return lvldata[x][y];
	}
}
