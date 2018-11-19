package Controller;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.SpilleBraetController;

import java.util.Random;
import java.util.Scanner;

public class SpilController extends SpilData {

    //|----------- Metoder:------------------
    /**
     * Hvorfor 2 constructore?
     * jo fordi man kan enten konstruere et spil med default configurationer eller man kan selv
     * vælge dem.
     */
    public SpilController(UserInterface gui){
        this.userInterface =gui;
        startMenu();
        genererSpillere(getAntalSpillere());
        SpilleBraetController spilleBret = new SpilleBraetController(getAntalFelter(),userInterface);
        RafleBaeger terningsKrus = new RafleBaeger (getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
    }

    public SpilController(int antalSpillere,int antalFelter, int antalTerninger, int bankeRaadtGrense,UserInterface gui){
        this.setAntalSpillere(antalSpillere);
        this.setAntalFelter(antalFelter);
        this.setAntalTerninger(antalTerninger);
        this.setBankeraadGraense(bankeRaadtGrense);
        this.userInterface =gui;
        genererSpillere(getAntalSpillere());
        SpilleBraetController spilleBret = new SpilleBraetController(getAntalFelter(),userInterface);
        RafleBaeger terningsKrus = new RafleBaeger (getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
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
            getSpillerObjekter().get(i).printSpillerStats(getUserInterface());
        }
    }
    public void anketDomsigelse(){
        Random ra = new Random();
        int domsAfsigelseDel1 = ra.nextInt(5)+1;
        int domsAfsigelseDel2 = ra.nextInt(5)+1;
        getUserInterface().retsTerninger(domsAfsigelseDel1, domsAfsigelseDel2);
        if(domsAfsigelseDel1 == domsAfsigelseDel2){
            getUserInterface().heldIRetten();
            getSpillerMedTur().setFaengselsStraf(false);
            getSpillerMedTur().setSpillerPosition( domsAfsigelseDel1+domsAfsigelseDel2);
        }else if(domsAfsigelseDel1 != domsAfsigelseDel2){
            getSpillerMedTur().setFaengselsStraf(true);
            getUserInterface().ingenHeldIRetten();
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
            getUserInterface().bankeRaadtGrundetLiquditet(getBankeraadGraense());
            getSpillerMedTur().setHarGivetOp(true);
            getSpillerMedTur().getSpillerEjendomme().clear();
            int udgaaetSpiller = getSpillerMedTur().getId()+1;
            getUserInterface().spillerUdgår(udgaaetSpiller);
        }

    }

    public void kastTerninger(RafleBaeger terningsKrus, SpilleBraetController spilleBret){
        if(!getSpillerMedTur().isHarSlaaetForTuren()) {
            terningsKrus.slaa();
            getUserInterface().spillerRykkerGrundetTerningslag(terningsKrus);
            if (terningsKrus.erEns()) {
                getUserInterface().ensTerninger();
                getSpillerMedTur().setHarSlaaetForTuren(false);
            } else {
                getSpillerMedTur().setHarSlaaetForTuren(true);
            }
            tjekForPasseringAfStartOgRykSpiller(terningsKrus);
            getUserInterface().midtTerminalLinje();
            spilleBret.getBret().get(getSpillerMedTur().getSpillerPosition()).aktionPaaFelt();
        }else{
            getUserInterface().harSlaaetMedTerningfor();
        }
    }

    //_____________________________________
    //Tjekkere:
    public void tjekForFeangselsStraf(){
        if(getSpillerMedTur().isFaengselsStraf()){
            if(!getSpillerMedTur().isHarAnketDomDenneRunde()){
                getUserInterface().terminalLinje();
                getUserInterface().anketStraffeDom(getSpillerTur());
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

    public void tjekForVinder(){
        if(antalSpillere-tjekAntalSpillereISpil() == 1){
            getUserInterface().terminalLinje();
            SpillerController spillerMedTur = spillerObjekter.get(spillerTur - 1);
            if (!spillerMedTur.isHarGivetOp()){
                //Der ligger en til for at da det er den spiller i rækken, der ligger forud for vinderen, der giver op.
                int vinder =spillerMedTur.getId()+1;
                getUserInterface().vinder(vinder);
                setVinderFindes(true);
                kør = false;
            }

        }
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
            getSpillerMedTur().passeringAfStart(terningKrus.getTotalVaerdi(),this,getUserInterface());
        }else{
            getSpillerMedTur().setSpillerPosition(getSpillerMedTur().getSpillerPosition()+rykVeardi);
        }
        getUserInterface().spillerPosition(getSpillerMedTur().getSpillerPosition());
    }

    //_____________________________________
    //Menuer:
    public void startMenu(){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        getUserInterface().velkomstMenu();

        int menuOpt = scan.nextInt();
        if(menuOpt == 2) {
            startInstillingsMenu();
        }else if(menuOpt == 3){
            getUserInterface().startSpilGrundFejl();
        }else if(menuOpt == 1){
            int starter = rand.nextInt(antalSpillere)+1;
            spillerTur = starter;
        }
        getUserInterface().opretteInstillinger(getAntalSpillere(),getAntalFelter(),getAntalTerninger(),getSpillerTur(),getBankeraadGraense());


    }
    public void startInstillingsMenu(){
        //Todo: make it possible to choose a default option here:
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        getUserInterface().instilingsSporgsmaal0();
        int felter = scan.nextInt();
        setAntalFelter(felter);

        getUserInterface().instilingsSporgsmaall();
        int spillerMeangde = scan.nextInt();
        setAntalSpillere(spillerMeangde);

        int starter = rand.nextInt(antalSpillere)+1;
        spillerTur = starter;
        getUserInterface().instilingsSporgsmaal2();
        int terninger = scan.nextInt();
        setAntalTerninger(terninger);

        getUserInterface().instilingsSporgsmaal3();
        int driftsomkostninger = scan.nextInt();
        setBankeraadGraense(driftsomkostninger);
    }

    public void turMenu(SpilleBraetController spilleBret, RafleBaeger terningsKrus){
        Scanner scan = new Scanner(System.in);

        getUserInterface().TurMenu(getSpillerTur());

        int input = scan.nextInt();
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
                getSpillerMedTur().chanceKortMuligheder(getUserInterface());
                break;
            case 4:
                getSpillerMedTur().visEjendeFelter(getUserInterface());
                break;
            case 5:
                spilleBret.printBret(getUserInterface());
                break;
            case 6:
                printSpilleresInfo();
                break;
            case 7:
                getSpillerMedTur().givOp(this,getUserInterface());
                break;
            case 8:
                //getSpillerMedTur().bygPaaEjendom();
                break;
            case 9:
                //getSpillerMedTur().handelMedEjendomme();
                break;
            case 99:
                kør = false;
                break;
            default:
                getUserInterface().ikkeMuligt();
        }

    }
}
