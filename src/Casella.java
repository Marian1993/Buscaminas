public class Casella {


    private boolean mines;
    private boolean bandera;
    private boolean visible;
    private int minesVeines = 0;


    public Casella(){
        this.mines = false;
        this.visible = false;
        this.bandera = false;
    }

    public int getMinesVeines() {
        return minesVeines;
    }

    public void addMinesVeines(int valorCelda) {
        this.minesVeines += valorCelda;
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean buida) {
        this.visible = buida;
    }

    public boolean isMines() {
        return mines;
    }

    public void setMines(boolean mines) {
        this.mines = mines;
    }

}
