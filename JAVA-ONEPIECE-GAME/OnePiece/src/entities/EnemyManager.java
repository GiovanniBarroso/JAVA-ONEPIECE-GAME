package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.PlayerConstants.*;

/**
 * Clase que gestiona a todos los enemigos en el juego.
 */
public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] bucaneroArr, espadachinArr, kurohigeArr;
    private ArrayList<Bucanero> bucaneros = new ArrayList<>();
    private ArrayList<Espadachin> espadachines = new ArrayList<>();
    private ArrayList<Kurohige> kurohiges = new ArrayList<>();

    /**
     * Constructor de la clase EnemyManager.
     *
     * @param playing La instancia de Playing.
     */
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    /**
     * Carga las imágenes de los enemigos.
     */
    private void loadEnemyImgs() {
        bucaneroArr = new BufferedImage[3][6];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.BUCANERO_SPRITE);
        for (int j = 0; j < bucaneroArr.length; j++)
            for (int i = 0; i < bucaneroArr[j].length; i++)
                bucaneroArr[j][i] = temp.getSubimage(i * BUCANERO_WIDTH_DEFAULT, j * BUCANERO_HEIGHT_DEFAULT, BUCANERO_WIDTH_DEFAULT, BUCANERO_HEIGHT_DEFAULT);

        espadachinArr = new BufferedImage[4][4];
        BufferedImage temp2 = LoadSave.GetSpriteAtlas(LoadSave.ESPADACHIN_SPRITE);
        for (int j = 0; j < espadachinArr.length; j++)
            for (int i = 0; i < espadachinArr[j].length; i++)
                espadachinArr[j][i] = temp2.getSubimage(i * ESPADACHIN_WIDTH_DEFAULT, j * ESPADACHIN__HEIGHT_DEFAULT, ESPADACHIN_WIDTH_DEFAULT, ESPADACHIN__HEIGHT_DEFAULT);

        kurohigeArr = new BufferedImage[5][12];
        BufferedImage temp3 = LoadSave.GetSpriteAtlas(LoadSave.KUROHIGE_SPRITE);
        for (int j = 0; j < kurohigeArr.length; j++)
            for (int i = 0; i < kurohigeArr[j].length; i++)
                kurohigeArr[j][i] = temp3.getSubimage(i * KUROHIGE_WIDTH_DEFAULT, j * KUROHIGE__HEIGHT_DEFAULT, KUROHIGE_WIDTH_DEFAULT, KUROHIGE__HEIGHT_DEFAULT);
    }

    /**
     * Carga los enemigos de un nivel.
     *
     * @param level El nivel.
     */
    public void loadEnemies(Level level) {
        bucaneros = level.getBucaneros();
        espadachines = level.getEspadachines();
        kurohiges = level.getKurohiges();
    }

    /**
     * Actualiza el estado de los enemigos.
     *
     * @param lvlData Los datos del nivel.
     * @param player  El jugador.
     */
    public void update(int[][] lvlData, Player player) {
        boolean isAnyActive = false;
        for (Bucanero c : bucaneros)
            if (c.isActive()) {
                c.update(lvlData, player);
                c.checkWaterCollision(lvlData); // Verificar colisión con agua
                isAnyActive = true;
            }
        for (Espadachin e : espadachines)
            if (e.isActive()) {
                e.update(lvlData, player);
                e.checkWaterCollision(lvlData); // Verificar colisión con agua
                isAnyActive = true;
            }
        for (Kurohige k : kurohiges)
            if (k.isActive()) {
                k.update(lvlData, player);
                k.checkWaterCollision(lvlData); // Verificar colisión con agua
                isAnyActive = true;
            }

        if (!isAnyActive)
            playing.setLevelCompleted(true);
    }

    /**
     * Dibuja los enemigos en pantalla.
     *
     * @param g         El contexto gráfico.
     * @param xLvlOffset El desplazamiento horizontal del nivel.
     */
    public void draw(Graphics g, int xLvlOffset) {
        drawBucaneros(g, xLvlOffset);
        drawEspadachines(g, xLvlOffset);
        drawKurohiges(g, xLvlOffset);
    }

    /**
     * Dibuja a los Kurohiges en pantalla.
     *
     * @param g         El contexto gráfico.
     * @param xLvlOffset El desplazamiento horizontal del nivel.
     */
    private void drawKurohiges(Graphics g, int xLvlOffset) {
        for (Kurohige k : kurohiges)
            if (k.isActive()) {
                g.drawImage(kurohigeArr[k.getState()][k.getAniIndex()], (int) k.getHitbox().x - xLvlOffset - KUROHIGE_DRAWOFFSET_X + k.flipX(), (int) k.getHitbox().y - KUROHIGE_DRAWOFFSET_Y,
                        KUROHIGE_WIDTH * k.flipW(), KUROHIGE_HEIGHT*2, null);
                k.drawHitbox(g, xLvlOffset);
            }
    }

    /**
     * Dibuja a los Espadachines en pantalla.
     *
     * @param g         El contexto gráfico.
     * @param xLvlOffset El desplazamiento horizontal del nivel.
     */
    private void drawEspadachines(Graphics g, int xLvlOffset) {
        for (Espadachin e : espadachines)
            if (e.isActive()) {
                g.drawImage(espadachinArr[e.getState()][e.getAniIndex()], (int) e.getHitbox().x - xLvlOffset - ESPADACHIN_DRAWOFFSET_X + e.flipX(), (int) e.getHitbox().y - ESPADACHIN_DRAWOFFSET_Y,
                        ESPADACHIN_WIDTH * e.flipW(), ESPADACHIN_HEIGHT, null);
                e.drawHitbox(g, xLvlOffset);
            }
    }

    /**
     * Dibuja a los Bucaneros en pantalla.
     *
     * @param g         El contexto gráfico.
     * @param xLvlOffset El desplazamiento horizontal del nivel.
     */
    private void drawBucaneros(Graphics g, int xLvlOffset) {
        for (Bucanero c : bucaneros)
            if (c.isActive()) {
                g.drawImage(bucaneroArr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - BUCANERO_DRAWOFFSET_X + c.flipX(), (int) c.getHitbox().y - BUCANERO_DRAWOFFSET_Y,
                        BUCANERO_WIDTH * c.flipW(), BUCANERO_HEIGHT, null);
                c.drawHitbox(g, xLvlOffset);
            }
    }

    /**
     * Verifica si se ha golpeado a algún enemigo con el ataque del jugador.
     *
     * @param attackBox La caja de colisión del ataque del jugador.
     */
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        // Verificar colisión con los Bucaneros
        for (Bucanero c : bucaneros)
            if (c.isActive())
                if (attackBox.intersects(c.getHitbox()) && playing.getPlayer().getState() == ATAQUE) {
                    c.hurt(10);
                   
                    return;
                } else if (attackBox.intersects(c.getHitbox()) && playing.getPlayer().getState() == ESPECIAL) {
                    c.hurt(20);
                   
                    return;
                }

        // Verificar colisión con los Espadachines
        for (Espadachin e : espadachines)
            if (e.isActive())
                if (attackBox.intersects(e.getHitbox()) && playing.getPlayer().getState() == ATAQUE) {
                    e.hurt(10);
                   
                    return;
                } else if (attackBox.intersects(e.getHitbox()) && playing.getPlayer().getState() == ESPECIAL) {
                    e.hurt(20);
                   
                    return;
                }

        // Verificar colisión con los Kurohiges
        for (Kurohige k : kurohiges)
            if (k.isActive())
                if (attackBox.intersects(k.getHitbox()) && playing.getPlayer().getState() == ATAQUE) {
                    k.hurt(10);
//                   
                    return;
                } else if (attackBox.intersects(k.getHitbox()) && playing.getPlayer().getState() == ESPECIAL) {
                    k.hurt(20);
                    
                    return;
                }
    }

    /**
     * Reinicia a todos los enemigos.
     */
    public void resetAllEnemies() {
        for (Bucanero c : bucaneros)
            c.resetEnemy();
        for (Espadachin e : espadachines)
            e.resetEnemy();
        for (Kurohige k : kurohiges)
            k.resetEnemy();
    }
    public void muerteGlobal() {
        for (Bucanero c : bucaneros)
            c.hurt(30);
        for (Espadachin e : espadachines)
            e.hurt(30);
        for (Kurohige k : kurohiges)
            k.hurt(20);
    }
}
