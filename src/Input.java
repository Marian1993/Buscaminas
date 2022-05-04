public class Input {

    public Taulell elegirDificultat(){

        int dificultat = PosarNumero.esNecessitaNumero();
        while (true) {

            switch (dificultat) {

                case 0:
                    System.out.print("Posa l'altura: ");
                    int files = PosarNumero.esNecessitaNumero();
                    System.out.print("Posa l'amplada: ");
                    int columnes = PosarNumero.esNecessitaNumero();
                    System.out.print("Posa la quantitat de mines: ");
                    int mines = PosarNumero.esNecessitaNumero();
                    return new Taulell(files, columnes,mines);
                case 1:
                    return new Taulell(8,8,10);
                case 2:
                    return new Taulell(16,16,40);
                case 3:
                    return new Taulell(30,16,99);
                default:
                    System.err.println("Torna a escollir una opció:");
                    dificultat = PosarNumero.esNecessitaNumero();
            }
        }
    }
    public void elegirAccio(Taulell taulell) {

        boolean eleccioCorrecte = false;

        while (!eleccioCorrecte) {
            System.out.println("Colocar bandera = 0\n" + "Llevar bandera = 1" + "\nDestepar casella = 2");
            System.out.print(Color.ANSI_GREEN + "Elegeix una opció: " + Color.ANSI_RESET);
            int num = PosarNumero.esNecessitaNumero();

            if (num == 0 || num == 1) {
                taulell.posarOLlevarBandera(posarCoordenades(taulell));
                eleccioCorrecte = true;
            } else if (num == 2) {
                taulell.estatCaselles(posarCoordenades(taulell));
                eleccioCorrecte = true;
            } else {
                System.out.println("No has posat cap de les opcions");
            }
        }
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
