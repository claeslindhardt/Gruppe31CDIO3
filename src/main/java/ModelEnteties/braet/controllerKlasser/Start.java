package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.braet.dataKlasser.AktionsFelter;

public class Start extends AktionsFelter {
    //|--------- Variabler:-----------------
    int penge;

    //|--------- Getters og Setters:-----------------
    public int getPenge() {
        return penge;
    }

    public void setPenge(int penge) {
        this.penge = penge;
    }

    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterface userInterface){
        userInterface.overStartAnimation();
    }
    public void printInfo(UserInterface userInterface){
        userInterface.startsFeltsInfo(this);
    }
    //|--------- Constructor:-----------------
    public Start(int startPasseringsInkomst, int placering){
        setPenge(startPasseringsInkomst);
        setPlacering(placering);
        setNavn("Start Felt");
        setFeltType("Start");
    }
}
