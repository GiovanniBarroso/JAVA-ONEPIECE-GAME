package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;

import main.Game;

/**
 * Clase que representa al enemigo tipo Espadachín en el juego.
 */
public class Espadachin extends Enemy {

    private int attackBoxOffsetX;

    /**
     * Constructor de la clase Espadachin.
     *
     * @param x La coordenada x inicial del Espadachin.
     * @param y La coordenada y inicial del Espadachin.
     */
    public Espadachin(float x, float y) {
        super(x, y, ESPADACHIN_WIDTH, ESPADACHIN_HEIGHT, ESPADACHIN);
        initHitbox(35, 19);
        initAttackBox();
    }

    /**
     * Inicializa el área de ataque del Espadachin.
     */
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
    }

    /**
     * Actualiza el estado del Espadachin en función del entorno y el jugador.
     *
     * @param lvlData Los datos del nivel.
     * @param player  El jugador.
     */
    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    /**
     * Actualiza la posición del área de ataque del Espadachin.
     */
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    /**
     * Actualiza el comportamiento del Espadachin en función del estado actual y el entorno.
     *
     * @param lvlData Los datos del nivel.
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
                case HIT2:
                    // Agregar comportamiento para el estado HIT2 si es necesario
                    break;
            }
        }
    }

    /**
     * Devuelve la posición X para voltear la imagen del Espadachin.
     *
     * @return La posición X para voltear la imagen.
     */
    public int flipX() {
        if (walkDir == RIGHT)
            return width;
        else
            return 0;
    }

    /**
     * Devuelve el factor de escala para voltear la imagen del Espadachin.
     *
     * @return El factor de escala para voltear la imagen.
     */
    public int flipW() {
        if (walkDir == RIGHT)
            return -1;
        else
            return 1;
    }
}
