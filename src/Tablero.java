import java.util.InputMismatchException;
import java.util.Scanner;

public class Tablero {

    private static Scanner sc = new Scanner(System.in);

    private Casella[][] tablero;
    private int x;
    private int y;
    private int quantitatMines = 0;
    private boolean ponerBandera = false;
    public boolean minadestepada = false;

    public Tablero() {}


    public void inicialitzarTablero(int dificultat) {

        if (dificultat == Dificultat.PRINCIPIANT) {
            y = 8+1;
            x = 8+1;
        }
        if (dificultat == Dificultat.INTERMITG) {
            y = 16+1;
            x = 16+1;
        }
        if (dificultat == Dificultat.EXPERT) {
            y = 16+1;
            x = 30+1;
        }
        if (dificultat == Dificultat.PERSONALITZAT) {

            System.out.print("Altura: ");
            y = sc.nextInt() +1;
            System.out.print("Amplada: ");
            x = sc.nextInt() +1;

        }
        this.tablero = new Casella[x][y];

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Casella();
            }
        }
        colocarmines(dificultat);

    }

    private void colocarmines(int dificultat) {

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
    }

    public Posicion entradaTablero() {

        while (true) {
            try {
                System.out.println("Colocar bandera 1" + "\nDestepar casella 2");
                int entrada = sc.nextInt();

                if (entrada == 1) {
                    ponerBandera = true;
                    System.out.print("Posa el número de la fila: ");
                    int banderaX = sc.nextInt();
                    System.out.print("Posa el número de la columna: ");
                    int banderaY = sc.nextInt();
                    return new Posicion(banderaX, banderaY);

                } else if (entrada == 2) {
                    System.out.print("Posa el número de la fila: ");
                    int desteparX = sc.nextInt();
                    System.out.print("Posa el número de la columna: ");
                    int desteparY = sc.nextInt();
                    return new Posicion(desteparX, desteparY);
                }
            } catch (InputMismatchException e) {
                System.out.println("No has introduit el número corresponent: ");
            }
        }
    }
    public void imprimirTablero() {


        for (int x = 0; x < tablero.length; x++) {


            for (int y = 0; y < tablero[x].length; y++) {

                if(tablero[x][y].isBandera()){
                    System.out.print("¶");
                    ponerBandera = false;

                }else if (!tablero[x][y].isVisible()) {

                    if (!minadestepada && !tablero[x][y].isMina()) {
                        System.out.print("■");
                    }else if(!minadestepada && tablero[x][y].isMina()){
                        System.out.print("■");
                    }
                    if(minadestepada && tablero[x][y].isMina()){
                        System.out.print("×");
                    }
                    else if(minadestepada && !tablero[x][y].isMina()){
                        System.out.print("■");
                    }

                }else if (tablero[x][y].isVisible()) {

                    if (tablero[x][y].isMina()) {
                        System.out.print("×");

                    }else if (tablero[x][y].getValorCelda() == 0) {
                        System.out.print(" ");

                    }else if(tablero[x][y].getValorCelda() < 9) {
                        assignarColores(x,y);
                    }
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }
    public void desteparCelda(Posicion posicion){

        for (int x = 0; x < tablero.length; x++) {

            for (int y = 0; y < tablero[x].length; y++) {

                if (posicion.posicioX == x && posicion.posicioY == y) {

                    if(ponerBandera && !tablero[x][y].isVisible()){

                        tablero[x][y].setBandera(true);

                    }else if (tablero[x][y].getValorCelda() == 0 && !tablero[x][y].isMina()) {

                        eliminarCerosAdyadcentes(x,y);

                    }else if(tablero[x][y].getValorCelda() < 9 && !tablero[x][y].isMina()){

                        tablero[x][y].setVisible(true);

                    } else if(tablero[x][y].isMina()){
                        minadestepada = true;
                        tablero[x][y].setVisible(true);
                    }
                }
            }
        }
    }
    private void eliminarCerosAdyadcentes(int x, int y){

        for (int  amplada = x-1 ; amplada <= x+1; amplada++) {

            for (int altura = y-1; altura <= y+1 ; altura++) {

                if(amplada >= 1 && altura >= 1 && amplada < this.y && altura < this.x){

                    if(!tablero[amplada][altura].isVisible()){

                        tablero[amplada][altura].setVisible(true);

                        if(tablero[amplada][altura].getValorCelda() == 0) {

                            eliminarCerosAdyadcentes(amplada, altura);
                        }
                    }
                }
            }
        }
    }
    private void colocarNumeroAdyadcents(int x, int y){

        for (int amplada = x-1; amplada <= x+1; amplada++) {

            for (int  altura = y-1; altura <= y+1; altura++) {

                if(amplada >= 1 && altura >= 1 && amplada < this.y && altura < this.x){

                    if (!tablero[amplada][altura].isMina()){

                        tablero[amplada][altura].addValorCelda(1);
                    }
                }
            }
        }
    }
    private void assignarColores(int x, int y){

        if(tablero[x][y].getValorCelda() == 1){
            System.out.print(Colores.ANSI_BLUE + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(tablero[x][y].getValorCelda() == 2){
            System.out.print(Colores.ANSI_GREEN + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(tablero[x][y].getValorCelda() == 3){
            System.out.print(Colores.ANSI_PURPLE + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(tablero[x][y].getValorCelda() == 4){
            System.out.print(Colores.ANSI_YELLOW + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(tablero[x][y].getValorCelda() == 5){
            System.out.print(Colores.ANSI_CYAN + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(tablero[x][y].getValorCelda() == 6){
            System.out.print(Colores.ANSI_BLACK + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(tablero[x][y].getValorCelda() == 7){
            System.out.print(Colores.ANSI_RED + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(tablero[x][y].getValorCelda() == 8){
            System.out.print(Colores.ANSI_RED + tablero[x][y].getValorCelda() + Colores.ANSI_RESET);

        }
    }
    public void fiDelJoc(){

        if(minadestepada){
            System.out.println("Has perdido");
        }else {
            System.out.println("Has ganado!");
        }

    }
}