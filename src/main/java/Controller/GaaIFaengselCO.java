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
        spillerMedTur.setFaengselsStraf(true);
        //Kan lave et braet objekt da der gaaIfængselfeltet altid ligger det samme sted, nemlig felt nr. 1.
        spillerMedTur.setSpillerPosition(1);

        userInterfaceKontrakt.iFaengselMedDig();
        userInterfaceKontrakt.duErLandetPå(spil.getSpil().getFelter()[1], spillerMedTur);

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
