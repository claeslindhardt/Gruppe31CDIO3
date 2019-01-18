package model.felter.ejeligefelter;

import model.felter.ejeligefelter.EjeligtFelt;

public class Bryggeri extends EjeligtFelt {

    private int leje;

    //|--------- Getters og Setters:-----------------

    public int getLeje(){return leje;}

    public void setLeje(int leje){this.leje = leje;}

    //|--------- Constructor:-----------------

    public Bryggeri(String navn, int pris, int placering){
        super(navn,pris,placering);

        setFeltType("Bryggeri");
    }
}
