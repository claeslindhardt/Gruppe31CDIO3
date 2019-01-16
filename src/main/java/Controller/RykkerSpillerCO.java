package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.chancekort.Chancekort;

import java.util.Random;

public class RykkerSpillerCO extends Chancekort {
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

        spil.getChanceKort().remove(this);
        spiller.addChancekort(  this );

        userInterfaceKontrakt.trækEtChancekort();
        userInterfaceKontrakt.printChanceKortDirekte(this);
        userInterfaceKontrakt.chanceKortTilføjet();
    }


    public void BetingetAktion(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        spil.getHandlinger().tagTaxi(spil.getSpil().getSpillerMedTur(),spil, userInterfaceKontrakt);
        userInterfaceKontrakt.chanceKortBrugt();
    }

    //|--------- Constructor:-----------------
    public RykkerSpillerCO(){
        super("", true );
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String startBeskrivelse = "Du for lov til at tage en TaxiCO, fordi ";
        String grund = getPositiveGrunde()[valgAfGrund];
        String forklaring = startBeskrivelse.concat(grund);

        this.setBeskrivelse(forklaring);
        this.setKortBeskrivelse("Kom gratis med taxa");
    }
}
