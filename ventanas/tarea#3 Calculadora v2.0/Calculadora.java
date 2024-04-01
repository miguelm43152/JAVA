import javax.swing.*;
import java.awt.event.*;
import java.lang.NumberFormatException;

public class Calculadora extends JFrame implements ActionListener{

	//constantes de clase

	static final int ANCHO_VENTANA = 450, ALTO_VENTANA = 400, ANCHO_E = 50, ALTO_E = 50, ELEMENTOS_X = 4, ELEMENTOS_Y = 4;
	static final int ANCHO_PANTALLA = 1920, ALTO_PANTALLA = 1080;

	static final int ANCHO_DISPLAY = 350, ALTO_DISPLAY = 50;

	//atributos de la clase

	JTextField display = new JTextField("0");

	JButton[][] num = new JButton[4][3];
	JButton[] op = new JButton[4];
	JButton clear = new JButton("Ce");
	JButton result = new JButton("=");

	String[][] numTxt ={ {"7", "8", "9"},
			   {"4", "5", "6"},
			   {"1", "2", "3"},
			   {"0" }	    };

	String[] opTxt = { "+", "-", "*", "/" };

	//variables para guardar numeros y realizar operaciones
		int n1 = 0,n2 = 0,operacion;

	//int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY
	OrdenMatriz e = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_VENTANA, ALTO_VENTANA, ELEMENTOS_X, ELEMENTOS_Y );

/*------------------------------------------------------------------------------------*/

/***************************************************************************************/
/*********************   constructor de la ventana ***********************************************/
/***************************************************************************************/

/*------------------------------------------------------------------------------------*/

	Calculadora(){

		setLayout(null);
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setTitle( "Calculadora" );

/***************************************************************************************/
/*********************   Imprimiendo el display de la calculadora************************************/
/***************************************************************************************/

		//Se tiene la intencion de seguir utilizando la clase creada para ordenar n elementos de
		//una matriz a una misma distancia entre cada unos con mismas dimensiones pero el problema
		//ahora es que el display y los botones no pueden tener las mismas dimensiones de alto y ancho

		//Entonces lo que haremos sera "separar" el display y el conjunto de botones. Nos da conflicto la
		//distancia de espaciado que tendra el primer elemento en el eje Y. Por tanto tenemos dos opciones
		//y es tener en cuenta la distancia del display al momento de ingresar la distancia que "observa"
		//la clase OrdenMatriz o podemos modificar esta clase y darle un nuevo atributo. Una distancia
		//incial Y. Esto ultimo es lo que se hara.

		//entonces unicamente posicionamos el display

			display.setBounds(  e.getPosicionX(0) ,30, ANCHO_DISPLAY, ALTO_DISPLAY);
			display.setEditable(false);
			add(display);

		//en esta linea le decimos a la matriz de botones que tenga un desplazamiento inicial de 100px y deje un
		//espacio al final de 50px

		e.setOffset(0,0,100,0);

/***************************************************************************************/
/*********************   Imprimiendo los botones del 0 al 9 ***********************************************/
/***************************************************************************************/

		//Lo que haremos ahora sera declarar las variables de iteracion antes del for para que
		// no sean destruidas al salir del bucle para asi aprovechar el valor que tengan guardado al 
		//terminar el bucle. 

		int i = 0 , j = 0;
		for(i = 0; i < 3 ; i++ )for( j = 0; j < 3 ; j++){

			num[i][j] = new JButton( numTxt[i][j] );
			num[i][j].setBounds( e.getPosicionX(j), e.getPosicionY(i), e.getW(), e.getH() );
			add( num[i][j] );
			num[i][j].addActionListener(this);

		}
	
		//en este momento i tiene el ultimo valor de iteracion +1. Es decir i = 3 + 1 y eso es lo que
		//queremos. Sin embargo, queremos reiniciar j. Por tanto la instruccion j = 0;
		j = 0;

			num[i][j] = new JButton( numTxt[i][j] );
			num[i][j].setBounds( e.getPosicionX(j), e.getPosicionY(i), e.getW(), e.getH() );
			add( num[i][j] );
			num[i][j].addActionListener(this);

/***************************************************************************************/
/*********************   Imprimiendo los botones de operacion ***********************************************/
/***************************************************************************************/
		
		for(i = 0 ; i < 4 ; i++ ){

			op[i] = new JButton( opTxt[i] );
			op[i].setBounds( e.getPosicionX(3), e.getPosicionY(i), e.getW(), e.getH() );
			add( op[i] );
			op[i].addActionListener(this);
		}


/***************************************************************************************/
/*********************   Imprimiendo los botones clear y resultado ***********************************************/
/***************************************************************************************/

			clear.setBounds( e.getPosicionX(1), e.getPosicionY(3), e.getW(), e.getH() );
			add( clear );
			clear.addActionListener(this);
			result.setBounds( e.getPosicionX(2), e.getPosicionY(3), e.getW(), e.getH() );
			add( result );
			result.addActionListener(this);

	}//fin del constructor "Calculadora"

/*------------------------------------------------------------------------------------*/

/***************************************************************************************/
/*********************  fin del constructor de la ventana ***********************************************/
/***************************************************************************************/

/*------------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------*/

/***************************************************************************************/
/*********************   Manejo de eventos ***********************************************/
/***************************************************************************************/

/*------------------------------------------------------------------------------------*/

			public void actionPerformed( ActionEvent accion ){

				//evento para botones numericos (0 al 9)

				int i,j;
				for(i = 0; i < 3 ; i++ )for(j = 0; j < 3 ; j++)
					if( accion.getSource() == num[i][j] ){
						if( display.getText().equals("0") || display.getText().equals("NaN") || display.getText().equals("Infinity") || display.getText().equals("-Infinity") )
							display.setText( num[i][j].getText() );
						else
						display.setText( display.getText() + num[i][j].getText() );
						break;
					}
				j = 0;
				if( accion.getSource() == num[i][j] )
				display.setText( display.getText() + num[i][j].getText() );


				//evento para botones de operacion (+,-,*,/)

				for(i = 0 ; i < 4 ; i++ ){
					if( accion.getSource() == op[i] ){

						try{
						n1 = Integer.parseInt( display.getText() );
						display.setText( "0");
						} catch (NumberFormatException error){
							display.setText( "NaN");
						}finally{}
						operacion = i;
						break;
					}//fin de if
				}//fin de for

				//evento para boton clear

				if( accion.getSource() == clear )
					display.setText( "0");

				//evento para boton resultado

				if( accion.getSource() == result ){

					try{
						n2 = Integer.parseInt( display.getText() );
					} catch (NumberFormatException error){
						display.setText( "Syntax Error");
					}finally{}

						switch(operacion){

							case 0: display.setText( Integer.toString( n1 + n2 ) ); break;
							case 1: display.setText( Integer.toString( n1 - n2 ) ); break;
							case 2: display.setText( Integer.toString( n1 * n2 ) ); break;
							case 3: display.setText( Double.toString( n1*1.0 / n2*1.0 ) ); break;

						}//fin de switch

				}//fin de if

			}

/*------------------------------------------------------------------------------------*/

/***************************************************************************************/
/*********************   fin Manejo de eventos ***********************************************/
/***************************************************************************************/

/*------------------------------------------------------------------------------------*/


	public static void main(String[] args){

		//int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY
		//objeto que nos ayuda a ordenar la ventana principal del programa. "v" de ventana
		OrdenMatriz v = new OrdenMatriz( ANCHO_VENTANA, ALTO_VENTANA, ANCHO_PANTALLA, ALTO_PANTALLA, 1, 1);

		Calculadora ventana = new Calculadora();
		ventana.setBounds( v.getPosicionX(), v.getPosicionY(), v.getW(), v.getH() );
		ventana.setVisible(true);

	}//fin del metodo main

}//fin de la clase "Calculadora"
