package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

/**
 * Clase abstracta que representa a una entidad en el juego.
 */
public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    protected int aniTick, aniIndex;
    protected int state;
    protected float airSpeed;
    protected boolean inAir = false;
    protected int maxHealth;
    protected int currentHealth;
    protected Rectangle2D.Float attackBox;
    protected float walkSpeed;
    protected static final int RETREAT_DISTANCE = 120;

    /**
     * Constructor de la clase Entity.
     *
     * @param x      La coordenada x de la entidad.
     * @param y      La coordenada y de la entidad.
     * @param width  El ancho de la entidad.
     * @param height La altura de la entidad.
     */
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Dibuja el cuadro de ataque de la entidad.
     *
     * @param g          El contexto gráfico.
     * @param xLvlOffset El desplazamiento horizontal del nivel.
     * @param facingLeft Si la entidad está mirando hacia la izquierda.
     */
    protected void drawAttackBox(Graphics g, int xLvlOffset, boolean facingLeft) {
        g.setColor(Color.red);
        // g.drawRect((int) (attackBox.x - xLvlOffset + xOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height+40);
    }

    /**
     * Dibuja el cuadro de colisión de la entidad.
     *
     * @param g          El contexto gráfico.
     * @param xLvlOffset El desplazamiento horizontal del nivel.
     */
    protected void drawHitbox(Graphics g, int xLvlOffset) {
        // For debugging the hitbox
        g.setColor(Color.PINK);
//         g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    /**
     * Inicializa el cuadro de colisión de la entidad.
     *
     * @param width  El ancho del cuadro de colisión.
     * @param height La altura del cuadro de colisión.
     */
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    /**
     * Devuelve el cuadro de colisión de la entidad.
     *
     * @return El cuadro de colisión.
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    /**
     * Devuelve el estado de la entidad.
     *
     * @return El estado de la entidad.
     */
    public int getState() {
        return state;
    }

    /**
     * Devuelve el índice de animación de la entidad.
     *
     * @return El índice de animación.
     */
    public int getAniIndex() {
        return aniIndex;
    }
}
