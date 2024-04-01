import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import com.toedter.calendar.*;		//para usar JDateChooser
//import BD.ManejoBD;
import java.awt.*;
import java.util.*;

public class Marco extends PreMarco implements ActionListener, FocusListener{

	Panel1 pan1 = new Panel1();
	Panel2 pan2 = new Panel2();
	Panel3 pan3 = new Panel3();
	Panel4 pan4 = new Panel4();
	Panel4B pan4b = new Panel4B();

	JMenuBar barraMenu = new JMenuBar();
	JMenu menus[] = new JMenu[2];
	JMenuItem[][] objetosMenu = new JMenuItem[2][3];
	JMenu menuInicio = new JMenu( "Inicio" );
	JMenuItem inicio = new JMenuItem( "Inicio" );

	String[][] texto = {
	{"Registro", "Consulta"},
	{"Personas", "Citas", "Completo"},
	{"Personas", "Citas"},
	};

	ManejoBD conector = new ManejoBD();

	String instruccionEnviada = "RC";
	String modo = "registro";
	int IDP = 0;

/*****************************************************************************************************************************/
/**************************************      INICIO DEL METODO COMSTRUCTOR  **************************************************/
/*****************************************************************************************************************************/

	Marco(){

		setTitle( "Control de Citas");
		setDefaultCloseOperation( DISPOSE_ON_CLOSE);

		barraMenu.add( menuInicio);
		barraMenu.add( inicio);	
		getContentPane().setBackground(new Color(255,254,225));
		//setBackground( new Color(245,227,201));

		//Agregando paneles
		add( pan1);
		add( pan2);
		add( pan3);
		add( pan4);
		add( pan4b);

		setResizable( false);

		pan4.setVisible( false);

		//cambiando visibilidad de paneles segun ventana anterior
		if( Marco1.comunicacion.equals( " ") == false )
			instruccionEnviada = Marco1.comunicacion;
		else if( Panel5.comunicacion.equals( " ") == false)
			instruccionEnviada = Panel5.comunicacion;
		else if( Panel6.comunicacion.equals( " ") == false)
			instruccionEnviada = Panel6.comunicacion;

		if( Panel5.IDPersona != 0 )
			IDP = Panel5.IDPersona;
		else if( Panel6.IDCitaPersona != 0 ){
			IDP = Panel6.IDCitaPersona;
		}

		if( instruccionEnviada.equals( "RU" ) ){
			setTitle("Registro de Usuario");
			pan1.setVisible( true );
			pan2.setVisible( false );
			pan3.setVisible( false );
			pan4.setVisible( true );
			pan4b.setVisible( false );

		}else if( instruccionEnviada.equals( "RC" ) ){
			setTitle("Agendar Cita");
			pan1.setVisible( false );
			pan2.setVisible( true );
			pan3.setVisible( true );
			pan4.setVisible( true );
			pan4b.setVisible( false );

		}else if( instruccionEnviada.equals( "RA" ) ){
			setTitle("Registro de Usuario y Cita");
			pan1.setVisible( true );
			pan2.setVisible( true );
			pan3.setVisible( true );
			pan4.setVisible( true );
			pan4b.setVisible( false );

		}else if( instruccionEnviada.equals( "CU" ) ){
			setTitle("Consulta Usuario");
			pan1.setVisible( true );
			pan2.setVisible( false );
			pan3.setVisible( false );
			pan4.setVisible( false );
			pan4b.setVisible( true );
			pan1.getUsuario( IDP );
			pan1.desactivar( );

		}else if( instruccionEnviada.equals( "CC" ) ){
			setTitle("Consulta Cita");
			pan1.setVisible( false );
			pan2.setVisible( true );
			pan3.setVisible( true );
			pan4.setVisible( false );
			pan4b.setVisible( true );

			pan2.getCita( IDP );	//en este caso IDP se maneja como un id de cita, pero para ahorrarnos el crear varias
			pan3.getCita( IDP );	//variables innecesariamente, lo dejamos asi. Le dejamos el mismo nombre para evitar
			pan2.desactivar( );	//buscar en todos los lugares que se uso y cambiar uno por uno.
			pan3.desactivar( );
			pan4b.ocultarCita();
			pan3.ocultar();

		}

//		System.out.println( instruccionEnviada );

		Marco1.comunicacion = " ";
		Panel5.comunicacion = " ";
		Panel6.comunicacion = " ";
		Panel5.IDPersona = 0;

		for(int i = 0 ; i < 4 ; i++){
			pan1.campos[i].addKeyListener( this);
			pan1.etiquetas[i].addKeyListener( this);
			pan4b.botones[i].addActionListener( this );
		}

		setFocusable(true);
		addKeyListener(this);
	
		pan4.confirmar.addActionListener( this);
		pan4.cancelar.addActionListener( this);
		pan1.addKeyListener( this);

		for( int i = 0; i < pan1.campos.length ; i++ ){ pan1.campos[i].addFocusListener(this); }

	}//fin del metodo constructor

/*****************************************************************************************************************************/
/**********************************************	FIN DEL METODO COMSTRUCTOR  **************************************************/
/*****************************************************************************************************************************/

public void obtenerDatosCita( String fecha, String hora, String servicio){

	SimpleDateFormat formateador = new SimpleDateFormat( "dd/MM/yyyy" );
	try{
		fecha = formateador.format( pan2.fecha.getDate() );
		}catch(java.lang.NullPointerException e){
			fecha = "vacio";
		}
		hora = pan2.hora.getSelectedItem().toString();
		servicio = pan3.servicio[0].getSelectedItem().toString();	
}

/*****************************************************************************************************************************/
/**************************************  INICIO DE LOS METODOS ABSTRACTOS   **************************************************/
/*****************************************************************************************************************************/

	public void salida()
	{ dispose(); }

/* --------------------------------------------------------------------------------------------------------------------------------------------- */

public void actionPerformed( ActionEvent ev){

	String nombre, domicilio, telefono, correo;
	String fecha, hora, servicio;
	//	String 	fecha, hora, servicio;
		int edad;
		boolean genero = false;

		if( ev.getSource() == pan4.confirmar ){

			nombre = pan1.campos[0].getText();
			domicilio = pan1.campos[1].getText();
			telefono = pan1.campos[2].getText();
			correo = pan1.campos[3].getText();

			edad = Integer.parseInt( pan1.selectorEdad.getValue().toString() );

			if( pan1.genero[1].isSelected() == true ){ genero = true; }

			if( instruccionEnviada.equals( "RU" ) ){

				if( nombre.isEmpty() ) System.out.println( "nombre vacio");
			
			pan1.mensajeError.setText( Validacion.panel1(pan1.campos, pan1.genero) );
			if( pan1.mensajeError.getText().isEmpty() == true ){
				conector.insertarU( nombre, domicilio, telefono, correo, edad, genero );
				dispose();
			}
			}else if( instruccionEnviada.equals( "CU" ) ){

				if( modo.equals("modificar")){

					pan1.mensajeError.setText( Validacion.panel1(pan1.campos, pan1.genero) );
					if( pan1.mensajeError.getText().isEmpty() == true ){
						conector.modificarU( nombre, domicilio, telefono, correo, edad, genero, IDP );
						dispose();
					}

				}else if( modo.equals("eliminar") ){

					conector.eliminarU( /* Panel5.IDPersona */ IDP );
					dispose();		

				}else if( modo.equals("agendar") ){
				System.out.println(  modo );
				SimpleDateFormat formateador = new SimpleDateFormat( "dd/MM/yyyy");
				try{
					fecha = formateador.format( pan2.fecha.getDate());
				}catch(java.lang.NullPointerException e){
					fecha = "vacio";
				}
				hora = pan2.hora.getSelectedItem().toString();
				servicio = pan3.servicio[0].getSelectedItem().toString();
				
					pan3.mensajeError.setText( Validacion.paneles23(fecha, hora, servicio) );
					if(pan3.mensajeError.getText().isEmpty() == true){
						conector.agendar( fecha, hora, servicio, /* Panel5.IDPersona */ IDP );		
						dispose();		
					}
				}

			}else if( instruccionEnviada.equals( "RA" ) ){

				String datos[] = new String[9];
				nombre = pan1.campos[0].getText();
				domicilio = pan1.campos[1].getText();
				telefono = pan1.campos[2].getText();
				correo = pan1.campos[3].getText();
	
				edad = Integer.parseInt( pan1.selectorEdad.getValue().toString() );
	
				if( pan1.genero[1].isSelected() == true ){ genero = true; }

				try{
					SimpleDateFormat formateador = new SimpleDateFormat( "dd/MM/yyyy");
					fecha = formateador.format( pan2.fecha.getDate() );
					}catch(java.lang.NullPointerException e){
						fecha = "vacio";
					}
					hora = pan2.hora.getSelectedItem().toString();
					servicio = pan3.servicio[0].getSelectedItem().toString();

				datos[0] = nombre;
				datos[1] = domicilio;
				datos[2] = telefono;
				datos[3] = correo;
				datos[4] = Integer.toString(edad);
				datos[5] = Boolean.toString(genero);
				datos[6] = fecha;
				datos[7] = hora;
				datos[8] = servicio;

				pan1.mensajeError.setText( Validacion.panel1( pan1.campos , pan1.genero));
				pan3.mensajeError.setText( Validacion.paneles23(fecha, hora, servicio) );
				if( pan1.mensajeError.getText().toString().isEmpty() == true && pan3.mensajeError.getText().toString().isEmpty() == true ){
					System.out.println( "Si se pudo");
					conector.insertarA(datos);
					dispose();
				}else System.out.println( "No se pudo");
			}//fin if(ev.getSource() == pan4.confirmar)

		if( ev.getSource() == pan4.confirmar && instruccionEnviada.equals( "CC") ){

			SimpleDateFormat formateador = new SimpleDateFormat( "dd/MM/yyyy" );
			try{
			fecha = formateador.format( pan2.fecha.getDate() );
			}catch(java.lang.NullPointerException e){
				fecha = "vacio";
			}
			hora = pan2.hora.getSelectedItem().toString();
			servicio = pan3.servicio[0].getSelectedItem().toString();
			
			if( modo.equals( "modificar" ) ){
				pan3.mensajeError.setText( Validacion.paneles23(fecha, hora, servicio) );
				if( pan3.mensajeError.getText().toString().isEmpty() == true ){
					conector.modificarC(fecha, hora, servicio, IDP);
				}

			}else if(modo.equals( "eliminar" )){
				conector.eliminarC( IDP );
			}
			dispose();

		}// fin if (ev.getSource() == pan4.confirmar && instruccionEnviada.equals( "CC") )

/* --------------------------------------------------------------------------------------------------------------------------------------------- */

	} //fin de if "confirmar"

	if( ev.getSource() == pan4b.botones[0] ){ // agregar cita

		pan1.setVisible( false );
		pan2.setVisible( true );
		pan3.setVisible( true );
		pan4.setVisible( true );
		pan4b.setVisible( false );
		modo = "agendar";
	}

	if( ev.getSource() == pan4.cancelar){

		if ( 	modo.equals( "agendar" ) ){
			pan1.desactivar();
			pan4.setVisible( false );
			pan4b.setVisible( true );
		}
	
		if( instruccionEnviada.equals( "CU" )  ){
			pan1.desactivar();
				pan1.setVisible( true );
				pan2.setVisible( false );
				pan3.setVisible( false );
				pan4.setVisible( false );
				pan4b.setVisible( true );

		}else if( instruccionEnviada.equals( "CC" ) ){
			pan1.setVisible( false );
			pan2.setVisible( true );
			pan3.setVisible( true );
			pan4.setVisible( false );
			pan4b.setVisible( true );
			pan2.desactivar( );
			pan3.desactivar( );
			pan4b.ocultarCita();
			pan3.ocultar();

		}else{
			dispose();
		}

	}// fin if pan4.cancelar

	if( ev.getSource() == pan4b.botones[2] ){ //eliminar

		pan4.setVisible( true );
		pan4b.setVisible( false );
		modo = "eliminar";
	}

	if( ev.getSource() == pan4b.botones[1] ){ //modificar

		if( instruccionEnviada.equals( "CU" )  ){

			pan1.activar();

		}else if( instruccionEnviada.equals( "CC" ) ){
			pan2.activar( );
			pan3.activar( );
		}

			modo = "modificar";
			pan4.setVisible( true );
			pan4b.setVisible( false );
	}

	if( ev.getSource() == pan4b.botones[3] ){ //eliminar
		dispose();
	}
}//	fin de metodo actionPerformed

	public void focusGained(FocusEvent e){}
	public void focusLost(FocusEvent e){

		System.out.println( "Evento focusLost activado" );
		pan1.mensajeError.setText( Validacion.panel1Foco(pan1.campos, pan1.genero) );

	}	//fin de metodo focusLost

	/* --------------------------------------------------------------------------------------------------------------------------------------------- */
	public static void main( String[] args){
		Marco ventana = new Marco();
		ventana.setVisible(true);

	}//fin del metodo main
}//fin de la clase Marco