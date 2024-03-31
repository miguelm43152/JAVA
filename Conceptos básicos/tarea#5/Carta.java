class Carta extends Titulo{

/*
	//Variables heredadas de alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;
*/

/*

	//variables heredadas de Irregular
	String materia;
	float calificacion;

*/

/*
	//variables heredadas de titulo
	float calificacion2;
*/

	float calificacion3;
	String justificacion;

	Carta(String r, String n, String d, String c, float p, int s, String mat, float cal, float cal2, float cal3, String jus){
	super(r, n, d, c, p, s, mat, cal, cal2);
//		materia = mat;
//		calificacion = cal;

//		calificacion2 = cal2;

		calificacion3 = cal3;
		justificacion = jus;

	}//fin del constructor

	void mostrarDatosCarta(){
		mostrarDatosAlumno();

		System.out.printf("Alumno en estado de Carta, materia: %s", materia);
		System.out.println();
		System.out.printf("calificacion: %.3f", calificacion);
		System.out.println();
		System.out.printf("Segunda calificacion: %.3f", calificacion2);
		System.out.println();
		System.out.printf("Tercer calificacion: %.3f", calificacion3);
		System.out.println();
		System.out.printf("Justificacion: %s", justificacion);
		System.out.println();
		
	}

}//fin de la clase "Carta"