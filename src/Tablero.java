import java.util.InputMismatchException;
import java.util.Scanner;

public class Tablero {

    private static Scanner sc = new Scanner(System.in);

    private Casella[][] tablero;
    private int x;
    private int y;
    private int quantitatMines = 0;
    private int numeros = 0;

    public Tablero() {

    }


    public void inicialitzarTablero(int dificultat) {

        if (dificultat == Dificultat.PRINCIPIANT) {
            y = 8;
            x = 8;
        }
        if (dificultat == Dificultat.INTERMITG) {
            y = 16;
            x = 16;
        }
        if (dificultat == Dificultat.EXPERT) {
            y = 16;
            x = 30;
        }
        if (dificultat == Dificultat.PERSONALITZAT) {

            System.out.print("Altura: ");
            y = sc.nextInt();
            System.out.print("Amplada: ");
            x = sc.nextInt();

        }
        this.tablero = new Casella[x][y];

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Casella();
            }
        }
        colocarmines(dificultat);

    }
    public void desteparCasella(int x,int y){

        tablero[x][y].isVisible();
    }
    public void colocarBandera(int x, int y){

        tablero[x][y].isBandera();
    }


    public void colocarmines(int dificultat) {

        if (dificultat == 0) {
            System.out.print("Mines: ");
            int mines = sc.nextInt();
            while (quantitatMines < mines) {

                int x = (int) (Math.random() * this.x);
                int y = (int) (Math.random() * this.y);

                if (!tablero[x][y].isMina()) {
                    tablero[x][y].setMina(true);

                    quantitatMines++;

                    colocarNumeroAdyadcents(x,y);
                }
            }

        } else {
            while (quantitatMines < Dificultat.nivellDificultat(dificultat)) {

                int x = (int) (Math.random() * this.x);
                int y = (int) (Math.random() * this.y);

                if (!tablero[x][y].isMina()) {
                    tablero[x][y].setMina(true);

                    quantitatMines++;

                    colocarNumeroAdyadcents(x,y);
                }
            }
        }
        imprimirTaulell2();
    }

    private void imprimirTaulell() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if (!tablero[i][j].isMina()) {
                    System.out.print(tablero[i][j].getValorCelda());

                }else if (tablero[i][j].isMina()) {
                    System.out.print('*');
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public void entradaTaulell(){

        try {

            System.out.println("Colocar bandera 1" + "\nDestepar casella 2");
            int entrada = sc.nextInt();

            switch (entrada){

                case 1:
                    System.out.print("Posa el número de la fila");
                    int banderaX = sc.nextInt();
                    System.out.print("Posa el número de la columna");
                    int banderaY = sc.nextInt();
                    colocarBandera(banderaX,banderaY);
                    break;
                case 2:
                    System.out.print("Posa el número de la fila");
                    int desteparX = sc.nextInt();
                    System.out.print("Posa el número de la columna");
                    int desteparY = sc.nextInt();
                    desteparCasella(desteparX,desteparY);
                    break;

                default:
                    System.out.println("No has introduit el número corresponent");
                    entradaTaulell();

            }
        }catch (InputMismatchException e){
            System.out.println("No has introduit el número corresponent");
            entradaTaulell();
        }
    }

    private void imprimirTaulell2() {


        for (int x = 0; x < tablero.length; x++) {

            for (int y = 0; y < tablero[x].length; y++) {

                if (!tablero[x][y].isVisible()) {
                    System.out.print("■");

                } else if (tablero[x][y].isVisible()) {

                    if (tablero[x][y].isMina()) {
                        System.out.println("*");

                    } else if (tablero[x][y].getValorCelda() != 0) {
                        System.out.print(tablero[x][y].getValorCelda());

                    } else {
                        eliminarCerosAdyadcentes(x, y);
                        System.out.print(" ");
                    }
                    if (tablero[x][y].isBandera()) {
                        System.out.print("¶");

                    }
                } else if (tablero[x][y].isMina()) {
                    System.out.println("*");
                }
                System.out.print("  ");

            }
            System.out.println();
        }
    }
    private void eliminarCerosAdyadcentes(int x, int y){

        for (int  amplada = x-1 ; amplada <= x+1; amplada++) {

            for (int altura = y-1; altura <= y+1 ; altura++) {

                if(amplada >= 0 && altura >= 0 && amplada < this.y && altura < this.x){

                    if(!tablero[amplada][altura].isVisible()){

                        tablero[amplada][altura].isVisible();

                        if(tablero[amplada][altura].getValorCelda() == 0){

                            eliminarCerosAdyadcentes(x,y);
                        }
                    }
                }

            }
        }
    }
    private void colocarNumeroAdyadcents(int x, int y){

        for (int amplada = x-1; amplada <= x+1; amplada++) {

            for (int  altura = y-1; altura <= y+1; altura++) {

                if(amplada >= 0 && altura >= 0 && amplada < this.y && altura < this.x){

                    if (!tablero[amplada][altura].isMina()){

                        tablero[amplada][altura].addValorCelda(1);
                    }
                }
            }
        }
    }
}