import java.util.Scanner;

public class Joc {

    public static void main(String[]args){


        Output output = new Output();
        Input input = new Input();
        Taulell taulell;


        do{

            output.textDificultats();
            taulell = input.elegirDificultat();
            Casella [][] casella = new Casella[taulell.getNumFiles()][taulell.getNumColumnes()];
            taulell.inicialitzarTaulell(casella);
            output.imprimirTaulell(taulell,casella);
            taulell.colocarMines(Input.posarCoordenades(taulell));


            do {
                output.imprimirTaulell(taulell,casella);
                input.elegirAccio(taulell);

            }while (!taulell.partidaPerduda());

            output.imprimirTaulell(taulell,casella);
            output.fiDelJoc(taulell.isMinaDestepada(), taulell.getIntents());

        }while (input.reiniciaJoc());

    }
}
