package ModelEnteties.braet.controllerKlasser;

import Controller.SpilCO;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.braet.dataKlasser.AktionsFelterDTO;

public class FaengselCO extends AktionsFelterDTO {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilCO spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.paaBesoegIFaengsel();
    }

    //|--------- Constructor:-----------------
    public FaengselCO(String navn, int placering){
        setFeltType("Fængsel");
        setNavn(navn);
        setPlacering(placering);
    }
}
