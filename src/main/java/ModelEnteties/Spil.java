package ModelEnteties;

import BoundaryView.UserInterfaceKontrakt;
import Controller.SpillerCO;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;
import ModelEnteties.felter.EjendomCO;

import java.util.ArrayList;


public class Spil {

    private int spillerTur = 1;

    private SpillerCO[] spillere;
    private BraetDTO braet;
    private UserInterfaceKontrakt ui;
    private RafleBaeger rafleBaeger;


    public ChanceAktionDTO[] getChanceKort() {
        return chanceKort;
    }

    public void setChanceKort(ChanceAktionDTO[] chanceKort) {
        this.chanceKort = chanceKort;
    }

    private ChanceAktionDTO[] chanceKort;

    private  int bankerotGraense = 0;
    private  boolean kør = true;

    public int getSpillerTur() {
        return spillerTur;
    }

    public void setSpillerTur(int spillerTur) {
        this.spillerTur = spillerTur;
    }

    public SpillerCO[] getSpillere() {
        return spillere;
    }

    public void setSpillere(SpillerCO ... spillere) {
        this.spillere = spillere;
    }

    public BraetDTO getBraet() {
        return braet;
    }

    public void setBraet(BraetDTO braet) {
        this.braet = braet;
    }

    public UserInterfaceKontrakt getUi() {
        return ui;
    }

    public void setUi(UserInterfaceKontrakt ui) {
        this.ui = ui;
    }

    public RafleBaeger getRaflebaeger() {
        return rafleBaeger;
    }

    public void setRaflebaeger(RafleBaeger rafleBaeger) {
        this.rafleBaeger = rafleBaeger;
    }

    public boolean isKør() {
        return kør;
    }


    public void setKør(boolean kør) {
        this.kør = kør;
    }

    public void setChanceKort(){
    }


    public EjendomCO getEjendomme(){




        return null;
    }

    public int getBankeraadGraense(){return 0;}


    public int getAntalSpillere(){
        return spillere.length;
    }

    public int getAntalFelter(){ return braet.getBret().size(); }

    public SpillerCO getSpiller( int spillerIndex ){
        return spillere[spillerIndex];
    }


    public SpillerCO getSpillerMedTur(){
        return spillere[spillerTur-1];
    }

    // TODO: Fjern denne
    public void setVinder(int i){}

    // TODO: Fjern denne!
    public ArrayList<SpillerCO> getSpillereArrayList(){

        ArrayList<SpillerCO> spillere = new ArrayList<>();

        for( SpillerCO spiller : this.spillere ){
            spillere.add(spiller);
        }

        return spillere;
    }
}
