package ModelEnteties.felter;

public class Rederi extends EjeligtFelt {

    private int leje;

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
