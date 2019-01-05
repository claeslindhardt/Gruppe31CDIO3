package Controller;

import ModelEnteties.Spiller.SpillerCO;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.SpilleBraetCO;

import java.util.ArrayList;

/**___________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: SpilData
 *
 * @author Claes
 *  dette er vores 'gameManager' Dette er klassen som indeholder vores main funktion.
 *  Det er her det bliver besluttet hvad der sker hvornår i spillet, og her alle funktinoer
 *  og klasser bliver samlet til et spil. Det er ligesom drejebogen som ændre sig en smule
 *  fra spil til spil med hjælp fra random(tilfældige) variabler.
 *
 *  Statiske Variabler? I denne klasse er det i udviklings processen, blevet tilstrebet
 *  kun at have statiske variabler her. Eftersom den information der bliver holdt her,
 *  burde være sandt lige meget hvis spillers tur det er. Så det er ting som Brettet
 *  og antallet af spillere. Ting der har en tendens til ikke at ændre sig igennem spillet.
 *
 *  |-- HVORFOR GETTERS OG SETTERS?(ENKAPSULERING)
 *  Vi bruger konceptet ENKAPSULERING som en del af det at vi arbejder objekt orienteret.
 *  Dette gøre det sværer at lave fejl, da man hele tiden skal tænke over om man getter eller setter noget.
 *  Man kapsler ligesom alle variablerne ind i funktioner, i denne kode har vi primært enkapsuleret dem i 2
 *  forskellige slags funktioner-nemlig getters og setters. Vil man tilgå en variabel går det således også gennem en funktinon.
 *  Frem for at gå dirkete til variablen.
 *          Getters: bruges til at få variablen af en bestem verdi
 *          Setters: bruges til at give en variabel en ny værdi
 *
 */
public abstract class SpilData {
    //|-------initiering af objekter: -----------
    //TODO: make singleton of scanner and random here.
    //|--------- Variabler:-----------------
    private int spillerTur=1;
    private int antalSpillere=3;
    private   int antalFelter=20;
    private  ArrayList<SpillerCO> spillerObjekter = new ArrayList<SpillerCO>();
    private  int antalTerninger=2;
    private  int antalChancekortPrFelt = 10;
    private  int bankeraadGraense = 15;
    private  boolean kør = true;
    private  boolean vinderFindes = false;
    private SpilleBraetCO bretGeneretForSpil;
    private UserInterfaceKontrakt userInterfaceKontrakt;
    private RafleBaeger terningeKrus;
    private int vinder=-1;

    //|--------- Getters og Setters:-----------------
    public int getVinder() {
        return vinder;
    }

    public void setVinder(int vinder) {
        this.vinder = vinder;
    }
    public RafleBaeger getTerningeKrus() {
        return terningeKrus;
    }

    public void setTerningeKrus(RafleBaeger terningeKrus) {
        this.terningeKrus = terningeKrus;
    }
    public UserInterfaceKontrakt getUserInterfaceKontrakt() {
        return userInterfaceKontrakt;
    }

    public void setUserInterfaceKontrakt(UserInterfaceKontrakt userInterfaceKontrakt) {
        this.userInterfaceKontrakt = userInterfaceKontrakt;
    }

    /**
     *
     * @return
     */
    public SpillerCO getSpillerMedTur() {
        return spillerObjekter.get(spillerTur-1);
    }

    public int getSpillerTur() {
        return spillerTur;
    }

    public void setSpillerTur(int spillerTur) {
        this.spillerTur = spillerTur;
    }

    public int getAntalSpillere() {
        return antalSpillere;
    }

    public void setAntalSpillere(int antalSpillere) {
        this.antalSpillere = antalSpillere;
    }

    public int getAntalFelter() {
        return antalFelter;
    }

    public void setAntalFelter(int antalFelter) {
        this.antalFelter = antalFelter;
    }

    public ArrayList<SpillerCO> getSpillerObjekter() {
        return spillerObjekter;
    }

    public void setSpillerObjekter(ArrayList<SpillerCO> spillerObjekter) {
        this.spillerObjekter = spillerObjekter;
    }


    public int getAntalChancekortPrFelt() {
        return antalChancekortPrFelt;
    }

    public void setAntalChancekortPrFelt(int antalChancekortPrFelt) {
        this.antalChancekortPrFelt = antalChancekortPrFelt;
    }

    public boolean isKør() {
        return kør;
    }

    public void setKør(boolean kør) {
        this.kør = kør;
    }

    public int getAntalTerninger() {
        return antalTerninger;
    }

    public void setAntalTerninger(int antalTerninger) {
        this.antalTerninger = antalTerninger;
    }
    public boolean isVinderFindes() {
        return vinderFindes;
    }

    public void setVinderFindes(boolean vinderFindes) {
        this.vinderFindes = vinderFindes;
    }
    public int getBankeraadGraense() {
        return bankeraadGraense;
    }

    public void setBankeraadGraense(int bankeraadsgraense) {
        this.bankeraadGraense = bankeraadsgraense;
    }

    /**
     * Denne metode returnerer en SpillerBraetCO variable, så derfor hedder metoden som den hedder istedet
     * for fx. int, double eller lignende.
     * @return
     */
    public SpilleBraetCO getBretGeneretForSpil() {
        return bretGeneretForSpil;
    }

    public void setBretGeneretForSpil(SpilleBraetCO generetBretForSpil) {
        this.bretGeneretForSpil = generetBretForSpil;
    }

    public boolean getVinderFindes(){
        return vinderFindes;
    }
}

