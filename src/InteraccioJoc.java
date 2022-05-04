
public class InteraccioJoc {

    private final static Output output = new Output();
    private final static Input input = new Input();
    private static  Taulell taulell;


    private static Taulell eleccioDificultat(int dificultat){

        switch (dificultat) {

            case 0:
                int[] taulell = input.taulellPerso();
                return new Taulell(taulell[0],taulell[1],taulell[2]);
            case 1:
                return new Taulell(8,8,10);
            case 2:
                return new Taulell(16,16,40);
            case 3:
                return new Taulell(30,16,99);
        }
        return new Taulell();
    }
    private static void eleccioARealitzar(Taulell taulell, int operacio){

        if (operacio == 0 || operacio == 1) {
            taulell.posarOLlevarBandera(Input.posarCoordenades(taulell));

        } else if (operacio == 2) {
            taulell.estatCaselles(Input.posarCoordenades(taulell));
        }
    }

    public static void partida(){
        do{

            output.textDificultats();
            taulell = eleccioDificultat(input.elegirDificultat());
            Casella [][] casella = new Casella[taulell.getNumFiles()][taulell.getNumColumnes()];
            taulell.inicialitzarTaulell(casella);
            output.imprimirTaulell(taulell,casella);
            taulell.colocarMines(Input.posarCoordenades(taulell));


            do {
                output.imprimirTaulell(taulell,casella);
                eleccioARealitzar(taulell, input.elegirAccio());
                //input.elegirAccio(taulell);

            }while (!taulell.partidaPerduda());

            output.imprimirTaulell(taulell,casella);
            output.fiDelJoc(taulell.isMinaDestepada(), taulell.getIntents());

        }while (input.reiniciaJoc());

    }


}
