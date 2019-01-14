package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.AktionsFelterDTO;

public class TaxiCO extends AktionsFelterDTO {
    //|----------- Metoder:------------------

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        spil.getHandlinger().tagTaxi( spil.getSpil().getSpillerMedTur(), spil, userInterfaceKontrakt );

    }

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.taxiInfo(this);
    }
    //|--------- Constructor:-----------------
    public TaxiCO(int placering){
        setFeltType("TaxiCO");
        setNavn("TaxiCO FeltDTO");
        setPlacering(placering);
    }

}
