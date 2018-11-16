package Controller;

public class SpilController extends SpilData {
    public void slutSpillerTur(){
        getSpillerMedTur().setHarSlaaetForTuren(false);
        getSpillerMedTur().setHarAnketDomDenneRunde(false);
        tjekForBankeRaadt();

        if (getSpillerTur() >= getAntalSpillere()) {
            setSpillerTur(1);

        } else if(getSpillerTur() <= getAntalSpillere()){
            setSpillerTur(getSpillerTur()+1);
        }


    }

    public void tjekForBankeRaadt(){

        if(getSpillerMedTur().getPenge()<getBankeraadGraense()){
            UserInterface.bankeRaadtGrundetLiquditet(getBankeraadGraense());
            getSpillerMedTur().setHarGivetOp(true);
            getSpillerMedTur().getSpillerEjendomme().clear();
            int udgaaetSpiller = getSpillerMedTur().getId()+1;
            UserInterface.spillerUdgår(udgaaetSpiller);
        }

    }
    /**
     * Hvorfor 2 constructore?
     * jo fordi man kan enten konstruere et spil med default configurationer eller man kan selv
     * vælge dem.
     */
    public SpilController(){

    }
    public SpilController(int antalSpillere,int antalFelter, int antalTerninger, int bankeRaadtGrense){
        this.setAntalSpillere(antalSpillere);
        this.setAntalFelter(antalFelter);
        this.setAntalTerninger(antalTerninger);
        this.setBankeraadGraense(bankeRaadtGrense);
    }
}
