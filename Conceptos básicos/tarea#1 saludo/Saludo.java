public class Saludo {

	String texto;

	void enviaMensajeMatutino(){
		
		texto = "Buenos dias";
		System.out.println(texto);

	} //fin del primer metodo

	void enviaMensajeVespertino(){
		
		texto = "Buenas tardes";
		System.out.println(texto);

	} //fin del segundo metodo

	void enviaMensajeNocturno(){
		
		texto = "Buenas noches";
		System.out.println(texto);

	} //fin del tercer metodo

	public static void main(String args[]){

		Saludo objeto1 = new Saludo();
		Saludo objeto2 = new Saludo();
		Saludo objeto3 = new Saludo();

		objeto1.enviaMensajeMatutino();
		objeto2.enviaMensajeVespertino();
		objeto3.enviaMensajeNocturno();

	} // Fin del main

}// Fin de la clase HolaMundoOO
