class Becado extends Alumno{

/*
	//Variables heredadas de alumno

	String registro, nombre, domicilio, carrera;
	float promedio;
	int semestre;
*/

	//variables de dato becado
	
	float monto;

	Becado(String r, String n, String d, String c, float p, int s, float m){
	super(r, n, d, c, p, s);
		monto = m;
	}//fin del constructor

	void mostrarDatosBecado(){
		mostrarDatosAlumno();

		System.out.printf("Alumno becado, monto de la beca: %.3f", monto);
		System.out.println();
		
	}

}//fin de la clase Becado