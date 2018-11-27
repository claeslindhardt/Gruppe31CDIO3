package ModelEnteties.braet.dataKlasser;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.controllerKlasser.EjendomCO;
import ModelEnteties.braet.controllerKlasser.EjendomsGruppeCO;
import ModelEnteties.braet.controllerKlasser.Jernbane;

import java.util.ArrayList;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjeligtFeltDTODTO
 * @author
 *  Denne klasse er forældre klassen til alle de braet som opfører sig relativt
 *  statisk, samt har den egenskab at de kan ejes af et spiller objekt. Så vidt
 *  har denne klasse børne-klasserne:
 *          1. Ejendomm
 *          2. Jernbane
 */
public abstract class EjeligtFeltDTODTO extends FeltDTO {
    //|--------- Variabler:-----------------
    //Statiske Variabler
    private static ArrayList<EjendomCO> alleEjendomme = new ArrayList<EjendomCO>();
    private static ArrayList<Jernbane> alleJernbaner = new ArrayList<Jernbane>();

    //  Dynamiske Variabler
    private SpillerController ejer;
    private int pris = 200;
    private boolean pantsat = false;

    // TODO: Flyt gruppe ud af EjeligtFeltDTODTO og over i EjendomCO - det er det eneste sted den bruges.
    private EjendomsGruppeCO gruppe;
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

    public EjendomsGruppeCO getGruppe() {
        return gruppe;
    }

    public void setGruppe(EjendomsGruppeCO gruppe) {
        this.gruppe = gruppe;
    }

    public static ArrayList<EjendomCO> getAlleEjendomme() {
        return alleEjendomme;
    }

    public static void setAlleEjendomme(ArrayList<EjendomCO> alleEjendomme) {
        EjeligtFeltDTODTO.alleEjendomme = alleEjendomme;
    }

    public static void addAlleEjendomme(EjendomCO enEjendom) {
        EjeligtFeltDTODTO.alleEjendomme.add(enEjendom);
    }

    public static ArrayList<Jernbane> getAlleJernbaner() {
        return alleJernbaner;
    }

    public static void setAlleJernbaner(ArrayList<Jernbane> alleJernbaner) {
        EjeligtFeltDTODTO.alleJernbaner = alleJernbaner;
    }

    public static void addAlleJernbaner(Jernbane enJernbane) {
        EjeligtFeltDTODTO.alleJernbaner.add(enJernbane);
    }

}
