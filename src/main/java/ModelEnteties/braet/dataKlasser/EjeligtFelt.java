package ModelEnteties.braet.dataKlasser;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.controllerKlasser.Ejendom;
import ModelEnteties.braet.controllerKlasser.EjendomsGruppe;
import ModelEnteties.braet.controllerKlasser.Jernbane;

import java.util.ArrayList;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjeligtFelt
 * @author
 *  Denne klasse er forældre klassen til alle de braet som opfører sig relativt
 *  statisk, samt har den egenskab at de kan ejes af et spiller objekt. Så vidt
 *  har denne klasse børne-klasserne:
 *          1. Ejendomm
 *          2. Jernbane
 */
public abstract class EjeligtFelt extends Felt{
    //|--------- Variabler:-----------------
    //Statiske Variabler
    private static ArrayList<Ejendom> alleEjendomme = new ArrayList<Ejendom>();
    private static ArrayList<Jernbane> alleJernbaner = new ArrayList<Jernbane>();

    //  Dynamiske Variabler
    private SpillerController ejer;
    private int pris = 200;
    private boolean pantsat = false;

    // TODO: Flyt gruppe ud af EjeligtFelt og over i Ejendom - det er det eneste sted den bruges.
    private EjendomsGruppe gruppe;
    //|--------- Getters og Setters:-----------------

    public String getEjerNavn(){
        return ejer.getNavn();
    }

    public SpillerController getEjer() {
        return ejer;
    }

    public void setEjer(SpillerController ejer) {
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

    public EjendomsGruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(EjendomsGruppe gruppe) {
        this.gruppe = gruppe;
    }

    public static ArrayList<Ejendom> getAlleEjendomme() {
        return alleEjendomme;
    }

    public static void setAlleEjendomme(ArrayList<Ejendom> alleEjendomme) {
        EjeligtFelt.alleEjendomme = alleEjendomme;
    }

    public static void addAlleEjendomme(Ejendom enEjendom) {
        EjeligtFelt.alleEjendomme.add(enEjendom);
    }

    public static ArrayList<Jernbane> getAlleJernbaner() {
        return alleJernbaner;
    }

    public static void setAlleJernbaner(ArrayList<Jernbane> alleJernbaner) {
        EjeligtFelt.alleJernbaner = alleJernbaner;
    }

    public static void addAlleJernbaner(Jernbane enJernbane) {
        EjeligtFelt.alleJernbaner.add(enJernbane);
    }

}
