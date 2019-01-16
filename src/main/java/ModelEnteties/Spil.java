package ModelEnteties;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.Faengsel;
import ModelEnteties.felter.Felt;
import ModelEnteties.raflebaeger.RafleBaeger;

import java.util.ArrayList;


public class Spil {

    private int spillerTur = 1;

    private Spiller[] spillere;
    private UserInterfaceKontrakt ui;
    private RafleBaeger rafleBaeger;
    private boolean vinderFindes;

    private Felt[] felter;

    private ArrayList<ChanceAktionDTO> chanceKort = new ArrayList<>();

    public ArrayList<ChanceAktionDTO> getChanceKort() {
        return chanceKort;
    }

    public void setChanceKort(ArrayList<ChanceAktionDTO> chanceKort) {
        this.chanceKort = chanceKort;
    }

    public void addChancekort(ChanceAktionDTO chancekort ){
        this.chanceKort.add(chancekort);
    }



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


    public Felt[] getFelter() {
        return felter;
    }

    public void setFelter(Felt[] felter) {
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

    /**
     * Finder fængsel feltet i listen over felter, og returnere det.
     * @author Malte
     * @return Faengselfeltet på gui'en.
     */
    public Faengsel getFaengsel(){
        for( Felt felt : felter ){

            if(  felt instanceof Faengsel){
                return (Faengsel) felt;
            }
        }
        return null;
    }

    public void setVinderFindes(boolean vinderFindes) {
        this.vinderFindes = vinderFindes;
    }

    public boolean getVinderFindes() {
        return vinderFindes;
    }

}
