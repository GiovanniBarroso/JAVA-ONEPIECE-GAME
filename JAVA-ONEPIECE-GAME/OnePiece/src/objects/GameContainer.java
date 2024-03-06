package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

/**
 * Representa un contenedor en el juego, como una caja o un barril.
 */
public class GameContainer extends GameObject {

    /**
     * Crea un nuevo contenedor.
     * @param x la coordenada x del contenedor
     * @param y la coordenada y del contenedor
     * @param objType el tipo de objeto
     */
    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    /**
     * Crea el hitbox del contenedor.
     */
    private void createHitbox() {
        if (objType == BOX) {
            initHitbox(25, 18);
            xDrawOffset = (int) (7 * Game.SCALE);
            yDrawOffset = (int) (12 * Game.SCALE);
        } else {
            initHitbox(23, 25);
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
        }

        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);
        hitbox.x += xDrawOffset / 2;
    }

    /**
     * Actualiza el contenedor.
     */
    public void update() {
        if (doAnimation)
            updateAnimationTick();
    }
}
