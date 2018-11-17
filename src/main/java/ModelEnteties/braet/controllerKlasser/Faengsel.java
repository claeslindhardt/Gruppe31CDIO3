package ModelEnteties.braet.controllerKlasser;

import Controller.UserInterface;
import ModelEnteties.braet.dataKlasser.AktionsFelter;

import java.util.HashMap;

public class Faengsel extends AktionsFelter {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(UserInterface userInterface){
        userInterface.paaBesoegIFaengsel();
    }

    //|--------- Constructor:-----------------
    public Faengsel(String navn, int placering){
        setFeltType("Fængsel");
        setNavn(navn);
        setPlacering(placering);
    }
}
