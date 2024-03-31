class Industria extends Egresado{

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

	String empresa;
	float salario;

	Industria(String r, String n, String d, String c, float p, int s, String date, String emp, float sal){
	super(r, n, d, c, p, s, date);

//		fechaGraduacion = date;	
		empresa = emp;
		salario = sal;

	}//fin del constructor

	void mostrarDatosIndustria(){
		mostrarDatosAlumno();

		System.out.printf("Alumno trabajando en industria, fecha de graduacion: %s", fechaGraduacion);
		System.out.println();
		System.out.printf("Empresa: %s", empresa);
		System.out.println();
		System.out.printf("Salario: %.3f", salario);
		System.out.println();

	}

}//fin de la clase "Industria"