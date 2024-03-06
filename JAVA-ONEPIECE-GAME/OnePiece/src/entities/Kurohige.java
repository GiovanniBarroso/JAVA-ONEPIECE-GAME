package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;

import main.Game;

/**
 * Clase que representa al enemigo Kurohige en el juego.
 */
public class Kurohige extends Enemy {

    private int attackBoxOffsetX;

    /**
     * Constructor de la clase Kurohige.
     * @param x La posición X inicial del enemigo.
     * @param y La posición Y inicial del enemigo.
     */
    public Kurohige(float x, float y) {
        super(x, y, KUROHIGE_WIDTH, KUROHIGE_HEIGHT, KUROHIGE);
        initHitbox(35, 19);
        initAttackBox();
    }

    /**
     * Inicializa el cuadro de ataque del enemigo.
     */
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
    }

    /**
     * Actualiza el estado del enemigo.
     * @param lvlData Los datos del nivel.
     * @param player El jugador.
     */
    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    /**
     * Actualiza la posición del cuadro de ataque.
     */
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    /**
     * Actualiza el comportamiento del enemigo.
     * @param lvlData Los datos del nivel.
     * @param player El jugador.
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
     * Voltea la imagen horizontalmente según la dirección del movimiento.
     * @return La coordenada X para voltear la imagen.
     */
    public int flipX() {
        if (walkDir == LEFT)
            return width;
        else
            return 0;
    }

    /**
     * Voltea la imagen horizontalmente según la dirección del movimiento.
     * @return La dirección en la que se debe voltear la imagen.
     */
    public int flipW() {
        if (walkDir == LEFT)
            return -1;
        else
            return 1;
    }
}
