package Controller;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.SpilleBraetController;
import ModelEnteties.singletoner.RandomSingleton;

import java.util.Random;

public class SpilController extends SpilData {

    //|----------- Metoder:------------------
    /**
     * Hvorfor 2 constructore?
     * jo fordi man kan enten konstruere et spil med default configurationer eller man kan selv
     * vælge dem.
     */
    public SpilController(UserInterfaceKontrakt gui){
        this.userInterfaceKontrakt =gui;
        startMenu();
        genererSpillere(getAntalSpillere());
        SpilleBraetController spilleBret = new SpilleBraetController(getAntalFelter(), userInterfaceKontrakt);
        RafleBaeger terningsKrus = new RafleBaeger (getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
        gui.generGUIBret(getAntalFelter());
    }

    public SpilController(int antalSpillere, int antalFelter, int antalTerninger, int bankeRaadtGrense, UserInterfaceKontrakt gui){
        this.setAntalSpillere(antalSpillere);
        this.setAntalFelter(antalFelter);
        this.setAntalTerninger(antalTerninger);
        this.setBankeraadGraense(bankeRaadtGrense);
        this.userInterfaceKontrakt =gui;
        genererSpillere(getAntalSpillere());
        SpilleBraetController spilleBret = new SpilleBraetController(getAntalFelter(), userInterfaceKontrakt);
        RafleBaeger terningsKrus = new RafleBaeger (getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
        gui.generGUIBret(getAntalFelter());
    }
    //_____________________________________
    // Diverse:

    public void genererSpillere(int antalSpillere){
        for(int i = 0;i < antalSpillere;i++){
            SpillerController deltager = new SpillerController("Jonny",i,0);
            getSpillerObjekter().add(deltager);
        }
    }
    public void printSpilleresInfo(){
        for(int i = 0;i < getSpillerObjekter().size();i++){
            getSpillerObjekter().get(i).printSpillerStats(getUserInterfaceKontrakt());
        }
    }
    public void anketDomsigelse(){
        Random ra = new Random();
        int domsAfsigelseDel1 = ra.nextInt(5)+1;
        int domsAfsigelseDel2 = ra.nextInt(5)+1;
        getUserInterfaceKontrakt().retsTerninger(domsAfsigelseDel1, domsAfsigelseDel2);
        if(domsAfsigelseDel1 == domsAfsigelseDel2){
            getUserInterfaceKontrakt().heldIRetten();
            getSpillerMedTur().setFaengselsStraf(false);
            getSpillerMedTur().setSpillerPosition( domsAfsigelseDel1+domsAfsigelseDel2);
        }else if(domsAfsigelseDel1 != domsAfsigelseDel2){
            getSpillerMedTur().setFaengselsStraf(true);
            getUserInterfaceKontrakt().ingenHeldIRetten();
        }

    }
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
            getUserInterfaceKontrakt().bankeRaadtGrundetLiquditet(getBankeraadGraense());
            getSpillerMedTur().setHarGivetOp(true);
            getSpillerMedTur().getSpillerEjendomme().clear();
            int udgaaetSpiller = getSpillerMedTur().getId()+1;
            getUserInterfaceKontrakt().spillerUdgår(udgaaetSpiller);
        }

    }

    public void kastTerninger(RafleBaeger terningsKrus, SpilleBraetController spilleBret){
        if(!getSpillerMedTur().isHarSlaaetForTuren()) {
            terningsKrus.slaa();
            getUserInterfaceKontrakt().spillerRykkerGrundetTerningslag(terningsKrus);
            if (terningsKrus.erEns()) {
                getUserInterfaceKontrakt().ensTerninger();
                getSpillerMedTur().setHarSlaaetForTuren(false);
            } else {
                getSpillerMedTur().setHarSlaaetForTuren(true);
            }
            tjekForPasseringAfStartOgRykSpiller(terningsKrus);
            getUserInterfaceKontrakt().midtTerminalLinje();
           spilleBret.getBret().get(getSpillerMedTur().getSpillerPosition()).aktionPaaFelt(this, getUserInterfaceKontrakt());
        }else{
            getUserInterfaceKontrakt().harSlaaetMedTerningfor();
        }
    }

    //_____________________________________
    //Tjekkere:
    public void tjekForFeangselsStraf(){
        if(getSpillerMedTur().isFaengselsStraf()){
            if(!getSpillerMedTur().isHarAnketDomDenneRunde()){
                getUserInterfaceKontrakt().terminalLinje();
                getUserInterfaceKontrakt().anketStraffeDom(getSpillerTur());
                anketDomsigelse();
                getSpillerMedTur().setHarAnketDomDenneRunde(true);
            }

        }
    }

    public void tjekOmGivetOp(){
        if (getSpillerMedTur().isHarGivetOp()) {
            if (spillerTur == antalSpillere) {
                spillerTur = 1;
            } else {
                spillerTur++;
            }
        }
    }

    public int tjekForVinder(){
        int vinder1=0;

        if(antalSpillere-tjekAntalSpillereISpil() == 1){
            getUserInterfaceKontrakt().terminalLinje();
            SpillerController spillerMedTur = spillerObjekter.get(spillerTur - 1);
            if (!spillerMedTur.isHarGivetOp()){
                //Der ligger en til for at da det er den spiller i rækken, der ligger forud for vinderen, der giver op.
                int vinder =spillerMedTur.getId()+1;
                vinder1=vinder;
                getUserInterfaceKontrakt().vinder(vinder);
                setVinderFindes(true);
                kør = false;
            }

        }return vinder1;
    }


    public int tjekAntalSpillereISpil() {
        int UdgaetSpillere = 0;
        for (int i = 0; i < spillerObjekter.size(); i++) {
            if (spillerObjekter.get(i).isHarGivetOp()) {
                UdgaetSpillere++;
            }
        }

        return UdgaetSpillere;
    }

    public void tjekForPasseringAfStartOgRykSpiller(RafleBaeger terningKrus){
        int rykVeardi = terningKrus.getTotalVaerdi();
        int nuvaerendeposition = getSpillerMedTur().getSpillerPosition();
        if (nuvaerendeposition+rykVeardi>antalFelter){
            getSpillerMedTur().passeringAfStart(terningKrus.getTotalVaerdi(),this, getUserInterfaceKontrakt());
        }else{
            getSpillerMedTur().setSpillerPosition(getSpillerMedTur().getSpillerPosition()+rykVeardi);
        }
        getUserInterfaceKontrakt().spillerPosition(getSpillerMedTur().getSpillerPosition());
    }

    //_____________________________________
    //Menuer:
    public void startMenu(){
        RandomSingleton rand = RandomSingleton.getInstance();
        int menuOpt = getUserInterfaceKontrakt().velkomstMenu();


        if(menuOpt == 2) {
            startInstillingsMenu();
        }else if(menuOpt == 3){
            getUserInterfaceKontrakt().startSpilGrundFejl();
        }else if(menuOpt == 1){
            int starter = rand.nextInt(antalSpillere)+1;
            spillerTur = starter;
        }
        getUserInterfaceKontrakt().opretteInstillinger(getAntalSpillere(),getAntalFelter(),getAntalTerninger(),getSpillerTur(),getBankeraadGraense());


    }
    public void startInstillingsMenu(){
        //Todo: make it possible to choose a default option here:
        Random rand = new Random();

        int felter = getUserInterfaceKontrakt().instilingsSporgsmaal0();
        setAntalFelter(felter);


        int spillerMeangde = getUserInterfaceKontrakt().instilingsSporgsmaall();
        setAntalSpillere(spillerMeangde);

        int starter = rand.nextInt(antalSpillere)+1;
        spillerTur = starter;

        int terninger = getUserInterfaceKontrakt().instilingsSporgsmaal2();
        setAntalTerninger(terninger);

        getUserInterfaceKontrakt().instilingsSporgsmaal3();
        int driftsomkostninger = getUserInterfaceKontrakt().instilingsSporgsmaal2();
        setBankeraadGraense(driftsomkostninger);
    }

    public void turMenu(SpilleBraetController spilleBret, RafleBaeger terningsKrus){

        int input = getUserInterfaceKontrakt().TurMenu(getSpillerTur());

        switch(input){
            case 1:
                kastTerninger(terningsKrus,spilleBret);
                //Denne funktion  kan kalder:
                //tjekForPasseringAfStartOgRykSpiller(Raflebaeger terningKrus)
                //og aktionPåFelt.
                break;
            case 2:
                slutSpillerTur();
                break;
            case 3:
                getSpillerMedTur().chanceKortMuligheder(getUserInterfaceKontrakt());
                break;
            case 4:
                getSpillerMedTur().visEjendeFelter(getUserInterfaceKontrakt());
                break;
            case 5:
                spilleBret.printBret(getUserInterfaceKontrakt());
                break;
            case 6:
                printSpilleresInfo();
                break;
            case 7:
                getSpillerMedTur().givOp(this, getUserInterfaceKontrakt());
                break;
            case 8:
                getSpillerMedTur().koebHusPaaEjendom(getUserInterfaceKontrakt());
                break;
            case 9:
                getSpillerMedTur().handelMedEjendomme();
                break;
            case 99:
                kør = false;
                break;
            default:
                getUserInterfaceKontrakt().ikkeMuligt();
        }

    }
}
