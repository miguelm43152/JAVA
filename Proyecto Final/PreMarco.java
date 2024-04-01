import javax.swing.*;
import java.awt.event.*;

public abstract class PreMarco extends JFrame implements KeyListener{

//CONSTANTES DE LONGITUDES
	static final int ANCHO_VENTANA = 900, ALTO_VENTANA = 640, ANCHO_E = 150, ALTO_E = 30, ELEMENTOS_X = 3, ELEMENTOS_Y = 5;
	static final int ANCHO_PANTALLA = 1920, ALTO_PANTALLA = 1080;
		OrdenMatriz v = new OrdenMatriz( ANCHO_VENTANA, ALTO_VENTANA, ANCHO_PANTALLA, ALTO_PANTALLA, 1, 1); //Constructor de la clase

	PreMarco(){

		setLayout( null);
		setBounds( v.getPosicionX(), v.getPosicionY(), ANCHO_VENTANA, ALTO_VENTANA );
		setVisible( true );
		addKeyListener( this);
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setResizable( true);

	}//fin del constructor de la clase

	public void mouseExited(MouseEvent e){ }
	public void mousePressed(MouseEvent e){ }
	public void mouseReleased(MouseEvent e){ }

	public void keyPressed(KeyEvent e){

		int codigo = e.getKeyCode();
		
		if( codigo == e.VK_ESCAPE  ) salida();
		//System.exit( 0 );

	}
	public void keyReleased(KeyEvent e){ }
	public void keyTyped(KeyEvent e){ }

	public abstract void salida();
	//{ System.exit( 0 );}

	public static void main( String[] args){

/*
		OrdenMatriz v = new OrdenMatriz( ANCHO_VENTANA, ALTO_VENTANA, ANCHO_PANTALLA, ALTO_PANTALLA, 1, 1); //Constructor de la clase
		Marco ventana = new Marco( );
//		salida(){ System.exit( 0 ); }
		ventana.setDefaultCloseOperation( EXIT_ON_CLOSE );
		ventana.setBounds( v.getPosicionX(), v.getPosicionY(), ANCHO_VENTANA, ALTO_VENTANA );
		ventana.setVisible( true );
*/

	}// fin del metodo main

}//fin de la clase MarcoPrueba