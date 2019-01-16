package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.ChanceAktionDTO;

import java.util.Random;

public class TagerPengeCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------

    /**
     * Indsæt beskrivelse
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void DirketeAktion(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.printChanceKortDirekte(this);

        Spiller spillerMedTur = spil.getSpil().getSpillerMedTur();
        spillerMedTur.setPenge(spillerMedTur.getPenge()-getPengeVerdi());
        userInterfaceKontrakt.updateSpillere(spil.getSpil().getSpillerMedTur());
    }


    public void BetingetAktion(){ }

    //|--------- Constructor:-----------------

    /**
     * Indsæt beskrivelse
     */
    public TagerPengeCO(){
        int potentiellePenge = ra.nextInt(499)+1;
        int valgAfGrund = ra.nextInt(getNegativeGrunde().length);
        String startBeskrivelse = "Du mister: ";
        String beløb = Integer.toString(potentiellePenge);
        String grund = getNegativeGrunde()[valgAfGrund];
        String forklaring = startBeskrivelse.concat(beløb).concat(grund); //Hvad er concat??

        this.setBeskrivelse(forklaring);
        this.setPengeVerdi(potentiellePenge);
    }
}
