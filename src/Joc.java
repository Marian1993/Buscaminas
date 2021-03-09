public class Joc {

    public static void main(String[]args){

        int [][] numeros  = new int[8][8];

        numeros = Tablero.rellenarTablero(numeros);
        Tablero.imprimirArray(numeros);
    }
}
