package ModelEnteties;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;


public class Spil {

    private int spillerTur;

    private SpillerDTO[] spillere;
    private BraetDTO braet;
    private UserInterfaceKontrakt ui;
    private RafleBaeger rafleBaeger;
    private ChanceAktionDTO[] chanceKort;

    private  int bankerotGraense = 0;
    private  boolean kør = true;

    public int getSpillerTur() {
        return spillerTur;
    }

    public void setSpillerTur(int spillerTur) {
        this.spillerTur = spillerTur;
    }

    public SpillerDTO[] getSpillere() {
        return spillere;
    }

    public void setSpillere(SpillerDTO ... spillere) {
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

    public RafleBaeger getRafleBaeger() {
        return rafleBaeger;
    }

    public void setRafleBaeger(RafleBaeger rafleBaeger) {
        this.rafleBaeger = rafleBaeger;
    }

    public boolean isKør() {
        return kør;
    }

    public void setKør(boolean kør) {
        this.kør = kør;
    }


    public int getAntalSpillere(){
        return spillere.length;
    }

    public SpillerDTO getSpiller( int spillerIndex ){
        return spillere[spillerIndex];
    }

}
