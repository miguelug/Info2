import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Info {
	public static String[] X = null;
	public static String[] Y = null;
	public static ArrayList<ArrayList<String>> Rel = null;
    public static String analizar(String file){
        /*
            Su código aquí
             ____  ____  __    ____  ____
            ( ___)( ___)(  )  (_  _)(_   )
             )__)  )__)  )(__  _)(_  / /_
            (__)  (____)(____)(____)(____)

             ____  ____  _____  ___  ____    __
            (  _ \(  _ \(  _  )/ __)(  _ \  /__\
             )___/ )   / )(_)(( (_-. )   / /(__)\
            (__)  (_)\_)(_____)\___/(_)\_)(__)(__)

         */
    	Rel = new ArrayList<ArrayList<String>>();
    	BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/" +file));
	    	
	    	try {
	    	    String linea = br.readLine();
	    	    int aux = 0;
	    	    while (linea != null) {
	    	    	aux++;
	    	    	popularVariables(linea, aux);
	    	        linea = br.readLine();
	    	    
	    	    }
	    	    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"+file + "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
	    	    System.out.println("X="+Arrays.toString(X));
	    	    System.out.println("Y="+Arrays.toString(Y));
	    	    System.out.print("Rel(X, Y)={");
	    	    for(int i = 0; i< Rel.size(); i++){
	    	    	System.out.print("("+Rel.get(i).get(0)+", "+Rel.get(i).get(1)+")");
	    	    	if(i != (Rel.size() - 1)){
	    	    		System.out.print(",");
	    	    	}
	    	    }
	    	    System.out.println("}");
	    	} finally {
	    	    br.close();
	    	}
    	
		} catch (IOException e) {
			e.printStackTrace();
		};
		//************-Inician validaciones-****************//
		String esGrafica = (new Grafica(X, Y, Rel).esGrafica()) ? "verdadero" : "falso";
		String esInyectiva = ((new Inyectividad(X,Y,Rel)).esInyectiva()) ? "verdadero": "falso";
		String esSobreyectiva = ((new Sobreyectiva(X, Y, Rel)).esSobreyectiva())? "verdadero":"falso";
		String esBiyectiva = (esInyectiva.equals("verdadero") && esSobreyectiva.equals("verdadero"))? "verdadero" : "falso";
		//Si es biyectiva, puedo calcular su funcion inversa.
		if(esBiyectiva.equals("verdadero")){
			System.out.println("Biyectividad - Es biyectiva");
			(new Inversa(Rel)).ImprimirInversa();
		}
		else{
			System.out.println("Biyectividad - NO Es biyectiva");
		}
		String esRelX = ((new RelX(Rel)).esRelX()) ? "verdadero" : "falso";
		String esReflexiva = ((new Refexividad(X,Rel)).esReflexiva())? "verdadero":"falso";
		String esSimetrica = ((new Simetria(Rel)).esSimetrica()) ? "verdadero":"falso";
		String esTransitiva = ((new Transitiva(Rel)).esTransitiva()) ? "verdadero":"falso";
		String esAntisimetria = ((new Antisimetria(Rel)).esAntisimetrica()) ? "verdadero" : "falso";
        String respuesta = "";
        respuesta = esGrafica + "\n"; // Pregunta 1
        respuesta = respuesta + esInyectiva + "\n"; // Pregunta 2
        respuesta = respuesta + esSobreyectiva + "\n"; // Pregunta 3
        respuesta = respuesta + esBiyectiva + "\n"; // Pregunta 4
        respuesta = respuesta + esRelX + "\n"; // Pregunta 5
        respuesta = respuesta + esReflexiva +"\n"; // Pregunta 6
        respuesta = respuesta + esSimetrica +"\n"; // Pregunta 7
        respuesta = respuesta + esTransitiva +"\n"; // Pregunta 8
        respuesta = respuesta + esAntisimetria; // Pregunta 9
        /*System.out.println("RESPUESTA:");
        System.out.println(respuesta);
        System.out.println("FIN RESPUESTA:");*/
        return respuesta;
    }
    public static int pos = 0;
    private static void popularVariables(String linea, int aux){
    	if(aux == 1){
    		X = linea.split(" ");
    	}
    	else if(aux ==2){
    		Y = linea.split(" ");
    	}
    	else{
    		String[] auxStr = linea.split(" ");
    		if(auxStr.length == 2){
    			ArrayList<String> tmp = new ArrayList<String>();
    			tmp.add(auxStr[0]);
    			tmp.add(auxStr[1]);
    			Rel.add(tmp);
    			pos++;
    		}
    	}
    }
}

class Grafica {
	private String[] X = null;
	private String[] Y = null;
	private ArrayList<ArrayList<String>> Rel = null;
	private boolean Print = true;
	public Grafica(String[]x, String[]y, ArrayList<ArrayList<String>> rel){
		this.X = x;
		this.Y = y;
		this.Rel = rel;
		this.Print = true;
	}
	public Grafica(String[]x, String[]y, ArrayList<ArrayList<String>> rel, boolean prints){
		this.X = x;
		this.Y = y;
		this.Rel = rel;
		this.Print = prints;
	}
	public boolean esGrafica(){
		//Creo un hash map donde como llave utilizo el elemento de mi conjunto X, y como valor la cantidad de veces que aparece en mi relacion
		HashMap<String,Integer> hm =new HashMap<String,Integer>();
		for(int i = 0; i < X.length; i++){
			hm.put(X[i], 0);
		}
		for(int i = 0; i< Rel.size(); i++){
			//Si el primer valor de mi par ordenado no se encuentra en el dominio, no es grafica de la funcion
			if(!Arrays.asList(X).contains(Rel.get(i).get(0))){
				if (Print)
				System.out.println("Grafica - X:" + Rel.get(i).get(0));
				return false;
			}
			//Si el segundo valor de mi par ordenado no se encuentra en el contradomino, no es grafica de la funcion
			if(!Arrays.asList(Y).contains(Rel.get(i).get(1))){
				if (Print)
				System.out.println("Grafica - Y:" + Rel.get(i).get(0));
				return false;
			}
			//Busco en mi hashmap la llave y aumento el valor
			hm.put(Rel.get(i).get(0),  hm.get(Rel.get(i).get(0))+ 1);
			if(hm.get(Rel.get(i).get(0)) > 1){
				if (Print)
				System.out.println("Grafica - Encontro mas de 1 valor para X=" + Rel.get(i).get(0) +", Ocurrencias:" + hm.get(Rel.get(i).get(0)));
				//Si encontro mas de dos valores entonces no es grafica
				return false;
			}
		}
		if (Print)
		System.out.println("Grafica - es Grafica");
		return true;
	}
}

class Inyectividad {
	private String[] X = null;
	private String[] Y = null;
	private ArrayList<ArrayList<String>> Rel = null;
	public Inyectividad(String[]x, String[]y, ArrayList<ArrayList<String>> rel){
		this.X = x;
		this.Y = y;
		this.Rel = rel;
	}
	public boolean esInyectiva(){
		if(!(new Grafica(X,Y,Rel, false).esGrafica())){
			System.out.println("Inyectividad - No es inyectiva por  que no es funcion");
			//Si no es grafica no es funcion
			return false;
		}
		//Por el principio del palomar
		if(X.length > Y.length){
			System.out.println("Inyectividad - No es inyectiva por el principio de las casillas");
			return false;
		}
		//Creo un hash map donde como llave utilizo el elemento de mi conjunto Y, y como valor la cantidad de veces que aparece en mi relacion
		HashMap<String,Integer> hm =new HashMap<String,Integer>();
		for(int i = 0; i < Y.length; i++){
			hm.put(Y[i], 0);
		}
		
		for(int i = 0; i< Rel.size(); i++){
			//Busco en mi hashmap la llave y aumento el valor
			hm.put(Rel.get(i).get(1),  hm.get(Rel.get(i).get(1))+ 1);
			
			if(hm.get(Rel.get(i).get(1)) > 1){
				System.out.println("Inyectividad - Encontro mas de 1 valor para Y=" + Rel.get(i).get(1) +", Ocurrencias:" + hm.get(Rel.get(i).get(1)));
				return false;
			}
		}
		System.out.println("Inyectividad - es Inyectiva");
		return true;
	}
}

class Sobreyectiva {
	private String[] X = null;
	private String[] Y = null;
	private ArrayList<ArrayList<String>> Rel = null;
	public Sobreyectiva(String[]x, String[]y, ArrayList<ArrayList<String>> rel){
		this.X = x;
		this.Y = y;
		this.Rel = rel;
	}
	public boolean esSobreyectiva(){
		if(!(new Grafica(X,Y,Rel,false).esGrafica())){
			//Si no es grafica no es funcion
			System.out.println("Sobreyectiva - No es sobreyectiva por  que no es funcion");
			return false;
		}
		HashMap<String,Integer> hm =new HashMap<String,Integer>();
		for(int i = 0; i < Y.length; i++){
			hm.put(Y[i], 0);
		}
		for(int i = 0; i< Rel.size(); i++){
			//Busco en mi hashmap la llave y aumento el valor
			hm.put(Rel.get(i).get(1),  hm.get(Rel.get(i).get(1))+ 1);
		}
		for(int i = 0; i < Y.length; i++){
			if(hm.get(Y[i])==0){
				System.out.println("Sobreyectiva - Y= " + Y[i] +", no se encuentra en la relacion");
				return false;
			}
		}
		System.out.println("Sobreyectiva - es Sobreyectiva");

		return true;
	}
}

class Inversa {
	private ArrayList<ArrayList<String>> Rel = null;
	public Inversa(ArrayList<ArrayList<String>> rel){
		this.Rel = rel;
	}
	public void ImprimirInversa(){
		 System.out.print("Inversa={");
 	    for(int i = 0; i< Rel.size(); i++){
 	    	System.out.print("("+Rel.get(i).get(1)+", "+Rel.get(i).get(0)+")");
 	    	if(i != (Rel.size() - 1)){
 	    		System.out.print(",");
 	    	}
 	    }
 	    System.out.println("}");
	}
}

class RelX {
	private ArrayList<ArrayList<String>> Rel = null;
	public RelX(ArrayList<ArrayList<String>> rel){
		this.Rel = rel;
	}
	public boolean esRelX(){
		for(int i = 0; i< Rel.size(); i++){
			//Si el primer valor de mi par ordenado es diferente a mi segundo valor del par ordenado, no es  RelX
			if(!Rel.get(i).get(0).equals(Rel.get(i).get(1))){ 
				System.out.println("RelX - NO es RelX");
				return false;
			}
		}
		System.out.println("RelX - es RelX");
		return true;
	}
}

class Refexividad {
	private String[] X = null;
	private ArrayList<ArrayList<String>> Rel = null;
	public Refexividad(String[]x, ArrayList<ArrayList<String>> rel){
		this.X = x;
		this.Rel = rel;
	}
	
	public boolean esReflexiva(){
		HashMap<String,Integer> hm =new HashMap<String,Integer>();
		for(int i = 0; i < X.length; i++){
			hm.put(X[i], 0);
		}
		for(int i = 0; i< Rel.size(); i++){
			//Si el primer elemento de mi par ordenado es igual al segundo, aumento las apariciones en mi hash
			if(Rel.get(i).get(0).equals(Rel.get(i).get(1))){
				//Busco en mi hashmap la llave y aumento el valor
				hm.put(Rel.get(i).get(0),  hm.get(Rel.get(i).get(0))+ 1);
			}
		}
		
		for(int i = 0; i < X.length; i++){
			if(hm.get(X[i])==0 || hm.get(X[i]) > 1){
				System.out.println("Reflexividad - el par (" + X[i] +","+X[i]+") no se encuentra");
				return false;
			}
		}
		System.out.println("Reflexividad - es Reflexiva");
		return true;
	}
}

class Simetria {
	private ArrayList<ArrayList<String>> Rel = null;
	public Simetria(ArrayList<ArrayList<String>> rel){
		this.Rel = rel;
	}
	
	public boolean esSimetrica(){
		for(int i = 0; i< Rel.size(); i++){
			boolean encontroSimetria = false;
			for(int j = 0; j< Rel.size(); j++){
				if(Rel.get(i).get(0).equals(Rel.get(j).get(1)) && Rel.get(i).get(1).equals(Rel.get(j).get(0))){
					encontroSimetria = true;
				}
			}
			if(!encontroSimetria){
				System.out.println("Simetria - NO cumple simetria el par ("+Rel.get(i).get(0)+", "+Rel.get(i).get(1)+")");
				return false;
			}
		}
		System.out.println("Simetria - es Simetrica");
		return true;
	}
}

class Transitiva {
	private ArrayList<ArrayList<String>> Rel = null;
	public Transitiva(ArrayList<ArrayList<String>> rel){
		this.Rel = rel;
	}
	//V x,y,z E Rel | (x,y) E Rel y (y,z) E Rel  Entonces (x,z) E Rel
	public boolean esTransitiva(){//x

		//boolean esTransitiva = false;
		int aux = 0;
		for(int i = 0; i< Rel.size(); i++){
			for(int j = 0; j< Rel.size(); j++){ //y 
				int aux2 = 0;
				//System.out.println(Rel.get(i).get(1).equals(Rel.get(j).get(0)) + " x:" + Rel.get(i).get(1) + ", y:"+Rel.get(j).get(0));
				if(Rel.get(i).get(1).equals(Rel.get(j).get(0))){ // Si  (y,z) E Rel
					aux++;
					for(int x = 0; x < Rel.size(); x++){//z
						if(Rel.get(i).get(0).equals(Rel.get(x).get(0)) && Rel.get(j).get(1).equals(Rel.get(x).get(1)) ){ // Entonces  (x,z) E Rel
							aux2++;
						}
						if(aux2 > 0){
							break;
						}
					}
					if (aux2 == 0 && (j == Rel.size() - 1)){
						System.out.println("Transitividad - Para ("+Rel.get(i).get(0)+", "+Rel.get(j).get(0)+", "+Rel.get(j).get(1)+")   No es transitiva");
						return false;
					}
				}
			}
		}
		if (aux == 0){
			System.out.println("Transitividad -  No es transitiva");
			return false;
		}
		System.out.println("Transitividad - es Transitiva");
		return true;
	}
}


class Antisimetria {
	private ArrayList<ArrayList<String>> Rel = null;
	public Antisimetria(ArrayList<ArrayList<String>> rel){
		this.Rel = rel;
	}
	
	public boolean esAntisimetrica(){
		for(int i = 0; i< Rel.size(); i++){
			boolean encontroSimetria = true;
			for(int j = 0; j< Rel.size(); j++){
				if(Rel.get(i).get(0).equals(Rel.get(j).get(1)) && Rel.get(i).get(1).equals(Rel.get(j).get(0))){
					if(!Rel.get(i).get(0).equals(Rel.get(i).get(1))){
						encontroSimetria = false;
					}
				}
			}
			if(!encontroSimetria){
				System.out.println("Antisimetria - NO cumple Antisimetria el par ("+Rel.get(i).get(0)+", "+Rel.get(i).get(1)+")");
				return false;
			}
		}
		System.out.println("Antisimetria - es Antisimetrica");
		return true;
	}
}