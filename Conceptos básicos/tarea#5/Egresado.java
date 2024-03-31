class Egresado extends Alumno{

/*
	//Variables heredadas de alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;
*/

	//variables de Egresado
	String fechaGraduacion;

	Egresado(String r, String n, String d, String c, float p, int s, String date){
	super(r, n, d, c, p, s);

		fechaGraduacion = date;		

	}//fin del constructor

	void mostrarDatosEgresado(){
		mostrarDatosAlumno();

		System.out.printf("Alumno Egresado, fecha de graduacion: %s", fechaGraduacion);
		System.out.println();
	}

}//fin de la clase "Egresado"