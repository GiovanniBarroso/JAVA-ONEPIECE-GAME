package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import entidades.Player2; // Importamos la clase Player2

public class Juego_Zoro extends JPanel implements Runnable {

    private final int FPS_SET = 110;
    private final int UPDATE = 200;
    private Player2 player; // Usamos Player2 en lugar de Player

    public Juego_Zoro() {
        initClases();
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamanioPantalla);
        setBackground(Color.orange);
        empezarBucle();
    }

    private void initClases() {
        player = new Player2(299, 299); // Creamos una instancia de Player2
    }

    private void render(Graphics g) {
        player.render(g);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public Player2 getPlayer() {
        return player;
    }

    public void WindowFocusLost() {
        player.resetDirBoolean();
    }

    private void update() {
        player.update();
    }

    @Override
    public void run() {
        double FPS = 1000000000.0 / FPS_SET;
        double UPS = 1000000000.0 / UPDATE;
        long antes = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long Ultimacomprobacion = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - antes) / UPS;
            deltaF += (currentTime - antes) / FPS;
            antes = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - Ultimacomprobacion >= 1000) {
                Ultimacomprobacion = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " -- UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void empezarBucle() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
}
