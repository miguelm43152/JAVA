import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.event.*; //para eventos de JCheckBox
import com.toedter.calendar.*;		//para usar JDateChooser
import java.text.*;
import java.util.*;

class Panel1 extends JPanel{

	static final int ANCHO_PANEL = 450, ALTO_PANEL = 300;

	JLabel[] etiquetas = new JLabel[5];
	JTextField[] campos = new JTextField[4];
	JSpinner selectorEdad = new JSpinner();
	JRadioButton[] genero = new JRadioButton[2];
	ButtonGroup grupoBoton = new ButtonGroup();
	JLabel mensajeError = new JLabel( "" );

	String[] texto = { "Nombre:", "Domicilio:", "Telefono:", "Correo:", "Edad:" };
	String[] tRadio = { "Mujer", "Hombre" };

	OrdenMatriz el = new OrdenMatriz( Marco.ANCHO_E, Marco.ALTO_E, ANCHO_PANEL, ALTO_PANEL, 2, 7); //Constructor de la clase
	Panel1(){

		setBackground(Color.YELLOW);
		setVisible( true );
		setLayout( null);

		setBounds( 0, 0 , ANCHO_PANEL, ALTO_PANEL );

		for(int i = 0 ; i < texto.length ; i++){
			etiquetas[i] = new JLabel( texto[i]);
			etiquetas[i].setBounds( el.getPosicionX(0), el.getPosicionY(i), Marco.ANCHO_E, Marco.ALTO_E );
			//etiquetas[i].addKeyListener( this);
			add( etiquetas[i]);
		}

		for(int i = 0 ; i < campos.length ; i++){
			campos[i] = new JTextField();
			campos[i].setBounds( el.getPosicionX(1), el.getPosicionY(i), Marco.ANCHO_E, Marco.ALTO_E );
			//campos[i].addKeyListener( this);
			add( campos[i]);
		}

		selectorEdad.setValue( 20);
		selectorEdad.setBounds( el.getPosicionX( 1 ) + Marco.ANCHO_E/ 3, el.getPosicionY( texto.length - 1 ), Marco.ANCHO_E/3, Marco.ALTO_E );
		add( selectorEdad);

		for(int i = 0 ; i < genero.length ; i++ ){
			genero[i] = new JRadioButton( tRadio[i] );
			grupoBoton.add( genero[i]);
			genero[i].setBounds( el.getPosicionX( i ), el.getPosicionY( texto.length ), Marco.ANCHO_E, Marco.ALTO_E );
			add( genero[i] );
		}

		mensajeError.setBounds( el.getPosicionX( 0 ), el.getPosicionY( texto.length + 1 ), Marco.ANCHO_E*3, Marco.ALTO_E );
		add( mensajeError );

	}//fin del constructor

	public void modificaEstadoActivo( boolean b ){

		boolean bandera = b;

		for( int i = 0 ; i < campos.length ; i++)
			campos[i].setEnabled( bandera);

		selectorEdad.setEnabled( bandera);

		for( int i = 0 ; i < genero.length; i++ )
			genero[i].setEnabled( bandera);

	}//fin de metodo desactivar

	public void activar(  ){ modificaEstadoActivo( true ); }
	public void desactivar(  ){ modificaEstadoActivo( false ); }

	public void getUsuario( int idp){

		ManejoBD conector = new ManejoBD();

		String datos[] = new String[6];

			conector.getUsuario( datos, idp );

			for(int i = 0 ; i < 4 ; i++ )
				campos[i].setText( datos[i]);

			selectorEdad.setValue( Integer.parseInt( datos[4]) );

			if( datos[5].equals("true") ){
				genero[1].setSelected(true);
			}else
				genero[0].setSelected(true);

	}//fin de metodo getUsuario

}// fin de la clase Panel1

/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel2 	     *******************************************************/
/***********************************************************************************************************************************/

class Panel2 extends JPanel{

	static final int ANCHO_PANEL = 450, ALTO_PANEL = 300;
	JDateChooser fecha;
	JLabel[] etiquetas = new JLabel[2];
	JTextField[] campos = new JTextField[2];
	JComboBox <String> hora = new JComboBox <String> ();
	SimpleDateFormat formateador = new SimpleDateFormat( "dd/MM/yyyy" );
	String[] textoHora = { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "18:00", "19:00", "20:00", "21:00", "22:00" };
	String[] texto = { "Fecha:", "Hora:"};

	OrdenMatriz el = new OrdenMatriz( Marco.ANCHO_E, Marco.ALTO_E, ANCHO_PANEL, ALTO_PANEL, 1, 4); //Constructor de la clase

	Panel2(){

		setBackground(Color.GREEN);
		setLayout( null);

		setBounds( ANCHO_PANEL , 0 , ANCHO_PANEL, ALTO_PANEL );

		for(int i = 0 ; i < etiquetas.length ; i++){
			etiquetas[i] = new JLabel( texto[i]);
			etiquetas[i].setBounds( el.getPosicionX(0), el.getPosicionY(i*2), Marco.ANCHO_E, Marco.ALTO_E );
			add( etiquetas[i]);
		}

		for( int i = 0 ; i < textoHora.length ; i++ ){
			hora.addItem( textoHora[i]);
			hora.setEditable( true);
			hora.setSelectedItem( "Elija una opcion");
			hora.setEditable( false);
		}

		fecha = new JDateChooser();
		fecha.setBounds( el.getPosicionX(0), el.getPosicionY( 1 ), Marco.ANCHO_E, Marco.ALTO_E );
		add( fecha);

		hora.setBounds( el.getPosicionX(0), el.getPosicionY( 3 ), Marco.ANCHO_E, Marco.ALTO_E );
		add( hora);

		setVisible( true );

	}//fin del constructor

	void desactivar(){	cambiarEstadoActivado(false);	}
	void activar(){		cambiarEstadoActivado(true);	}
	void cambiarEstadoActivado( boolean b ){

		hora.setEnabled( b);
		fecha.setEnabled( b);

	}// fin de metodo cambiarEstadoActivado

	void getCita( int idc){

		String[] datos = new String[4];
		ManejoBD conector = new ManejoBD( );

		conector.getCita( datos, idc);
		System.out.println( datos[2] );

		try{
			fecha.setDate( formateador.parse( datos[0] ) );
		}catch( ParseException e ){ /* nada */	}

		for( int i = 0 ; i < textoHora.length ; i++)
			if( datos[1].equals( textoHora[i] ) ){
					hora.setSelectedItem( datos[1] );
				break;
			}

	}//fin de metodo getCita

}// fin de la clase Panel2

/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel3 	     *******************************************************/
/***********************************************************************************************************************************/

class Panel3 extends JPanel implements ChangeListener{

	static final int ANCHO_PANEL = 450, ALTO_PANEL = 300, ANCHO_E = 120, ALTO_E = 30;
	JComboBox[] servicio = new  JComboBox[3];
	JLabel[] etiquetas = new JLabel[3];	//con texto de "Servicio:"
	String[] textoCombo = { "Tintes", "Cortes", "Maquillaje", "Faciales", "Pedicure", "Manicure", "Ondulados", "Laciados", "Depilaciones" };
	JCheckBox[] extra = new JCheckBox[2];
	String[] textoCheckBox = { "2do. Servicio", "3er. Servicio" };
	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 3, 3); //Constructor de la clase
	JLabel mensajeError = new JLabel("");

	Panel3(){

		setBackground(Color.WHITE);
		setLayout( null);
		setBounds( 0 , ALTO_PANEL , ANCHO_PANEL, ALTO_PANEL );
		setVisible( true );

		for(int i = 0 ; i < servicio.length ; i++ ){

			servicio[i] = new JComboBox();

			servicio[i].setEditable( true);
			servicio[i].setSelectedItem( "Elija una opcion");
			servicio[i].setEditable( false);

			for(int j = 0 ; j < textoCombo.length ; j++ )
				servicio[i].addItem( textoCombo[j] );
			servicio[i].setBounds( el.getPosicionX(1), el.getPosicionY(i), ANCHO_E, ALTO_E );
			add( servicio[i] );

		}

		for(int i = 0 ; i < 3 ; i++ ){
			etiquetas[i] = new JLabel( "Servicio:" );
			etiquetas[i].setBounds( el.getPosicionX(0), el.getPosicionY(i), ANCHO_E, ALTO_E );
			add( etiquetas[i]);
			add( servicio[i]);
			if( i > 0 )
				servicio[i].setEnabled( false);
		}

		for(int i = 0 ; i < 2 ; i++){
			extra[i] = new JCheckBox( textoCheckBox[i] );
			extra[i].setBounds( el.getPosicionX(2), el.getPosicionY(i + 1), ANCHO_E, ALTO_E );
			add( extra[i]);
		}

		extra[1].setEnabled( false);

		for( int i = 0 ; i < 2 ; i++ ){	extra[i].addChangeListener( this); }
		cambiarEstadoVisible(false);

		mensajeError.setBounds( el.getPosicionX(0), el.getPosicionY(1), ANCHO_E*2, ALTO_E );
		add( mensajeError);
	
	}//fin del constructor

	void desactivar(){	cambiarEstadoActivado( false);	}// fin de metodo desactivar

	void activar(){		cambiarEstadoActivado( true);	}//fin de metodo activar

	void cambiarEstadoActivado( boolean b){

		for(int i = 0 ; i < servicio.length ; i++ )
			servicio[i].setEnabled( b);
		for(int i = 0 ; i < extra.length ; i++ )
			extra[i].setEnabled( b);
		
	}//fin de metodo cambiarEstadoActivado


	void ocultar(){	cambiarEstadoVisible( false);	}
	void mostrar(){	cambiarEstadoVisible(true);	}

	void cambiarEstadoVisible( boolean b){

		for(int i = 1 ; i < servicio.length ; i++ )
			servicio[i].setVisible( b);
		for(int i = 0 ; i < extra.length ; i++ )
			extra[i].setVisible( b);
		for(int i = 1 ; i < etiquetas.length; i++)
			etiquetas[i].setVisible( b);

	}//fin de metodo cambiarEstadoVisible

	public void stateChanged( ChangeEvent ev){

			if( extra[0].isSelected() ){
				servicio[1].setEnabled( true);
				extra[1].setEnabled( true);

				if( extra[1].isSelected() )
					servicio[2].setEnabled( true);
				else
					servicio[2].setEnabled( false);		
			}else{
				extra[1].setEnabled( false);
				servicio[1].setEnabled( false);
				servicio[2].setEnabled( false);
			}

	}// fin de metodo stateChanged

	void getCita( int idc){

		String[] datos = new String[4]; 
		ManejoBD conector = new ManejoBD( );

		conector.getCita( datos, idc);
//		System.out.println( datos[2] );
		
		for( int i = 0 ; i < textoCombo.length ; i++)
			if( datos[2].equals( textoCombo[i] ) ){
					servicio[0].setSelectedItem( datos[2] );
				break;
			}

	}//fin de metodo getCita
}// fin de la clase Panel3