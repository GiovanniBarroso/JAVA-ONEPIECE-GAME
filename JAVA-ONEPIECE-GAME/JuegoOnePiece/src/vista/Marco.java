package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import controlador.ManejoMouse;
import controlador.ManejoTeclas;

import static vista.Juego_Luffy.GAME_HEIGHT;
import static vista.Juego_Luffy.GAME_WIDTH;

public class Marco extends JFrame implements ActionListener {
	
    public PantallaInicial inicio = new PantallaInicial();
    public JButton juego1 = new JButton("Entrar en el juego");
    public JButton juego2 = new JButton("Entrar en el juego 2");
    public Juego_Luffy panel1 = new Juego_Luffy();
    public Juego_Zoro panel2 = new Juego_Zoro();

    public Marco() {
        setLayout(null);
        setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        setTitle("Juego One piece");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(inicio);
        add(panel1);
        add(panel2); 
        inicio.setVisible(true);
        panel1.setVisible(false);
        panel2.setVisible(false); 
        inicio.juego1.addActionListener(this);
        inicio.juego2.addActionListener(this);
        panel1.addKeyListener(new ManejoTeclas(panel1));
        panel1.addMouseListener(new ManejoMouse(panel1));
		panel1.addMouseMotionListener(new ManejoMouse(panel1));
		
		
//        panel2.addKeyListener(new ManejoTeclas(panel2));
        
        
        addWindowFocusListener(new WindowFocusListener() {
        	
        	
            @Override
            public void windowLostFocus(WindowEvent e) {
                panel1.WindowFocusLost();
                panel2.WindowFocusLost(); // También notificamos a panel2 si el marco pierde el foco
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // No es necesario hacer algo aquí
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inicio.juego1) {
            inicio.setVisible(false);
            panel1.setVisible(true);
        } else if (e.getSource() == inicio.juego2) {
            inicio.setVisible(false);
            panel2.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Marco();
    }
}
