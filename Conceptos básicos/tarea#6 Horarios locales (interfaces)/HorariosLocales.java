interface InterfaceHorarios{

   public void horaLocal();
   public void horaGMT();

}// Fin de la interface

/*************************************************/

class LaPaz implements InterfaceHorarios{

	public void horaLocal(){
		System.out.println("La hora local en La Paz es XX:XX");
	}
	public void horaGMT(){};

}// Fin de la clase "LaPaz"

/*************************************************/

class Guadalajara implements InterfaceHorarios{

	public void horaLocal(){
		System.out.println("La hora local en Guadalajara es XX:XX");
	}

	public void horaGMT(){};

}// Fin de la clase "Guadalajara"

/*************************************************/

class Tijuana implements InterfaceHorarios{

	public void horaLocal(){};

	public void horaGMT(){
		System.out.println("La hora GMT en Tijuana es XX:XX");
	}

}// Fin de la clase "LaPaz"

/*************************************************/

/*****************  Note como la clase principal esta hasta el final del documento              ********************************/
/*****************  Sin embargo el nombre del archivo debe de ser el mismo nombre de esta clase ********************************/
/*****************  debido a que esta clase es la que contiene el metodo main                   ********************************/

public class HorariosLocales{
	
	public static void main(String args[]){

		LaPaz lp = new LaPaz();
		Guadalajara g = new Guadalajara();
		Tijuana t = new Tijuana();

		lp.horaLocal();
		g.horaLocal();
		t.horaGMT();

	}//fin del metodo main

}//fin de la clase "HorariosLocales"
