package ModelEnteties.chanceKort.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;

import java.util.Random;

public class RykkerSpiller extends ChanceAktion {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------
    public void DirketeAktion(SpilController spil, UserInterface userInterface){
        SpillerController spillerMedTur = spil.getSpillerMedTur();

        userInterface.printChanceKortDirekte(this);
        userInterface.chanceKortTilføjet();

        spillerMedTur.addSpillerAktionsKort(this);

    }
    public void BetingetAktion(SpilController spil, UserInterface userInterface){
        spil.getSpillerMedTur().tagTaxi(spil,userInterface);
        userInterface.chanceKortBrugt();
    }

    //|--------- Constructor:-----------------
    public RykkerSpiller(){
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String startBeskrivelse = "Du for lov til at tage en Taxi, fordi ";
        String grund = getPositiveGrunde()[valgAfGrund];
        String forklaring = startBeskrivelse.concat(grund);

        this.setBeskrivelse(forklaring);
    }
}
