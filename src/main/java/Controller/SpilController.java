package Controller;

import BoundaryView.GUI.GUIinterface;
import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.BraetDTO;
import ModelEnteties.Spil;
import ModelEnteties.SpillerDTO;
import ModelEnteties.Terning.FalskRaflebaeger;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.singletoner.RandomSingleton;
import spillogik.BevaegelsesLogik;
import spillogik.SpilGenerator;

import java.util.Random;

public class SpilController{

    private UserInterfaceKontrakt ui;
    private Spil spil;


    // TODO: Fjern denne
    public Spil getSpil(){return spil;}

    public void setSpil(Spil spil){
        this.spil = spil;
    }


    /** Laver en ny SpilController med en vilkårlig UI */
    public SpilController(UserInterfaceKontrakt ui){
        this.ui = ui;
    }

    /** Laver en ny SpilController med en GUI */
    public SpilController(){
        ui = new GUIinterface();
    }



    //_____________________________________
    // Diverse:

    /**
     * @author Andreas
     * Metoden gennemgår listen af spillere og undersøger om en spiller i listen har det samme navn.
     * @param navn
     * @return boolean
     */
    public boolean kontrollerNavn(String navn){

        for(int i = 0; i < spil.getSpillere().length; i++){
            if(spil.getSpillere()[i].getNavn().equalsIgnoreCase( navn )) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indsæt beskrivelse her
     */
    public void printSpilleresInfo() {
        /*for (int i = 0; i < spil.getSpillereArrayList().size(); i++) {
            spil.getSpiller(i).printSpillerStats(ui);
        }*/
    }

    /**
     * @author Filip
     * Metode, der afgør om en faengslet spiller løslades eller skal blive i faengsel.
     */
    public void anketDomsigelse() {
        Random ra = new Random();
        int domsAfsigelseDel1 = ra.nextInt(5) + 1;
        int domsAfsigelseDel2 = ra.nextInt(5) + 1;
        ui.retsTerninger(domsAfsigelseDel1, domsAfsigelseDel2);
        if (domsAfsigelseDel1 == domsAfsigelseDel2) {
            ui.heldIRetten();
            spil.getSpillerMedTur().setFaengselsStraf(false);
            //Har udkemmenteret denne da jeg ikke syntes at det giver mening at have den.
            //getSpillerMedTur().setSpillerPosition(domsAfsigelseDel1 + domsAfsigelseDel2);
        } else {
            spil.getSpillerMedTur().setFaengselsStraf(true);
            ui.ingenHeldIRetten();
        }

    }

    /**
     * Indsæt beskrivelse her
     */
    public void slutSpillerTur() {
        spil.getSpillerMedTur().setHarSlaaetForTuren(false);
        spil.getSpillerMedTur().setHarAnketDomDenneRunde(false);
        tjekForBankeRaadt();

        if ( spil.getSpillerTur() >= spil.getAntalSpillere() ) {
            spil.setSpillerTur(1);

        } else if (spil.getSpillerTur() <= spil.getAntalSpillere()) {
            spil.setSpillerTur(spil.getSpillerTur() + 1);
        }


    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForBankeRaadt() {

        if (spil.getSpillerMedTur().getPenge() < 0) {
            ui.bankeRaadtGrundetLiquditet(spil.getBankeraadGraense());
            spil.getSpillerMedTur().setHarGivetOp(true);
            spil.getSpillerMedTur().getSpillerEjendomme().clear();
            int udgaaetSpiller = spil.getSpillerMedTur().getId() + 1;
            ui.spillerUdgår(udgaaetSpiller);
        }

    }

    /**
     * Indsæt beskrivelse her
     * @param terningsKrus
     */
    public void kastTerninger(RafleBaeger terningsKrus) {
        if (!spil.getSpillerMedTur().isHarSlaaetForTuren()) {

            terningsKrus.slaa();

            ui.spillerRykkerGrundetTerningslag(terningsKrus, spil.getSpillerTur());

            if (terningsKrus.erEns()) {
                ui.ensTerninger();
                spil.getSpillerMedTur().setHarSlaaetForTuren(false);
            } else {
                spil.getSpillerMedTur().setHarSlaaetForTuren(true);
            }

            rykSpillerAntalFelter( spil.getSpillerMedTur(), spil.getRaflebaeger().getTotalVaerdi());

        } else {
            ui.harSlaaetMedTerningfor();
        }
    }


    /**
     * @author Malte
     * Rykker spilleren et bestemt antal felter fremad. Den beregner hvor mange
     * gange over start man bevæger sig, og udløser metoden {@link #rykSpillerTilFelt}.
     *
     * @param spiller       Spilleren der skal rykkes
     * @param felterAtRykke Hvor mange felter fremad spilleren rykker
     */
    public void rykSpillerAntalFelter( SpillerCO spiller, int felterAtRykke ) {

        FeltDTO[] braet = spil.getBraet().getBretArray();

        FeltDTO endeligtFelt = BevaegelsesLogik.beregnEndeligtFelt( braet, braet[spiller.getSpillerPosition()], felterAtRykke  );

        int gangeOverStart  = BevaegelsesLogik.antalGangeOverStart(spiller.getSpillerPosition(), felterAtRykke, braet.length);

        rykSpillerTilFelt( spiller, endeligtFelt, gangeOverStart);
    }


    /**
     * @author Malte
     * Rykker spilleren til et specifikt felt på brættet, og udløser aktioner
     * ift. feltet, samt UI-metoder ifm. at flytte felt.
     * Beregner ikke selv, hvor mange gange spilleren bevæger sig over start,
     * men den udløser metoden passererStart() i SpillerCO med udgangspunkt i
     * 'gangeOverStart'
     *
     * @param spiller Spiller der skal rykkes
     * @param felt Feltet spilleren skal rykke til
     * @param gangeOverStart Hvor mange gange over start spilleren kommer. Hvis =0 sker der ikke noget.
     */
    public void rykSpillerTilFelt( SpillerCO spiller, FeltDTO felt, int gangeOverStart){

        if( gangeOverStart > 0 ) {
            spiller.setPenge(spiller.getPenge() - BevaegelsesLogik.passererStartPenge(gangeOverStart));
            ui.passeringAfStart(gangeOverStart);
            ui.updateSpillere(spiller);

        }

        spiller.setSpillerPosition(felt.getPlacering());

        ui.duErLandetPå(felt, spiller);
        HandelsController handel = new HandelsController();
        felt.aktionPaaFelt(handel, this, ui);
    }


    //_____________________________________
    //Tjekkere:

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForFeangselsStraf(){
        if (spil.getSpillerMedTur().isFaengselsStraf()) {
            if (!spil.getSpillerMedTur().isHarAnketDomDenneRunde()) {
                ui.terminalLinje();
                ui.anketStraffeDom(spil.getSpillerTur());
                anketDomsigelse();
                spil.getSpillerMedTur().setHarAnketDomDenneRunde(true);
            }

        }
    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekOmGivetOp() {
        if (spil.getSpillerMedTur().isHarGivetOp()) {
            if (spil.getSpillerTur() == spil.getAntalSpillere()) {
                spil.setSpillerTur(1);
            } else {
                spil.setSpillerTur(spil.getSpillerTur() + 1);
            }
        }
    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForVinder() {
        if (spil.getAntalSpillere() - tjekAntalSpillereISpil() == 1) {
            ui.terminalLinje();
            //SpillerCO spillerMedTur = spillerObjekter.get(spillerTur - 1);
            if (!spil.getSpillerMedTur().isHarGivetOp()) {
                //Der ligger en til for at da det er den spiller i rækken, der ligger forud for vinderen, der giver op.
                spil.setVinder(spil.getSpillerMedTur().getId() + 1);
                spil.setVinderFindes(true);
                ui.vinder(spil.getSpillerMedTur().getId());
            }
        }
    }

    /**
     * Indsæt beskrivelse her
     * @return
     */
    public int tjekAntalSpillereISpil() {
        int UdgaetSpillere = 0;
        for (int i = 0; i < spil.getSpillereArrayList().size(); i++) {
            if (spil.getSpillereArrayList().get(i).isHarGivetOp()) {
                UdgaetSpillere++;
            }
        }

        return UdgaetSpillere;
    }


    /**
     * @author Filip
     * Gør det muligt for spillerne at vælge de forskellige funktioner i turmenuen og
     * sørger for at tilhørende metoder udføres
     * @param spilleBret BraetCO objekt, hvor nogle af metoderne benyttes af turmenu
     * @param terningsKrus RafleBaeger objekt, som benyttes til at kaste terninger
     */
    public void turMenu(BraetDTO spilleBret, RafleBaeger terningsKrus) {

        int input = ui.TurMenu(spil.getSpillerTur(), 1, 10);

        switch (input) {
            case 1:

                if (!spil.getSpillerMedTur().isFaengselsStraf()) {
                    kastTerninger(terningsKrus);
                    //Denne funktion  kan kalder:
                    //tjekForPasseringAfStartOgRykSpiller(Raflebaeger terningKrus)
                    //og aktionPåFelt.
                }
                else if (spil.getSpillerMedTur().isFaengselsStraf()){
                    ui.kanIkkeSlaaFaengsel();
                }
                break;
            case 2:
                slutSpillerTur();
                break;
            case 3:
                spil.getSpillerMedTur().chanceKortMuligheder(this,ui);
                break;
            case 4:
                spil.getSpillerMedTur().visEjendeFelter(ui);
                break;
            case 5:
                /*spilleBret.printBret(ui);*/
                break;
            case 6:
                printSpilleresInfo();
                break;
            case 7:
                /*spil.getSpillerMedTur().givOp(this, ui);*/
                break;
            case 8:
                spil.getSpillerMedTur().koebHusPaaEjendom(ui);
                break;
            case 9:
                /*spil.getSpillerMedTur().handelMedEjendomme();*/
                break;
            default:
                ui.ikkeMuligt();
        }

    }


    public void start(){

        int input = ui.velkomstMenu(1,3);

        switch( input ){

            case 1:
                spil = SpilGenerator.genererSpil();
                break;

            case 2:
                spil = spilIndstillinger();
                break;
        }

        indtastSpillerNavne();

        koerSpil();
    }


    /**
     * Efterspørger brugerdefineret indstillinger til et spil,
     * og generer derefter spillet.
     * @author Malte
     * @return Det generet spil med brugerdefineret indstillinger
     */
    private Spil spilIndstillinger(){

        int antalFelter = ui.instilingsSporgsmaal0(9, 21);
        int antalSpillere = ui.instilingsSporgsmaall(2,11);
        // TODO: Implementer disse i UI v
        int antalChancekort = 20; // ui.indstillingsSpørgsmål, antalchancekort
        double startPenge = 2000; // ui.startpenge

        return SpilGenerator.genererSpil( antalSpillere, antalFelter, antalChancekort, startPenge );
    }


    /**
     * Beder UI om at efterspørge spillernavne, på spillerne i det nuvaerende
     * spil.
     * Det er ikke nødvendigt, at køre denne for at kunne køre spillet.
     * @author Malte
     */
    private void indtastSpillerNavne() {

        // Et for-each loop der kører i gennem alle spillere.
        for( SpillerDTO spiller : spil.getSpillere() ){

            String navn = ui.spillerNavne();

            /*Det er ikke muligt for GUI'en at vise to spillere med det samme navn. Derfor er det ikke heller ikke muligt for to spillere at hedde det samme.
            Derfor er der lavet denne kode der sikrer at to spillere ikke kan hedde det samme.*/
            boolean harNavn = kontrollerNavn(navn);

            while(harNavn){
                ui.spillerMaaIkkeEns();
                navn =  ui.spillerNavne();
                //Her undersøges det om en allerede genereret spiller har det samme navn.
                if(!kontrollerNavn(navn)){
                    harNavn = kontrollerNavn(navn);
                }
            }
            spiller.setNavn(navn);

        }
    }


    /**
     * Kører det spil som SpilControlleren er blevet givet (spil variablen).
     * @author Malte
     */
    public void koerSpil(){

        ui.startSpil( spil );

        do{
            tjekForVinder();
            tjekOmGivetOp();
            tjekForFeangselsStraf();

            if( !spil.getVinderFindes() ){
                turMenu( spil.getBraet(), spil.getRaflebaeger() );

            }else{
                break;
            }

        }while( true );


        ui.spilletErSlut();
    }





}
