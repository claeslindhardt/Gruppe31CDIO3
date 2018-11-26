package ModelEnteties.braet;

import ModelEnteties.braet.controllerKlasser.ChanceFelt;
import ModelEnteties.braet.controllerKlasser.EjendomsGruppeManager;
import ModelEnteties.braet.controllerKlasser.Jernbane;
import ModelEnteties.braet.dataKlasser.Felt;

import java.util.ArrayList;
import java.util.Random;

public class SpilleBraetData {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|--------- Variabler:-----------------
    private ArrayList<Felt> bret = new ArrayList<Felt>();
    private int StartGrundPris = 200;
    private int prisStigningAfEjendomme = 5;
    private int standardLeje = 50;
    private int standardAntalChanceKortPrFelt = 12;
    private ArrayList<Jernbane> jernbaner= new ArrayList<Jernbane>();



    private EjendomsGruppeManager ejendomsGruppeManager = new EjendomsGruppeManager(3);

    //|--------- Getters og Setters:-----------------
    public ArrayList<Felt> getBret() {
        return bret;
    }

    public void setBret(ArrayList<Felt> bret) {
        this.bret = bret;
    }

    public EjendomsGruppeManager getEjendomsGruppeManager() {
        return ejendomsGruppeManager;
    }

    public void setEjendomsGruppeManager(EjendomsGruppeManager ejendomsGruppeManager) {
        this.ejendomsGruppeManager = ejendomsGruppeManager;
    }

    public void addBret(Felt felt){
        this.bret.add(felt);
    }

    public int getStartGrundPris() {
        return StartGrundPris;
    }

    public void setStartGrundPris(int startGrundPris) {
        StartGrundPris = startGrundPris;
    }

    public int getPrisStigningAfEjendomme() {
        return prisStigningAfEjendomme;
    }

    public void setPrisStigningAfEjendomme(int prisStigningAfEjendomme) {
        this.prisStigningAfEjendomme = prisStigningAfEjendomme;
    }
    public int getStandardLeje() {
        return standardLeje;
    }

    public void setStandardLeje(int standardLeje) {
        this.standardLeje = standardLeje;
    }
    public ArrayList<Jernbane> getJernbaner() {
        return jernbaner;
    }

    public int getStandardAntalChanceKortPrFelt() {
        return standardAntalChanceKortPrFelt;
    }

    public void setStandardAntalChanceKortPrFelt(int standardAntalChanceKortPrFelt) {
        this.standardAntalChanceKortPrFelt = standardAntalChanceKortPrFelt;
    }


}
