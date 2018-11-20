package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.dataKlasser.AktionsFelter;

public class GaaIFaengsel extends AktionsFelter {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setFaengselsStraf(true);
        spillerMedTur.setSpillerPosition(1);
        userInterfaceKontrakt.iFaengselMedDig();

        spil.slutSpillerTur();
    }
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.faengselInfo(this);
    }
    //|--------- Constructor:-----------------
    public GaaIFaengsel(int placering){
        setFeltType("Gå i fængsel");
        setNavn("Gå til fængsel!");
        setPlacering(placering);
    }
}
