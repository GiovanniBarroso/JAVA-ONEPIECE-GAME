package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

/**
 * Panel principal del juego.
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private MouseInputs mouseInputs;
    private Game game;
    JLabel alertLabel = new JLabel("El enemigo ha sido lanzado al agua", SwingConstants.CENTER);

    /**
     * Constructor para inicializar el panel del juego.
     * 
     * @param game La instancia del juego.
     */
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        alertLabel.setForeground(Color.RED); // Establecer el color del texto
        alertLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Establecer el tipo de fuente y tamaño
        alertLabel.setVisible(false); 
        add(alertLabel);
    }

    /**
     * Establece el tamaño preferido del panel.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Actualiza el juego.
     */
    public void updateGame() {
        // Método para futuras actualizaciones del juego
    }

    /**
     * Pinta los componentes del panel.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    /**
     * Obtiene la instancia del juego.
     * 
     * @return La instancia del juego.
     */
    public Game getGame() {
        return game;
    }

}
