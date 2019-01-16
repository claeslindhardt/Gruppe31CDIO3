package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.ChanceAktionDTO;

import java.util.Random;

public class GiverPengeCO extends ChanceAktionDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:--------------------

    /**@author Jacob
     *
     * Denne metode gør at spilleren får de penge som chancekortet fortæller at spilleren skal modtage, og det
     * sker direkte når spilleren trækker kortet.
     *
     * @param spil - SpilController som leder og fordeler funktionerne i spillet
     * @param userInterfaceKontrakt - Så det kan ske i GUI
     */
    public void DirketeAktion(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.trækEtChancekort();
        userInterfaceKontrakt.printChanceKortDirekte(this);
        Spiller spillerMedTur = spil.getSpil().getSpillerMedTur();
        spillerMedTur.setPenge(spillerMedTur.getPenge()+ getPengeVerdi());
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
