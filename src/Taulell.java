import java.util.Scanner;

public class Taulell {

    private static Scanner sc = new Scanner(System.in);

    private Casella[][] taulell;
    private int x;
    private int y;
    private int quantitatMines = 0;
    private boolean banderaPosada = false;
    protected boolean minaDestepada = false;
    private boolean primeraDestepada = false;
    private int difAux;
    protected static int minesPers = 0;

    public Taulell() {}

    public void crearTaulell(int dificultat){

        difAux = dificultat;

        while (true) {

            if (difAux == Dificultat.PRINCIPIANT) {
                x = 8;
                y = 8;
                break;
            } else if (difAux == Dificultat.INTERMITG) {
                x = 16;
                y = 16;
               break;
            } else if (difAux == Dificultat.EXPERT) {
                x = 30;
                y = 16;
                break;
            } else if (difAux == Dificultat.PERSONALITZAT) {

                System.out.print("Posa l'altura: ");
                y = sc.nextInt();
                System.out.print("Posa l'amplada: ");
                x = sc.nextInt();
                System.out.print("Posa la quantitat de mines: ");
                minesPers = sc.nextInt();
                break;
            } else {
                System.err.println("Torna a escollir una opció:");
                difAux = sc.nextInt();
            }
        }
        inicialitzarTaulell(difAux);
    }


    private void inicialitzarTaulell(int dificultat) {


        this.taulell = new Casella[x][y];

        for (int i = 0; i < taulell.length; i++) {

            for (int j = 0; j < taulell[i].length; j++) {
                taulell[i][j] = new Casella();
            }
        }
        imprimirTablero();
        colocarMines(dificultat,entradaTablero());
    }


    private void colocarMines(int dificultat, Posicio posicio) {

        while (quantitatMines < Dificultat.quantitatMines(dificultat)) {

            int x = (int) (Math.random() * this.x);
            int y = (int) (Math.random() * this.y);

            if(posicio.posicioX == x  && posicio.posicioY == y){

                primeraDestepada = true;

            }else if (!taulell[x][y].isMina() && primeraDestepada) {
                taulell[x][y].setMina(true);

                quantitatMines++;

                colocarNumeroAdyadcents(x,y);
            }
        }
        desteparCelda(posicio);
    }


    public Posicio entradaTablero() {


        while (true) {

            System.out.println("Colocar bandera o quitar bandera 1" + "\nDestepar casella 2");
            System.out.print(Colores.ANSI_GREEN + "Elegeix una opció: " + Colores.ANSI_RESET);
            int entrada = Error.esNecessitaNumero();

            if(entrada == 1){
                banderaPosada = true;
            }
            if(entrada > 2 || entrada < 1){
                System.err.println("Has de posar una de les tres opcions");
                continue;
            }
            return posarCoordenades();
        }
    }


    private Posicio posarCoordenades(){

        System.out.print("Posa el número de la fila: ");
        int desteparX = Error.esNecessitaNumero();

        while(desteparX >= this.x || desteparX < 0){
            System.err.println("Has posat un número massa gran, torna a intriduir un número:");
            desteparX = Error.esNecessitaNumero();
        }
        System.out.print("Posa el número de la columna: ");
        int desteparY = Error.esNecessitaNumero();

        while(desteparY >= this.y || desteparY < 0){
            System.err.println("Has posat un número massa gran, torna a intriduir un número:");
            desteparY = Error.esNecessitaNumero();
        }
        return new Posicio(desteparX, desteparY);
    }


    public void imprimirTablero() {

        int contadorMines = 0;

        posarNumerosAdaltAbaix();

        for (int x = 0; x < this.x; x++) {

            for (int y = 0; y < this.y; y++) {

                if(y == 0 && x < 10){
                    System.out.print(x + "  ");
                }else if(y == 0 && x >= 10){
                    System.out.print(x + " ");
                }

                if(taulell[x][y].isBandera()){
                    System.out.print("¶");
                    banderaPosada = false;
                    contadorMines++;

                }else if (!taulell[x][y].isVisible()) {

                    if (!minaDestepada && !taulell[x][y].isMina()) {
                        System.out.print("■");
                    }else if(!minaDestepada && taulell[x][y].isMina()){
                        System.out.print("■");
                    }
                    if(minaDestepada && taulell[x][y].isMina()){
                        System.out.print("×");
                    }
                    else if(minaDestepada && !taulell[x][y].isMina()){
                        System.out.print("■");
                    }

                }else if (taulell[x][y].isVisible()) {

                    if (taulell[x][y].isMina()) {
                        System.out.print("×");

                    }else if (taulell[x][y].getValorCelda() == 0) {
                        System.out.print(" ");

                    }else if(taulell[x][y].getValorCelda() < 9) {
                        assignarColors(x,y);
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
        System.out.println(Colores.ANSI_GREEN + "Tens " + contadorMines + " mines de " + Dificultat.quantitatMines(difAux) + " ja identificades." +  Colores.ANSI_RESET);
        if(contadorMines > Dificultat.quantitatMines(difAux)){
            System.out.println(Colores.ANSI_RED + "Tens més banderes que de quantitat de mines d'es taulell!");
        }

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


    public void desteparCelda(Posicio posicio){

        if(banderaPosada && !taulell[posicio.posicioX][posicio.posicioY].isVisible()){

            taulell[posicio.posicioX][posicio.posicioY].setBandera(true);

        }else if(banderaPosada && taulell[posicio.posicioX][posicio.posicioY].isBandera()){

            taulell[posicio.posicioX][posicio.posicioY].setBandera(false);

        }else if (taulell[posicio.posicioX][posicio.posicioY].getValorCelda() == 0 && !taulell[posicio.posicioX][posicio.posicioY].isMina() && !taulell[posicio.posicioX][posicio.posicioY].isVisible()) {

            eliminarCerosAdyadcents(posicio.posicioX,posicio.posicioY);

        }else if(taulell[posicio.posicioX][posicio.posicioY].getValorCelda() < 9 && !taulell[posicio.posicioX][posicio.posicioY].isMina() && !taulell[posicio.posicioX][posicio.posicioY].isVisible()){

            taulell[posicio.posicioX][posicio.posicioY].setVisible(true);

        } else if(taulell[posicio.posicioX][posicio.posicioY].isMina()){
            minaDestepada = true;
            taulell[posicio.posicioX][posicio.posicioY].setVisible(true);
        }else{
            System.out.println(Colores.ANSI_RED + "Has seleccionat una casella visibles" + Colores.ANSI_RESET);
            System.out.println();
        }
    }


    public boolean partidaGuanyada(){

        if (difAux == Dificultat.PRINCIPIANT) {

            for (int x = 0; x < taulell.length; x++) {

                for (int y = 0; y < taulell[x].length; y++) {

                    if(!taulell[x][y].isMina() && !taulell[x][y].isVisible()){

                        return false;
                    }
                }
            }
            return true;

        } else if (difAux == Dificultat.INTERMITG) {

            for (int x = 0; x < taulell.length; x++) {

                for (int y = 0; y < taulell[x].length; y++) {

                    if(!taulell[x][y].isMina() && !taulell[x][y].isVisible()){

                        return false;
                    }
                }
            }
            return true;

        } else if (difAux == Dificultat.EXPERT) {

            for (int x = 0; x < taulell.length; x++) {

                for (int y = 0; y < taulell[x].length; y++) {

                    if(!taulell[x][y].isMina() && !taulell[x][y].isVisible()){

                        return false;
                    }
                }
            }
            return true;

        } else if (difAux == Dificultat.PERSONALITZAT) {

            for (int x = 0; x < taulell.length; x++) {

                for (int y = 0; y < taulell[x].length; y++) {

                    if(!taulell[x][y].isMina() && !taulell[x][y].isVisible()){

                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }


    private void eliminarCerosAdyadcents(int x, int y){

        for (int  amplada = x-1 ; amplada <= x+1; amplada++) {

            for (int altura = y-1; altura <= y+1 ; altura++) {

                if(amplada >= 0 && altura >= 0 && amplada < this.y && altura < this.x){

                    if(!taulell[amplada][altura].isVisible()){

                        taulell[amplada][altura].setVisible(true);

                        if(taulell[amplada][altura].getValorCelda() == 0) {

                            eliminarCerosAdyadcents(amplada, altura);
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

                    if (!taulell[amplada][altura].isMina()){

                        taulell[amplada][altura].addValorCelda(1);
                    }
                }
            }
        }
    }


    private void assignarColors(int x, int y){

        if(taulell[x][y].getValorCelda() == 1){
            System.out.print(Colores.ANSI_BLUE + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(taulell[x][y].getValorCelda() == 2){
            System.out.print(Colores.ANSI_GREEN + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(taulell[x][y].getValorCelda() == 3){
            System.out.print(Colores.ANSI_PURPLE + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(taulell[x][y].getValorCelda() == 4){
            System.out.print(Colores.ANSI_YELLOW + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(taulell[x][y].getValorCelda() == 5){
            System.out.print(Colores.ANSI_CYAN + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(taulell[x][y].getValorCelda() == 6){
            System.out.print(Colores.ANSI_BLACK + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(taulell[x][y].getValorCelda() == 7){
            System.out.print(Colores.ANSI_RED + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }else if(taulell[x][y].getValorCelda() == 8){
            System.out.print(Colores.ANSI_RED + taulell[x][y].getValorCelda() + Colores.ANSI_RESET);

        }
    }

    public boolean reiniciaJoc(boolean opcio){

        if(opcio || !opcio){

            System.out.println("Posa un 0 si vols reiniciar la partida, sino posa un 1");
            int reinici;
            String confirmacio;
            String auxConfrim;
            reinici = Error.esNecessitaNumero();
            if (reinici == 0) {
                return true;
            }

            while (true) {

                if (reinici == 1) {
                    System.out.println("Segur?(S/N)");
                    sc.nextLine();
                    confirmacio = sc.nextLine();
                    auxConfrim = confirmacio.toLowerCase();

                    if (auxConfrim.charAt(0) == 's') {
                        return true;
                    } else if (confirmacio.charAt(0) == 'n') {
                        return false;
                    } else {
                        System.err.println("No has posat cap dels caracter solicitats, torna a posa la teva resposta:");
                    }

                }

            }
        }
        return false;
    }


    public boolean fiDelJoc(){

        if(minaDestepada){
            System.out.println("     _____               .---...-.");
            System.out.println("   ,'  -. `.           ,' _____...'");
            System.out.println("  /   - _ - \\         : .' _   _ \\\\");
            System.out.println(" :    ' _)'  :        | :-(_).(_)::");
            System.out.println("(_           ;)       | |    -'  ||");
            System.out.println("  \\     _   /         ; |    _   ||");
            System.out.println("   `..___..'          `-'..____.'`'");
            System.out.println("      ;._:                _; :_");
            System.out.println("     /    \\ Has perdut  ,'  `' `. Es un perdedor");
            return false;
        }else {
            System.out.println("                       __");
            System.out.println("                     .'  '.");
            System.out.println("                 _.-'/  |  \\");
            System.out.println("    ,        _.-\"  ,|  /  0 `-.");
            System.out.println("    |\\    .-\"       `--\"\"-.__.'=====================-,");
            System.out.println("    \\ '-'`        .___.--._)=========================|");
            System.out.println("     \\             .'      |                         |");
            System.out.println("       |     /,_.-'        |                         |");
            System.out.println("     _/   _.'(             |       Has guanyat!      |");
            System.out.println("   /  ,-' \\  \\             |                         |");
            System.out.println("   \\  \\    `-'             |                         |");
            System.out.println("    `-'                    '-------------------------'");
            return true;
        }
    }
}