package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.felter.AktionsFelterDTO;

public class GaaIFaengselCO extends AktionsFelterDTO {
    //|----------- Metoder:------------------

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller spillerMedTur = spil.getSpil().getSpillerMedTur();
        FaengselCO faengsel = spil.getSpil().getFaengsel();

        spillerMedTur.setFaengselsStraf(true);
        spillerMedTur.setSpillerPosition( faengsel.getPlacering() );

        userInterfaceKontrakt.iFaengselMedDig();
        userInterfaceKontrakt.duErLandetPå( faengsel, spillerMedTur);

        spil.slutSpillerTur();
    }

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.faengselInfo(this);
    }
    //|--------- Constructor:-----------------
    public GaaIFaengselCO(int placering){
        setFeltType("Gå i fængsel");
        setNavn("Gå til fængsel!");
        setPlacering(placering);
    }
}
