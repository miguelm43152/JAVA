import java.util.Scanner;

class Cliente{

	String nombre, domicilio, telefono;
	int edad;
	float saldo, movimiento;
	boolean dadoAlta = false;

	Scanner pal = new Scanner(System.in);
	Scanner num = new Scanner(System.in);

	void espera(){

		System.out.println("\nIngrese un valor para continuar");
		pal.nextLine();

	}

	void alta(){

		System.out.println("\nCreacion de cuenta nueva:");
		System.out.println("Ingrese el nombre del nuevo cliente:");
		nombre = pal.nextLine();
		System.out.println("Ingrese el domicilio:");
		domicilio = pal.nextLine();
		System.out.println("Ingrese el telefono:");
		telefono = pal.nextLine();
		System.out.println("Ingrese la edad:");
		edad = num.nextInt();
		System.out.println("El saldo de la cuenta es de 0.");
		saldo = 0;
		dadoAlta = true;	
		espera();	

	}

	void Error(){
		System.out.println("No hay ningun cliente registrado.");
		espera();
	}

	void mostrarDatos(){

		System.out.println("\nDatos del cliente:\n");
		System.out.printf("Nombre: %s\n",nombre);
		System.out.printf("Domicilio: %s\n", domicilio);
		System.out.printf("Telefono: %s\n", telefono);
		System.out.printf("Edad: %d\n", edad);
		System.out.printf("Saldo: %.2f\n", saldo);

		espera();
		
	}//fin del metodo "mostrarDatos"

	void hacerRetiro(){

		System.out.printf("Ingrese cantidad a retirar:\n");

		movimiento = num.nextFloat();

		if( (saldo - movimiento) < 0 ){
			System.out.printf("\nNo hay fondos suficientes para realizar la accion.\n");
			espera();
		}else{
			saldo -= movimiento;
			System.out.printf("Retiro realizado\n");
			espera();
		}

	}//fin del metodo "hacerRetiro"

	void hacerAbono(){

		System.out.printf("Ingrese cantidad a ingresar:\n");
		saldo += num.nextFloat();
		System.out.printf("Abono realizado\n");
		espera();

	}//fin del metodo "hacer abono"

	void modificarDatos(){

		char c;

		System.out.printf("Seleccione la opcion que desea modificar:\n");
		System.out.printf("a) nombre\tb)domicilio\tc)telefono\td)edad\ne)cancelar\n\n");

		c = pal.nextLine().toUpperCase().charAt(0);
	
		switch( c ){
			case 'A': 
				System.out.printf("\n\nIngrese el nuevo nombre:\n");
				nombre = pal.nextLine(); 
			break;

			case 'B': 
				System.out.printf("\n\nIngrese el nuevo domicilio:\n");
				domicilio = pal.nextLine(); 
			break;

			case 'C': 
				System.out.printf("\n\nIngrese el nuevo telefono:\n");
				telefono = pal.nextLine();
			break;

			case 'D': 
				System.out.printf("\n\nIngrese la nueva edad:\n");
				edad = num.nextInt();
			break;

			case 'E': /* opcion "cancelar" */ break;
			
			default:
				System.out.printf("\n\nOpcion no valida.\n");
		}// fin de switch case

	}//fin del metodo "modificarDatos"

	public static void main(String args[]){

		Cliente cliente1 = new Cliente();

		cliente1.nombre = "Miguel";		
		cliente1.domicilio = "domicilio";
		cliente1.telefono = "13246898";
		cliente1.edad = 23;
		cliente1.saldo = 0.05f;

		cliente1.mostrarDatos();
		cliente1.modificarDatos();
		cliente1.mostrarDatos();
		
	}//fin del metodo main

}//fin de la clase "BancoMillenial"