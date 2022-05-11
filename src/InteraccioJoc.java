
public class InteraccioJoc {

    private static Taulell taulell;

    private static Taulell eleccioDificultat(int dificultat) {

        int[][] dificultatsTaulell = {{}, {8, 8, 10}, {16, 16, 40}, {30, 16, 99}};

        int[] parametresTaulell;

        if (dificultat == 0) {
            parametresTaulell = Input.taulellPerso();
        } else {
            parametresTaulell = dificultatsTaulell[dificultat];
        }
        return new Taulell(parametresTaulell[0], parametresTaulell[1], parametresTaulell[2]);
    }


    private static void eleccioARealitzar(Taulell taulell, int operacio) {

        if (operacio == 0 || operacio == 1) {
            taulell.posarOLlevarBandera(Input.posarCoordenades(taulell));

        } else if (operacio == 2) {
            taulell.estatCaselles(Input.posarCoordenades(taulell));
        }
    }

    //Aqui està tota la interacció que fera l'usuari amb el joc

    public static void partida() {
        do {

            Output.textDificultats();
            taulell = eleccioDificultat(Input.elegirDificultat());
            Casella[][] casella = new Casella[taulell.getNumFiles()][taulell.getNumColumnes()];
            taulell.inicialitzarTaulell(casella);
            Output.imprimirTaulell(taulell, casella);
            taulell.colocarMines(Input.posarCoordenades(taulell));


            do {
                Output.imprimirTaulell(taulell, casella);
                eleccioARealitzar(taulell, Input.elegirAccio());

            } while (!taulell.partidaPerduda());

            Output.imprimirTaulell(taulell, casella);
            Output.fiDelJoc(taulell.isMinaDestepada(), taulell.getIntents());

        } while (Input.reiniciaJoc());

    }


}
