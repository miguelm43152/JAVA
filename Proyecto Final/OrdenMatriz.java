import java.lang.Math;//importamos la clase Math para poder usar potencias.
//version 5

//CONSTANTES DE LONGITUDES
//	static final int ANCHO_VENTANA = 900, ALTO_VENTANA = 650; //, ANCHO_E = 150, ALTO_E = 30, ELEMENTOS_X = 3, ELEMENTOS_Y = 5;
//	static final int ANCHO_PANTALLA = 1920, ALTO_PANTALLA = 1080;

// constructor de la clase		
//OrdenMatriz(int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY); //Constructor de la clase

//general pra objetos
//	OrdenMatriz( ANCHO_E, ALTO_E, ANCHO_VENTANA, ALTO_VENTANA, ELEMENTOS_X, ELEMENTOS_Y); //Constructor de la clase

//metodo setOffset
//void setOffset(int desplazamientoInicialX, int desplazamienoInicialY, int desplazamientoFinalX, int desplazamientoFinalY);

//getters
//int getW(){ return w;}
//int getH(){ return h;}

public class OrdenMatriz{

	//Atributos
	
	int w, h, Lx, Ly, nx, ny;
	int x0 = 0,xf = 0,y0 = 0,yf = 0; //variables que restringen el desplazamiento (x o y) inicial y final
	int dx, dy; 
	//L: longitud total (altura o ancho) que encierra a los elementos
	//n: numero de elementos en x o y.

/********************************************************************************************************************************/
/********************************	INICIO		CONSTRUCTOR DE CLASE		*****************************************/			
/********************************************************************************************************************************/

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
		//elementos de las esquinas de la matriz y el central: (1,1) (3,1) (2,2) (3,1) y (3,3

			//calculo de distancia de separacion entre elementos en x cuando no existe
			//un desplazamiento inicial o final diferente de cero. Se asumira que este siempre es el
			//caso y se calcula desde el constructor para evitar realizar mas llamadas a metodos.
			dx = (Lx - w*nx) / ( nx + 1 );
			dy = (Ly - h*ny) / ( ny + 1 );

		}//fin del constructor de la clase

/********************************************************************************************************************************/
/********************************	FIN		CONSTRUCTOR DE CLASE		*****************************************/			
/********************************************************************************************************************************/


/********************************************************************************************************************************/
/********************************	INICIO	METODOS POSICIONAMIENTO OFFSET		*****************************************/			
/********************************************************************************************************************************/

/*****************  Metodos para solicitud de desplazamientos inicial, final y su calculo ************************/

		void setOffset(int desplazamientoInicialX, int desplazamienoInicialY, int desplazamientoFinalX, int desplazamientoFinalY){

			x0 = desplazamientoInicialX;
			xf = desplazamientoFinalX;
			y0 = desplazamienoInicialY;
			yf = desplazamientoFinalY;

			//Calculos en X
			
			calculaDx();

			//calculos en Y

			calculaDy();

		}//fin del metodo setOffset

		void modificaMatriz( int ancho, int alto, int longitudHorizontal, int longitudVertical, int nElementosX, int nElementosY ){

			Lx = longitudHorizontal;
			Ly = longitudVertical;
			nx = nElementosX;
			ny = nElementosY;
			w = ancho;
			h = alto;

			//Calculos en X
			
			calculaDx();

			//calculos en Y

			calculaDy();

		}

		void setElementosX( int nElementosX ){

			nx = nElementosX;

			//Calculos en X
			
			calculaDx();

		}


		void setElementosY( int nElementosY ){

			ny = nElementosY;

			//Calculos en X
			
			calculaDy();

		}

		void calculaDx(){

			if(x0 > 0 && xf > 0)
				dx = (Lx - x0 - xf - nx*w ) / (nx - 1);

			else if( x0 == 0 && xf > 0 )
				dx = (Lx - xf - nx*w) / (nx);

			else if(x0 > 0 && xf == 0 )
				dx = (Lx - x0 - nx*w) / (nx);

			else
				dx = (Lx - w*nx) / ( nx + 1 );

		}//fin de metodo calculaDx.


		void calculaDy(){

			if(y0 > 0 && yf > 0)
				dy = (Ly - y0 - yf - ny*h ) / (ny - 1);

			else if( y0 == 0 && yf > 0 )
				dy = (Ly - yf - ny*h) / (ny);

			else if(y0 > 0 && yf == 0 )
				dy = (Ly - y0 - ny*h) / (ny);

			else
				dy = (Ly - h*ny) / ( ny + 1 );


		}//fin de metodo calculaDy

/********************************************************************************************************************************/
/********************************	FIN	METODOS POSICIONAMIENTO OFFSET		*****************************************/			
/********************************************************************************************************************************/

/********************************************************************************************************************************/
/********************************	INICIO		POSICION ELEMENTO I		*****************************************/			
/********************************************************************************************************************************/

		//metodo para calculo de posicion de un elemento iterado
		int getPosicionX(int iteradorx){

			if( x0 == 0 && xf == 0 )		//opcion default, no hay offset.

				return w*iteradorx + dx*(iteradorx + 1);

			else if(x0 > 0 && xf == 0 )

					return x0 + (w + dx) * iteradorx;

			else if( x0 == 0 && xf > 0 ) 

				return dx * (iteradorx + 1) + w*iteradorx;

			else{ //y0 > 0 && yf > 0
				
				if(iteradorx == 0) return x0;
				else return x0 + (w + dx) * iteradorx;
			
			}

		}//FIN METODO getPosicionX

/*------------------------------------------------------------------------------------------------------------------------------*/

		int getPosicionY(int iteradory){

			if( y0 == 0 && yf == 0 )		//opcion default, no hay offset.

				return h*iteradory + dy*(iteradory + 1);

			else if(y0 > 0 && yf == 0 )

					return y0 + (h + dy) * iteradory;

			else if( y0 == 0 && yf > 0 ) 

				return dy * (iteradory + 1) + h*iteradory;

			else{ //y0 > 0 && yf > 0
				
				if(iteradory == 0) return y0;
				else return y0 + (h + dy) * iteradory;
			
			}

/*------------------------------------------------------------------------------------------------------------------------------*/

		}//FIN METODO getPosicionY

		//usamos la sobre carga de POO para definir un "default" que nos permita obtener una posicion inicial por defecto
		int getPosicionX(){
			return x0 + dx;
		}
		int getPosicionY(){
			return y0 + dy;
		}

		int getPXLast(){  //DEVUELVE EL VALOR X DE LA POSICION DEL ULTIMO ELEMENTO
			return getPosicionX(nx) - dx;
		}
		int getPYLast(){
			return getPosicionY(ny) - dy;
		}

/********************************************************************************************************************************/
/********************************	FIN		POSICION ELEMENTO I		*****************************************/			
/********************************************************************************************************************************/

		//getters para ancho y alto
		int getW(){ return w;}
		int getH(){ return h;}

		void imprimedXdY(){
			System.out.println("dX = " + this.dx);
			System.out.println("dY = " + this.dy);
		}

		void imprimeTamagnoMatriz(){
			System.out.println("X = " + (getPosicionX(nx) - dx));
			System.out.println("Y = " + (getPosicionY(ny) - dy));
		}

		int getTamagnoX(){ return (getPosicionX(nx) - dx); }
		int getTamagnoY(){ return (getPosicionY(ny) - dy); }

		boolean calculaAdjunto(int iteradori, int iteradorj){ //regresa un valor verdadero para elementos positivos (esquina y centro) y falso para aristas.
			boolean b;
			if( Math.pow(-1,iteradori + iteradorj) > 0 ) b = true; else b = false;
			return b;
		}

}//fin de clase OrdenMatriz