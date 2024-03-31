class ServicioSocial extends Alumno{

/*
	//Variables heredadas de alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;
*/

	//variables servicio social
	
	int horas;
	String area;

	ServicioSocial(String r, String n, String d, String c, float p, int s, int h, String a){
	super(r, n, d, c, p, s);
		horas = h;
		area = a;
	}//fin del constructor

	void mostrarDatosServicioSocial(){
		mostrarDatosAlumno();

		System.out.printf("Alumno realiza su servicio social, horas totales: %d", horas);
		System.out.println();
		System.out.printf("Area de servicio: %s", area);
		System.out.println();
		
	}

}//fin de la clase Servicio Social