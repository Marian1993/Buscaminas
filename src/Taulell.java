import java.util.Scanner;

public class Taulell {

    private Casella[][] taulell;
    private int numFiles;
    private int numColumnes;
    private int intents;
    private int quantitatMines;
    private boolean minaDestepada;


    public Taulell() {}

    public Taulell(int numFiles, int numColumnes, int quantitatMines) {

        this.numFiles =  numFiles;
        this.numColumnes = numColumnes;
        this.quantitatMines = quantitatMines;
        this.minaDestepada = false;
    }

    public void inicialitzarTaulell( Casella[][] casella) {

        this.taulell = casella;

        for (int i = 0; i < this.taulell.length; i++) {

            for (int j = 0; j < this.taulell[i].length; j++) {
                this.taulell[i][j] = new Casella();
            }
        }
    }

    public void colocarMines(Posicio posicio) {

        int contadorMines = 0;

        while (contadorMines < quantitatMines) {

            int x = (int) (Math.random() * this.numFiles);
            int y = (int) (Math.random() * this.numColumnes);

            if (!taulell[x][y].isMines() && !(posicio.posicioX == x  && posicio.posicioY == y)) {
                taulell[x][y].setMines(true);

                contadorMines++;

                colocarNumeroAdyadcents(x,y);
            }
        }
        estatCaselles(posicio);
    }

    public void posarOLlevarBandera(Posicio posicio){

        if(!taulell[posicio.posicioX][posicio.posicioY].isBandera() && !taulell[posicio.posicioX][posicio.posicioY].isVisible()){
            taulell[posicio.posicioX][posicio.posicioY].setBandera(true);

        }else if(taulell[posicio.posicioX][posicio.posicioY].isBandera()){

            taulell[posicio.posicioX][posicio.posicioY].setBandera(false);
        }else if(taulell[posicio.posicioX][posicio.posicioY].isVisible()) {
            System.out.println(Color.ANSI_RED + "La casella es visible, no pots posar una bandera" + Color.ANSI_RESET);
        }else {
            System.out.println(Color.ANSI_RED + "Has de posar una bandera abans" + Color.ANSI_RESET);
        }
    }

    public void estatCaselles(Posicio posicio){

        if(taulell[posicio.posicioX][posicio.posicioY].isBandera()){
            System.out.println(Color.ANSI_RED +"No pot destapar la casella si tens una bandera colocada" + Color.ANSI_RESET);
        }else if (taulell[posicio.posicioX][posicio.posicioY].getMinesVeines() == 0 && !taulell[posicio.posicioX][posicio.posicioY].isMines() && !taulell[posicio.posicioX][posicio.posicioY].isVisible()) {

            expandirCasellesBuides(posicio.posicioX,posicio.posicioY);

        }else if(!taulell[posicio.posicioX][posicio.posicioY].isMines() && !taulell[posicio.posicioX][posicio.posicioY].isVisible()){

            taulell[posicio.posicioX][posicio.posicioY].setVisible(true);

        }else if(taulell[posicio.posicioX][posicio.posicioY].isMines()){
            taulell[posicio.posicioX][posicio.posicioY].setVisible(true);
            setMinaDestepada(true);
        }else{
            System.out.println(Color.ANSI_RED + "Has seleccionat una casella visibles" + Color.ANSI_RESET);
            System.out.println();
        }
    }


    public boolean partidaPerduda(){

        int casallesNoVisisbles = 0;

        for (int x = 0; x < this.numFiles; x++) {

            for (int y = 0; y < this.numColumnes; y++) {

                if(taulell[x][y].isMines() && taulell[x][y].isVisible()){
                    return true;
                }
                if(taulell[x][y].isVisible()){
                    casallesNoVisisbles++;
                }
            }
        }
        return(casallesNoVisisbles == this.quantitatMines);
    }


    private void expandirCasellesBuides(int x, int y){

        for (int  amplada = x-1 ; amplada <= x+1; amplada++) {

            for (int altura = y-1; altura <= y+1 ; altura++) {

                if(amplada >= 0 && altura >= 0 && altura < this.numColumnes && amplada < this.numFiles){

                    if(!taulell[amplada][altura].isVisible()){

                        taulell[amplada][altura].setVisible(true);

                        if(taulell[amplada][altura].getMinesVeines() == 0) {

                            expandirCasellesBuides(amplada, altura);
                        }
                    }
                }
            }
        }
    }


    private void colocarNumeroAdyadcents(int x, int y){

        for (int amplada = x-1; amplada <= x+1; amplada++) {

            for (int  altura = y-1; altura <= y+1; altura++) {

                if(amplada >= 0 && altura >= 0 && altura < this.numColumnes && amplada < this.numFiles){

                    if (!taulell[amplada][altura].isMines()){

                        taulell[amplada][altura].addMinesVeines(1);
                    }
                }
            }
        }
    }



    public int getNumFiles() {
        return numFiles;
    }

    public void setNumFiles(int numFiles) {
        this.numFiles = numFiles;
    }

    public int getNumColumnes() {
        return numColumnes;
    }

    public void setNumColumnes(int numColumnes) {
        this.numColumnes = numColumnes;
    }

    public int getQuantitatMines() {
        return quantitatMines;
    }

    public void setQuantitatMines(int quantitatMines) {
        this.quantitatMines = quantitatMines;
    }

    public int getIntents() {
        return intents;
    }

    public void setIntents(int intents) {
        this.intents = intents;
    }

    public Casella[][] getTaulell() {
        return taulell;
    }

    public void setTaulell(Casella[][] taulell) {
        this.taulell = taulell;
    }

    public boolean isMinaDestepada() {
        return minaDestepada;
    }

    public void setMinaDestepada(boolean minaDestepada) {
        this.minaDestepada = minaDestepada;
    }
}