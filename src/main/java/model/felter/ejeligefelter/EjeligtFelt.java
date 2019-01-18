package model.felter.ejeligefelter;

import model.Spiller;
import model.felter.Felt;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjeligtFelt
 * @author
 *  Denne klasse er forældre klassen til alle de braet som opfører sig relativt
 *  statisk, samt har den egenskab at de kan ejes af et spiller objekt. Så vidt
 *  har denne klasse børne-klasserne:
 *          1. Ejendomm
 *          2. JernbaneCO
 */
public abstract class EjeligtFelt extends Felt {

    //  Dynamiske Variabler
    private Spiller ejer;
    private int pris;
    private boolean pantsat = false;
    private double pantsaetningsVaerdi;

    // TODO: Flyt gruppe ud af EjeligtFelt og over i Ejendom - det er det eneste sted den bruges.
    private Ejendomsgruppe gruppe;


    public EjeligtFelt(String navn, int pris, int placering ){
        super(navn, placering);
        this.pris = pris;
    }


    //|--------- Getters og Setters:-----------------

    public double getPantsaetningsVaerdi() {
        return pantsaetningsVaerdi;
    }

    public void setPantsaetningsVaerdi(double pantsaetningsVaerdi) {
        this.pantsaetningsVaerdi = pantsaetningsVaerdi;
    }

    public String getEjerNavn(){
        return ejer.getNavn();
    }

    public Spiller getEjer() {
        return ejer;
    }

    public void setEjer(Spiller ejer) {
        this.ejer = ejer;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public boolean isPantsat() {
        return pantsat;
    }

    public void setPantsat(boolean pantsat) {
        this.pantsat = pantsat;
    }

    public Ejendomsgruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(Ejendomsgruppe gruppe) {
        this.gruppe = gruppe;
    }

}
