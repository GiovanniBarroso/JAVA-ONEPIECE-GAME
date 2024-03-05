package levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Bucanero;
import entities.Espadachin;
import main.Game;
import objects.BackgroundTree;
import objects.Cannon;
import objects.GameContainer;
import objects.Grass;
import objects.Potion;
import objects.Spike;
import utilz.HelpMethods;

import static utilz.Constants.ObjectConstants.*;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

public class Level {

	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Bucanero> bucanero;
	private ArrayList<Espadachin> espadachin;
	private ArrayList<Potion> potions;
	private ArrayList<Spike> spikes;
	private ArrayList<GameContainer> containers;
	private ArrayList<Cannon> cannons;
	private ArrayList<BackgroundTree> trees = new ArrayList<>();
	private ArrayList<Grass> grass = new ArrayList<>();

	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;
	public Level(BufferedImage img) {
	    this.img = img;
	    bucanero = new ArrayList<>(); // Inicializa bucanero
	    espadachin = new ArrayList<>(); // Inicializa espadachin
	    potions = new ArrayList<>(); // Inicializa potions
	    containers = new ArrayList<>(); // Inicializa containers
	    spikes = new ArrayList<>(); // Inicializa spikes
	    cannons = new ArrayList<>(); 
	    trees = new ArrayList<>(); // Inicializa trees
	    grass = new ArrayList<>(); // Inicializa grass

	    createLevelData();
	    loadLevel();
	    calcPlayerSpawn();
	    calcLvlOffsets();
	}

	private void loadLevel() {

		// Looping through the image colors just once. Instead of one per
		// object/enemy/etc..
		// Removed many methods in HelpMethods class.

		for (int y = 0; y < img.getHeight(); y++)
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();

				loadLevelData(red, x, y);
				loadEntities(green, x, y);
				loadObjects(blue, x, y);
			}
	}

	private void loadLevelData(int redValue, int x, int y) {
		if (redValue >= 50)
			lvlData[y][x] = 0;
		else
			lvlData[y][x] = redValue;
		switch (redValue) {
		case 0, 1, 2, 3, 30, 31, 33, 34, 35, 36, 37, 38, 39 -> 
		grass.add(new Grass((int) (x * Game.TILES_SIZE), (int) (y * Game.TILES_SIZE) - Game.TILES_SIZE, getRndGrassType(x)));
		}
	}

	private int getRndGrassType(int xPos) {
		return xPos % 2;
	}

	private void loadEntities(int greenValue, int x, int y) {
		switch (greenValue) {
		case BUCANERO -> bucanero.add(new Bucanero(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case ESPADACHIN -> espadachin.add(new Espadachin(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
//		case SHARK -> sharks.add(new Shark(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case 100 -> playerSpawn = new Point(x * Game.TILES_SIZE, y * Game.TILES_SIZE);
		}
	}

	private void loadObjects(int blueValue, int x, int y) {
		switch (blueValue) {
		case RED_POTION, BLUE_POTION -> potions.add(new Potion(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		case BOX, BARREL -> containers.add(new GameContainer(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		case SPIKE -> spikes.add(new Spike(x * Game.TILES_SIZE, y * Game.TILES_SIZE, SPIKE));
		case CANNON_LEFT, CANNON_RIGHT -> cannons.add(new Cannon(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		case TREE_ONE, TREE_TWO, TREE_THREE -> trees.add(new BackgroundTree(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		}
	}

	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}

	

	private void calcPlayerSpawn() {
		playerSpawn = GetPlayerSpawn(img);
	}




	private void createLevelData() {
		lvlData = GetLevelData(img);
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

	public int[][] getLevelData() {
		return lvlData;
	}

	public int getLvlOffset() {
		return maxLvlOffsetX;
	}

	public ArrayList<Bucanero> getCrabs() {
		return bucanero;
	}
	public ArrayList<Espadachin> getEspadachines() {
		return espadachin;
	}

	public Point getPlayerSpawn() {
		return playerSpawn;
	}

	public ArrayList<Potion> getPotions() {
		return potions;
	}

	public ArrayList<GameContainer> getContainers() {
		return containers;
	}
	public ArrayList<Spike> getSpikes(){
		return spikes;
	}
	public ArrayList<Cannon> getCannons(){
		return cannons;
	}
	public ArrayList<Grass> getGrass() {
		return grass;
	}
	public ArrayList<BackgroundTree> getTrees() {
		return trees;
	}
}