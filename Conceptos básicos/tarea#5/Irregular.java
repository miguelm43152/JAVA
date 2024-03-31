class Irregular extends Alumno{

/*
	//Variables heredadas de alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;
*/

	//variables de Irregular
	String materia;
	float calificacion;

	Irregular(String r, String n, String d, String c, float p, int s, String mat, float cal){
	super(r, n, d, c, p, s);
		materia = mat;
		calificacion = cal;
	}//fin del constructor

	void mostrarDatosIrregular(){
		mostrarDatosAlumno();

		System.out.printf("Alumno Irregular, materia: %s", materia);
		System.out.println();
		System.out.printf("Calificacion: %.3f", calificacion);
		System.out.println();
		
	}

}//fin de la clase "Irregular"