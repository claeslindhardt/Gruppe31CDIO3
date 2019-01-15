package ModelEnteties;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.raflebaeger.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;
import ModelEnteties.felter.EjendomCO;

import java.util.ArrayList;


public class Spil {

    private int spillerTur = 1;

    private Spiller[] spillere;
    private UserInterfaceKontrakt ui;
    private RafleBaeger rafleBaeger;
    private boolean vinderFindes;

    private FeltDTO[] felter;

    public ArrayList<ChanceAktionDTO> getChanceKort() {
        return chanceKort;
    }

    public void setChanceKort(ArrayList<ChanceAktionDTO> chanceKort) {
        this.chanceKort = chanceKort;
    }
    private ArrayList<ChanceAktionDTO> chanceKort;

    private  boolean kør = true;

    public int getSpillerTur() {
        return spillerTur;
    }

    public void setSpillerTur(int spillerTur) {
        this.spillerTur = spillerTur;
    }

    public Spiller[] getSpillere() {
        return spillere;
    }

    public void setSpillere(Spiller ... spillere) {
        this.spillere = spillere;
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


    public FeltDTO[] getFelter() {
        return felter;
    }

    public void setFelter(FeltDTO[] felter) {
        this.felter = felter;
    }


    public int getBankeraadGraense(){return 0;}


    public int getAntalSpillere(){
        return spillere.length;
    }


    public int getAntalFelter(){ return felter.length;}

    public Spiller getSpiller( int spillerIndex ){
        return spillere[spillerIndex];
    }


    public Spiller getSpillerMedTur(){
        return spillere[spillerTur-1];
    }

    // TODO: Fjern denne
    public void setVinder(int i){}

    // TODO: Fjern denne!
    public ArrayList<Spiller> getSpillereArrayList(){

        ArrayList<Spiller> spillere = new ArrayList<>();

        for( Spiller spiller : this.spillere ){
            spillere.add(spiller);
        }

        return spillere;
    }

    public void setVinderFindes(boolean vinderFindes) {
        this.vinderFindes = vinderFindes;
    }

    public boolean getVinderFindes() {
        return vinderFindes;
    }
}
