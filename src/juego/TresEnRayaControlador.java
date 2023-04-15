package juego;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TresEnRayaControlador implements ActionListener {

	TresEnRayaVista vista;
	public char tablero[][];
	int turnoJugador;

	public TresEnRayaControlador(TresEnRayaVista vista) {
		this.vista = vista;
		tablero = new char[3][3];
		turnoJugador = -1;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		JButton boton = (JButton)ae.getSource();
		String textoBoton = boton.getText();
		
		if (textoBoton.equals("Salir")) {
			
			salir();
			
		} else if (textoBoton.equals("Jugar")) {
			
			iniciarPartida();
			
		} else if (textoBoton.equals("Reiniciar")) {
			
			reiniciarPartida();
			
		} else {
			
			int indiceBoton = 0;
			while (ae.getSource() != vista.botonesJuego[indiceBoton])
				indiceBoton++;
			
			turnoPartida(indiceBoton);
			
		}
	}
	
	public void turnoPartida(int indiceBoton) {
		if (!hayGanador() && !tableroLleno()) {
			turnoJugador++;
			rellenarPosicion(indiceBoton);
			hayGanador();
		} 
		tableroLleno();
	}
	
	public void coloreaBoton(int indiceBoton) {
		if (turnoJugador % 2 == 0) {
			vista.botonesJuego[indiceBoton].setBackground(Color.RED);
			vista.botonesJuego[indiceBoton].setText("O");
			letreroJugador();
		} else {
			vista.botonesJuego[indiceBoton].setBackground(Color.BLUE);
			vista.botonesJuego[indiceBoton].setText("X");
			letreroJugador();
		}
	}
	
	public void iniciarPartida() {
		vista.jugar.setText("Reiniciar");
		desbloqueaBotones();
		letreroJugador();
		inicializarArray();
	}
	
	public void reiniciarPartida() {
		turnoJugador = -1;
		inicializarArray();
		desbloqueaBotones();
		reiniciaBotones();
		letreroJugador();
	}
	
	public void reiniciaBotones() {
		for (int i = 0; i < 9; i++) {
			vista.botonesJuego[i].setBackground(Color.ORANGE);
			vista.botonesJuego[i].setText("");
		}
	}

	public void letreroJugador() {
		vista.letrero2.setText("Elija posicion>");
		if (fichaEnJuego() == 'o') {
			vista.letrero1.setText("JUGADOR 2 (cruzadas)");
		} else {
			vista.letrero1.setText("JUGADOR 1 (redondas)");
		}

	}

	public void inicializarArray() {
		tablero = new char[3][3];
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j] = ' ';
			}
		}
	}

	public boolean tableroLleno() {
		if (turnoJugador == 8) {
			vista.letrero1.setText("TABLERO LLENO...GAME OVER");
			vista.letrero2.setText("+------------------------------------+");
			return true;
		}
		return false;
	}

	public char fichaEnJuego() {
		if (turnoJugador % 2 == 0) {
			return 'o';
		}
		return 'x';
	}

	public boolean posicionLibre(int x, int y) {
		if (tablero[x][y] == ' ') {
			return true;
		}
		vista.letrero2.setText("POSICION OKUPADA");
		turnoJugador--;
		return false;
	}

	public void colocarFicha(int x, int y) {
		tablero[x][y] = fichaEnJuego();
	}

	public void rellenarPosicion(int indice) {
		int fila, columna;

		fila = indice / 3;
		if (fila == 0)
			columna = indice;
		else if (fila == 1)
			columna = (indice % 3);
		else
			columna = (indice % 3);

		if (posicionLibre(fila, columna)) {
			colocarFicha(fila, columna);
			coloreaBoton(indice);
		}
	}

	public boolean columnaGanadora() {
		for (int fila = 0; fila < tablero.length; fila++) {
			boolean hayGanador = true;
			for (int columna = 0; columna < tablero[fila].length; columna++) {
				if (tablero[columna][fila] != fichaEnJuego())
					hayGanador = false;
			}
			if (hayGanador)
				return true;
		}
		return false;
	}

	public boolean filaGanadora() {
		for (int fila = 0; fila < tablero.length; fila++) {
			boolean hayGanador = true;
			for (int columna = 0; columna < tablero[fila].length; columna++) {
				if (tablero[fila][columna] != fichaEnJuego())
					hayGanador = false;
			}
			if (hayGanador)
				return true;
		}
		return false;
	}

	public boolean diagonalGanadora() {
		for (int fila = 0; fila < tablero.length; fila++) {
			if (tablero[fila][fila] != fichaEnJuego())
				return false;
		}
		return true;
	}

	public boolean diagonalInversaGanadora() {
		for (int fila = 0, columna = 2; fila < tablero.length; fila++, columna--) {
			if (tablero[fila][columna] != fichaEnJuego())
				return false;
		}
		return true;
	}

	public boolean hayGanador() {
		if (columnaGanadora() || filaGanadora() || diagonalGanadora() || diagonalInversaGanadora()) {
			turnoJugador--;
			letreroJugador();
			vista.letrero2.setText("GANADOR ENHORABUENA!!!");
			bloqueaBotones();
			return true;
		}
		return false;
	}
	
	public void bloqueaBotones() {
		for (int i = 0; i < 9; i++) {
			vista.botonesJuego[i].setEnabled(false);
		}
	}
	
	public void desbloqueaBotones() {
		for (int i = 0; i < 9; i++) {
			vista.botonesJuego[i].setEnabled(true);
		}
	}
	
	public void salir() {
		int eleccion = JOptionPane.showConfirmDialog(vista.ventana,
				"<html><font face='Arial'" + "size ='18'color='red'>Cerrar la app???");
		if (eleccion == JOptionPane.OK_OPTION)
			System.exit(0);
	}
}
