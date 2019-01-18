package model.felter.ejeligefelter;

import model.felter.ejeligefelter.EjeligtFelt;

public class Rederi extends EjeligtFelt {

    private int leje = 25;

    //|--------- Getters og Setters:-----------------

    public int getLeje(){
        return leje;
    }

    public void setLeje(int leje){this.leje = leje;}

    //|--------- Constructor:-----------------

    public Rederi( String navn, int pris, int leje, int placering ){
        super(navn, pris, placering);

        setFeltType("Rederi");
    }

}
