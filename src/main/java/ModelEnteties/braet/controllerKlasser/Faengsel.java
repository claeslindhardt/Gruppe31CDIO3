package ModelEnteties.braet.controllerKlasser;

import ModelEnteties.braet.dataKlasser.AktionsFelter;

import java.util.HashMap;

public class Faengsel extends AktionsFelter {
    //|----------- Metoder:------------------
    public void aktionPaaFelt(){
        //Todo:insert Spilleder.gui.paabesoegIFeangsel her.
    }

    //|--------- Constructor:-----------------
    public Faengsel(String navn, int placering){
        setFeltType("Fængsel");
        setNavn(navn);
        setPlacering(placering);
    }
}
