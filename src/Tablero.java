public class Tablero {

    int [][] tablero;

    public Tablero(int [][] tablero){

        this.tablero = tablero;
    }

    public static int [][] rellenarTablero(int [][] numeros){

        int [][] num = numeros;

        for (int i = 0; i < num.length; i++) {

            for (int j = 0; j < num.length; j++) {
                num[i][j] = 0;
            }
        }
        return num;
    }
    public static void imprimirArray(int [][] numeros){

        String[][]tapaTablero = new String[numeros.length][numeros.length];
        for (int i = 0; i < numeros.length; i++) {

            for (int j = 0; j < numeros.length; j++) {

                tapaTablero[i][j] = "▀";
                System.out.print(numeros[i][j]);
                System.out.print(tapaTablero[i][j]);

                System.out.print("   ");
            }
            System.out.println();
        }
    }
    /*public static String[][] rellenarTapaTablero(int [][] numeros){

        String[][]tapaTablero = new String[numeros.length][numeros.length];

        for (int i = 0; i < tapaTablero.length; i++) {

            for (int j = 0; j < tapaTablero.length; j++) {

                tapaTablero[i][j] = "223";

            }
        }
       return tapaTablero;
    }*/


}
