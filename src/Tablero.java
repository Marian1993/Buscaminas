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
    private boolean primeraDestepada = false;
    private int tipusDificultat = 0;
    private int minesPers = 0;

    public Tablero() {}


    public void inicialitzarTablero(int dificultat) {

        if (dificultat == Dificultat.PRINCIPIANT) {
            x = 8;
            y = 8;
        }
        if (dificultat == Dificultat.INTERMITG) {
            x = 16;
            y = 16;
        }
        if (dificultat == Dificultat.EXPERT) {
            x = 30;
            y = 16;
        }
        if (dificultat == Dificultat.PERSONALITZAT) {

            System.out.print("Altura: ");
            y = sc.nextInt();
            System.out.print("Amplada: ");
            x = sc.nextInt();
            tipusDificultat = dificultat;
            System.out.print("Mines: ");
            minesPers = sc.nextInt();
        }
        this.tablero = new Casella[x][y];

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Casella();
            }
        }
        imprimirTablero();
        colocarmines(dificultat,entradaTablero());
    }

    private void colocarmines(int dificultat,Posicion posicion) {

        if (dificultat == 0) {

            while (quantitatMines < minesPers) {

                int x = (int) (Math.random() * this.x);
                int y = (int) (Math.random() * this.y);

                if(posicion.posicioX == x  && posicion.posicioY == y){

                    primeraDestepada = true;

                }else if (!tablero[x][y].isMina() && primeraDestepada) {
                    tablero[x][y].setMina(true);

                    quantitatMines++;

                    colocarNumeroAdyadcents(x,y);
                }else if(posicion.posicioX > this.x || posicion.posicioY > this.y){

                    System.out.println();
                    System.out.println("has posat una o unes dimensions massa elevades");
                    System.out.println();
                    System.out.print("Posa el número de la fila: ");
                    posicion.posicioX = sc.nextInt();
                    System.out.print("Posa el número de la columna: ");
                    posicion.posicioY = sc.nextInt();
                }
            }
            desteparCelda(posicion);

        } else {
            while (quantitatMines < Dificultat.nivellDificultat(dificultat)) {

                int x = (int) (Math.random() * this.x);
                int y = (int) (Math.random() * this.y);

                if (!tablero[x][y].isMina()) {
                    tablero[x][y].setMina(true);

                    quantitatMines++;

                    colocarNumeroAdyadcents(x,y);
                }else if (!tablero[x][y].isMina() && primeraDestepada) {
                    tablero[x][y].setMina(true);

                    quantitatMines++;

                    colocarNumeroAdyadcents(x,y);
                }else if(posicion.posicioX > this.x || posicion.posicioY > this.y){

                    System.out.println();
                    System.out.println("has posat una o unes dimensions massa elevades");
                    System.out.println();
                    System.out.print("Posa el número de la fila: ");
                    posicion.posicioX = sc.nextInt();
                    System.out.print("Posa el número de la columna: ");
                    posicion.posicioY = sc.nextInt();
                }
            }
        }
        desteparCelda(posicion);
    }

    public Posicion entradaTablero() {

        while (true) {
            try {
                System.out.println("Colocar bandera 1" + "\nDestepar casella 2" + "\nLlevar bandera 0");
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
                else if( entrada == 0){
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

        posarNumerosAdaltAbaix();

        for (int x = 0; x < this.x; x++) {

            for (int y = 0; y < this.y; y++) {

                if(y == 0 && x < 10){
                    System.out.print(x + "  ");
                }else if(y == 0 && x >= 10){
                    System.out.print(x + " ");
                }

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

                if(y == this.y-1){
                    System.out.print(x);
                }
            }
            System.out.println();
        }
        posarNumerosAdaltAbaix();
    }
    private void posarNumerosAdaltAbaix(){

        for (int i = 0; i < this.y; i++) {
            if(i == 0){
                System.out.print("   " + i + "  ");
            } else if(i < 10){
                System.out.print(i + "  ");
            }else{
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    public void desteparCelda(Posicion posicion){

        if(ponerBandera && !tablero[posicion.posicioX][posicion.posicioY].isVisible()){

            tablero[posicion.posicioX][posicion.posicioY].setBandera(true);

        }else if(tablero[posicion.posicioX][posicion.posicioY].isBandera()){

            tablero[posicion.posicioX][posicion.posicioY].setBandera(false);

        }else if (tablero[posicion.posicioX][posicion.posicioY].getValorCelda() == 0 && !tablero[posicion.posicioX][posicion.posicioY].isMina()) {

            eliminarCerosAdyadcentes(posicion.posicioX,posicion.posicioY);

        }else if(tablero[posicion.posicioX][posicion.posicioY].getValorCelda() < 9 && !tablero[posicion.posicioX][posicion.posicioY].isMina()){

            tablero[posicion.posicioX][posicion.posicioY].setVisible(true);

        } else if(tablero[posicion.posicioX][posicion.posicioY].isMina()){
            minadestepada = true;
            tablero[posicion.posicioX][posicion.posicioY].setVisible(true);
        }


    }
    private void eliminarCerosAdyadcentes(int x, int y){

        for (int  amplada = x-1 ; amplada <= x+1; amplada++) {

            for (int altura = y-1; altura <= y+1 ; altura++) {

                if(amplada >= 0 && altura >= 0 && amplada < this.y && altura < this.x){

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

                if(amplada >= 0 && altura >= 0 && amplada < this.y && altura < this.x){

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