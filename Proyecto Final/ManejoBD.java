
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class ManejoBD{

	Connection cn = null;
	PreparedStatement pst = null;
	String accionSQL;
	ResultSet rs = null;

	//metodo para conectar con BD
	public void conectarBD(){

		try{

			cn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/proyectoprogramacion", "root","" );

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(");
		}

	}//	fin de metodo conectarBD

/**************************************************************************************************************************/
/*******************************************     Insertar Columnas    *****************************************************/
/**************************************************************************************************************************/

	/*---------- Registro de usuarios  ---------*/

	public void insertarU( String nombre, String domicilio, String telefono, String correo, int edad, boolean genero ){

		try{

			conectarBD();

			accionSQL = "INSERT INTO `usuarios`(`NOMBRE`, `DOMICILIO`, `TELEFONO`, `CORREO`, `EDAD`, `GENERO`) VALUES ( ?, ?, ?, ?, ?, ?) ";

			pst = cn.prepareStatement( accionSQL );

			pst.setString(	1, nombre);
			pst.setString(	2, domicilio);
			pst.setString(	3, telefono);
			pst.setString(	4, correo);
			pst.setInt(	5, edad);
			pst.setBoolean(	6, genero);

			pst.executeUpdate();

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}// fin de metodo insertarU

	public void insertarA( String[] datos){
		int IDUltimaPersona = 0;
		int edad = Integer.parseInt( datos[4]);
		boolean genero = Boolean.parseBoolean(	datos[5]);
		insertarU( datos[0], datos[1], datos[2], datos[3], edad, genero);

		try{

			conectarBD();

			accionSQL = "SELECT * FROM `usuarios` ORDER BY IDPERSONAS DESC";

			pst = cn.prepareStatement( accionSQL );

			rs = pst.executeQuery();

			rs.next();

			IDUltimaPersona = rs.getInt( "IDPERSONAS" );

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

		agendar( datos[6], datos[7], datos[8], IDUltimaPersona);


	}// fin de merodo insertarA
	
	/*---------- Registro de citas  ---------*/

	public void agendar( String fecha, String hora, String servicio, int idp ){

		try{

			conectarBD();

			accionSQL = "INSERT INTO `citas`( `FECHA`, `HORA`, `SERVICIO`, ID_PERSONAS) VALUES ( ?, ?, ?, ?) ";

			pst = cn.prepareStatement( accionSQL );

			pst.setString(	1, fecha);
			pst.setString(	2, hora);
			pst.setString(	3, servicio);

			pst.setInt( 4, idp);

			pst.executeUpdate();
			System.out.println( "Exito" );

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}// fin de metodo agendar

	/*---------- Registro de usuarios y citas  ---------*/

/**************************************************************************************************************************/
/*******************************************    Consultar Columnas    *****************************************************/
/**************************************************************************************************************************/

	public void getUsuario( String datos[], int IDPersona ){

		try{

			conectarBD();

			accionSQL = "SELECT * FROM `usuarios` WHERE IDPERSONAS = ?";
			pst = cn.prepareStatement( accionSQL );

			pst.setInt( 1, IDPersona);

			rs = pst.executeQuery();

			rs.next();

			datos[0] = rs.getString( "NOMBRE" );
			datos[1] = rs.getString( "DOMICILIO" );
			datos[2] = rs.getString( "TELEFONO" );
			datos[3] = rs.getString( "CORREO" );
			datos[4] = String.valueOf( rs.getInt( "EDAD" ) );
			datos[5] = String.valueOf(  rs.getBoolean( "GENERO" ) );

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}//fin de metodo getUsuario

/* --------------------------------------------------------------------------------------- */

	public void getCita( String datos[], int IDCita ){

		try{

			conectarBD();

			accionSQL = "SELECT * FROM `citas` WHERE IDCITAS = ?";
			pst = cn.prepareStatement( accionSQL );

			pst.setInt( 1, IDCita);

			rs = pst.executeQuery();

			rs.next();

			datos[0] = rs.getString( "FECHA" );
			datos[1] = rs.getString( "HORA" );
			datos[2] = rs.getString( "SERVICIO" );
			datos[3] = rs.getString( "ID_PERSONAS" );

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}//fin de metodo getCita

	public void coincidenciaC( ArrayList < ArrayList < JButton> > botonesInfo, String fecha, String servicio, String hora ){

		try{

			conectarBD();

			accionSQL = "SELECT * FROM `citas` WHERE ";
			String comillaSimple  = "'";
			boolean condicionAgregada = false;

			if( fecha.equals("vacio") == false 		){	
				accionSQL+= " FECHA = '" + fecha + comillaSimple;
				condicionAgregada = true;
			}
			if( servicio.equals("vacio") == false 	){
				if( condicionAgregada == true){
					accionSQL+= " AND SERVICIO = '" + servicio + comillaSimple;
				}else{
				accionSQL+= " SERVICIO = '" + servicio + comillaSimple;
				condicionAgregada = true;
				}
			}
			if( hora.equals("vacio") == false		){
				if( condicionAgregada == true){
					accionSQL+= " AND HORA = '" + hora + comillaSimple;
				}else{
				accionSQL+= " HORA = '" + hora + comillaSimple;
				condicionAgregada = true;
				}
			}

			System.out.println( accionSQL);

			pst = cn.prepareStatement( accionSQL );
			rs = pst.executeQuery();

			for( int n = 0 ; rs.next() ; n++ ){

				botonesInfo.add( new ArrayList() );
				botonesInfo.get(n).add( new JButton( rs.getString( "IDCITAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "ID_PERSONAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "FECHA" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "HORA" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "SERVICIO" ) ));

			}//fin de for

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}//fin de metodo getCita

	/* --------------------------------------------------------------------------------------- */

	public void tablaUsuarios( ArrayList < ArrayList < JButton> > botonesInfo ){

		try{

			conectarBD();

			accionSQL = " SELECT * FROM `usuarios` ";
			pst = cn.prepareStatement( accionSQL );
			rs = pst.executeQuery();

			for( int n = 0 ; rs.next() ; n++ ){

				botonesInfo.add( new ArrayList() );
				botonesInfo.get(n).add( new JButton( rs.getString( "IDPERSONAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "NOMBRE" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "DOMICILIO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "TELEFONO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "CORREO" ) ));
				botonesInfo.get(n).add( new JButton( Integer.toString( rs.getInt( "EDAD" ) ) ));

				if( rs.getBoolean( "GENERO" ) == true )
					botonesInfo.get(n).add( new JButton( "Hombre" ));
				else
					botonesInfo.get(n).add( new JButton( "Mujer" ));

			}//fin de for

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	} //fin de tablaUsuarios

	/*-----------Tabla de Citas-------------*/

	public void tablaCitas( ArrayList < ArrayList < JButton> > botonesInfo ){

		try{

			conectarBD();

			accionSQL = " SELECT * FROM `citas` ";
			pst = cn.prepareStatement( accionSQL );
			rs = pst.executeQuery();

			for( int n = 0 ; rs.next() ; n++ ){

				botonesInfo.add( new ArrayList() );
				botonesInfo.get(n).add( new JButton( rs.getString( "IDCITAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "ID_PERSONAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "FECHA" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "HORA" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "SERVICIO" ) ));

			}//fin de for

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch
	}// fin de metodo tablaCitas

/* --------------------------------------------------------------------------------------- */

	public void coincidenciaU( ArrayList < ArrayList < JButton> > botonesInfo, int columna, String coincidencia ){
	
		String[] textoBotones = { "NOMBRE", "TELEFONO", "DOMICILIO", "CORREO", "EDAD" , "GENERO" };
		String[] sql = { "SELECT * FROM `usuarios` WHERE ", " LIKE \"%", "%\""  };

		try{

			conectarBD();

			accionSQL = sql[0] + textoBotones[columna] + sql[1] + coincidencia + sql[2];
			//System.out.println( accionSQL );

			pst = cn.prepareStatement( accionSQL );

			rs = pst.executeQuery();

			for( int n = 0 ; rs.next() ; n++ ){

				botonesInfo.add( new ArrayList() );
				botonesInfo.get(n).add( new JButton( rs.getString( "IDPERSONAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "NOMBRE" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "DOMICILIO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "TELEFONO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "CORREO" ) ));
				botonesInfo.get(n).add( new JButton( Integer.toString( rs.getInt( "EDAD" ) ) ));

				if( rs.getBoolean( "GENERO" ) == true )
					botonesInfo.get(n).add( new JButton( "Hombre" ));
				else
					botonesInfo.get(n).add( new JButton( "Mujer" ));

			}//fin de for

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}// fin de metodo coincidenciaU

	public void coincidenciaU( ArrayList < ArrayList < JButton> > botonesInfo, int columna, int coincidencia ){
	
		String[] textoBotones = { "NOMBRE", "TELEFONO", "DOMICILIO", "CORREO", "EDAD" , "GENERO" };
		String[] sql = { "SELECT * FROM `usuarios` WHERE ", " LIKE \"%", "%\""  };
		System.out.println( accionSQL );

		try{

			conectarBD();

			accionSQL = "SELECT * FROM `usuarios` WHERE EDAD = " + coincidencia;
			//System.out.println( accionSQL );

			pst = cn.prepareStatement( accionSQL );

			rs = pst.executeQuery();

			for( int n = 0 ; rs.next() ; n++ ){

				botonesInfo.add( new ArrayList() );
				botonesInfo.get(n).add( new JButton( rs.getString( "IDPERSONAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "NOMBRE" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "DOMICILIO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "TELEFONO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "CORREO" ) ));
				botonesInfo.get(n).add( new JButton( Integer.toString( rs.getInt( "EDAD" ) ) ));

				if( rs.getBoolean( "GENERO" ) == true )
					botonesInfo.get(n).add( new JButton( "Hombre" ));
				else
					botonesInfo.get(n).add( new JButton( "Mujer" ));

			}//fin de for

//			mensaje();

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}// fin de metodo coincidenciaU

	public void coincidenciaU( ArrayList < ArrayList < JButton> > botonesInfo, int columna, boolean coincidencia ){
	
		try{

			conectarBD();

			accionSQL = "SELECT * FROM `usuarios` WHERE GENERO = " + coincidencia;

			pst = cn.prepareStatement( accionSQL );

			rs = pst.executeQuery();

			for( int n = 0 ; rs.next() ; n++ ){

				botonesInfo.add( new ArrayList() );
				botonesInfo.get(n).add( new JButton( rs.getString( "IDPERSONAS" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "NOMBRE" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "DOMICILIO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "TELEFONO" ) ));
				botonesInfo.get(n).add( new JButton( rs.getString( "CORREO" ) ));
				botonesInfo.get(n).add( new JButton( Integer.toString( rs.getInt( "EDAD" ) ) ));

				if( rs.getBoolean( "GENERO" ) == true )
					botonesInfo.get(n).add( new JButton( "Hombre" ));
				else
					botonesInfo.get(n).add( new JButton( "Mujer" ));

			}//fin de for

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}// fin de metodo coincidenciaU

/**************************************************************************************************************************/
/*******************************************    Modificar Columnas    *****************************************************/
/**************************************************************************************************************************/

	/*---------- modificacion de usuarios ---------*/

	public void modificarU( String nombre, String domicilio, String telefono, String correo, int edad, boolean genero, int IDPersona ){

		try{

			conectarBD();

			accionSQL = "UPDATE `usuarios` SET `NOMBRE`= ?,`DOMICILIO`= ?,`TELEFONO`= ?,`CORREO`= ?,`EDAD`= ? ,`GENERO`= ? WHERE `IDPERSONAS`= ? " ;

			pst = cn.prepareStatement( accionSQL );

			pst.setString(	1, nombre);
			pst.setString(	2, domicilio);
			pst.setString(	3, telefono);
			pst.setString(	4, correo);
			pst.setInt(		5, edad);
			pst.setBoolean(	6, genero);
			pst.setInt( 	7, IDPersona);

			pst.executeUpdate();

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}// fin de metodo modificarU

	/*---------- modificacion de citas ---------*/

	public void modificarC( String fecha, String hora, String servicio, int IDCita ){

		try{

			conectarBD();

			accionSQL = "UPDATE `citas` SET `FECHA`= ? ,`HORA`= ? ,`SERVICIO`= ? WHERE `IDCITAS`= ?";

			pst = cn.prepareStatement( accionSQL );

			pst.setString(	1, fecha);
			pst.setString(	2, hora);
			pst.setString(	3, servicio);
			pst.setInt( 4, IDCita);
			
			pst.executeUpdate();

			mensaje();
		}catch( Exception e ){
			System.out.println( "Algo salio mal :-( " + e);
		}//fin de try-catch

	}// fin de metodo modificarU


/**************************************************************************************************************************/
/*******************************************    eliminar Columnas    *****************************************************/
/**************************************************************************************************************************/

	/*---------- eliminar usuarios ---------*/

	public void eliminarU( int IDPersona ){

		try{

			conectarBD();

			accionSQL = "DELETE FROM `citas` WHERE ID_PERSONAS = ?";

			pst = cn.prepareStatement( accionSQL );

			pst.setInt( 1, IDPersona);

			pst.executeUpdate();

			accionSQL = "DELETE FROM `usuarios` WHERE IDPERSONAS = ?";

			pst = cn.prepareStatement( accionSQL );

			pst.setInt( 1, IDPersona);

			pst.executeUpdate();

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}// fin de metodo modificarU

	public void eliminarC( int IDCita ){

		try{

		conectarBD();

		accionSQL = "DELETE FROM `citas` WHERE IDCITAS = ?";

		pst = cn.prepareStatement( accionSQL );

		pst.setInt( 1, IDCita);

		pst.executeUpdate();

		}catch( Exception e ){
			System.out.println( "Algo salio mal :-(" + e);
		}//fin de try-catch

	}//fin de metodo eliminarC

	void mensaje(){
		System.out.println( "Exito");
	}

}//fin de clase manejoBD