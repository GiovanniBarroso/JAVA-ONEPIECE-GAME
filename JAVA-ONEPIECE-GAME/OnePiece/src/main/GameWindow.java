package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Ventana principal del juego.
 */
public class GameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor que inicializa la ventana del juego.
     * 
     * @param gamePanel El panel del juego.
     */
    public GameWindow(GamePanel gamePanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setTitle("La Búsqueda del Sake de Zoro");
        setVisible(true);
        ImageIcon icon = new ImageIcon(getClass().getResource("/res/logo.jpg"));
        setIconImage(icon.getImage());

        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // No es necesario implementar este método en este momento
            }
        });
    }
}
