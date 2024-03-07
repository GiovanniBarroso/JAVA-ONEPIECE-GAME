package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import static utilz.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

/**
 * Clase que representa al jugador en el juego.
 */
public class Player extends Entity {

    private BufferedImage[][] animations;
    private boolean moving = false, attacking = false, attacking2 = false;
    private boolean left, right, jump;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    // Jumping / Gravity
    private float jumpSpeed = -2.5f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;

    // StatusBarUI
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);

    private int healthBarWidth = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) (4 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (14 * Game.SCALE);
    private int healthWidth = healthBarWidth;

    private int powerBarWidth = (int) (104 * Game.SCALE);
    private int powerBarHeight = (int) (2 * Game.SCALE);
    private int powerBarXStart = (int) (44 * Game.SCALE);
    private int powerBarYStart = (int) (34 * Game.SCALE);
    private int powerWidth = powerBarWidth;
    private int powerMaxValue = 200;
    private int powerValue = powerMaxValue;

    private int flipX = 0;
    private int flipW = 1;

    private boolean attackChecked;
    private Playing playing;

    private int tileY = 0;
    private boolean powerAttackActive;
    private int powerAttackTick;
    private int powerRecoveryTick = 0;
    private int powerRecoveryInterval = 20;

    /**
     * Constructor de la clase Player.
     * @param x La posición X inicial del jugador.
     * @param y La posición Y inicial del jugador.
     * @param width El ancho del jugador.
     * @param height La altura del jugador.
     * @param playing El estado de juego en el que se encuentra el jugador.
     */
    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        this.state = QUIETO;
        this.maxHealth = 100;
        this.currentHealth = 100;
        this.walkSpeed = Game.SCALE * 1f;
        loadAnimations();
        initHitbox(20, 27);
        initAttackBox();
    }

    /**
     * Establece la posición de inicio del jugador.
     * @param spawn La posición de inicio.
     */
    public void setSpawn(Point spawn) {
        this.x = spawn.x;
        this.y = spawn.y;
        hitbox.x = x;
        hitbox.y = y;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Métodos getter y setter para x
    public int getX() {
        return (int)x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Métodos getter y setter para y
    public int getY() {
        return (int)y;
    }

    public void setY(int y) {
        this.y = y;
    }
    /**
     * Inicializa el cuadro de ataque del jugador.
     */
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (10 * Game.SCALE), (int) (10 * Game.SCALE));
    }

    /**
     * Actualiza el estado del jugador.
     */
    public void update() {
        updateHealthBar();
        updatePowerBar();
        if (currentHealth <= 0) {
            playing.setGameOver(true);
            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.DIE);
            playing.getGame().getAudioPlayer().stopSong();
            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
            return;
        }

        updateAttackBox();
        updatePos();
        if (IsEntityInWater(hitbox, lvlData)) {
            currentHealth = 0;
            if (currentHealth <= 0) {
                playing.setGameOver(true);
                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.DIE);
                playing.getGame().getAudioPlayer().stopSong();
                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
                return;
            }
        }
        if (currentHealth <= 0) {
            playing.setGameOver(true);
            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.DIE);
            playing.getGame().getAudioPlayer().stopSong();
            playing.getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
            return;
        }
        if (moving) {
            checkPotionTouched();
            checkSpikeTouched();
            tileY = (int) (hitbox.y / Game.TILES_SIZE);
            if (powerAttackActive) {
                powerAttackTick++;
                if (powerAttackTick >= 35) {
                    powerAttackTick = 0;
                    powerAttackActive = false;
                }

            }
        }
        if (attacking || powerAttackActive || attacking2)
            checkAttack();

        updateAnimationTick();
        setAnimation();
    }

    /**
     * Verifica si el jugador ha tocado una trampa de pinchos.
     */
    private void checkSpikeTouched() {
        playing.checkSpikesTouched(this);
    }

    /**
     * Verifica si el jugador ha tocado una poción.
     */
    private void checkPotionTouched() {
        playing.checkPotionTouched(hitbox);
    }

    /**
     * Verifica si el jugador está atacando.
     */
    private void checkAttack() {
        if (attackChecked || aniIndex != 1)
            return;
        attackChecked = true;
        if (powerAttackActive) {
            attackChecked = false;
        }

        playing.checkEnemyHit(attackBox);
        playing.checkObjectHit(attackBox);
        playing.getGame().getAudioPlayer().playAttackSound();
    }

    /**
     * Actualiza la posición del cuadro de ataque.
     */
    private void updateAttackBox() {
        if (right || powerAttackActive && flipW == 1)
            attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 5);
        else if (left || powerAttackActive && flipW == -1)
            attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 5);

        attackBox.y = hitbox.y + (Game.SCALE * 5);
    }

    /**
     * Actualiza la barra de salud del jugador.
     */
    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
    }

    /**
     * Actualiza la barra de poder del jugador.
     */
    public void updatePowerBar() {
        powerWidth = (int) ((powerValue / (float) powerMaxValue) * powerBarWidth);
        powerRecoveryTick++;
        if (powerRecoveryTick >= powerRecoveryInterval) {
            powerRecoveryTick = 0;
            changePower(1);
        }
    }

    /**
     * Renderiza el jugador en el lienzo.
     * @param g El contexto de gráficos.
     * @param lvlOffset El desplazamiento del nivel.
     */
    public void render(Graphics g, int lvlOffset) {
        g.drawImage(animations[state][aniIndex], (int) (hitbox.x - xDrawOffset) - lvlOffset + flipX,
                (int) (hitbox.y - yDrawOffset), width * flipW, height, null);
        drawAttackBox(g, lvlOffset, flipW == -1);
        drawUI(g);
    }

    /**
     * Dibuja la interfaz de usuario (UI) del jugador.
     * @param g El contexto de gráficos.
     */
    private void drawUI(Graphics g) {
        // Fondo de la interfaz de usuario (UI)
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        // Barra de salud
        g.setColor(Color.red);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
        // Barra de poder
        g.setColor(Color.yellow);
        g.fillRect(powerBarXStart + statusBarX, powerBarYStart + statusBarY, powerWidth, powerBarHeight);
    }

    /**
     * Actualiza el recuento de animación del jugador.
     */
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(state)) {
                aniIndex = 0;
                attacking = false;
                attacking2 = false;
                attackChecked = false;
            }

        }

    }

    /**
     * Establece la animación actual del jugador.
     */
    private void setAnimation() {
        int startAni = state;

        if (right)
            state = ANDAR;
        else if (left)
            state = ANDAR;
        else
            state = QUIETO;

        if (inAir) {
            if (airSpeed < 0)
                state = SALTO;
        }
        if (powerAttackActive) {
            state = CORRERALANTE;
            aniIndex = 1;
            aniTick = 0;
            return;
        }

        if (attacking)
            state = ATAQUE;
        if (attacking2)
            state = ESPECIAL;

        if (startAni != state)
            resetAniTick();
    }

    /**
     * Reinicia el recuento de animación del jugador.
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Actualiza la posición del jugador.
     */
    private void updatePos() {
        moving = false;

        if (jump)
            jump();

        if (!inAir)
            if (!powerAttackActive)
                if ((!left && !right) || (right && left))
                    return;

        float xSpeed = 0;

        if (left) {
            xSpeed -= walkSpeed;
            flipX = width;
            flipW = -1;
        }
        if (right) {
            xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }
        if (powerAttackActive) {
            if (!left && !right) {
                if (flipW == -1) {
                    xSpeed = -walkSpeed;
                } else {
                    xSpeed = walkSpeed;
                }
            }

            xSpeed *= 3;
        }
        if (!inAir)
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;

        if (inAir) {
            powerAttackActive = false;
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += GRAVITY;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }

        } else
            updateXPos(xSpeed);
        moving = true;
    }

    /**
     * Hace que el jugador salte.
     */
    private void jump() {
        if (inAir)
            return;
        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.JUMP);
        inAir = true;
        airSpeed = jumpSpeed;

    }

    /**
     * Reinicia el estado "en el aire" del jugador.
     */
    private void resetInAir() {
        inAir = false;
        airSpeed = 0;

    }

    /**
     * Actualiza la posición horizontal del jugador.
     * @param xSpeed La velocidad horizontal.
     */
    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
            if (powerAttackActive) {
                powerAttackActive = false;
                powerAttackTick = 0;
            }

        }

    }

    /**
     * Cambia la salud del jugador.
     * @param value El valor del cambio.
     */
    public void changeHealth(int value) {
        currentHealth += value;

        if (currentHealth <= 0) {
            currentHealth = 0;

        } else if (currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    /**
     * Elimina al jugador.
     */
    public void kill() {
        currentHealth = 0;
    }

    /**
     * Cambia el valor de poder del jugador.
     * @param value El valor del cambio.
     */
    public void changePower(int value) {

        powerValue += value;
        if (powerValue >= powerMaxValue) {
            powerValue = powerMaxValue;
        } else if (powerValue <= 0) {
            powerValue = 0;
        }
    }

    /**
     * Carga las animaciones del jugador desde un atlas de sprites.
     */
    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[14][11];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);

    }

    /**
     * Carga los datos del nivel.
     * @param lvlData Los datos del nivel.
     */
    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;

    }

    /**
     * Reinicia las variables booleanas de dirección del jugador.
     */
    public void resetDirBooleans() {
        left = false;
        right = false;
        jump = false;
        powerAttackActive=false;
        attacking=false;
        
    }

    /**
     * Establece si el jugador está atacando.
     * @param attacking El estado de ataque del jugador.
     */
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    /**
     * Establece si el jugador está realizando un ataque especial.
     * @param attacking2 El estado de ataque especial del jugador.
     */
    public void setAttacking2(boolean attacking2) {
        this.attacking2 = attacking2;
    }

    /**
     * Verifica si el jugador se está moviendo hacia la izquierda.
     * @return `true` si el jugador se está moviendo hacia la izquierda, de lo contrario `false`.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Establece el estado de movimiento hacia la izquierda del jugador.
     * @param left El estado de movimiento hacia la izquierda.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Verifica si el jugador se está moviendo hacia la derecha.
     * @return `true` si el jugador se está moviendo hacia la derecha, de lo contrario `false`.
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Establece el estado de movimiento hacia la derecha del jugador.
     * @param right El estado de movimiento hacia la derecha.
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Establece si el jugador está saltando.
     * @param jump El estado de salto del jugador.
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }



    /**
     * Restablece todas las variables del jugador.
     */
    public void resetAll() {
        resetDirBooleans();
        inAir = false;
        attacking = false;
        attacking2 = false;
        moving = false;
        jump = false;
        state = ANDAR;
        currentHealth = maxHealth;
        powerValue = powerMaxValue;
        hitbox.x = x;
        hitbox.y = y;

        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
    }

    /**
     * Obtiene la posición Y del jugador en los bloques del nivel.
     * @return La posición Y del jugador en los bloques del nivel.
     */
    public int getTileY() {
        return tileY;
    }

    /**
     * Realiza un ataque especial si las condiciones son válidas.
     */
    public void powerAttack() {
        if (inAir == true && powerAttackActive == true) {
            powerAttackActive = false;
            changePower(0);
        } else if (powerAttackActive)
            return;
        else if (powerValue >= 50 && !inAir) {
            powerAttackActive = true;
            changePower(-50);
        }
    }
}