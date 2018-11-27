package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.braet.dataKlasser.AktionsFelterDTO;

public class Faengsel extends AktionsFelterDTO {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.paaBesoegIFaengsel();
    }

    //|--------- Constructor:-----------------
    public Faengsel(String navn, int placering){
        setFeltType("Fængsel");
        setNavn(navn);
        setPlacering(placering);
    }
}
