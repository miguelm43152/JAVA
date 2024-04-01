import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.event.*; //para eventos de JCheckBox
import com.toedter.calendar.*;		//para usar JDateChooser
import java.text.*;
import java.util.*;

/* Paneles para consulta y filtro de citas */

/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel6 	     *******************************************************/
/***********************************************************************************************************************************/

class Panel6 extends JPanel implements ActionListener{
	
	static final int ANCHO_PANEL = 1265, ALTO_PANEL = 400, ANCHO_E = 180, ALTO_E = 20;
	JPanel mipanel = new JPanel();
	JScrollPane panelDeslizante = new JScrollPane(mipanel);
	JButton[] encabezados = new JButton[5];
	String[] textoEncabezados = { "ID CITA", "ID PERSONA", "Fecha", "Hora", "Servicio" };
	JButton actualizar = new JButton("Actualizar");

	ArrayList < ArrayList < JButton> > botonesInfo = new ArrayList < ArrayList < JButton> > (35);
	ArrayList < JButton> informacion = new ArrayList < JButton> (10);

	static String comunicacion = " ";
	static int IDCitaPersona = 0;

	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 5, 1); //Constructor de la clase

	Panel6(){

		mipanel.setLayout( null);
		setLayout( null);
		setBackground( Color.ORANGE);
		mipanel.setBackground(Color.ORANGE);
		setBackground(Color.ORANGE);
		setBounds( 0 , 150 , ANCHO_PANEL + 20, ALTO_PANEL );
		mipanel.setPreferredSize( new Dimension( ANCHO_PANEL - 50, ALTO_PANEL + 50) );
		setVisible( true );

		panelDeslizante.getVerticalScrollBar().setUnitIncrement(15);
		actualizar.setBounds( el.getPosicionX(0), 0, ANCHO_E, ALTO_E );
		mipanel.add( actualizar);
		actualizar.addActionListener(this);

		imprimeEncabezado();

		panelDeslizante.setBounds( 0 , 0 , ANCHO_PANEL - 15 , ALTO_PANEL - 30 );

		add( panelDeslizante);

		consultaCita();

	}//fin del constructor

	public void imprimeEncabezado(){
		for(int i = 0 ; i < textoEncabezados.length ; i++ ){
			encabezados[i] = new JButton( textoEncabezados[i] );
			encabezados[i].setBounds( el.getPosicionX(i), 30, ANCHO_E, ALTO_E );
			mipanel.add( encabezados[i]);
		}//fin for
	}

	public void consultaCita( ){

		ManejoBD conector = new ManejoBD();

			botonesInfo.clear();
			conector.tablaCitas( botonesInfo );

			for( int i = 0 ; i < botonesInfo.size() ; i++ ){

					botonesInfo.get(i).get(0).addActionListener( this);
					botonesInfo.get(i).get(1).addActionListener( this);

				for( int j = 0 ; j < botonesInfo.get(i).size() ; j++){

					botonesInfo.get(i).get(j).setBounds( el.getPosicionX(j), (i+2)*30, ANCHO_E, ALTO_E );
					mipanel.add( botonesInfo.get(i).get(j) );

				}//fin for anidado
			}//fin for

	}// fin de metodo consultaCita

	public void coincidenciaC( String fecha, String servicio, String hora){

		ManejoBD conector = new ManejoBD();
		//		Panel5 panelito = new Panel5();
		
			botonesInfo.clear();
			conector.coincidenciaC( botonesInfo, fecha, servicio, hora );

			for( int i = 0 ; i < botonesInfo.size() ; i++ ){

					botonesInfo.get(i).get(0).addActionListener( this);
					botonesInfo.get(i).get(1).addActionListener( this);

				for( int j = 0 ; j < botonesInfo.get(i).size() ; j++){

					botonesInfo.get(i).get(j).setBounds( el.getPosicionX(j), (i+2)*30, ANCHO_E, ALTO_E );
					mipanel.add( botonesInfo.get(i).get(j) );

				}//fin for anidado
			}//fin for

	}//fin de metodo coincidenciaC

	void reImprimirTabla(){
		mipanel.removeAll();
		mipanel.add( actualizar);
		mipanel.setBackground(Color.BLUE);
		mipanel.setBackground(Color.ORANGE);
		imprimeEncabezado();
	}// fin de metodo reImprimirTabla

	public void actionPerformed( ActionEvent ev){

		int id = 0;
		boolean bandera = false; // falso si es una cita, true si es una persona
		boolean idActivado = false;

//		System.out.println( 		ev.getSource().toString());

/* 		if( ev.getSource() == actualizar ){
			reImprimirTabla();
			consultaCita();
		} */

		// imprimiendo la lista
		for( int i = 0 ; i < botonesInfo.size() ; i++ ){

			if( ev.getSource() == botonesInfo.get(i).get(0) ){
				id = Integer.parseInt(  botonesInfo.get(i).get(0).getText()  );
				bandera = false;
				idActivado = true;
				break;
			}

			if( ev.getSource() == botonesInfo.get(i).get(1) ){
				id = Integer.parseInt(  botonesInfo.get(i).get(1).getText()  );
				bandera = true;
				idActivado = true;
				break;
			}

		}//fin for anidado

		if( idActivado == true){
			if( bandera == true){
				comunicacion = "CU";
				IDCitaPersona = id;
			}else{
				comunicacion = "CC";
				IDCitaPersona = id;
				System.out.println( "El id es: "  +IDCitaPersona );
			}
			Marco marco = new Marco();
			bandera = false;
			IDCitaPersona = 0;
		}

	}//fin de metodo actionPerformed

}// fin de la clase Panel6

/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel6B	     *******************************************************/
/***********************************************************************************************************************************/

class Panel6B extends JPanel implements ChangeListener{

	static final int ANCHO_PANEL = 1300, ALTO_PANEL = 150, ANCHO_E = 180, ALTO_E = 30, ANCHO_IMAGEN = 128, ALTO_IMAGEN = 134;

	JCheckBox[] botones = new JCheckBox[3];
	//JLabel etiqueta = new JLabel( "Consulta por tipo");
	JButton[] busqueda = new JButton[2];
	JDateChooser fecha = new JDateChooser();
	JComboBox[] combox  = { new JComboBox(), new JComboBox()};
	ImageIcon imagen = new ImageIcon("logo.PNG");
	JLabel etiquetaImagen = new JLabel(imagen);

	String[][] textoCombo = {
	{ "Tintes", "Cortes", "Maquillaje", "Faciales", "Pedicure", "Manicure", "Ondulados", "Laciados", "Depilaciones" },
	{ "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "18:00", "19:00", "20:00", "21:00", "22:00" },
	};

	String[] textoBotones = { "Fecha", "Tipo de servicio", "Hora"};
	
	String[] textoBusqueda = { "Buscar" , "Avanzada"  };

	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 4, 4); //Constructor de la clase

	Panel6B(){

		setBackground(Color.ORANGE);
		setLayout( null);
		setBounds( 0 , 0 , ANCHO_PANEL, ALTO_PANEL );
		setVisible( true );
	
		etiquetaImagen.setBounds( el.getPosicionX(0) , el.getPosicionY( 0 ) , ANCHO_IMAGEN , ALTO_IMAGEN );
		//etiqueta.setBounds( el.getPosicionX(0) , el.getPosicionY( 0 ) , ANCHO_E , ALTO_E );
		add( etiquetaImagen);

		fecha.setBounds( el.getPosicionX(1) , el.getPosicionY( 0 ) , ANCHO_E , ALTO_E );
		fecha.setEnabled( false);
		add( fecha);

		for( int i = 0; i < 2 ; i++ ){
			botones[i] = new JCheckBox( textoBotones[i]); 
			botones[i].setBounds( el.getPosicionX(2) , el.getPosicionY( i ) , ANCHO_E , ALTO_E );
			botones[i].addChangeListener( this);
			add( botones[i]);
		}

		for( int i = 0; i < textoCombo.length ; i++ ){
			combox[i].setBounds( el.getPosicionX(1) , el.getPosicionY( i + 1 ) , ANCHO_E , ALTO_E );
			combox[i].setEnabled( false);
			add( combox[i] );

			combox[i].setEditable( true);
			combox[i].setSelectedItem( "Elija una opcion");
			combox[i].setEditable( false);

			for( int j = 0 ; j < textoCombo[i].length ; j++ ){
				combox[i].addItem( textoCombo[i][j] );
			}
		}

		botones[2] = new JCheckBox( textoBotones[2]); 
		botones[2].setBounds( el.getPosicionX(3) , el.getPosicionY( 0 ) , ANCHO_E , ALTO_E );
		botones[2].addChangeListener( this);
		add( botones[2]);

		//botones buscar y busqueda avanzada
		for(int k = 0 ; k < 2 ; k++){
			busqueda[k] = new JButton( textoBusqueda[k]); 
			busqueda[k].setBounds( el.getPosicionX( 2 + k ) , el.getPosicionY( 3 ) , ANCHO_E , ALTO_E );
//			add( busqueda[k]);
		}

	}//fin del constructor

	public void desmarcar(){
		for( int i = 0 ; i < botones.length ; i++){ botones[i].setSelected(false); }
	}// fin de metodo desmarcar

/**************************************** Metodos ChangeListener ********************************************************/

	public void stateChanged( ChangeEvent ev){

		if( botones[0].isSelected() )
			fecha.setEnabled( true);
		else 
			fecha.setEnabled( false);

		if( botones[1].isSelected() ){
			combox[0].setEnabled( true);
		}else
			combox[0].setEnabled( false);

		if( botones[2].isSelected() )
			combox[1].setEnabled( true);
		else
			combox[1].setEnabled( false);

	}

}// fin de la clase Panel6B