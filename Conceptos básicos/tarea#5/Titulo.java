class Titulo extends Irregular{

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

	float calificacion2;

	Titulo(String r, String n, String d, String c, float p, int s, String mat, float cal, float cal2){
	super(r, n, d, c, p, s, mat, cal);
//		materia = mat;
//		calificacion = cal;

		calificacion2 = cal2;
	}//fin del constructor

	void mostrarDatosTitulo(){
		mostrarDatosAlumno();

		System.out.printf("Alumno en estado de Titulo, materia: %s", materia);
		System.out.println();
		System.out.printf("calificacion: %.3f", calificacion);
		System.out.println();
		System.out.printf("Segunda calificacion: %.3f", calificacion2);
		System.out.println();
		
	}

}//fin de la clase "Titulo"