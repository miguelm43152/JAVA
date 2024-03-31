class CetiWorker extends Egresado{

/*
	//Variables heredadas de alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;
*/

/*
	//variables heredadas de Egresado
	String fechaGraduacion;
*/

	//variables de CetiWorker
	String area;
	int horas;
	float sueldo;

	CetiWorker(String r, String n, String d, String c, float p, int s, String date, String ar, int hrs, float suel){
	super(r, n, d, c, p, s, date);

//		fechaGraduacion = date;	
		
		area = ar;
		horas = hrs;
		sueldo = sueldo;

	}//fin del constructor

	void mostrarDatosCetiWorker(){
		mostrarDatosAlumno();

		System.out.printf("Alumno Ceti Worker, fecha de graduacion: %s", fechaGraduacion);
		System.out.println();
		System.out.printf("Horas: %d", horas);
		System.out.println();
		System.out.printf("Sueldo: %.3f", sueldo);
		System.out.println();

	}


}//fin de la clase "CetiWorker"