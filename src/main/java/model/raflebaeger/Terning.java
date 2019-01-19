package model.raflebaeger;


import model.singletoner.RandomSingleton;


/**
 * @author Claes
 * Denne klasses formål er at lave objekter som indeholder en verdi,
 * som kan ændre sig (inden for en ramme) når man bruger dens slaa funktion.
 */
public class Terning {

    private int vaerdi = 1;

    public int getVaerdi() {
        return vaerdi; }

    public void setVaerdi(int vaerdi){
        this.vaerdi = vaerdi;
    }

    public int slaa(){
        vaerdi = RandomSingleton.getInstance().nextInt(6)+1;
        return vaerdi;
    }

}
