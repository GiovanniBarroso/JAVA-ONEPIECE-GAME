package objects;

import main.Game;

/**
 * Representa un cañón en el juego.
 */
public class Cannon extends GameObject {

    private int tileY;
    private int shootTimer = 0; // Temporizador de disparo
    private int shootDelay = 150; // Retraso entre disparos (en frames)

    /**
     * Crea un nuevo cañón.
     * @param x la coordenada x del cañón
     * @param y la coordenada y del cañón
     * @param objType el tipo de objeto
     */
    public Cannon(int x, int y, int objType) {
        super(x, y, objType);
        tileY = y / Game.TILES_SIZE;
        initHitbox(40, 26);
//      hitbox.x -= (int) (1 * Game.SCALE);
        hitbox.y += (int) (6 * Game.SCALE);
    }

    /**
     * Actualiza el cañón.
     */
    public void update() {
        if (doAnimation)
            updateAnimationTick();

        // Actualizar el temporizador de disparo
        shootTimer++;
    }

    public int getTileY() {
        return tileY;
    }

    public int getShootTimer() {
        return shootTimer;
    }

    public int getShootDelay() {
        return shootDelay;
    }

    public void resetShootTimer() {
        shootTimer = 0;
    }
}
