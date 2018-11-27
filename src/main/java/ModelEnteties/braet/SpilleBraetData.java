package ModelEnteties.braet;

import ModelEnteties.braet.controllerKlasser.EjendomsGruppeManager;
import ModelEnteties.braet.controllerKlasser.Jernbane;
import ModelEnteties.braet.dataKlasser.FeltDTO;

import java.util.ArrayList;
import java.util.Random;

public class SpilleBraetData {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|--------- Variabler:-----------------
    private ArrayList<FeltDTO> bret = new ArrayList<FeltDTO>();
    private int StartGrundPris = 200;
    private int prisStigningAfEjendomme = 5;
    private int standardLeje = 50;
    private int standardAntalChanceKortPrFelt = 12;
    private ArrayList<Jernbane> jernbaner= new ArrayList<Jernbane>();



    private EjendomsGruppeManager ejendomsGruppeManager = new EjendomsGruppeManager(3);

    //|--------- Getters og Setters:-----------------
    public ArrayList<FeltDTO> getBret() {
        return bret;
    }

    public void setBret(ArrayList<FeltDTO> bret) {
        this.bret = bret;
    }

    public EjendomsGruppeManager getEjendomsGruppeManager() {
        return ejendomsGruppeManager;
    }

    public void setEjendomsGruppeManager(EjendomsGruppeManager ejendomsGruppeManager) {
        this.ejendomsGruppeManager = ejendomsGruppeManager;
    }

    public void addBret(FeltDTO feltDTO){
        this.bret.add(feltDTO);
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
