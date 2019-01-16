package ModelEnteties.felter;

import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;

public class FriParkering extends FeltDTO {
    private int leje = 0;

    //|--------- Getters og Setters:-----------------

    public int getLeje(){ return leje; }

    public void setLeje(int leje){
        this.leje = leje;
    }

    //|----------- Metoder:------------------
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
        userInterfaceKontrakt.friParkering();
    }

    //|--------- Constructor:-----------------
    public FriParkering( int placering ){
        super( "Fri Parkering", placering );
    }

}
