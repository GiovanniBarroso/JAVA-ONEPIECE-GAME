package objects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

/**
 * Representa un objeto en el juego.
 */
public class GameObject {

    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;

    /**
     * Crea un nuevo objeto en el juego.
     * @param x la coordenada x del objeto
     * @param y la coordenada y del objeto
     * @param objType el tipo de objeto
     */
    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    /**
     * Actualiza el temporizador de animación.
     */
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0;
                if (objType == BARREL || objType == BOX) {
                    doAnimation = false;
                    active = false;
                } else if (objType == CANNON_LEFT || objType == CANNON_RIGHT) {
                    doAnimation = false;
                }
            }
        }
    }

    /**
     * Reinicia el objeto.
     */
    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;

        if (objType == BARREL || objType == BOX || objType == CANNON_LEFT || objType == CANNON_RIGHT) {
            doAnimation = false;
        } else {
            doAnimation = true;
        }
    }

    /**
     * Inicializa el hitbox del objeto.
     * @param width el ancho del hitbox
     * @param height la altura del hitbox
     */
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    /**
     * Dibuja el hitbox del objeto.
     * @param g el contexto gráfico
     * @param xLvlOffset el desplazamiento horizontal del nivel
     */
    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getAniTick() {
        return aniTick;
    }

}
