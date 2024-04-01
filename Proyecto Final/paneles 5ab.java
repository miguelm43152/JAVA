import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.event.*; //para eventos de JCheckBox
import com.toedter.calendar.*;		//para usar JDateChooser
import java.text.*;
import java.util.*;

//tablas de consulta de usuarios
/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel5 	     *******************************************************/
/***********************************************************************************************************************************/

class Panel5 extends JPanel implements ActionListener{

	static final int ANCHO_PANEL = 1265, ALTO_PANEL = 400, ANCHO_E = 170, ALTO_E = 20;
	JPanel mipanel = new JPanel();
	JScrollPane panelDeslizante = new JScrollPane( mipanel /* 22, 32 */);
	JButton[] encabezados = new JButton[7];
	String[] textoEncabezados = { "ID", "Nombre", "Domicilio", "Telefono", "Correo", "Edad", "Genero" };
	JButton actualizar = new JButton("Actualizar");
	ArrayList < ArrayList < JButton> > botonesInfo = new ArrayList < ArrayList < JButton> > (35);

	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 7, 1); //Constructor de la clase

	int n = 0, m = 0;

	static String comunicacion = " ";
	static int IDPersona = 0;

	Panel5(){

		mipanel.setLayout( null);
		setLayout( null);
		setBackground( Color.CYAN);
		mipanel.setBackground( Color.CYAN);
		setBackground( Color.CYAN);
		setBounds( 0 , 150 , ANCHO_PANEL + 20, ALTO_PANEL );
		mipanel.setPreferredSize( new Dimension( ANCHO_PANEL - 20, ALTO_PANEL + 50) );
		setVisible( true );

		panelDeslizante.setBounds( 0 , 0 , ANCHO_PANEL + 20 , ALTO_PANEL );
		panelDeslizante.getVerticalScrollBar().setUnitIncrement(15);
		actualizar.setBounds( el.getPosicionX(0), 0, ANCHO_E, ALTO_E );
		mipanel.add( actualizar);
		actualizar.addActionListener( this);

		add( panelDeslizante);

		imprimeEncabezado();
		consultaU();

	}//fin del constructor

	public void imprimeEncabezado(){

		for(int i = 0 ; i < textoEncabezados.length ; i++ ){
			encabezados[i] = new JButton( textoEncabezados[i] );
			encabezados[i].setBounds( el.getPosicionX(i), 30, ANCHO_E, ALTO_E );
			mipanel.add( encabezados[i]);
		}//fin for

	} // fin de metodo imprimeEncabezado

	public void consultaU( ){

		ManejoBD conector = new ManejoBD();

		botonesInfo.clear();
		conector.tablaUsuarios( botonesInfo );

		for( int i = 0 ; i < botonesInfo.size() ; i++ ){

				botonesInfo.get(i).get(0).addActionListener( this);

			for( int j = 0 ; j < botonesInfo.get(i).size() ; j++){

				botonesInfo.get(i).get(j).setBounds( el.getPosicionX(j), (i+2)*30, ANCHO_E, ALTO_E );
				mipanel.add( botonesInfo.get(i).get(j) );

			}//fin for anidado
		}//fin for

	}// fin de metodo consultaU

	public void coincidenciaU( int columna , String coincidencia ){

		ManejoBD conector = new ManejoBD();

		botonesInfo.clear();
		conector.coincidenciaU( botonesInfo, columna, coincidencia );

		for( int i = 0 ; i < botonesInfo.size() ; i++ ){

				botonesInfo.get(i).get(0).addActionListener( this);

			for( int j = 0 ; j < botonesInfo.get(i).size() ; j++){

				botonesInfo.get(i).get(j).setBounds( el.getPosicionX(j), (i+2)*30, ANCHO_E, ALTO_E );
				mipanel.add( botonesInfo.get(i).get(j) );

			}//fin for anidado
		}//fin for

	}// fin de metodo consulta

	public void coincidenciaU( int columna , int coincidencia ){

		ManejoBD conector = new ManejoBD();

		botonesInfo.clear();
		conector.coincidenciaU( botonesInfo, columna, coincidencia );

		for( int i = 0 ; i < botonesInfo.size() ; i++ ){

				botonesInfo.get(i).get(0).addActionListener( this);

			for( int j = 0 ; j < botonesInfo.get(i).size() ; j++){

				botonesInfo.get(i).get(j).setBounds( el.getPosicionX(j), (i+2)*30, ANCHO_E, ALTO_E );
				mipanel.add( botonesInfo.get(i).get(j) );

			}//fin for anidado
		}//fin for

	}// fin de metodo consulta

	public void coincidenciaU( int columna , boolean coincidencia ){

		ManejoBD conector = new ManejoBD();

		botonesInfo.clear();
		conector.coincidenciaU( botonesInfo, columna, coincidencia );

		for( int i = 0 ; i < botonesInfo.size() ; i++ ){

				botonesInfo.get(i).get(0).addActionListener( this);

			for( int j = 0 ; j < botonesInfo.get(i).size() ; j++){

				botonesInfo.get(i).get(j).setBounds( el.getPosicionX(j), (i+2)*30, ANCHO_E, ALTO_E );
				mipanel.add( botonesInfo.get(i).get(j) );

			}//fin for anidado
		}//fin for

	}// fin de metodo consulta

	public void actionPerformed( ActionEvent ev){

		int id = 0;
		boolean idActivado = false;

		if( ev.getSource() == actualizar ){
			reImprimirTabla();
			consultaU();
		}

		for( int i = 0 ; i < botonesInfo.size() ; i++ )
			if( ev.getSource() == botonesInfo.get(i).get(0) ){
				id = Integer.parseInt(  botonesInfo.get(i).get(0).getText()  );
				idActivado = true;
				break;
			}//fin for anidado

		if( idActivado == true){

			comunicacion = "CU";
			IDPersona = id;
			Marco marco = new Marco();
		}

	}//fin de metodo actionPerformed

	void reImprimirTabla(){
			mipanel.removeAll();
			mipanel.add( actualizar);
			mipanel.setBackground(Color.BLUE);
			mipanel.setBackground(Color.CYAN);
			imprimeEncabezado();
	}// fin de metodo reImprimirTabla

}// fin de la clase Panel5

/***********************************************************************************************************************************/
/*****************************************  Inicio CLASE Panel5B	     *******************************************************/
/***********************************************************************************************************************************/

class Panel5B extends JPanel implements ChangeListener{

	static final int ANCHO_PANEL = 1300, ALTO_PANEL = 150, ANCHO_E = 180, ALTO_E = 30, ANCHO_IMAGEN = 128, ALTO_IMAGEN = 134;

	JRadioButton[] botones = new JRadioButton[6];
	ButtonGroup[] grupo = new ButtonGroup[2];
	//JLabel etiqueta = new JLabel( "Consulta por tipo");
	JTextField campo = new JTextField( );
	JSpinner campoEdad = new JSpinner();
	JRadioButton[] genero = new JRadioButton[2];
	JButton[] busqueda = new JButton[2];
	ImageIcon imagen = new ImageIcon("logo.PNG");
	JLabel etiquetaImagen = new JLabel(imagen);

	String[] textoBotones = { "Nombre", "Telefono", "Domicilio", "Correo", "Edad" , "Genero" };
	String[] textoGenero = { "Mujer", "Hombre" };
	
	String[] textoBusqueda = { "Buscar" , "Avanzada"  };

	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 4, 4); //Constructor de la clase

	Panel5B(){

		setBackground(Color.CYAN);
		setLayout( null);
		setBounds( 0 , 0 , ANCHO_PANEL, ALTO_PANEL );
		setVisible( true );
		
		etiquetaImagen.setBounds( el.getPosicionX(0) , el.getPosicionY( 0 ) , ANCHO_IMAGEN , ALTO_IMAGEN );
		//etiqueta.setBounds( el.getPosicionX(0) , el.getPosicionY( 0 ) , ANCHO_E , ALTO_E );
		add( etiquetaImagen);

		campo.setBounds( el.getPosicionX(1) , el.getPosicionY( 0 ) , ANCHO_E , ALTO_E );
		campo.setEnabled( false);
		add( campo);

		campoEdad.setBounds( el.getPosicionX(1) + ANCHO_E/3 , el.getPosicionY( 1 ) , ANCHO_E / 3 , ALTO_E );
		add( campoEdad);
		campoEdad.setEnabled( false);

		for(int k = 0 ; k < 2 ; k++)
			grupo[k] = new ButtonGroup();

		for(int k = 0 ; k < 2 ; k++){
			busqueda[k] = new JButton( textoBusqueda[k]); 
			busqueda[k].setBounds( el.getPosicionX( 2 + k ) , el.getPosicionY( 3 ) , ANCHO_E , ALTO_E );
//			add( busqueda[k]);
		}

		for(int k = 0 ; k < 2 ; k++){
			genero[k] = new JRadioButton( textoGenero[k]); 
			genero[k].setBounds( el.getPosicionX( 1 ) , el.getPosicionY( k + 2 ) , ANCHO_E , ALTO_E );
			add( genero[k]);
			grupo[1].add( genero[k] );
			genero[k].setEnabled( false);
		}

		//int i = 0;

		for( int i = 0, n = 0 ; i < 2 ; i++ )
			for(int j = 0 ; j < 3 ; j++, n++){

			botones[n] = new JRadioButton( textoBotones[n]); 
			botones[n].setBounds( el.getPosicionX(i + 2) , el.getPosicionY( j ) , ANCHO_E , ALTO_E );
			add( botones[n]);
			grupo[0].add( botones[n] );
			botones[n].addChangeListener( this);

		}//fin de for anidado

	}//fin del constructor

	/****************************************** manejo de eventos *****************************************************************/

	public void stateChanged( ChangeEvent ev){

		for( int i = 0 ; i < 4 ; i++){
			if( botones[i].isSelected() ){	campo.setEnabled( true);  break; }
			else 				campo.setEnabled( false);
		}

		if( botones[4].isSelected() )	campoEdad.setEnabled( true);
		else 				campoEdad.setEnabled( false);

		if( botones[5].isSelected() )	for(int i = 0 ; i < 2 ; i++ ) genero[i].setEnabled( true);
		else	for(int i = 0 ; i < 2 ; i++ ) genero[i].setEnabled( false);

	}//fin de metodo StateChange
	
	public void desmarcar(){
		for( int i = 0 ; i < botones.length ; i++ ) grupo[0].clearSelection();
	}

}// fin de la clase Panel5B