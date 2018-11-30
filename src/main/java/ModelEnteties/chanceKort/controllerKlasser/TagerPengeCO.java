package ModelEnteties.chanceKort.controllerKlasser;

import Controller.SpilCO;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerCO;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktionDTO;

import java.util.Random;

public class TagerPengeCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------
    public void DirketeAktion(SpilCO spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.printChanceKortDirekte(this);

        SpillerCO spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setPenge(spillerMedTur.getPenge()-getPengeVerdi());
    }
    public void BetingetAktion(){ }

    //|--------- Constructor:-----------------
    public TagerPengeCO(){
        int potentiellePenge = ra.nextInt(499)+1;
        int valgAfGrund = ra.nextInt(getNegativeGrunde().length);
        String startBeskrivelse = "Du mister: ";
        String beløb = Integer.toString(potentiellePenge);
        String grund = getNegativeGrunde()[valgAfGrund];
        String forklaring = startBeskrivelse.concat(beløb).concat(grund);

        this.setBeskrivelse(forklaring);
        this.setPengeVerdi(potentiellePenge);
    }
}
