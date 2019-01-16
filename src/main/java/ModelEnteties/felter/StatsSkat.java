package ModelEnteties.felter;
//Ekstra Commit
import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;

public class StatsSkat extends Felt {

    public double getSkat() {
        return skat;
    }

    private double skat;

    public StatsSkat(int placering, double skat) {
        super( "Betal statsskat", placering );
        this.skat = skat;
    }
}
