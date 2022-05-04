public class Input {

    public int elegirDificultat(){

        int dificultat = PosarNumero.esNecessitaNumero();

            while (!(dificultat >= 0 && dificultat <= 3)){

                System.err.println("No has escollit cap de les opcions, torna a escollir una opció:");
                dificultat = PosarNumero.esNecessitaNumero();
            }
        return dificultat;
    }
    public int[] taulellPerso(){

        int[] perso = new int[3];

        System.out.print("Posa l'altura: ");
        perso[0] = PosarNumero.esNecessitaNumero();
        System.out.print("Posa l'amplada: ");
        perso[1] = PosarNumero.esNecessitaNumero();
        System.out.print("Posa la quantitat de mines: ");
        perso[2] = PosarNumero.esNecessitaNumero();
        while(!((perso[0]*perso[1]) > perso[2])){

            System.out.print("Massa mines, torna a posar la quantitat de mines:");
            perso[2] = PosarNumero.esNecessitaNumero();
        }
        return perso;
    }
    public int elegirAccio() {

        System.out.println("Colocar bandera = 0\n" + "Llevar bandera = 1" + "\nDestepar casella = 2");
        System.out.print(Color.ANSI_GREEN + "Elegeix una opció: " + Color.ANSI_RESET);
        int num = PosarNumero.esNecessitaNumero();

        while (!(num >= 0 && num <=2)) {

            System.out.println("No has posat cap de les opcions");
            num = PosarNumero.esNecessitaNumero();
        }
        return num;
    }

    public static Posicio posarCoordenades(Taulell taulell){

        System.out.print("Posa el número de la fila: ");
        int desteparX = PosarNumero.esNecessitaNumero();

        while(desteparX >= taulell.getNumFiles() || desteparX < 0){
            System.err.println("Has posat un número massa gran, torna a intriduir un número:");
            desteparX = PosarNumero.esNecessitaNumero();
        }
        System.out.print("Posa el número de la columna: ");
        int desteparY = PosarNumero.esNecessitaNumero();

        while(desteparY >= taulell.getNumColumnes() || desteparY < 0){
            System.err.println("Has posat un número massa gran, torna a intriduir un número:");
            desteparY = PosarNumero.esNecessitaNumero();
        }
        return new Posicio(desteparX, desteparY);
    }

    public boolean reiniciaJoc(){

        System.out.println("Reiniciar partida = 0 \n" + "Per sortir pitja cualsevol numero");
        int reinici = PosarNumero.esNecessitaNumero();

        return reinici == 0;
    }
}
