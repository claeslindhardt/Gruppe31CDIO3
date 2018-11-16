package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.braet.dataKlasser.AktionsFelter;

public class Taxi extends AktionsFelter {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterface userInterface){
        spil.getSpillerMedTur().tagTaxi(spil, userInterface);

    }

    public void printInfo(UserInterface userInterface){
        userInterface.taxiInfo(this);
    }
    //|--------- Constructor:-----------------
    public Taxi(int placering){
        setFeltType("Taxi");
        setNavn("Taxi Felt");
        setPlacering(placering);
    }

}
