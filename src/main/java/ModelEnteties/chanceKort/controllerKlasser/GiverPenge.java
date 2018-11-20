package ModelEnteties.chanceKort.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;

import java.util.Random;

public class GiverPenge extends ChanceAktion {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------
    public void DirketeAktion(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.printChanceKortDirekte(this);
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setPenge(spillerMedTur.getPenge()+getPengeVerdi());
    }
    public void BetingetAktion(){ }

    //|--------- Constructor:-----------------
    public GiverPenge(){
        int potentiellePenge = ra.nextInt(499)+1;
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String startBeskrivelse = "Du modtager: ";
        String beløb = Integer.toString(potentiellePenge);
        String grund = getPositiveGrunde()[valgAfGrund];
        String forklaring = startBeskrivelse.concat(beløb).concat(grund);

        this.setBeskrivelse(forklaring);
        this.setPengeVerdi(potentiellePenge);
    }
}
