public class CalificarInfo2 {

    public static String getAnsw(String fileName) {
    	//Corregi la antisimetria de la 1 a la 4
    	//Ademas del Rel(X) de un par de respuestas
        switch (fileName) {
            case "respuesta1.txt":
                return "verdadero\nfalso\nfalso\nfalso\nfalso\nfalso\nfalso\nfalso\nverdadero";
            case "respuesta2.txt":
                return "verdadero\nverdadero\nfalso\nfalso\nfalso\nfalso\nfalso\nfalso\nverdadero";
            case "respuesta3.txt":
                return "verdadero\nfalso\nverdadero\nfalso\nfalso\nfalso\nfalso\nfalso\nverdadero";
            case "respuesta4.txt":
                return "verdadero\nverdadero\nverdadero\nverdadero\nfalso\nfalso\nfalso\nfalso\nverdadero";
            case "respuesta5.txt":
                return "verdadero\nverdadero\nverdadero\nverdadero\nverdadero\nverdadero\nverdadero\nverdadero\nverdadero";
            case "respuesta6.txt":
                return "falso\nfalso\nfalso\nfalso\nfalso\nfalso\nfalso\nverdadero\nverdadero";
            case "respuesta7.txt":
                return "falso\nfalso\nfalso\nfalso\nfalso\nverdadero\nfalso\nfalso\nfalso";
            default:
                return ".\n.\n.\n";
        }
    }

    public static void main(String[] args) {
        String[] pruebas = {"ejemplo1.txt", "ejemplo2.txt", "ejemplo3.txt", "ejemplo4.txt", "ejemplo5.txt", "ejemplo6.txt", "ejemplo7.txt"};
        String[] respuestas = {"respuesta1.txt", "respuesta2.txt", "respuesta3.txt", "respuesta4.txt", "respuesta5.txt", "respuesta6.txt", "respuesta7.txt"};
        int aprobadas = 0;
        for (int i = 0; i < 7; i++) {
            if (getAnsw(respuestas[i]).equals(Info.analizar(pruebas[i]))) {
                aprobadas++;
                System.out.println("Prueba " + i + " aprobada.");
            } else {
                System.out.println("Prueba " + i + " incorrecta. ");
                System.out.println("Respuetas esperada: ");
                System.out.println("------------------------------------");
                System.out.print(getAnsw(respuestas[i]));
                System.out.println("------------------------------------");
            }
        }
        System.out.println("Pruebas correctas: " + aprobadas);
    }
}