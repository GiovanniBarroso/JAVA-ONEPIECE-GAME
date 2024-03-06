package objects;

import main.Game;

/**
 * Clase que representa una trampa de pinchos en el juego.
 */
public class Spike extends GameObject {

    /**
     * Crea una nueva trampa de pinchos en el juego.
     * 
     * @param x la coordenada x de la trampa de pinchos
     * @param y la coordenada y de la trampa de pinchos
     * @param objType el tipo de la trampa de pinchos
     */
    public Spike(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(32, 16);
        
        xDrawOffset = 0;
        yDrawOffset = (int) (Game.SCALE * 16);
        hitbox.y += yDrawOffset;
    }
}
