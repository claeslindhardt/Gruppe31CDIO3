package ModelEnteties.felter;

public class Rederi extends EjeligtFelt {

    private int leje;

    public int getLeje(){
        return leje;
    }

    public Rederi( String navn, int pris, int leje, int placering ){
        super(navn, pris, placering);
        this.leje = leje;

    }

}
