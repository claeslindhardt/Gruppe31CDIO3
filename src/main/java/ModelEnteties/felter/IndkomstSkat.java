package ModelEnteties.felter;

import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;

public class IndkomstSkat extends FeltDTO {

    public IndkomstSkat(int placering) {
        super( "Betal indkomstskat", placering );
    }


    @Override
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {


    }
}

