package ModelEnteties;

import Controller.EjendomsGruppeCO;
import Controller.JernbaneCO;
import ModelEnteties.felter.FeltDTO;

import java.util.ArrayList;
import java.util.Random;

/**
 * Indsæt beskrivelse her
 */
public class BraetDTO {
    //|-------initiering af objekter: -----------
    protected Random ra = new Random();

    //|--------- Variabler:-----------------
    private ArrayList<FeltDTO> bret = new ArrayList<FeltDTO>();
    private int StartGrundPris = 200;
    private int prisStigningAfEjendomme = 5;
    private int standardLeje = 50;
    private int standardAntalChanceKortPrFelt = 12;
    private ArrayList<JernbaneCO> jernbaner= new ArrayList<JernbaneCO>();



    private EjendomsGruppeCO ejendomsGruppeCO = new EjendomsGruppeCO(3);

    //|--------- Getters og Setters:-----------------
    public ArrayList<FeltDTO> getBret() {
        return bret;
    }

    public void setBret(ArrayList<FeltDTO> bret) {
        this.bret = bret;
    }

    public EjendomsGruppeCO getEjendomsGruppeCO() {
        return ejendomsGruppeCO;
    }

    public void setEjendomsGruppeCO(EjendomsGruppeCO ejendomsGruppeCO) {
        this.ejendomsGruppeCO = ejendomsGruppeCO;
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
    public ArrayList<JernbaneCO> getJernbaner() {
        return jernbaner;
    }

    public int getStandardAntalChanceKortPrFelt() {
        return standardAntalChanceKortPrFelt;
    }

    public void setStandardAntalChanceKortPrFelt(int standardAntalChanceKortPrFelt) {
        this.standardAntalChanceKortPrFelt = standardAntalChanceKortPrFelt;
    }

    /**
     * Laver 'bret' ArrayListen om til en FeltDTO-array
     */
    public FeltDTO[] getBretArray(){
        // TODO: Denne skal fjernes når braettet bliver til et array i stedet for array list.
        FeltDTO[] felter = new FeltDTO[bret.size()];

        for( int i = 0; i < felter.length; i++){
            felter[i] = bret.get(i);
        }
        return felter;
    }


}
