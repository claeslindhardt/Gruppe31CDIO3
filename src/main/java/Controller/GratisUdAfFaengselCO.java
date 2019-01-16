package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.ChanceAktionDTO;

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
    public void DirketeAktion(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller spiller = spil.getSpil().getSpillerMedTur();

        spil.getSpil().getChanceKort().remove(this); // Fjerner kortet fra bunken
        spiller.addChancekort(  this );

        userInterfaceKontrakt.printChanceKortDirekte(this);
        userInterfaceKontrakt.trækEtChancekort();

        userInterfaceKontrakt.chanceKortTilføjet();
    }

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void BetingetAktion(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller spillerMedTur = spil.getSpil().getSpillerMedTur();
        spillerMedTur.setFaengselsStraf(false);
        spillerMedTur.setHarSlaaetForTuren(false);
        userInterfaceKontrakt.brugtUdAfFaengsel();
    }

    //|----------- Constructor:------------------
    public GratisUdAfFaengselCO(){
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String grund = getPositiveGrunde()[valgAfGrund];
        String slutBeskrivelse = "Derfor syntes vi at du er så god en borger," +
                "at du fortjener at komme engang gratis ud af fængsel.";
        String forklaring = grund.concat(slutBeskrivelse);

        this.setBeskrivelse(forklaring);
        this.setKortBeskrivelse("Kom gratis ud af faengsel");
    }
}
