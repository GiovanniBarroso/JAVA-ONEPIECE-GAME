package main;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameWindow extends JFrame{


	public GameWindow(GamePanel gamePanel) {



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gamePanel);

		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setTitle("Grand Voyage: La Leyenda de los Mares");
		setVisible(true);
		ImageIcon icon = new ImageIcon(getClass().getResource("/res/logo.jpg")); // Obtén la imagen del recurso
		setIconImage(icon.getImage());
       
		addWindowFocusListener(new WindowFocusListener() {

			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

}