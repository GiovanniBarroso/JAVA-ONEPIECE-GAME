package utilz;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Bucanero;
import entities.Espadachin;
import entities.Kurohige;
import main.Game;
import objects.BackgroundTree;
import objects.Cannon;
import objects.GameContainer;
import objects.Potion;
import objects.Projectile;
import objects.Spike;

public class HelpMethods {

	/**
	 * Verifica si un área es transitable en el nivel del juego.
	 *
	 * @param x       La coordenada x del área a verificar.
	 * @param y       La coordenada y del área a verificar.
	 * @param width   El ancho del área a verificar.
	 * @param height  La altura del área a verificar.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si el área es transitable, false de lo contrario.
	 */
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!IsSolid(x, y, lvlData))
			if (!IsSolid(x + width, y + height, lvlData))
				if (!IsSolid(x + width, y, lvlData))
					if (!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}

	/**
	 * Verifica si una posición es sólida en el nivel del juego.
	 *
	 * @param x       La coordenada x de la posición a verificar.
	 * @param y       La coordenada y de la posición a verificar.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si la posición es sólida, false de lo contrario.
	 */
	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		int maxWidth = lvlData[0].length * Game.TILES_SIZE;
		if (x < 0 || x >= maxWidth)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;

		return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
	}

	/**
	 * Verifica si un proyectil colisiona con el nivel del juego.
	 *
	 * @param p       El proyectil a verificar.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si el proyectil colisiona, false de lo contrario.
	 */
	public static boolean IsProjectileHittingLevel(Projectile p, int[][] lvlData) {

		return IsSolid(p.getHitbox().x + p.getHitbox().width / 2, p.getHitbox().y + p.getHitbox().height / 2, lvlData);
	}

	/**
	 * Verifica si una entidad está en el agua en el nivel del juego.
	 *
	 * @param hitbox  La hitbox de la entidad.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si la entidad está en el agua, false de lo contrario.
	 */
	public static boolean IsEntityInWater(Rectangle2D.Float hitbox, int[][] lvlData) {
		// Obtener el índice del tile debajo del centro inferior del jugador
		int tileX = (int) Math.floor((hitbox.x + hitbox.width / 2) / Game.TILES_SIZE);
		int tileY = (int) Math.ceil((hitbox.y + hitbox.height) / Game.TILES_SIZE);

		// Verificar si el tile debajo del centro inferior del jugador es agua
		if (tileY >= 0 && tileY < lvlData.length && tileX >= 0 && tileX < lvlData[0].length) {
			if (lvlData[tileY][tileX] == 45) {
				return true; // Si el tile debajo del centro inferior del jugador es agua, retornamos
				// verdadero
			}
		}
		return false; // Si no, retornamos falso
	}

	/**
	 * Verifica si un tile es sólido en el nivel del juego.
	 *
	 * @param xTile   La coordenada x del tile.
	 * @param yTile   La coordenada y del tile.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si el tile es sólido, false de lo contrario.
	 */
	public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
		int value = lvlData[yTile][xTile];

		if (value >= 48 || value < 0 || value != 11)
			return true;
		return false;
	}

	/**
	 * Calcula la próxima posición X de una entidad junto a una pared en el nivel
	 * del juego.
	 *
	 * @param hitbox La hitbox de la entidad.
	 * @param xSpeed La velocidad horizontal de la entidad.
	 * @return La próxima posición X de la entidad junto a una pared.
	 */
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
		if (xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		} else
			// Left
			return currentTile * Game.TILES_SIZE;
	}

	/**
	 * Calcula la próxima posición Y de una entidad bajo un techo o encima de un
	 * suelo en el nivel del juego.
	 *
	 * @param hitbox   La hitbox de la entidad.
	 * @param airSpeed La velocidad vertical de la entidad.
	 * @return La próxima posición Y de la entidad bajo un techo o encima de un
	 *         suelo.
	 */
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			// Falling - touching floor
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		} else
			// Jumping
			return currentTile * Game.TILES_SIZE;

	}

	/**
	 * Verifica si una entidad está en el suelo en el nivel del juego.
	 *
	 * @param hitbox  La hitbox de la entidad.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si la entidad está en el suelo, false de lo contrario.
	 */
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
				return false;
		return true;
	}

	/**
	 * Verifica si hay suelo debajo de una entidad en movimiento en el nivel del
	 * juego.
	 *
	 * @param hitbox  La hitbox de la entidad.
	 * @param xSpeed  La velocidad horizontal de la entidad.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si hay suelo debajo de la entidad, false de lo contrario.
	 */
	public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
		if (xSpeed > 0)
			return IsSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
		else
			return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
	}

	/**
	 * Verifica si un cañón puede ver al jugador en el nivel del juego.
	 *
	 * @param lvlData      Los datos del nivel del juego.
	 * @param firstHitbox  La hitbox del primer punto del cañón.
	 * @param secondHitbox La hitbox del segundo punto del cañón.
	 * @param yTile        La coordenada Y del tile del cañón.
	 * @return true si el cañón puede ver al jugador, false de lo contrario.
	 */
	public static boolean CanCannonSeePlayer(int[][] lvlData, Rectangle2D.Float firstHitbox,
			Rectangle2D.Float secondHitbox, int yTile) {
		int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
		int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

		if (firstXTile > secondXTile)
			return IsAllTilesClear(secondXTile, firstXTile, yTile, lvlData);
		else
			return IsAllTilesClear(firstXTile, secondXTile, yTile, lvlData);
	}

	/**
	 * Verifica si todos los tiles entre dos puntos son transitables en el nivel del
	 * juego.
	 *
	 * @param xStart  La coordenada X del punto de inicio.
	 * @param xEnd    La coordenada X del punto de fin.
	 * @param y       La coordenada Y de los tiles.
	 * @param lvlData Los datos del nivel del juego.
	 * @return true si todos los tiles entre los puntos son transitables, false de
	 *         lo contrario.
	 */
	public static boolean IsAllTilesClear(int xStart, int xEnd, int y, int[][] lvlData) {
		for (int i = 0; i < xEnd - xStart; i++)
			if (IsTileSolid(xStart + i, y, lvlData))
				return false;
		return true;
	}

	public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
		for (int i = 0; i < xEnd - xStart; i++) {
			if (IsTileSolid(xStart + i, y, lvlData))
				return false;
			if (!IsTileSolid(xStart + i, y + 1, lvlData))
				return false;
		}
		return true;
	}

	public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox,
			int yTile) {
		int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
		int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

		if (firstXTile > secondXTile)
			return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
		else
			return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
	}

	/**
	 * Obtiene los datos del nivel del juego a partir de una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return Los datos del nivel del juego.
	 */
	public static int[][] GetLevelData(BufferedImage img) {
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;
	}

	/**
	 * Obtiene una lista de los bucaneros presentes en el nivel del juego a partir
	 * de una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de bucaneros en el nivel del juego.
	 */
	public static ArrayList<Bucanero> GetBucaneros(BufferedImage img) {
		ArrayList<Bucanero> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == BUCANERO)
					list.add(new Bucanero(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}

	/**
	 * Obtiene una lista de los espadachines presentes en el nivel del juego a
	 * partir de una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de espadachines en el nivel del juego.
	 */
	public static ArrayList<Espadachin> GetEspadachines(BufferedImage img) {
		ArrayList<Espadachin> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == ESPADACHIN)
					list.add(new Espadachin(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}

	/**
	 * Obtiene una lista de los kurohiges presentes en el nivel del juego a partir
	 * de una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de kurohiges en el nivel del juego.
	 */
	public static ArrayList<Kurohige> GetKurohiges(BufferedImage img) {
		ArrayList<Kurohige> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == KUROHIGE)
					list.add(new Kurohige(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}

	/**
	 * Obtiene la posición de aparición del jugador en el nivel del juego a partir
	 * de una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La posición de aparición del jugador.
	 */
	public static Point GetPlayerSpawn(BufferedImage img) {
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == 100)
					return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
			}
		return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
	}

	/**
	 * Obtiene una lista de las pociones presentes en el nivel del juego a partir de
	 * una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de pociones en el nivel del juego.
	 */
	public static ArrayList<Potion> GetPotions(BufferedImage img) {
		ArrayList<Potion> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == RED_POTION || value == BLUE_POTION)
					list.add(new Potion(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
			}

		return list;
	}

	/**
	 * Obtiene una lista de los contenedores de juego presentes en el nivel del
	 * juego a partir de una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de contenedores de juego en el nivel del juego.
	 */
	public static ArrayList<GameContainer> GetContainers(BufferedImage img) {
		ArrayList<GameContainer> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == BOX || value == BARREL)
					list.add(new GameContainer(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
			}

		return list;
	}

	/**
	 * Obtiene una lista de los pinchos presentes en el nivel del juego a partir de
	 * una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de pinchos en el nivel del juego.
	 */
	public static ArrayList<Spike> GetSpikes(BufferedImage img) {

		ArrayList<Spike> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == SPIKE)
					list.add(new Spike(i * Game.TILES_SIZE, j * Game.TILES_SIZE, SPIKE));
			}

		return list;
	}

	/**
	 * Obtiene una lista de los cañones presentes en el nivel del juego a partir de
	 * una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de cañones en el nivel del juego.
	 */
	public static ArrayList<Cannon> GetCannons(BufferedImage img) {
		ArrayList<Cannon> list = new ArrayList<>();

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == CANNON_LEFT || value == CANNON_RIGHT)
					list.add(new Cannon(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
			}

		return list;
	}

	/**
	 * Obtiene una lista de los árboles de fondo presentes en el nivel del juego a
	 * partir de una imagen.
	 *
	 * @param img La imagen del nivel del juego.
	 * @return La lista de árboles de fondo en el nivel del juego.
	 */
	public static ArrayList<BackgroundTree> GetBackgroundTrees(BufferedImage img) {
		ArrayList<BackgroundTree> list = new ArrayList<>();

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == TREE_ONE || value == TREE_TWO || value == TREE_THREE)
					list.add(new BackgroundTree(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
			}

		return list;
	}

}