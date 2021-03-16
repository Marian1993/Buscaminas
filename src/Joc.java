public class Joc {

    public static void main(String[]args){

        int [][] numeros  = new int[8][8];

        Tablero tablero = new Tablero(8, 8);

        tablero.inicialitzarTablero();

        tablero.imprimirTablero();
    }
}
