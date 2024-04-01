import javax.swing.*;
import java.awt.event.*;

public class Encuesta extends JFrame implements ActionListener{

//CONSTANTES DE LONGITUDES
	static final int ANCHO_VENTANA = 900, ALTO_VENTANA = 650, ANCHO_E = 150, ALTO_E = 30, ELEMENTOS_X = 3, ELEMENTOS_Y = 5;
	static final int ANCHO_PANTALLA = 1920, ALTO_PANTALLA = 1080;

	JLabel[] etiquetas = new JLabel[5];
	String[] etiquetasTxt = {"Municipio", "Genero", "Horario", "Nivel de estudios", "Gustos" };

	String[][] opcionesTxt = {

	{"Municipio", "Guadalajara", "Zapopan", "Tonala", "El salto", "Tenamaxtlan", "Degollado", "Tomatlan", "Arandas"},	//municipio
	{"Masculino", "Femenino"},	//genero
	{"Matutino", "Vespertino", "Nocturno"},	//horario
	{"Licenciatura", "Maestria", "Doctorado"},	//nivel de estudios
	{"Leer", "Correr", "Bicicleta"},	//gustos

	};

	JComboBox <String> municipio = new JComboBox <String>();
	ButtonGroup[] botonesDeRadioGroup = new ButtonGroup[2];
	JRadioButton[] genero = new JRadioButton[ opcionesTxt[1].length ];
	JRadioButton[] horario = new JRadioButton[ opcionesTxt[2].length ];
	JCheckBox[] nivelEstudios = new JCheckBox[ opcionesTxt[3].length ];
	JCheckBox[] gustos = new JCheckBox[ opcionesTxt[4].length ];
	JTextArea campo = new JTextArea();
	JButton confirmacion = new JButton("Terminar Encuesta");

	//objeto para elementos general
	OrdenMatriz e = new OrdenMatriz( ANCHO_E, ALTO_E,  ANCHO_VENTANA, ALTO_VENTANA, 5, 4); //Constructor de la clase


/****************************************************************************************************************************************/
/*********************************		INICIO DEL CONSTRUCTOR DE CLASE		  ***********************************************/
/****************************************************************************************************************************************/

		Encuesta(){

			setLayout(null);
			setDefaultCloseOperation( EXIT_ON_CLOSE );
			setTitle("Encuesta");
			e.setOffset( 0, 20, 0, 500);

			for(int i = 0 ; i < 5 ; i++ ){

				etiquetas[i] = new JLabel( etiquetasTxt[i] );
				etiquetas[i].setBounds( e.getPosicionX(i), e.getPosicionY(0), e.getW(), e.getH() );
				add( etiquetas[i]);
	
			}//fin de for

			for(int i = 0 ; i < opcionesTxt[0].length ; i++ )
				municipio.addItem( opcionesTxt[0][i] );
			
			municipio.setBounds( e.getPosicionX(0), e.getPosicionY(1), e.getW(), e.getH() );
			add(municipio);



	//configurando grupos de botones
			for( int i = 0 ; i < 2 ; i++ ){
				botonesDeRadioGroup[i] = new ButtonGroup();
			}


	//inicio para configuracion y agnadiendo botones de genero a ventana

			for(int i = 0 ; i < opcionesTxt[1].length ; i++ ){

				genero[i] = new JRadioButton( opcionesTxt[1][i] );
				genero[i].setBounds( e.getPosicionX(1), e.getPosicionY(i + 1), e.getW(), e.getH() );
				add(genero[i]);

			}

			for(int i = 0 ; i < opcionesTxt[1].length ; i++ ){
				botonesDeRadioGroup[0].add( genero[i]);
			}

	//fin para configuracion y agnadiendo botones de genero a ventana


	//inicio para configuracion y agnadiendo botones de horario a ventana

			for(int j = 0 ; j < opcionesTxt[2].length ; j++ ){
				horario[j] = new JRadioButton( opcionesTxt[2][j] );
				horario[j].setBounds( e.getPosicionX(2), e.getPosicionY(j + 1), e.getW(), e.getH() );
				add( horario[j]);
			}


			for(int i = 0 ; i < opcionesTxt[2].length ; i++ ){
				botonesDeRadioGroup[1].add( horario[i]);
			}

	//fin para configuracion y agnadiendo botones de horario a ventana


	//inicio para configuracion y agnadiendo botones de nivel de estudio a ventana

			for(int j = 0 ; j < opcionesTxt[3].length ; j++ ){
				nivelEstudios[j] = new JCheckBox( opcionesTxt[3][j] );
				nivelEstudios[j].setBounds( e.getPosicionX(3), e.getPosicionY(j + 1), e.getW(), e.getH() );
				add( nivelEstudios[j]);
			}

	//fin para configuracion y agnadiendo botones de nivel de estudio a ventana

	//inicio para configuracion y agnadiendo botones de nivel de estudio a ventana

			for(int j = 0 ; j < opcionesTxt[4].length ; j++ ){
				gustos[j] = new JCheckBox( opcionesTxt[4][j] );
				gustos[j].setBounds( e.getPosicionX(4), e.getPosicionY(j + 1), e.getW(), e.getH() );
				add( gustos[j]);
			}

	//fin para configuracion y agnadiendo botones de nivel de estudio a ventana

			campo.setBounds( e.getPosicionX(1), e.getPYLast() + 20 , (e.getPosicionX(4) - 100 )  , 300 );
			campo.setEditable( false);
			add( campo);

			confirmacion.setBounds( e.getPosicionX(0), e.getPYLast(),  e.getW(), e.getH() );
			add( confirmacion);

			confirmacion.addActionListener(this);
	
	}//fin del metodo constructor de la clase

/****************************************************************************************************************************************/
/*********************************		FIN DEL CONSTRUCTOR DE CLASE		  ***********************************************/
/****************************************************************************************************************************************/
/*--------------------------------------------------------------------------------------------------------------------------------------*/
/****************************************************************************************************************************************/
/*********************************		INICIO DEL MANEJO DE EVENTOS		  ***********************************************/
/****************************************************************************************************************************************/

		public void actionPerformed(ActionEvent e){
			if( e.getSource() == confirmacion ){

				//guardando datos municipio en cadena
				String cadena = "*Municipio*" + "\n";
				cadena += municipio.getSelectedItem().toString() + "\n";

				//guardando datos de genero en cadena
				for(int i = 0 ; i < genero.length ; i++ ){
					if( genero[i].isSelected()){
						cadena += "\n" + "*Genero*" + "\n";
						cadena += genero[i].getText() + "\n";
					}
				}

				//guardando datos de horario en cadena
				for(int i = 0 ; i < horario.length ; i++ ){
					if( horario[i].isSelected()){
						cadena += "\n" + "*Horario*" + "\n";
						cadena += horario[i].getText() + "\n";
					}
				}


				//guardando datos de nivel de estudios en cadena
				boolean b = true;
				for(int i = 0  ; i < nivelEstudios.length ; i++ ){
					if( nivelEstudios[i].isSelected()){
						if( b ){
							cadena += "\n" + "*Nivel de estudios*" + "\n";
							b = false;
						}
						cadena += nivelEstudios[i].getText() + "\n";
					}
				}


				//guardando datos de gustos en cadena
				b = true;
				for(int i = 0  ; i < gustos.length ; i++ ){
					if( gustos[i].isSelected()){
						if( b ){
							cadena += "\n" + "*Gustos*" + "\n";
							b = false;
						}
						cadena += gustos[i].getText() + "\n";
					}
				}

				campo.setText( cadena );
			}
		}

/****************************************************************************************************************************************/
/*********************************		FIN DEL MANEJO DE EVENTOS		  ***********************************************/
/****************************************************************************************************************************************/

	public static void main(String[] args){

		OrdenMatriz v = new OrdenMatriz( ANCHO_VENTANA , ALTO_VENTANA, ANCHO_PANTALLA, ALTO_PANTALLA, 1, 1); //Constructor de la clase
		Encuesta ventana = new Encuesta();
		ventana.setBounds( v.getPosicionX(), v.getPosicionY(), v.getW(), v.getH() );
		ventana.setVisible(true);

	}//fin del metodo main

}//fin de la clase "Encuesta"
