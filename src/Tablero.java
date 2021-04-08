import java.util.Scanner;

public class Tablero{

    private static Scanner sc =  new Scanner(System.in);

    private Casella[][] tablero;
    private int x;
    private int y;
    private int quantitatMines = 0;
    private int numeros = 0;

    public Tablero() {

    }


    public void inicialitzarTablero(int dificultat) {

        if(dificultat == Dificultat.PRINCIPIANT){
            y = 8;
            x= 8;
        }
        if (dificultat == Dificultat.INTERMITG){
            y = 16;
            x= 16;
        }
        if (dificultat == Dificultat.EXPERT){
            y = 16;
            x= 30;
        }
        if (dificultat == Dificultat.PERSONALITZAT){

            System.out.print("Altura: ");
            y= sc.nextInt();
            System.out.print("Amplada: ");
            x= sc.nextInt();

        }
        this.tablero =  new Casella[y][x];

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Casella();
            }
        }
        mines(dificultat);

    }


    public void mines(int dificultat) {

        if(dificultat == 0){
            System.out.print("Mines: ");
            int mines = sc.nextInt();
            while (quantitatMines < mines) {

                int x = (int) (Math.random() * tablero.length);
                int y = (int) (Math.random() * tablero.length);

                if (!tablero[x][y].isMina()) {
                    tablero[x][y].setMina(true);

                    quantitatMines++;
                }
            }

        }else {
            while (quantitatMines < Dificultat.nivellDificultat(dificultat)) {

                int x = (int) (Math.random() * tablero.length);
                int y = (int) (Math.random() * tablero.length);

                if (!tablero[x][y].isMina()) {
                    tablero[x][y].setMina(true);

                    quantitatMines++;
                }
            }
        }
        numerosTaulell();
        imprimirTaulell();
    }


    public void imprimirTaulell() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if (!tablero[i][j].isMina()) {
                    System.out.print(0);
                }
                if (tablero[i][j].isMina()) {
                    System.out.print('*');
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public void numeros() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                int numero = 0;

                if (i != 0) {
                    if (tablero[i - 1][j].isMina()) {
                        numero++;
                        tablero[i - 1][j].setMinasCercanas(numero);
                        numero = 0;

                    }
                    if (tablero[i - 1][j - 1].isMina()) {
                        numero++;
                        numero = 0;
                    }
                    if (tablero[i - 1][j + 1].isMina()) {
                        numero++;
                        numero = 0;
                    }
                }

                if (tablero[i][j - 1].isMina()) {
                    numero++;
                    numero = 0;
                }
                if (i == -1) {

                    if (tablero[i + 1][j - 1].isMina()) {
                        numero++;
                        numero = 0;
                    }
                    if (tablero[i + 1][j].isMina()) {
                        numero++;
                        numero = 0;
                    }

                }
                if (i == -1 || j == -1) {
                    if (tablero[i + 1][j + 1].isMina()) {
                        numero++;
                        numero = 0;
                    }
                    if (tablero[i][j + 1].isMina()) {
                        numero++;
                        numero = 0;
                    }
                }
            }
        }
    }

    public void numerosTaulell(){

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                int numero = 0;
                if(tablero[i][j].isMina()) {

                    if(!(i == 0)){

                        if (!(tablero[i - 1][j].isMina())) {
                            numero++;
                        }
                        if (!(tablero[i - 1][j - 1].isMina()) && !(j == 0)) {
                            numero++;
                        }
                        if (!(tablero[i - 1][j + 1].isMina()) && !(j == tablero[j].length-1)) {
                            numero++;
                        }
                    }
                    if (!(tablero[i][j - 1].isMina()) && !(j ==0)) {
                        numero++;
                    }
                    if (!(tablero[i][j + 1].isMina()) && !(j == tablero[j].length-1)) {
                        numero++;
                    }
                    if(!(i == tablero[i].length-1)){
                        if (!(tablero[i + 1][j].isMina())) {
                            numero++;
                        }
                        if (!(tablero[i + 1][j - 1].isMina()) && !(j ==0)) {
                            numero++;
                        }
                        if (!(tablero[i + 1][j + 1].isMina()) && !(j == tablero[j].length-1)) {
                            numero++;
                        }
                    }

                }

            }

        }
    }
}