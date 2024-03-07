package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utilz.LoadSave;

/**
 * La clase `Credits` representa el estado del juego dedicado a mostrar los créditos del juego.
 * Extiende la clase `State` e implementa la interfaz `Statemethods`.
 * 
 * Las características principales de esta clase son:
 * - Carga las imágenes de fondo y los créditos utilizando la clase `LoadSave`.
 * - Gestiona una lista de entidades que se muestran en los créditos, como sprites de personajes o logos.
 * - Actualiza la posición de las imágenes de fondo y las entidades para simular un efecto de desplazamiento.
 * - Controla el tiempo transcurrido para salir del estado después de un cierto período.
 * - Implementa métodos para manejar eventos de teclado y ratón, aunque en su mayoría no tienen implementación.
 */
public class Credits extends State implements Statemethods {
    private BufferedImage backgroundImg, creditsImg;
    private int bgX, bgY, bgW, bgH;
    private float bgYFloat;
    private int elapsedTimeSeconds = 0;
    private ArrayList<ShowEntity> entitiesList;

    /**
     * Constructor de la clase `Credits`.
     * 
     * @param game El objeto `Game` al que pertenece este estado.
     */
    public Credits(Game game) {
        super(game);
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
        creditsImg = LoadSave.GetSpriteAtlas(LoadSave.CREDITS);
        bgW = (int) (creditsImg.getWidth() * Game.SCALE);
        bgH = (int) (creditsImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = Game.GAME_HEIGHT;
        loadEntities();
    }

    /**
     * Carga las entidades que se mostrarán durante los créditos.
     */
    private void loadEntities() {
        entitiesList = new ArrayList<>();
        entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS), 4, 64, 40), (int) (Game.GAME_WIDTH * 0.05), (int) (Game.GAME_HEIGHT * 0.8)));
    }

    /**
     * Obtiene una animación de inactividad a partir de un atlas de sprites.
     * 
     * @param atlas         El atlas de sprites del que se extraerá la animación.
     * @param spritesAmount La cantidad de sprites en la animación.
     * @param width         El ancho de cada sprite.
     * @param height        La altura de cada sprite.
     * @return Un arreglo de imágenes que representa la animación de inactividad.
     */
    private BufferedImage[] getIdleAni(BufferedImage atlas, int spritesAmount, int width, int height) {
        BufferedImage[] arr = new BufferedImage[spritesAmount];
        for (int i = 0; i < spritesAmount; i++)
            arr[i] = atlas.getSubimage(width * i, 0, width, height);
        return arr;
    }

    /**
     * Actualiza el estado del estado de créditos.
     * Se encarga de actualizar la posición de las imágenes de fondo y las entidades, así como de controlar la finalización de los créditos.
     */
    @Override
    public void update() {
        bgYFloat -= 0.2f;
        for (ShowEntity se : entitiesList)
            se.update();

        elapsedTimeSeconds++;
        if (elapsedTimeSeconds >= 6300) {
            System.exit(0); // Salir del juego después de cierto tiempo
        }
    }

    /**
     * Dibuja el estado de créditos en la pantalla.
     * Se encarga de dibujar las imágenes de fondo, los créditos y las entidades en pantalla.
     * 
     * @param g El objeto `Graphics` utilizado para dibujar en la pantalla.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(creditsImg, bgX, (int) (bgY + bgYFloat), bgW, bgH, null);

        for (ShowEntity se : entitiesList)
            se.draw(g);
    }

    // Otros métodos de la interfaz Statemethods (keyReleased, mouseMoved, etc.) tienen una implementación vacía.
    // No se ha proporcionado ninguna funcionalidad específica para estos métodos.
    
    /**
     * La clase `ShowEntity` representa una entidad que se muestra durante los créditos.
     * Cada entidad tiene su propia animación y posición.
     */
    private class ShowEntity {
        private BufferedImage[] idleAnimation;
        private int x, y, aniIndex, aniTick;

        /**
         * Constructor de la clase `ShowEntity`.
         * 
         * @param idleAnimation La animación de inactividad de la entidad.
         * @param x             La coordenada X de la posición inicial de la entidad.
         * @param y             La coordenada Y de la posición inicial de la entidad.
         */
        public ShowEntity(BufferedImage[] idleAnimation, int x, int y) {
            this.idleAnimation = idleAnimation;
            this.x = x;
            this.y = y;
        }

        /**
         * Dibuja la entidad en la pantalla.
         * 
         * @param g El objeto `Graphics` utilizado para dibujar en la pantalla.
         */
        public void draw(Graphics g) {
            g.drawImage(idleAnimation[aniIndex], x, y, (int) (idleAnimation[aniIndex].getWidth() * 4), (int) (idleAnimation[aniIndex].getHeight() * 4), null);
        }

        /**
         * Actualiza la entidad.
         * Se encarga de avanzar la animación de la entidad.
         */
        public void update() {
            aniTick++;
            if (aniTick >= 25) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= idleAnimation.length)
                    aniIndex = 0;
            }
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
