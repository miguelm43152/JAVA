import java.io.IOException;
import java.util.Scanner;

public class Calculadora{

/*-----------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------- variables -----------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------------------------------*/

	Scanner pal = new Scanner(System.in);
	Scanner num = new Scanner(System.in);

	float n1,n2;

/*-----------------------------------------------------------------------------------------------------------------------*/
/*-------------------------------------------------- metodos ------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------------------------------*/

	void presentacion(){

		System.out.printf("\n\n\tHola! bienvenido a la calculadora.\n\n");
		espera();
	}

	void espera(){

		System.out.println("Ingrese un valor para continuar");
		pal.nextLine();

	}

	void menu(){
		System.out.printf("\n\n\tSeleccione una opcion:\n\n");
		System.out.println("\t\ta) sumar\t\tb) restar\t\tc) multiplicar\t\td) dividir\n");
		System.out.println("\t\te) ingresar datos\t\tf) mostrar datos\t\tg)salir\n");

	}

/*-----------------------------------------------------------------------------------------------------------------------*/
/*---------------------------------------------- Metodos de operacion ---------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------------------------------*/

	void operacion(char x){



		switch(x){
			case 'A': suma();	break;
			case 'B': resta();	break;
			case 'C': producto();	break;
			case 'D': if(n2 == 0) {System.out.println("Error, division entre cero"); break; } cociente();	break;
			default:
		}
		
		espera();

	}

	void suma(){
		System.out.printf("El resultado de la suma es: %.3f\n",n1 + n2);
	}

	void resta(){
		System.out.printf("El resultado de la resta es: %.3f\n",n1 - n2);
	}

	void producto(){
		System.out.printf("El resultado del producto es: %.3f\n",n1 * n2);
	}

	void cociente(){
		System.out.printf("El resultado del cociente es: %.3f\n",n1*1.0 / n2*1.0);
	}

/*-----------------------------------------------------------------------------------------------------------------------*/
/*----------- Solicita que se ingresen los valores numericos del primero y segundo numero a operar ----------------------*/
/*-----------------------------------------------------------------------------------------------------------------------*/

	void preguntaDatos(){

		System.out.printf("\n\n\tIngrese el valor del primer numero:\n");

		n1 = num.nextFloat();

		System.out.printf("\n\n\tIngrese el valor del segundo numero:\n");

		n2 = num.nextFloat();

	} //fin del metodo preguntaDatos

	void mostradDatos(){

		System.out.printf("\n\n\tLos valores numericos de los numeros a operar:\n\n");
		System.out.printf("primer numero: %.3f\n",n1);
		System.out.printf("segundo numero: %.3f\n",n2);
		espera();

	} // fin del metodo mostradDatos

/*-----------------------------------------------------------------------------------------------------------------------*/
/*--------------------------------------------- metodo main -------------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------------------------------*/

	public static void main(String args[]) throws IOException, InterruptedException {

/*/////////////////////////////////////////////////////////////////////////////////*/
/*//////////////////////////// Variables de main //////////////////////////////////*/
/*/////////////////////////////////////////////////////////////////////////////////*/

		Scanner mainPal = new Scanner(System.in);

		char c;

/*/////////////////////////////////////////////////////////////////////////////////*/
/*//////////////////////////// funciones de main //////////////////////////////////*/
/*/////////////////////////////////////////////////////////////////////////////////*/

		Calculadora casio = new Calculadora();

		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

		casio.presentacion();

		do{

			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)
			casio.menu();

			c = mainPal.nextLine().toUpperCase().charAt(0);
			
			switch(c){
				case 'A': casio.operacion(c); 		break;
				case 'B': casio.operacion(c); 		break;
				case 'C': casio.operacion(c); 		break;
				case 'D': casio.operacion(c); 		break;
				case 'E': casio.preguntaDatos();	break;
				case 'F': casio.mostradDatos();		break;
				case 'G': break;
				default:
					System.out.printf("\n\n\tOpcion no valida, intentelo de nuevo.\n");
					casio.espera();
			}

		}while( c != 'G');

			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

	}//fin del metodo main

} //Fin de la clase "Calculadora"

/*-----------------------------------------------------------------------------------------------------------------------*/
/*---------------------------------------------- fin del programa -------------------------------------------------------*/
/*-----------------------------------------------------------------------------------------------------------------------*/
