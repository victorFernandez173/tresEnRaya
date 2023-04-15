package juego;

public class TresEnRayaTestMain { 	
	public static void main(String args[]) { 	
		TresEnRayaVista vista = new TresEnRayaVista();
		TresEnRayaControlador controlador = new TresEnRayaControlador(vista);
		vista.conectaControlador(controlador);
	}
}