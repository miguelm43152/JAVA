import java.io.IOException;
import java.util.Scanner;

class Banco{


	void presentacion(){
		Scanner palabra = new Scanner(System.in);
		System.out.printf("\n\n\tHola! bienvenido a al Banco.\n\n");
		System.out.println("\nIngrese un valor para continuar");
		palabra.nextLine();
	}

	public static void main(String args[]) throws IOException, InterruptedException {

		Scanner pal = new Scanner(System.in);
		Cliente clienteBanco = new Cliente();
		Banco banco = new Banco();
		banco.presentacion();

			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

		char c;

		do{

		System.out.printf("Seleccione la opcion que desea Realizar:\n");
		System.out.printf("a) dar de alta un usuario\tb)mostrar datos\tc)modificar datos\td)Realizar un retiro\ne)Realizar un abono\tf)salir\n\n");

		c = pal.nextLine().toUpperCase().charAt(0);

	
		switch( c ){
			case 'A': 
				clienteBanco.alta();
			break;

			case 'B': 
				if(clienteBanco.dadoAlta) clienteBanco.mostrarDatos(); else clienteBanco.Error();
			break;

			case 'C': 
				if(clienteBanco.dadoAlta) clienteBanco.modificarDatos(); else clienteBanco.Error();
			break;

			case 'D': 
				if(clienteBanco.dadoAlta) clienteBanco.hacerRetiro(); else clienteBanco.Error();
			break;

			case 'E':
				if(clienteBanco.dadoAlta) clienteBanco.hacerAbono(); else clienteBanco.Error();
			break;

			case 'F': /* opcion "cancelar" */ break;
			
			default:
				System.out.printf("\n\nOpcion no valida.\n");
				clienteBanco.espera();
		}// fin de switch case

			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)
		}while( c != 'F' );

	}//fin del metodo main

}//fin de la clase "BancoMillenial"
