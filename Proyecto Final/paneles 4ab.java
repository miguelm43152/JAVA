import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.event.*; //para eventos de JCheckBox
import com.toedter.calendar.*;		//para usar JDateChooser
import java.text.*;
import java.util.*;

/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel4 	     *******************************************************/
/***********************************************************************************************************************************/

class Panel4 extends JPanel{

	static final int ANCHO_PANEL = 450, ALTO_PANEL = 300, ANCHO_E = 120, ALTO_E = 30;

	JButton confirmar = new JButton( "Confirmar" );
	JButton cancelar = new JButton( "Cancelar" );

	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 3, 2); //Constructor de la clase

	Panel4(){

		setBackground(Color.ORANGE);
		setLayout( null);
		setBounds( ANCHO_PANEL , ALTO_PANEL , ANCHO_PANEL, ALTO_PANEL );
		setVisible( true );

		confirmar.setBounds( el.getPosicionX(1) , el.getPosicionY(0), ANCHO_E , ALTO_E );
		cancelar.setBounds( el.getPosicionX(1) , el.getPosicionY(1), ANCHO_E , ALTO_E );
		add( confirmar);
		add( cancelar);

	}//fin del constructor

}// fin de la clase Panel4

/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel4B 	     *******************************************************/
/***********************************************************************************************************************************/

class Panel4B extends JPanel{

	static final int ANCHO_PANEL = 450, ALTO_PANEL = 300, ANCHO_E = 120, ALTO_E = 30;

	JButton[] botones = new JButton[4];
	String[] textoBotones = { "Agregar cita", "Modificar", "Eliminar", "Salir" };

	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 3, 4); //Constructor de la clase

	Panel4B(){

		setBackground(Color.PINK);
		setLayout( null);
		setBounds( ANCHO_PANEL , ALTO_PANEL , ANCHO_PANEL, ALTO_PANEL );
		setVisible( true );

		for( int i = 0 ; i < 4 ; i++ ){
			botones[i] = new JButton( textoBotones[i]); 
			botones[i].setBounds( (ANCHO_PANEL - ANCHO_E )/2 , el.getPosicionY( i ) , ANCHO_E , ALTO_E );
			add( botones[i]);
		}

	}//fin del constructor

	void mostrarCita(){	cambiarModoVisibleCita( true);	}
	void ocultarCita(){	cambiarModoVisibleCita( false);	}
	void cambiarModoVisibleCita( boolean b){

		botones[0].setVisible( b);

	}// fin metodo cambiarModoVisibleCita

}// fin de la clase Panel4B