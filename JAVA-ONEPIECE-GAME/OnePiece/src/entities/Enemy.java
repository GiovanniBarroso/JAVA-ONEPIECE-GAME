package entities;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;
import static utilz.Constants.*;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

import main.Game;

/**
 * Clase abstracta que representa a un enemigo en el juego.
 */
public abstract class Enemy extends Entity {
    protected int retreatDistance = 100;
    protected int enemyType;
    protected boolean firstUpdate = true;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float attackDistance = Game.TILES_SIZE;
    protected boolean active = true;
    protected boolean attackChecked;

    /**
     * Constructor de la clase Enemy.
     *
     * @param x         La coordenada X inicial del enemigo.
     * @param y         La coordenada Y inicial del enemigo.
     * @param width     El ancho del enemigo.
     * @param height    La altura del enemigo.
     * @param enemyType El tipo de enemigo.
     */
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;
        walkSpeed = Game.SCALE * 0.2f;
    }

    // Métodos protegidos

//    protected void knockback(Player player, int[][] lvlData) {
//        // Aplica el retroceso
//        if (player.hitbox.x < hitbox.x) {
//            hitbox.x += retreatDistance;
//        } else {
//            hitbox.x -= retreatDistance;
//        }
//
//        // Verifica si hay un bloque de suelo debajo del enemigo
//        boolean onGround = IsEntityOnFloor(hitbox, lvlData);
//        if (!onGround) {
//            while (!IsEntityOnFloor(hitbox, lvlData)) {
//                hitbox.y += GRAVITY;
//                if (IsEntityInWater(hitbox, lvlData)) {
//                    hurt(100);
//                    // Actualizar la animación mientras el enemigo está siendo golpeado por el agua
//                    break;
//                }
//                active = false;
//            }
//        }
//    }

    protected void checkWaterCollision(int[][] lvlData) {
        if (IsEntityInWater(hitbox, lvlData)) {
            newState(DEAD); // Establecer el estado como muerto
            currentHealth -= 20; // Establecer la salud en 0 después de la animación
            aniIndex = 0; // Reiniciar el índice de la animación
        }
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED + 20) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, state)) {
                aniIndex = 0;

                switch (state) {
                    case ATTACK -> state = IDLE;
                    case DEAD, DEAD2 -> {
                        active = false;
                    }
                }
            }
        }
    }

    protected void firstUpdateCheck(int[][] lvlData) {
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
        firstUpdate = false;
    }

    protected void updateInAir(int[][] lvlData) {
        if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.y += airSpeed;
            airSpeed += GRAVITY;
        } else {
            inAir = false;
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
            tileY = (int) (hitbox.y / Game.TILES_SIZE);
        }
    }

    protected void move(int[][] lvlData) {
        float xSpeed = 0;

        if (walkDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;

        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            if (IsFloor(hitbox, xSpeed, lvlData)) {
                hitbox.x += xSpeed;
                return;
            }

        changeWalkDir();
    }

    protected void turnTowardsPlayer(Player player) {
        if (player.hitbox.x > hitbox.x)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    protected boolean canSeePlayer(int[][] lvlData, Player player) {
        int playerTileY = (int) (player.getHitbox().y / Game.TILES_SIZE);
        if (playerTileY == tileY)
            if (isPlayerInRange(player)) {
                if (IsSightClear(lvlData, hitbox, player.hitbox, tileY))
                    return true;
            }

        return false;
    }

    protected boolean isPlayerInRange(Player player) {
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance * 5;
    }

    protected boolean isPlayerCloseForAttack(Player player) {
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance;
    }

    protected void newState(int enemyState) {
        this.state = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }

    public void hurt(int amount) {
        currentHealth -= amount;

        if (currentHealth <= 0) {
            newState(DEAD);
        }
    }

    protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitbox)) {
            player.changeHealth(-GetEnemyDmg(enemyType));
        }

        attackChecked = true;
    }

    protected void changeWalkDir() {
        if (walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(IDLE);
        active = true;
        airSpeed = 0;
    }

    public boolean isActive() {
        return active;
    }
}
