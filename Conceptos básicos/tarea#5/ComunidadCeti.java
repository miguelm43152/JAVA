import java.io.IOException;
import java.util.Scanner;

class ComunidadCeti{

		void espera(){
			
			Scanner p = new Scanner(System.in);
			System.out.println("Presione 0 para continuar");
			p.nextInt();
		}

	public static void main(String args[]) throws IOException, InterruptedException {

		ComunidadCeti aux = new ComunidadCeti();

		Alumno al1 = new Alumno("20110144", "Miguel", "calle santa cruz", "mecatronica", 87.06f, 4);
		Becado al2 = new Becado("20110145", "David", "colonia santa fe", "informatica", 96f, 6, 3000);
		ServicioSocial al3 = new ServicioSocial("20110156", "Axel", "chulavista", "diseno electronico", 93f, 5, 80, "area");
		PracticasLaborales al4 = new PracticasLaborales("20130162", "Juan", "calle azul", "mecatronica", 80.0f, 3, 100, "bimbo", 1500);

		Irregular al5 = new Irregular("20100360", "Jose", "calle verde", "informatica", 95f, 3 ,"programacion", 68);
		Titulo al6 = new Titulo("20160660", "Daniel", "calle rosa", "diseno electronico", 95f, 5 ,"programacion", 30, 60);
		Carta al7 = new Carta("20200360", "Rosa", "calle blanca", "mecatronica", 80.02f, 3, "calculo", 66, 35, 55, "laboral");

		Egresado al8 = new Egresado("20300625", "Julian", "calle cafe", "ing industrial", 96, 8, "13/07/2022");
		CetiWorker al9 = new CetiWorker("21110350", "Laura", "calle gris", "ing industrial", 89, 8, "13/07/2020","mantenimiento", 100, 3000);
		Industria al10 = new Industria("20363600", "Guadalupe", "calle blanca", "ing industrial", 76, 8, "13/01/2019", "bimbo", 5000);

			al1.mostrarDatosAlumno();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al2.mostrarDatosBecado();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al3.mostrarDatosServicioSocial();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al4.mostrarDatosPracticasLaborales();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al5.mostrarDatosIrregular();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al6.mostrarDatosTitulo();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al7.mostrarDatosCarta();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al8.mostrarDatosEgresado();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al9.mostrarDatosCetiWorker();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

			al10.mostrarDatosIndustria();
			aux.espera();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Clear Screen (cls)

	}

}//fin de la clase "ComunidadCeti"
