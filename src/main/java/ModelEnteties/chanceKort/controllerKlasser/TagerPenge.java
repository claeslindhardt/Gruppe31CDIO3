package ModelEnteties.chanceKort.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;

import java.util.Random;

public class TagerPenge extends ChanceAktion {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------
    public void DirketeAktion(SpilController spil, UserInterface userInterface){
        userInterface.printChanceKortDirekte(this);

        SpillerController spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setPenge(spillerMedTur.getPenge()-getPengeVerdi());
    }
    public void BetingetAktion(){ }

    //|--------- Constructor:-----------------
    public TagerPenge(){
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
