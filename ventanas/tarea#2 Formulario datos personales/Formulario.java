import javax.swing.*;
import java.awt.event.*;

public class Formulario extends JFrame implements ActionListener{

	//definimos nuestras constantes de ancho y alto de los elementos y ventana.

		static final int ANCHO_VENTANA = 450, ALTO_VENTANA = 400, ANCHO_E = 150, ALTO_E = 25, ELEMENTOS_X = 2, ELEMENTOS_Y = 4;
		static final int ANCHO_PANTALLA = 1920, ALTO_PANTALLA = 1080;

/**************************************************************************************************************/
	//atributos de la clase

		JTextField[] camposTxt = new JTextField[3];
		JLabel[] etiquetas = new JLabel[3];
		JButton[] botones = new JButton[2];

		String[] textoBoton = {"Actualizar","Cerrar"}, textoEtiquetas = {"Nombre", "Domicilio", "e-mail" };

		//un total de 8 elementos que tenemos que acomodar dentro de la ventana.
		//para la solucion podemos utilizar la clase programada para la actividad anterior
		// "OrdenMatriz" para ayudarnos a realizar esta tarea.

		//int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY
		OrdenMatriz e = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_VENTANA, ALTO_VENTANA, ELEMENTOS_X, ELEMENTOS_Y );

		//La instruccion anterior nos ayudara a realizar los calculos de espaciamiento entre cada elemento.

/**************************************************************************************************************/

	//metodo constructor de clase

			Formulario(){ //constructor de la clase

				setLayout(null);
				setTitle("Formulario");
				setDefaultCloseOperation(EXIT_ON_CLOSE);

					for(int i = 0 ; i < 3 ; i++ ){//posicionamiento de campos de texto
					
						camposTxt[i] = new JTextField();
						camposTxt[i].setBounds( e.getPosicionX(0), e.getPosicionY(i), e.getW(), e.getH() );
						add(camposTxt[i]);

					}


					for(int i = 0 ; i < 2 ; i++ ){//posicionamiento de campos boton
					
						botones[i] = new JButton( textoBoton[i] );
						botones[i].setBounds( e.getPosicionX(i), e.getPosicionY(3), e.getW(), e.getH() );
						add( botones[i]);
						botones[i].addActionListener(this);

					}


					for(int i = 0 ; i < 3 ; i++ ){//posicionamiento de etiquetas
					
						etiquetas[i] = new JLabel( textoEtiquetas[i] );
						etiquetas[i].setBounds( e.getPosicionX(1), e.getPosicionY(i), e.getW(), e.getH() );
						add( etiquetas[i]);

					}

			}//fin del constructor de la clase

			public void actionPerformed( ActionEvent accion ){

				if( accion.getSource() == botones[0] /* boton "Actualizar" */ )
					for(int i = 0 ; i < 3 ; i++)
						etiquetas[i].setText( camposTxt[i].getText() );

				else System.exit(0); //cierra la ventana principal

			}//metodo para ejecutar la accion al realizarse un evento

/**************************************************************************************************************/

	//metodo main de la clase

	public static void main(String args[]){

		Formulario ventana = new Formulario();
		OrdenMatriz principal = new OrdenMatriz( ANCHO_VENTANA, ALTO_VENTANA, ANCHO_PANTALLA, ALTO_PANTALLA, 1, 1);
		ventana.setBounds( principal.getPosicionX(0), principal.getPosicionY(0), principal.getW(), principal.getH() );
		ventana.setVisible(true);

	}//fin del metodo main

}//fin de la clase "Formulario"

/**************************************************************************************************************/
