package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.Random;

public class RykkerSpillerCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:-------------------

    /**
     * Indsæt beskrivelse her
     * @param userInterfaceKontrakt
     */
    public void DirketeAktion(Handel handel, SpilController spilController, UserInterfaceKontrakt userInterfaceKontrakt){
        Spil spil = spilController.getSpil();
        Spiller spiller = spil.getSpillerMedTur();

        spil.getChanceKort().remove(this); // Fjerner kortet fra bunken
        spiller.addChancekort(  this );

        userInterfaceKontrakt.printChanceKortDirekte(this);
        userInterfaceKontrakt.chanceKortTilføjet();
    }


    public void BetingetAktion(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        spil.getHandlinger().tagTaxi(spil.getSpil().getSpillerMedTur(),spil, userInterfaceKontrakt);
        userInterfaceKontrakt.chanceKortBrugt();
    }

    //|--------- Constructor:-----------------
    public RykkerSpillerCO(){
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String startBeskrivelse = "Du for lov til at tage en TaxiCO, fordi ";
        String grund = getPositiveGrunde()[valgAfGrund];
        String forklaring = startBeskrivelse.concat(grund);

        this.setBeskrivelse(forklaring);
        this.setKortBeskrivelse("Kom gratis med taxa");
    }
}
