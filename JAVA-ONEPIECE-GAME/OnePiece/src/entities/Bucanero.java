package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;

import java.awt.geom.Rectangle2D;

import main.Game;

/**
 * Clase que representa al enemigo Bucanero en el juego.
 */
public class Bucanero extends Enemy {

    private int attackBoxOffsetX;

    /**
     * Constructor de la clase Bucanero.
     *
     * @param x La coordenada X inicial del Bucanero.
     * @param y La coordenada Y inicial del Bucanero.
     */
    public Bucanero(float x, float y) {
        super(x, y, BUCANERO_WIDTH, BUCANERO_HEIGHT, BUCANERO);
        initHitbox(35, 19);
        initAttackBox();
    }

    /**
     * Inicializa el cuadro de ataque del Bucanero.
     */
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
    }

    /**
     * Actualiza el comportamiento del Bucanero.
     *
     * @param lvlData La matriz de datos del nivel.
     * @param player  El jugador.
     */
    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    /**
     * Actualiza la posición y el tamaño del cuadro de ataque del Bucanero.
     */
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    /**
     * Actualiza el comportamiento del Bucanero en función del estado y de la interacción con el jugador.
     *
     * @param lvlData La matriz de datos del nivel.
     * @param player  El jugador.
     */
    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate)
            firstUpdateCheck(lvlData);

        if (inAir)
            updateInAir(lvlData);
        else {
            switch (state) {
                case IDLE:
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player))
                            newState(ATTACK);
                    }

                    move(lvlData);
                    break;

                case ATTACK:
                    if (aniIndex == 0)
                        attackChecked = false;
                    if (aniIndex == 3 && !attackChecked)
                        checkPlayerHit(attackBox, player);
                    break;
            }
        }
    }

    /**
     * Voltea la posición X del Bucanero según su dirección.
     *
     * @return La posición X ajustada.
     */
    public int flipX() {
        if (walkDir == RIGHT)
            return width;
        else
            return 0;
    }

    /**
     * Voltea el tamaño del Bucanero según su dirección.
     *
     * @return El tamaño ajustado.
     */
    public int flipW() {
        if (walkDir == RIGHT)
            return -1;
        else
            return 1;
    }
}
