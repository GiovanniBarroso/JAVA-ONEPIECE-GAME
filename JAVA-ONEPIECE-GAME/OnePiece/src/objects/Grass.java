package objects;

/**
 * Representa un objeto de hierba en el juego.
 */
public class Grass {

    private int x, y, type;

    /**
     * Crea un nuevo objeto de hierba en el juego.
     * @param x la coordenada x del objeto de hierba
     * @param y la coordenada y del objeto de hierba
     * @param type el tipo de hierba
     */
    public Grass(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Obtiene la coordenada x del objeto de hierba.
     * @return la coordenada x del objeto de hierba
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada y del objeto de hierba.
     * @return la coordenada y del objeto de hierba
     */
    public int getY() {
        return y;
    }

    /**
     * Obtiene el tipo de hierba.
     * @return el tipo de hierba
     */
    public int getType() {
        return type;
    }
}
