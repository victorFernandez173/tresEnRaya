package juego;

import java.awt.*;
import javax.swing.*;

public class TresEnRayaVista {
	JFrame ventana;
	JLabel letrero1;
	JLabel letrero2;
	Container cp;
	JButton jugar;
	JButton cerrar;
	JButton reiniciar;
	JPanel panelNorte;
	JPanel botonera;
	JPanel botoneraSur;
	BotonRedondo[] botonesJuego = new BotonRedondo[9];

	public TresEnRayaVista() {
		crearComponentes();
		colocarComponentes();
	}

	public void setUpComponents(Component[] comps) {
		for (int x = 0; x < comps.length; x++) {
			if (comps[x] instanceof Container)
				setUpComponents(((Container) comps[x]).getComponents());
			try {
				comps[x].setFont(new Font("Arial", Font.BOLD, 33));
				comps[x].setBackground(Color.YELLOW);
			} catch (Exception e) {
			}
		}
	}

	public void crearComponentes() {
		ventana = new JFrame("***Tres en raya***");
		letrero1 = new JLabel("  Pulsa jugar para comenzar partida ");
		letrero2 = new JLabel("+---------------------------------------------+");
		jugar = new JButton("Jugar");
		cerrar = new JButton("Salir");
		reiniciar = new JButton("Reiniciar");
		botonera = new JPanel(new GridLayout(3, 3, 15, 15));
		botoneraSur = new JPanel(new GridLayout(1, 2, 120, 100));
		panelNorte = new JPanel(new GridLayout(2, 1));
	}

	public void colocarComponentes() {
		cp = ventana.getContentPane();
		ventana.setLayout(new BorderLayout());
		ventana.setSize(900, 975);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		panelNorte.add(letrero1);
		panelNorte.add(letrero2);
		ventana.add(panelNorte, BorderLayout.NORTH);
		ventana.add(botonera, BorderLayout.CENTER);
		botoneraSur.add(jugar);
		botoneraSur.add(cerrar);
		ventana.add(botoneraSur, BorderLayout.SOUTH);
		setUpComponents(cp.getComponents());
		for (int i = 0; i < 9; i++) {
			BotonRedondo br = new BotonRedondo("");
			br.setBackground(Color.ORANGE);
			br.setBounds(0, 0, 100, 100);
			br.setFont(new Font("Arial", Font.BOLD, 93));
			br.setForeground(Color.BLACK);
			botonesJuego[i] = br;
			botonera.add(botonesJuego[i]);
			botonesJuego[i].setEnabled(false);
		}
		cerrar.setBackground(Color.ORANGE);
		jugar.setBackground(Color.ORANGE);
		ventana.setVisible(true);
	}

	public void conectaControlador(TresEnRayaControlador control) {
		jugar.addActionListener(control);
		cerrar.addActionListener(control);
		for (int i = 0; i < 9; i++) {
			botonesJuego[i].addActionListener(control);
		}
	}
}
