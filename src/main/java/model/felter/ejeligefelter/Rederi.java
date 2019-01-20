package model.felter.ejeligefelter;


public class Rederi extends EjeligtFelt {

    private int leje = 25;


    public Rederi( String navn, int pris, int placering ){
        super(navn, pris, placering);
    }


    public int getLeje(){
        return leje;
    }

}
