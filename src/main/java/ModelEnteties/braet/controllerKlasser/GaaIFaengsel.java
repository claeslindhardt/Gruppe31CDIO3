package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.dataKlasser.AktionsFelter;

public class GaaIFaengsel extends AktionsFelter {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterface userInterface){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setFaengselsStraf(true);
        spillerMedTur.setSpillerPosition(1);
        userInterface.iFaengselMedDig();

        spil.slutSpillerTur();
    }
    public void printInfo(UserInterface userInterface){
        userInterface.faengselInfo(this);
    }
    //|--------- Constructor:-----------------
    public GaaIFaengsel(int placering){
        setFeltType("Gå i fængsel");
        setNavn("Gå til fængsel!");
        setPlacering(placering);
    }
}
