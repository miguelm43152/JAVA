class PracticasLaborales extends Alumno{

/*
	//Variables heredadas de alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;
*/

	int horas;
	String empresa;
	float remuneracion;

	PracticasLaborales(String r, String n, String d, String c, float p, int s, int h, String emp, float rem){
	super(r, n, d, c, p, s);
		horas = h;
		empresa = emp;
		remuneracion = rem;
	}//fin del constructor

	void mostrarDatosPracticasLaborales(){
		mostrarDatosAlumno();

		System.out.printf("Alumno realiza practicas laborales, horas totales: %d", horas);
		System.out.println();
		System.out.printf("Empresa de servicio: %s", empresa);
		System.out.println();
		System.out.printf("Cantidad de remuneracion: %.3f", remuneracion);
		System.out.println();
		
	}

}//fin de la clase "PracticasLaborales"