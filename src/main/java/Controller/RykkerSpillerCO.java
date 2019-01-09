package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.ChanceAktionDTO;

import java.util.Random;

public class RykkerSpillerCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void DirketeAktion(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();

        userInterfaceKontrakt.printChanceKortDirekte(this);
        userInterfaceKontrakt.chanceKortTilføjet();

        spillerMedTur.addSpillerAktionsKort(this);

    }
    public void BetingetAktion(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
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
