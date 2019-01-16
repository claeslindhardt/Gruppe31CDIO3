package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.FeltDTO;

public class FaengselCO extends FeltDTO {
    //|----------- Metoder:------------------

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.paaBesoegIFaengsel();
    }

    //|--------- Constructor:-----------------
    public FaengselCO(String navn, int placering){
        super("Fængsel", placering );
    }
}
