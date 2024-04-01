import java.lang.Math;//importamos la clase Math para poder usar potencias.

public class OrdenMatriz{

	//Atributos
	
	int w, h, Lx, Ly, nx, ny;
	int dx, dy; 
	//L: longitud total (altura o ancho) que encierra a los elementos
	//n: numero de elementos en x o y.

		OrdenMatriz(int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY){ //Constructor de la clase

			Lx = longitudHorizontal;
			Ly = longitudVertical;
			nx = nElementosX;
			ny = nElementosY;
			w = ancho;
			h = alto;

		//dada la ecuacion para el acomodo de ventanas L = n + x(n + 1)
		//donde 
		//n: numero de ventanas; x: espacio entre ventanas; L: longitud de pantalla; w: ancho de ventana.
		//L es una cantidad constante y n es el numero requerido para las ventanas.
		//La solucion del problema sera crear una matriz de 3x3 y mostrar solo los
		//elementos de las esquinas de la matriz y el central: (1,1) (3,1) (2,2) (3,1) y (3,3)


			//calculo de distancia de separacion entre elementos en x e y.
			dx = (Lx - w*nx) / ( nx + 1 );
			dy = (Ly - h*ny) / ( ny + 1 );

		}//fin del constructor de la clase

		//getters para ancho y alto
		int getW(){ return w;}
		int getH(){ return h;}

		//metodo para calculo de posicion de un elemento iterado
		int getPosicionX(int iteradorx){
			return w*iteradorx + dx*(iteradorx + 1);
		}
		int getPosicionY(int iteradory){
			return h*iteradory + dy*(iteradory + 1);
		}

		boolean calculaAdjunto(int iteradori, int iteradorj){ //regresa un valor verdadero para elementos positivos (esquina y centro) y falso para aristas.
			boolean b;
			if( Math.pow(-1,iteradori + iteradorj) > 0 ) b = true; else b = false;
			return b;
		}

}//fin de clase OrdenMatriz