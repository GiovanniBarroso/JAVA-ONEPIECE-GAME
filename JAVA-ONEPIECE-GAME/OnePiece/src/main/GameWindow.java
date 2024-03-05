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
		setTitle("La Busqueda del Sake de Zoro");
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
				// TODO Auto-generated method stub

			}
		});

	}

}