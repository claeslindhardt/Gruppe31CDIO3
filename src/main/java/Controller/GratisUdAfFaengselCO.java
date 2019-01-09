package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.Random;

public class GratisUdAfFaengselCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:----------------------

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

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void BetingetAktion(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setFaengselsStraf(false);
        spillerMedTur.setHarSlaaetForTuren(false);
        //todo:test
        userInterfaceKontrakt.brugtUdAfFaengsel();
    }
    //|----------- Constructor:------------------
    public GratisUdAfFaengselCO(){
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String grund = getPositiveGrunde()[valgAfGrund];
        String slutBeskrivelse = " Derfor syntes vi at du er så god en borger," +
                "\nat du fortjener at komme engang gratis ud af fængsel. ";
        String forklaring = grund.concat(slutBeskrivelse);

        this.setBeskrivelse(forklaring);
    }
}
