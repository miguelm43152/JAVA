import java.util.Scanner;

public class Persona{

	String nombre;
	int edad;
	String domicilio;
	String telefono;
	float promedio;

	void obtenerDatos(){
	
		Scanner num = new Scanner(System.in);
		Scanner pal = new Scanner(System.in);

		System.out.println("Escriba su nombre: ");
		nombre = pal.next();

		System.out.println("Escriba su edad: ");
		edad = num.nextInt();

		System.out.println("Escriba su domicilio: ");
		domicilio = pal.next();

		System.out.println("Escriba su telefono: ");
		telefono = pal.next();

		System.out.println("Escriba su promedio: ");
		promedio = num.nextFloat();

		System.out.println();

	} //fin del metodo "obtenerDatos"

	void mostrarDatos(){
	
		System.out.println("Su nombre es: " + nombre);
		System.out.println("Su edad es: " + edad);
		System.out.println("Su domicilio es: " + domicilio);
		System.out.println("Su telefono es: " + telefono);
		System.out.println("Su promedio es: " + promedio);
		System.out.println();

	} //fin del metodo "mostrarDatos"

	public static void main(String args[]){

/*
		System.out.println("Persona 1 escriba sus datos");
		System.out.println();
		Persona persona1 = new Persona();
		persona1.obtenerDatos();

		System.out.println("Persona 2 escriba sus datos");
		System.out.println();
		Persona persona2 = new Persona();
		persona2.obtenerDatos();

		System.out.println("Persona 3 escriba sus datos");
		System.out.println();
		Persona persona3 = new Persona();
		persona3.obtenerDatos();

		persona1.mostrarDatos();
		persona2.mostrarDatos();
		persona3.mostrarDatos();
*/

		// forma alternativa, crear un arreglo de personas y recorrer todas las instrucciones en un ciclo for
	
		int numeroDePersonas = 1;
		Persona[] personas = new Persona[numeroDePersonas];
		
		for( int i = 0 ; i < numeroDePersonas; i++ ){

			System.out.println("Persona " + Integer.toString(i+1) +" escriba sus datos");
			System.out.println();
			personas[i] = new Persona();
			personas[i].obtenerDatos();
			
		} // fin de ciclo for

		for( int i = 0 ; i < numeroDePersonas; i++ )
			personas[i].mostrarDatos();

	} //Fin del metodo main

} //fin de la clase "Persona"
