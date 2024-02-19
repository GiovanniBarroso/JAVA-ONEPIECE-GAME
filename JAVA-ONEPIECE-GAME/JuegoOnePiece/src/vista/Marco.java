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

public class Marco extends JFrame implements ActionListener{

	public PantallaInicial inicio = new PantallaInicial();
	public JButton juego1=new JButton("Entrar en el juego");
	public JButton juego2=new JButton("Entrar en el juego 2");
	public Juego1 panel1 = new Juego1();
	public Marco() {
		setLayout(null);
		setSize(new Dimension(700,700));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);  
		setTitle("Juego One piece");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(inicio);
		add(panel1);
		inicio.setVisible(true);
		panel1.setVisible(false);
		inicio.juego1.addActionListener(this);
		panel1.addKeyListener(new ManejoTeclas(panel1));
		addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				panel1.WindowFocusLost();
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== inicio.juego1) {
			inicio.setVisible(false);
			panel1.setVisible(true);

		}

	}




}