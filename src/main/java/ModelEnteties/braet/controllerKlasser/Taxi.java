package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.braet.dataKlasser.AktionsFelterDTO;

public class Taxi extends AktionsFelterDTO {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        spil.getSpillerMedTur().tagTaxi(spil, userInterfaceKontrakt);

    }

    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.taxiInfo(this);
    }
    //|--------- Constructor:-----------------
    public Taxi(int placering){
        setFeltType("Taxi");
        setNavn("Taxi FeltDTO");
        setPlacering(placering);
    }

}
