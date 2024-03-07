package levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Bucanero;
import entities.Espadachin;
import entities.Kurohige;
import main.Game;
import objects.BackgroundTree;
import objects.Cannon;
import objects.GameContainer;
import objects.Grass;
import objects.Potion;
import objects.Spike;

import static utilz.Constants.ObjectConstants.*;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

/**
 * Clase que representa un nivel del juego.
 */
public class Level {

    private BufferedImage img;
    private int[][] lvlData;
    private ArrayList<Bucanero> bucanero;
    private ArrayList<Espadachin> espadachin;
    private ArrayList<Kurohige> kuro;
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

    /**
     * Constructor para crear un nivel.
     * 
     * @param img La imagen del nivel.
     */
    public Level(BufferedImage img) {
        this.img = img;
        bucanero = new ArrayList<>();
        espadachin = new ArrayList<>();
        kuro = new ArrayList<>();
        potions = new ArrayList<>();
        containers = new ArrayList<>();
        spikes = new ArrayList<>();
        cannons = new ArrayList<>();
        trees = new ArrayList<>();
        grass = new ArrayList<>();

        createLevelData();
        loadLevel();
        calcPlayerSpawn();
        calcLvlOffsets();
    }

    // Métodos privados

    /**
     * Carga el nivel a partir de una imagen.
     */
    private void loadLevel() {
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

    /**
     * Carga los datos del nivel.
     * 
     * @param redValue El valor rojo.
     * @param x        La coordenada x.
     * @param y        La coordenada y.
     */
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

    /**
     * Obtiene el tipo de césped aleatorio en función de la posición x.
     * 
     * @param xPos La posición x.
     * @return El tipo de césped.
     */
    private int getRndGrassType(int xPos) {
        return xPos % 2;
    }

    /**
     * Carga las entidades del nivel.
     * 
     * @param greenValue El valor verde.
     * @param x          La coordenada x.
     * @param y          La coordenada y.
     */
    private void loadEntities(int greenValue, int x, int y) {
        switch (greenValue) {
            case BUCANERO -> bucanero.add(new Bucanero(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
            case ESPADACHIN -> espadachin.add(new Espadachin(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
            case KUROHIGE -> kuro.add(new Kurohige(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
            case 100 -> playerSpawn = new Point(x * Game.TILES_SIZE, y * Game.TILES_SIZE);
        }
    }

    /**
     * Carga los objetos del nivel.
     * 
     * @param blueValue El valor azul.
     * @param x         La coordenada x.
     * @param y         La coordenada y.
     */
    private void loadObjects(int blueValue, int x, int y) {
        switch (blueValue) {
            case RED_POTION, BLUE_POTION -> potions.add(new Potion(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
            case BOX, BARREL -> containers.add(new GameContainer(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
            case SPIKE -> spikes.add(new Spike(x * Game.TILES_SIZE, y * Game.TILES_SIZE, SPIKE));
            case CANNON_LEFT, CANNON_RIGHT -> cannons.add(new Cannon(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
            case TREE_ONE, TREE_TWO, TREE_THREE -> trees.add(new BackgroundTree(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
        }
    }

    /**
     * Calcula los desplazamientos del nivel.
     */
    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
    }

    /**
     * Calcula la posición de aparición del jugador.
     */
    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    /**
     * Crea los datos del nivel.
     */
    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    // Métodos públicos

    /**
     * Obtiene el índice del sprite en una posición específica del nivel.
     * 
     * @param x La coordenada x.
     * @param y La coordenada y.
     * @return El índice del sprite.
     */
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    /**
     * Obtiene los datos del nivel.
     * 
     * @return Los datos del nivel.
     */
    public int[][] getLevelData() {
        return lvlData;
    }

    /**
     * Obtiene el desplazamiento máximo del nivel.
     * 
     * @return El desplazamiento máximo.
     */
    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    /**
     * Obtiene la lista de piratas bucaneros en el nivel.
     * 
     * @return La lista de piratas bucaneros.
     */
    public ArrayList<Bucanero> getBucaneros() {
        return bucanero;
    }

    /**
     * Obtiene la lista de espadachines en el nivel.
     * 
     * @return La lista de espadachines.
     */
    public ArrayList<Espadachin> getEspadachines() {
        return espadachin;
    }

    /**
     * Obtiene la lista de Kurohige en el nivel.
     * 
     * @return La lista de Kurohige.
     */
    public ArrayList<Kurohige> getKurohiges() {
        return kuro;
    }

    /**
     * Obtiene la posición de aparición del jugador en el nivel.
     * 
     * @return La posición de aparición del jugador.
     */
    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    /**
     * Obtiene la lista de pociones en el nivel.
     * 
     * @return La lista de pociones.
     */
    public ArrayList<Potion> getPotions() {
        return potions;
    }

    /**
     * Obtiene la lista de contenedores en el nivel.
     * 
     * @return La lista de contenedores.
     */
    public ArrayList<GameContainer> getContainers() {
        return containers;
    }

    /**
     * Obtiene la lista de pinchos en el nivel.
     * 
     * @return La lista de pinchos.
     */
    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    /**
     * Obtiene la lista de cañones en el nivel.
     * 
     * @return La lista de cañones.
     */
    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    /**
     * Obtiene la lista de césped en el nivel.
     * 
     * @return La lista de césped.
     */
    public ArrayList<Grass> getGrass() {
        return grass;
    }

    /**
     * Obtiene la lista de árboles de fondo en el nivel.
     * 
     * @return La lista de árboles de fondo.
     */
    public ArrayList<BackgroundTree> getTrees() {
        return trees;
    }
}
