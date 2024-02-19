package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.DropMode;

public class PantallaInicial extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton juego1=new JButton("Entrar en el juego");
	public JButton juego2=new JButton("Entrar en el juego 2");
	public JTextPane textPane = new JTextPane();
	public PantallaInicial() {
		this.setLayout(null);

		//metodo para obtener el tamaño de la pantalla y aplicarlo al tamaño del panel
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setBackground(new Color(255, 255, 255));
		juego1.setBackground(Color.white);
		juego1.setBounds(100,800,300,100);
		juego2.setBackground(Color.white);
		juego2.setBounds(1500,800,300,100);

		add(juego1);
		add(juego2);
		textPane.setForeground(new Color(0, 255, 255));
		textPane.setEnabled(false);


		textPane.setBackground(new Color(98, 151, 157));
		textPane.setBounds(900, 716, 208, 227);
		textPane.setText("Los controles  del juego son las siguientes:");
		add(textPane);
	}

	@Override
	public void paint(Graphics g){
		Dimension dimension = this.getSize();
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/fondo.jpeg"));
		g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
		setOpaque(false);
		super.paintChildren(g);
	}   
}