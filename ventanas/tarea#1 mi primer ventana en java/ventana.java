import javax.swing.*; //esta instruccion nos sirve para tener acceso a las clases de diseño de ventanas.
import java.lang.Math;

public class ventana extends JFrame{

	static final int ANCHO_PANTALLA = 1920, ALTO_PANTALLA = 1080;	// medidas de la pantalla de la computadora (importante para saber donde posicionar las ventanas)
	static final int ANCHO_VENTANA = 600, ALTO_VENTANA = 200; 	// medidas de cada ventana
	static final int ANCHO_TEXTO = 50, ALTO_TEXTO = 10;		// medidas de las etiquetas de texto

/*********************************************************************************************************************************************/
/************************************** Constructor de la clase ******************************************************************************/
/*****************************  Aqui se construye todo lo que lleva la ventana ***************************************************************/
/*****************************  	( botones, texto, etc... ) ***************************************************************************/
/*********************************************************************************************************************************************/

	public ventana(){//constructor

	String[][] textoEtiquetas = {  {"Mi", "" , "primer"}, {"", "ventana", ""}, {"en", "", "java"} };
	JLabel[][] etiquetas = new JLabel[3][3];

		OrdenMatriz ordenLabel = new OrdenMatriz(ANCHO_TEXTO, ALTO_TEXTO, ANCHO_VENTANA, ALTO_VENTANA,3,3);//ancho,alto,Longitudpantalla,altopantalla,elementosx,elementosy


		// esto le asigna el texto que debe de llevar cada etiqueta y tambien su posicion en la ventana, las etiquetas de las esquinas y del centro llevan texto.
		// tambien les asigna una posicion dentro de la ventana
		for(int i = 0 ; i < 3 ; i++ )
			for(int j = 0 ; j < 3 ; j++){
				etiquetas[i][j] = new JLabel( textoEtiquetas[i][j] );
				etiquetas[i][j].setBounds( ordenLabel.getPosicionX(j) , ordenLabel.getPosicionY(i) - 15 , ANCHO_TEXTO, ALTO_TEXTO);	//posicion x,y, ancho, alto
				if( ordenLabel.calculaAdjunto(i,j) ) add(etiquetas[i][j]);
		}

	setLayout(null);
	setTitle("ventana");
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	}//fin del constructor


/*********************************************************************************************************************************************/
/************************************** metodo main ******************************************************************************************/
/*****************************  Aqui se instancian las ventanas y se muestran en la pantalla *************************************************/
/******  En este caso creamos un arreglo de ventanas porque queremos mostrar muchas de la misma clase ****************************************/
/*********************************************************************************************************************************************/

	public static void main(String args[]){

		ventana[][] v = new ventana[3][3];

		OrdenMatriz ordenVentana = new OrdenMatriz(ANCHO_VENTANA, ALTO_VENTANA, ANCHO_PANTALLA, ALTO_PANTALLA,3,3);//ancho,alto,Longitudpantalla,altopantalla,elementosx,elementosy

			for(int i = 0 ; i < 3 ; i++)
				for(int j = 0 ; j < 3 ; j++){
					
					v[i][j] = new ventana();
					v[i][j].setBounds( ordenVentana.getPosicionX(j) , ordenVentana.getPosicionY(i) , ANCHO_VENTANA , ALTO_VENTANA );	//x,y,ancho,alto
					v[i][j].setVisible( (ordenVentana.calculaAdjunto(i,j)) );

				}

	}//fin del metodo main

}//fin de la clase ventana
