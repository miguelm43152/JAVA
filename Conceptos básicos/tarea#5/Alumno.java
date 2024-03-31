class Alumno{

	//Variables de datos del alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;

	Alumno(String r, String n, String d, String c, float p, int s){

		registro = r;
		nombre = n;
		domicilio = d;
		carrera = c;
		promedio = p;
		semestre = s;

	}

	void	mostrarDatosAlumno(){
		System.out.println();
		System.out.printf("Los datos del alumno de registro %s son:",registro);
		System.out.println();
		System.out.printf("nombre: %s",nombre);
		System.out.println();
		System.out.printf("domicilio: %s",domicilio);
		System.out.println();
		System.out.printf("carrera: %s",carrera);
		System.out.println();
		System.out.printf("promedio: %.2f", promedio);
		System.out.println();
		System.out.printf("semestre: %d",semestre);
		System.out.println();
		
	}

	public static void main(String args[]){
			Alumno al1 = new Alumno("20110144", "Miguel", "calle santa cruz", "mecatronica", 87.06f, 4);
			al1.mostrarDatosAlumno();
	}//fin del metodo main

}//fin de la clase "Alumno"