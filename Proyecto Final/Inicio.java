import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Inicio extends JPanel {
    //static final int ANCHO_PANEL = 450, ALTO_PANEL = 300, ANCHO_E = 120, ALTO_E = 30;
    static final int ANCHO_PANEL = 1300, ALTO_PANEL = 650, ANCHO_E = 170, ALTO_E = 20, ANCHO_IMAGEN = 600, ALTO_IMAGEN = 445, ANCHO_TITULO = 400;
	JButton usuarios = new JButton( "usuarios" );
	JButton citas = new JButton( "citas" );
    JButton agendar = new JButton( "agendar" );
    JButton registrar = new JButton( "registrar" );
    Color colorInicio = new Color(245,227,201);
	ImageIcon imagen = new ImageIcon("logo1.PNG");
	JLabel etiquetaImagen = new JLabel(imagen);
	JLabel nombreLocal = new JLabel("ESTETICA MENDOZA");
	Font fuente = new Font("Algerian", Font.BOLD, 36);

	OrdenMatriz el = new OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_PANEL, ALTO_PANEL, 4, 5); //Constructor de la clase

	Inicio(){

		setLayout( null);
		setBounds( 0 , 0, ANCHO_PANEL, ALTO_PANEL );
		setVisible( true );
        setBackground( colorInicio );

		etiquetaImagen.setBounds( (ANCHO_PANEL - ANCHO_IMAGEN)/2 , 100 , ANCHO_IMAGEN , ALTO_IMAGEN );
		usuarios.setBounds( el.getPosicionX(0) , el.getPosicionY(4), ANCHO_E , ALTO_E );
		citas.setBounds( el.getPosicionX(2) , el.getPosicionY(4), ANCHO_E , ALTO_E );
        agendar.setBounds( el.getPosicionX(1) , el.getPosicionY(4), ANCHO_E , ALTO_E );
        registrar.setBounds( el.getPosicionX(3) , el.getPosicionY(4), ANCHO_E , ALTO_E );
		nombreLocal.setBounds( (ANCHO_PANEL - ANCHO_TITULO)/2, 50, ANCHO_TITULO, ALTO_E*2 );
		nombreLocal.setFont(fuente);
		add( usuarios);
		add( citas);
        add( agendar);
        add( registrar);
		add( etiquetaImagen);
		add( nombreLocal);

	}//fin del constructor
}//fin de clase Inicio
