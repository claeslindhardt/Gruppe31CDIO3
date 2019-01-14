package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.AktionsFelterDTO;

public class GaaIFaengselCO extends AktionsFelterDTO {
    //|----------- Metoder:------------------

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(HandelsController handel,SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpil().getSpillerMedTur();
        spillerMedTur.setFaengselsStraf(true);
        //Kan lave et braet objekt da der gaaIfængselfeltet altid ligger det samme sted, nemlig felt nr. 1.
        BraetCO braet =new BraetCO(20,userInterfaceKontrakt);
        spillerMedTur.setSpillerPosition(1);

        userInterfaceKontrakt.iFaengselMedDig();
        userInterfaceKontrakt.duErLandetPå(braet.getBret().get(1), spillerMedTur);

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
