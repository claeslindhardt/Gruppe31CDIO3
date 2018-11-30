package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerCO;
import ModelEnteties.braet.dataKlasser.AktionsFelterDTO;

public class GaaIFaengselCO extends AktionsFelterDTO {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setFaengselsStraf(true);
        spillerMedTur.setSpillerPosition(1);
        userInterfaceKontrakt.iFaengselMedDig();

        spil.slutSpillerTur();
    }
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
