package ModelEnteties.Spiller;

import ModelEnteties.braet.controllerKlasser.Ejendom;
import ModelEnteties.braet.controllerKlasser.Jernbane;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;
import ModelEnteties.singletoner.RandomSingleton;
import ModelEnteties.singletoner.ScannerSingleton;

import java.util.ArrayList;
import java.util.Scanner;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: Spiller
 *
 * @author Claes
 *  Denne klasse holder den indivudelles spiller informationer,
 *  Den bliver brugt i SpilLederen, til at genere en liste med spillerObjekter
 *  som indholder alle de spillere der indgår i et givet spil.
 */
public abstract class SpillerData {
    //|--------- Variabler:-----------------
    private int spillerPosition=0;
    private int id;
    private String navn;
    ArrayList<Ejendom> spillerEjendomme = new ArrayList<Ejendom>();
    ArrayList<Jernbane> spillerJernbaner = new ArrayList<Jernbane>();
    ArrayList<ChanceAktion> spillerAktionsKort = new ArrayList<ChanceAktion>();
    double penge = 1500;
    boolean faengselsStraf = false;
    boolean harGivetOp=false;
    boolean harAnketDomDenneRunde = false;
    boolean harSlaaetForTuren = false;

    //Singleton Variabler;
    private RandomSingleton randomTal = RandomSingleton.getInstance();
    private ScannerSingleton scanner = ScannerSingleton.getInstance();

    //|--------- Getters og Setters:-----------------
    public RandomSingleton getRandomTal() {
        return randomTal;
    }

    public void setRandomTal(RandomSingleton randomTal) {
        this.randomTal = randomTal;
    }

    public ScannerSingleton getScanner() {
        return scanner;
    }

    public void setScanner(ScannerSingleton scanner) {
        this.scanner = scanner;
    }
    public int getSpillerPosition() {
        return spillerPosition;
    }

    public void setSpillerPosition(int spillerPosition) {
        this.spillerPosition = spillerPosition;
    }

    public boolean isFaengselsStraf() {
        return faengselsStraf;
    }

    public void setFaengselsStraf(boolean faengselsStraf) {
        this.faengselsStraf = faengselsStraf;
    }

    public ArrayList<Ejendom> getSpillerEjendomme() {
        return spillerEjendomme;
    }

    public void setSpillerEjendomme(ArrayList<Ejendom> spillerEjendomme) {
        this.spillerEjendomme = spillerEjendomme;
    }

    public ArrayList<Jernbane> getSpillerJernbaner() {
        return spillerJernbaner;
    }

    public void setSpillerJernbaner(ArrayList<Jernbane> spillerJernbaner) {
        this.spillerJernbaner = spillerJernbaner;
    }

    public ArrayList<ChanceAktion> getSpillerAktionsKort() {
        return spillerAktionsKort;
    }

    public void setSpillerAktionsKort(ArrayList<ChanceAktion> spillerAktionsKort) {
        this.spillerAktionsKort = spillerAktionsKort;
    }

    public void addSpillerAktionsKort(ChanceAktion spillerAktionsKort) {
        this.spillerAktionsKort.add(spillerAktionsKort);
    }

    public double getPenge() {
        return penge;
    }

    public void setPenge(double penge) {
        this.penge = penge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHarGivetOp() {
        return harGivetOp;
    }

    public void setHarGivetOp(boolean harGivetOp) {
        this.harGivetOp = harGivetOp;
    }
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    public boolean isHarSlaaetForTuren() {
        return harSlaaetForTuren;
    }

    public void setHarSlaaetForTuren(boolean harSlaaetForTuren) {
        this.harSlaaetForTuren = harSlaaetForTuren;
    }

    public boolean isHarAnketDomDenneRunde() {
        return harAnketDomDenneRunde;
    }

    public void setHarAnketDomDenneRunde(boolean harAnketDomDenneRunde) {
        this.harAnketDomDenneRunde = harAnketDomDenneRunde;
    }
    public void addPenge(int beloeb) {
        this.penge+=beloeb;
    }


}
