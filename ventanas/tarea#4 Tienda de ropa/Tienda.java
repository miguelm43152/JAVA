import javax.swing.*;
import java.awt.event.*;
import java.lang.ArrayIndexOutOfBoundsException;

public class Tienda extends JFrame implements ActionListener, ItemListener{

/********************************************************************************************************************************/
/********************************	INICIO	DECLARACION DE VARIABLES		*****************************************/			
/********************************************************************************************************************************/

//CONSTANTES DE LONGITUDES
	static final int ANCHO_VENTANA = 800, ALTO_VENTANA = 600, ANCHO_E = 125, ALTO_E = 30, ELEMENTOS_X = 3, ELEMENTOS_Y = 5;
	static final int ANCHO_PANTALLA = 1920, ALTO_PANTALLA = 1080;

//VARIABLES PARA COMBOBOX

//variable que nos indica que el boton ya se ha presionado
	boolean botonPresionado = false;

	JLabel[] etiquetas = new JLabel[4];
	String[] etiquetasTxt = { "Pantalon", "Camisas", "Calceta", "Ropa inf." };

	JComboBox[] Cajitas = new JComboBox[9];
	
	String[][] CajitasTxt = {//INICIO ARREGLO BIDIMENSIONAL STRING

	//pantalon
	{ "Color", "azul", "marron", "blanco", "rojo", "mezquilla" }, //color de pantalon
	{ "Talla", "32", "34", "36", "38", "40" },			//talla de pantalon
	{ "Corte", "corte1", "corte2", "corte3"},			//corte de pantalon

	//camisa
	{ "Color", "azul", "violeta", "blanco", "rojo", "rayas" },	//color de camisa
	{ "Talla", "Xs", "chica", "mediana", "grande", "XL" },			//Talla
	{ "Manga", "corta", "larga"},						//manga de la camisa

	//calcetas
	{ "Color", "negras", "rojas", "blancas", "lunares"},			//color de calcetas

	//Ropa infantil
	{ "Genero", "nino", "nina"},						//genero
	{ "Meses", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"} //meses	

	};//FIN ARREGLO BIDIMENSIONAL STRING


	JTextArea cuadroTexto = new JTextArea();

	JLabel letrero = new JLabel("Tienda de Ropa");
	JButton registrar = new JButton("Registrar Pedido");


	//int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY
	OrdenMatriz e = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_VENTANA, ALTO_VENTANA, ELEMENTOS_X, ELEMENTOS_Y );
	OrdenMatriz e2 = new OrdenMatriz( ANCHO_E + 130, ALTO_E, ANCHO_VENTANA, ALTO_VENTANA, 3, 3 );

/********************************************************************************************************************************/
/********************************	FIN	DECLARACION DE VARIABLES		*****************************************/			
/********************************************************************************************************************************/
/*------------------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************************************************************************************/
/********************************	INICIO		CONSTRUCTOR DE CLASE		*****************************************/			
/********************************************************************************************************************************/


	Tienda(){//constructor de clase

		e.setOffset( 15, 30 , 400, 300 );

//		e.imprimeTamagnoMatriz();

		setLayout(null);
		setTitle("Tienda de ropa");
		setDefaultCloseOperation( EXIT_ON_CLOSE );

/********************************		INICIO	MATRIZ DE COMBOBOX		*****************************************/	

		for(int i = 0 ; i < 4 ; i++){
			etiquetas[i] = new JLabel( etiquetasTxt[i] );
			add(etiquetas[i]);
		}


		for(int i = 0 ; i < 9 ; i++ ){
			Cajitas[i] = new JComboBox();
			add(Cajitas[i]);
		}


//Precaucion impresion de texto en los combo box. 

		try{

		for(int i = 0 ; i < 9 ; i++ ){
			for(int j = 0 ; j < CajitasTxt[i].length ; j++ ){
	
				Cajitas[i].addItem( CajitasTxt[i][j] );

			}
		}

		}catch(ArrayIndexOutOfBoundsException error){
			System.out.println("Ha ocurrido un error al imprimir los combo box en ventana.\nEl elemento Cajitas[i] esta vacio");
				System.exit(0); //cierra la ventana principal
		}

//agregando la seccion de pantalon a la ventana

		etiquetas[0].setBounds(e.getPosicionX(0), e.getPosicionY(0), e.getW(), e.getH() ) ;
		etiquetas[1].setBounds(e.getPosicionX(1), e.getPosicionY(0), e.getW(), e.getH() ) ;
		etiquetas[2].setBounds(e.getPosicionX(2), e.getPosicionY(0), e.getW(), e.getH() ) ;
		etiquetas[3].setBounds(e.getPosicionX(2), e.getPosicionY(2), e.getW(), e.getH() ) ;


		for(int i = 0 ; i < 3 ; i++ ){
			Cajitas[i].setBounds(e.getPosicionX(0), e.getPosicionY( i + 1 ), e.getW(), e.getH() ) ;
		}//fin de for

		for(int i = 0 ; i < 3 ; i++ ){
			Cajitas[3 + i].setBounds(e.getPosicionX(1), e.getPosicionY( i + 1 ), e.getW(), e.getH() ) ;
		}//fin de for

			Cajitas[3 + 3].setBounds(e.getPosicionX(2), e.getPosicionY( 1 ), e.getW(), e.getH() ) ;

		for(int i = 0 ; i < 2 ; i++ ){
			Cajitas[3 + 3 + 1 +i].setBounds(e.getPosicionX(2), e.getPosicionY( i + 3 ), e.getW(), e.getH() ) ;
		}//fin de for



/********************************		FIN	MATRIZ DE COMBOBOX		*****************************************/

/********************************			INICIO	TEXTAREA		*****************************************/	

		cuadroTexto.setBounds(  420, e.getPosicionY(0) , 325, 500);
		cuadroTexto.setEditable(false);
		add(cuadroTexto);
	
/********************************			FIN	TEXTAREA		*****************************************/	

/********************************			INICIO	TEXTO-BOTON		*****************************************/

		e2.setOffset( 15, 350 , 400, 100 );//desplazamientoInicialX, desplazamienoInicialY, desplazamientoFinalX, desplazamientoFinalY
		letrero.setBounds(e2.getPosicionX(1), e2.getPosicionY(0), e2.getW(), e2.getH() ) ;
		registrar.setBounds(e2.getPosicionX(1), e2.getPosicionY(1), e2.getW(), e2.getH() ) ;
		add(letrero);
		add(registrar);
		
		for(int i = 0 ; i < 9 ; i++) Cajitas[i].addItemListener(this);
		registrar.addActionListener(this);
/********************************			FIN	TEXTO-BOTON		*****************************************/	

	}//fin del constructor "tienda"

/********************************************************************************************************************************/
/********************************	FIN		CONSTRUCTOR DE CLASE		*****************************************/			
/********************************************************************************************************************************/
/*------------------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************************************************************************************/
/********************************		INICIO		MANEJO EVENTOS		*****************************************/
/********************************************************************************************************************************/

	void imprimeEnCuadroTexto(){

			cuadroTexto.setText( "***" + etiquetasTxt[0] + "***\n" );
			int i = 0;
			for( ; i < 3 ; i++)
			cuadroTexto.setText( cuadroTexto.getText() + CajitasTxt[i][0] + ": " + Cajitas[i].getSelectedItem().toString() + "\n"  );

			cuadroTexto.setText( cuadroTexto.getText() + "\n" );
			cuadroTexto.setText( cuadroTexto.getText() + "***" + etiquetasTxt[1] + "***\n" );

			for( ; i < 3 + 3 ; i++)
			cuadroTexto.setText( cuadroTexto.getText() + CajitasTxt[i][0] + ": " + Cajitas[i].getSelectedItem().toString() + "\n"  );

			cuadroTexto.setText( cuadroTexto.getText() + "\n" );
			cuadroTexto.setText( cuadroTexto.getText() + "***" + etiquetasTxt[2] + "***\n" );

			for( ; i < 3 + 3 + 1 ; i++)
			cuadroTexto.setText( cuadroTexto.getText() + CajitasTxt[i][0] + ": " + Cajitas[i].getSelectedItem().toString() + "\n"  );

			cuadroTexto.setText( cuadroTexto.getText() + "\n" );
			cuadroTexto.setText( cuadroTexto.getText() + "***" + etiquetasTxt[3] + "***\n" );

			for( ; i < 3 + 3  + 1 + 2; i++)
			cuadroTexto.setText( cuadroTexto.getText() + CajitasTxt[i][0] + ": " + Cajitas[i].getSelectedItem().toString() + "\n"  );
	}

	public void itemStateChanged(ItemEvent e){
		if(botonPresionado) imprimeEnCuadroTexto();			
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == registrar){
			botonPresionado = true;
			imprimeEnCuadroTexto();
		}//fin if
	}

/********************************************************************************************************************************/
/********************************		FIN		MANEJO EVENTOS		*****************************************/
/********************************************************************************************************************************/

	public static void main(String[] args){

		//int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY
		//objeto que nos ayuda a ordenar la ventana principal del programa. "v" de ventana
		OrdenMatriz v = new OrdenMatriz( ANCHO_VENTANA, ALTO_VENTANA, ANCHO_PANTALLA, ALTO_PANTALLA, 1, 1);

		Tienda ventana = new Tienda();
		ventana.setBounds( v.getPosicionX(), v.getPosicionY(), v.getW(), v.getH() );
		ventana.setVisible(true);

	}//fin del metodo main

}//fin de la clase "Tienda"
