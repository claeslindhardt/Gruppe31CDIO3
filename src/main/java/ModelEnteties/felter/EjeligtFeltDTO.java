package ModelEnteties.felter;

import Controller.JernbaneCO;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spiller;

import java.util.ArrayList;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjeligtFeltDTO
 * @author
 *  Denne klasse er forældre klassen til alle de braet som opfører sig relativt
 *  statisk, samt har den egenskab at de kan ejes af et spiller objekt. Så vidt
 *  har denne klasse børne-klasserne:
 *          1. Ejendomm
 *          2. JernbaneCO
 */
public abstract class EjeligtFeltDTO extends FeltDTO {
    //|--------- Variabler:-----------------
    //Statiske Variabler
    private static ArrayList<EjendomCO> alleEjendomme = new ArrayList<EjendomCO>();
    private static ArrayList<JernbaneCO> alleJernbaner = new ArrayList<JernbaneCO>();

    //  Dynamiske Variabler
    private Spiller ejer;
    private int pris = 200;
    private boolean pantsat = false;

    // TODO: Flyt gruppe ud af EjeligtFeltDTO og over i EjendomCO - det er det eneste sted den bruges.
    private EjendomsGruppeDTO gruppe;
    //|--------- Getters og Setters:-----------------

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

    public EjendomsGruppeDTO getGruppe() {
        return gruppe;
    }

    public void setGruppe(EjendomsGruppeDTO gruppe) {
        this.gruppe = gruppe;
    }

    public static ArrayList<EjendomCO> getAlleEjendomme() {
        return alleEjendomme;
    }

    public static void setAlleEjendomme(ArrayList<EjendomCO> alleEjendomme) {
        EjeligtFeltDTO.alleEjendomme = alleEjendomme;
    }

    public static void addAlleEjendomme(EjendomCO enEjendom) {
        EjeligtFeltDTO.alleEjendomme.add(enEjendom);
    }

    public static ArrayList<JernbaneCO> getAlleJernbaner() {
        return alleJernbaner;
    }

    public static void setAlleJernbaner(ArrayList<JernbaneCO> alleJernbaner) {
        EjeligtFeltDTO.alleJernbaner = alleJernbaner;
    }

    public static void addAlleJernbaner(JernbaneCO enJernbane) {
        EjeligtFeltDTO.alleJernbaner.add(enJernbane);
    }

}
