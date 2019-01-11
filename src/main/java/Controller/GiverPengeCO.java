package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.Random;

public class GiverPengeCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void DirketeAktion(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.printChanceKortDirekte(this);
        SpillerCO spillerMedTur = spil.getSpil().getSpillerMedTur();
        spillerMedTur.setPenge(spillerMedTur.getPenge()+getPengeVerdi());
        userInterfaceKontrakt.updateSpillere(spil.getSpil().getSpillerMedTur());
    }
    public void BetingetAktion(){ }

    //|--------- Constructor:-----------------
    public GiverPengeCO(){
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
