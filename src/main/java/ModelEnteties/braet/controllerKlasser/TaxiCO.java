package ModelEnteties.braet.controllerKlasser;

import Controller.SpilCO;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.braet.dataKlasser.AktionsFelterDTO;

public class TaxiCO extends AktionsFelterDTO {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilCO spil, UserInterfaceKontrakt userInterfaceKontrakt){
        spil.getSpillerMedTur().tagTaxi(spil, userInterfaceKontrakt);

    }

    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.taxiInfo(this);
    }
    //|--------- Constructor:-----------------
    public TaxiCO(int placering){
        setFeltType("TaxiCO");
        setNavn("TaxiCO FeltDTO");
        setPlacering(placering);
    }

}
