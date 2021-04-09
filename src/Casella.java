public class Casella {


    private boolean mina;
    private boolean bandera;
    private boolean visible;
    private int minasCercanas;

    public int getMinasCercanas() {
        return minasCercanas;
    }

    public void addMinasCercanas(int minasCercanas) {
        this.minasCercanas += minasCercanas;
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


}
