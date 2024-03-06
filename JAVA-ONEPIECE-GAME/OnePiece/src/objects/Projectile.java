package objects;

import java.awt.geom.Rectangle2D;

import main.Game;

import static utilz.Constants.Projectiles.*;

/**
 * Clase que representa un proyectil en el juego.
 */
public class Projectile {
    private Rectangle2D.Float hitbox;
    private int dir;
    private boolean active = true;

    /**
     * Crea un nuevo proyectil en el juego.
     * 
     * @param x la coordenada x del proyectil
     * @param y la coordenada y del proyectil
     * @param dir la dirección del proyectil (1 para derecha, -1 para izquierda)
     */
    public Projectile(int x, int y, int dir) {
        int xOffset = (int) (-3 * Game.SCALE);
        int yOffset = (int) (5 * Game.SCALE);

        if (dir == 1)
            xOffset = (int) (29 * Game.SCALE);

        hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT);
        this.dir = dir;
    }

    /**
     * Actualiza la posición del proyectil en función de su dirección y velocidad.
     */
    public void updatePos() {
        hitbox.x += dir * SPEED;
    }

    /**
     * Establece la posición del proyectil.
     * 
     * @param x la nueva coordenada x del proyectil
     * @param y la nueva coordenada y del proyectil
     */
    public void setPos(int x, int y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    /**
     * Obtiene el hitbox del proyectil.
     * 
     * @return el hitbox del proyectil
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    /**
     * Establece el estado de actividad del proyectil.
     * 
     * @param active true si el proyectil está activo, false de lo contrario
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Verifica si el proyectil está activo.
     * 
     * @return true si el proyectil está activo, false de lo contrario
     */
    public boolean isActive() {
        return active;
    }
}
