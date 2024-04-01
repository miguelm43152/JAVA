/* import java.security.MessageDigest; */
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Validacion {

    private static boolean campoEsNumerico( JTextField campo){

        boolean numero = false;

        String texto = campo.getText().toString();

        for( int i = 0 ; i < texto.length() ; i++ ){
            numero = Character.isDigit( texto.charAt(i) );
            if( numero == true){
                return true;
            }
        }
        return numero;

    }//fin de metodo

    private static boolean campoEsVacio( JTextField campo){
        String texto = campo.getText().toString();

        return texto.isEmpty();
    }//fin de metodo campoEsVacio

    private static boolean campoEsVacio( String texto ){
        if( texto.isEmpty() == true ) return true;
        if( texto.equals("vacio") == true) return true;
        if( texto.equals( "Elija una opcion" ) == true) return true;
        return false;
    }

    public static String panel1( JTextField[] campos, JRadioButton botones[] ){
        String mensajeError = "";
        for( int i = 0 ; i < campos.length ; i++) if( campoEsVacio( campos[i] ) == true ) return mensajeError = "Favor de llenar todos los campos." ;
        mensajeError = validacionPanel1Basica( campos, botones);
        return mensajeError; 
    }//fin de metodo panel1

    public static String panel1Foco( JTextField[] campos, JRadioButton botones[] ){

        String mensajeError = "";
        mensajeError = validacionPanel1Basica( campos, botones);

        return mensajeError;
    }//fin de metodo panel1

    private static String validacionPanel1Basica( JTextField[] campos, JRadioButton botones[]){
        boolean radiosVacios = false;
        String mensajeError = "";

        if( campoEsNumerico( campos[0]) == true ) return mensajeError = "Favor de no ingresar numeros en campo nombre." ;
        if( campoEsNumerico( campos[2]) == false ) return mensajeError = "Favor de ingresar solo numeros en campo telefono.";
        for( int i = 0 ; i < botones.length ; i++ ) 
            if( botones[i].isSelected() == true ) {
                radiosVacios = true; 
                break;
            }
            else radiosVacios = false;
        if( radiosVacios == false ) return "Favor de seleccionar un genero.";

        return mensajeError;
    }//fin de metodo validacionBasica

    public static String paneles23(String fecha, String hora, String servicio){
        String error = "";
        if( campoEsVacio( fecha ) == true ) error = "Favor de llenar el campo fecha.";
        if( campoEsVacio( hora ) == true ) error = "Favor de llenar el campo hora.";
        if( campoEsVacio( servicio ) == true ) error = "Favor de llenar el campo servicio.";
        return error;
    }// fin de metodo paneles23

}//fin de la clase Validacion