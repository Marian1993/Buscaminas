public class Casella {


    private boolean mina;
    private boolean bandera;
    private boolean visible;
    private int valorCelda;

    public int getValorCelda() {
        return valorCelda;
    }

    public void addValorCelda(int valorCelda) {
        this.valorCelda += valorCelda;
    }

    public Casella(){
        this.mina = false;
        this.visible = false;
        this.bandera = false;
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

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public static void destapaCasilla(int y, int x){


    }


}
