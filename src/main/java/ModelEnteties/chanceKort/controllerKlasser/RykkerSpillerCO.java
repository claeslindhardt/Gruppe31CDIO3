package ModelEnteties.chanceKort.controllerKlasser;

import Controller.SpilCO;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerCO;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktionDTO;

import java.util.Random;

public class RykkerSpillerCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------
    public void DirketeAktion(SpilCO spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();

        userInterfaceKontrakt.printChanceKortDirekte(this);
        userInterfaceKontrakt.chanceKortTilføjet();

        spillerMedTur.addSpillerAktionsKort(this);

    }
    public void BetingetAktion(SpilCO spil, UserInterfaceKontrakt userInterfaceKontrakt){
        spil.getSpillerMedTur().tagTaxi(spil, userInterfaceKontrakt);
        userInterfaceKontrakt.chanceKortBrugt();
    }

    //|--------- Constructor:-----------------
    public RykkerSpillerCO(){
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String startBeskrivelse = "Du for lov til at tage en TaxiCO, fordi ";
        String grund = getPositiveGrunde()[valgAfGrund];
        String forklaring = startBeskrivelse.concat(grund);

        this.setBeskrivelse(forklaring);
    }
}
