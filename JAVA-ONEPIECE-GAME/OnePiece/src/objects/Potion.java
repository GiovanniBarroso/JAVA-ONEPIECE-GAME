package objects;

import main.Game;

/**
 * Clase que representa una poción en el juego.
 */
public class Potion extends GameObject {

    private float hoverOffset;
    private int maxHoverOffset, hoverDir = 1;

    /**
     * Crea una nueva poción en el juego.
     * 
     * @param x la coordenada x de la poción
     * @param y la coordenada y de la poción
     * @param objType el tipo de poción
     */
    public Potion(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;

        initHitbox(7, 14);

        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);

        maxHoverOffset = (int) (10 * Game.SCALE);
    }

    /**
     * Actualiza la lógica de la poción.
     */
    public void update() {
        updateAnimationTick();
        updateHover();
    }

    /**
     * Actualiza el efecto de flotación de la poción.
     */
    private void updateHover() {
        hoverOffset += (0.075f * Game.SCALE * hoverDir);

        if (hoverOffset >= maxHoverOffset)
            hoverDir = -1;
        else if (hoverOffset < 0)
            hoverDir = 1;

        hitbox.y = y + hoverOffset;
    }
}
