package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.braet.dataKlasser.AktionsFelter;

public class Taxi extends AktionsFelter {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterface userInterface){
        spil.getSpillerMedTur().tagTaxi(spil, userInterface);

    }
    /*
    public void printInfo(){
        System.out.println("| Felt nr: " + getPlacering() +" | Felt Navn:" + getNavn()+" | Felt type:"+ getFeltType()+" |");
    }*/
    //|--------- Constructor:-----------------
    public Taxi(int placering){
        setFeltType("Taxi");
        setNavn("Taxi Felt");
        setPlacering(placering);
    }

}
