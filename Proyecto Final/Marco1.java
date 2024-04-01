import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Color;
import java.util.Date;			//para formatear la fecha
import java.text.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/*************************** esta es la clase principal del proyecto ******************************************************/
/*************************** esta es la clase principal del proyecto ******************************************************/
/*************************** esta es la clase principal del proyecto ******************************************************/


public class Marco1 extends PreMarco implements ActionListener, KeyListener, ChangeListener, ItemListener, PropertyChangeListener, MouseListener{

	static final int ANCHO_PANEL = 1300, ALTO_PANEL = 650, ANCHO_E = 170, ALTO_E = 20;
	Panel5 pan5 = new Panel5();
	Panel5B pan5b = new Panel5B();
	Panel6 pan6 = new Panel6();
	Panel6B pan6b = new Panel6B();
	Inicio inicio = new Inicio();
	Color colorInicio = new Color(245,227,201);

	JMenuBar barraMenu = new JMenuBar();
	JMenu[] menu = new JMenu[3];
	JMenuItem[][] objetoMenu = new JMenuItem[3][3];
	String[] textoMenu = { "Consultas", "Registrar", "Mas"};
	String[][] textoObjMenu = {
	{"Usuarios", "Citas"},
	{"Usuarios", "Completo" },
	{"Inicio", "Cerrar"}
	};

	public static String comunicacion = " ";

	Marco1(){

		getContentPane().setBackground( colorInicio );
		setJMenuBar( barraMenu );

		for(int i = 0 ; i < textoMenu.length ; i++ ){

			menu[i] = new JMenu( textoMenu[i] );
				barraMenu.add( menu[i] );

		}// fin de ciclo for

		for(int i = 0 ; i < textoObjMenu.length ; i++ )
			for( int j = 0 ; j < textoObjMenu[i].length ; j++ ){

				objetoMenu[i][j] = new JMenuItem( textoObjMenu[i][j] );

					menu[i].add( objetoMenu[i][j] );
					objetoMenu[i][j].addActionListener( this);

			}// fin de ciclo for

		setBounds( (1920 - ANCHO_PANEL)/2 , ( 1080 - ALTO_PANEL  )/2 , ANCHO_PANEL, ALTO_PANEL );
		setTitle( "Estetica Mendoza" );

		add( inicio);
		add( pan5 );
		add( pan5b );
		add( pan6 );
		add( pan6b );
		pan5.setVisible( false);
		pan5b.setVisible( false);
		pan6.setVisible( false);
		pan6b.setVisible( false);
		inicio.setVisible(true);

		pan5b.busqueda[0].addActionListener( this);
		pan5b.campo.addKeyListener( this);
		pan5b.campoEdad.addChangeListener( this);
		pan6b.fecha.addPropertyChangeListener( this);
		inicio.citas.addActionListener(this);
		inicio.agendar.addActionListener(this);
		inicio.registrar.addActionListener(this);
		inicio.usuarios.addActionListener(this);
		pan5b.etiquetaImagen.addMouseListener(this);
		pan6b.etiquetaImagen.addMouseListener(this);
		pan6.actualizar.addActionListener(this);
		pan5.actualizar.addActionListener(this);
		addKeyListener(this);	

		for( int i= 0 ; i < pan6b.combox.length ; i++) pan6b.combox[i].addItemListener( this);
		for( int i = 0 ; i < pan5b.genero.length ; i++ ) pan5b.genero[i].addChangeListener( this);

		setResizable( false);
	}

/*************************************************************************************************************************************/
/************************************************* INICIO MANEJOS DE EVENTOS *********************************************************/
/*************************************************************************************************************************************/

	public void actionPerformed( ActionEvent ev){

		if( ev.getSource() == pan5.actualizar ){
			pan5.reImprimirTabla();
			pan5.consultaU();
			pan5b.desmarcar();
		}

		if( ev.getSource() == pan6.actualizar ){
			pan6.reImprimirTabla();
			pan6.consultaCita();
			pan6b.desmarcar();
		}

		//mostrar panel de consulta de usuarios
		if( ev.getSource() == objetoMenu[0][0] || ev.getSource() == inicio.usuarios ){
			pan5.setVisible( true);
			pan5b.setVisible( true);
			pan6.setVisible( false);
			pan6b.setVisible( false);
			inicio.setVisible(false);
			setFocusable(true);
			getContentPane().setBackground( Color.CYAN);
		}
	
		//mostrar panel de consulta de citas
		if( ev.getSource() == objetoMenu[0][1] || ev.getSource() == inicio.citas ){
			pan5.setVisible( false);
			pan5b.setVisible( false);
			pan6.setVisible( true);
			pan6b.setVisible( true);
			inicio.setVisible(false);
			setFocusable(true);
			getContentPane().setBackground( Color.ORANGE);
		}

		//registro de usuarios
		if( ev.getSource() == objetoMenu[1][0] || ev.getSource() == inicio.registrar ){
			comunicacion = "RU";
			Marco ventanaRegistro = new Marco();
		}

		//registro de ambos
		if( ev.getSource() == objetoMenu[1][1] || ev.getSource() == inicio.agendar ){
			comunicacion = "RA";
			Marco ventanaRegistro = new Marco();
		}

		if( ev.getSource() == objetoMenu[2][0] ){
			pan5.setVisible( false);
			pan5b.setVisible( false);
			pan6.setVisible( false);
			pan6b.setVisible( false);
			inicio.setVisible(true);
			getContentPane().setBackground( colorInicio);
		}

		if( ev.getSource() == objetoMenu[2][1] ){
			System.exit(0);
		}

		/*--------------- Seccion para busqueda por coincidencia --------------*/
		int i = 0;
		String coincidencia = " ";

		if( ev.getSource() == pan5b.busqueda[0] ){ // busqueda por coincidencia escrita en el JTextField
			for( i = 0 ; i < pan5b.botones.length ; i++ ) if( pan5b.botones[i].isSelected() == true ){ coincidencia = pan5b.campo.getText(); break; }
			System.out.println( "evento");
			pan5.reImprimirTabla();
			pan5.coincidenciaU(i, coincidencia);
		}
		/*--------------- Seccion para busqueda por coincidencia --------------*/
	}//fin de metodo actionPerformed

	public void keyPressed(KeyEvent ev){	}//fin de metodo keyPressed

	public void keyReleased(KeyEvent ev){

		int i = 0;
		boolean bandera = false;
		String coincidencia = " ";
		boolean coincidenciab = false;
		int coincidenciai = 0;

		if( ev.getSource() == pan5b.campo ){ // busqueda por coincidencia escrita en el JTextField

			coincidencia = pan5b.campo.getText();
			for( i = 0 ; i < pan5b.botones.length - 2 ; i++ ) if( pan5b.botones[i].isSelected() == true && bandera == false ){ 	pan5.reImprimirTabla();	pan5.coincidenciaU(i, coincidencia);	bandera = true;	break;	}
			for( ; i < pan5b.botones.length ; i++ ) if( pan5b.botones[i].isSelected() == true && bandera == false ){		pan5.coincidenciaU(i, coincidenciab);	bandera = true;	break;	}
		}

		//if( ev.getKeyCode() == KeyEvent.VK_BACK_SPACE ) muestraInicio();
		if( ev.getKeyCode() == KeyEvent.VK_ESCAPE ) System.exit(0);
	}

	public void keyTyped(KeyEvent ev){}// fin de metodo KeyTyped

	public void stateChanged(ChangeEvent ev){

		if( pan5b.botones[4].isSelected() ){
			int coincidenciai = Integer.parseInt( pan5b.campoEdad.getValue().toString() );
			pan5.reImprimirTabla();
			pan5.coincidenciaU(4, coincidenciai);
		}

		if( pan5b.botones[5].isSelected() ){
			boolean coincidenciab = pan5b.genero[1].isSelected();
			pan5.reImprimirTabla();
			pan5.coincidenciaU( 5 , coincidenciab);
		}

	}// fin de metodo stateChanged

	public void itemStateChanged(ItemEvent ev){

		recogeDatosFiltro();

	}//fin de metodo itemStateChange

	public void mouseClicked(MouseEvent ev){

		if( ev.getSource() == pan5b.etiquetaImagen || ev.getSource() == pan6b.etiquetaImagen ){
			muestraInicio();
		}

	}//fin de metodo mouseClicked
	public void mouseEntered(MouseEvent ev){ }
	public void mouseExited(MouseEvent ev){ }
	public void mousePressed(MouseEvent ev){ }
	public void mouseReleased(MouseEvent ev){}

	public void propertyChange(PropertyChangeEvent evt){

		recogeDatosFiltro();

	}//fin de metodo propertyChange

	public void recogeDatosFiltro(){

		boolean vacio = false;
		String fecha, servicio, hora;
		//combobox de servicio
			SimpleDateFormat formateador = new SimpleDateFormat( "dd/MM/yyyy");
			if( pan6b.botones[0].isSelected() == false ) fecha = "vacio";
			else{
				try{
					fecha = formateador.format( pan6b.fecha.getDate());
				}catch(java.lang.NullPointerException e){
					fecha = "vacio";
				}
			}
			servicio = pan6b.combox[0].getSelectedItem().toString();
			hora = pan6b.combox[1].getSelectedItem().toString();

			if(servicio.equals("Elija una opcion") == true || pan6b.botones[1].isSelected() == false ){ servicio = "vacio"; }
			if(hora.equals("Elija una opcion") == true || pan6b.botones[2].isSelected() == false ){ hora = "vacio"; }

			System.out.println( fecha + "\n" + hora + "\n" + servicio);

			if( fecha.equals("vacio") && servicio.equals("vacio") && hora.equals("vacio")){

			}else{

				pan6.reImprimirTabla();
				pan6.coincidenciaC( fecha, servicio, hora );

			}

	}//fin de metodo recogeDatosFiltro

	public void muestraInicio(){
		pan5.setVisible( false);
		pan5b.setVisible( false);
		pan6.setVisible( false);
		pan6b.setVisible( false);
		inicio.setVisible(true);
	}//fin de metodo muestraInicio

/*************************************************************************************************************************************/
/*********************************************** METODOS DE CLASE ABSTRACTOS *********************************************************/
/*************************************************************************************************************************************/

	public void salida(){ System.exit(0); }

	public static void main( String[] args ){
		Marco1 ventana = new Marco1();
		ventana.setVisible(true);
	}//fin de metodo main

}// fin de la clase Marco1
