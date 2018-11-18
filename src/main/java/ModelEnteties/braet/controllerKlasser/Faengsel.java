package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.braet.dataKlasser.AktionsFelter;

import java.util.HashMap;

public class Faengsel extends AktionsFelter {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterface userInterface){
        userInterface.paaBesoegIFaengsel();
    }

    //|--------- Constructor:-----------------
    public Faengsel(String navn, int placering){
        setFeltType("Fængsel");
        setNavn(navn);
        setPlacering(placering);
    }
}
