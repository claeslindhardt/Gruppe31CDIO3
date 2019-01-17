package controller;

import view.UserInterfaceKontrakt;
import model.chancekort.Chancekort;
import model.Spiller;
import model.felter.*;
import java.util.ArrayList;


/**
 *  controller-klassen der håndterer hvad der skal ske, når man lander paa et felt.
 *  Den tilgås udelukkende via metoden {@link #landPaaFelt}.
 *
 *  @author Malte
 */
class LandPaaFelt {


    /**
     * Vurderer typen af feltet man lander paa, og kalder den korrekte
     * controller metode ift. feltet. Alle metoderne der kan kaldes
     * befinder sig i klassen her.
     *
     * @author Malte
     *
     * @param felt      Feltet der landes paa
     * @param spiller   Spilleren der lander paa feltet
     */
    void landPaaFelt( Felt felt, Spiller spiller, SpilController spilController, UserInterfaceKontrakt ui){

        ui.duErLandetPå(felt, spiller);

        if( felt instanceof EjeligtFelt) {
            ejeligtFelt( spilController, spiller, (EjeligtFelt) felt, ui );

        }else if ( felt instanceof StartFelt){
            startFelt( ui );

        } else if( felt instanceof ProevLykken) {
            proevLykken( spiller, spilController, ui );

        } else if( felt instanceof FriParkering ){
            friParkering( ui );

        } else if( felt instanceof GaaIFaengsel) {
            gaaIFaengsel(spilController, ui );

        } else if( felt instanceof Faengsel){
            faengsel(ui);

        } else if (felt instanceof IndkomstSkat){
            indkomstSkat(spiller, ui);

        } else if( felt instanceof StatsSkat) {
            statsSkat( (StatsSkat) felt, spiller, ui);
        }
    }


    // ---------------------------------------------------------------------------------------------------------------------


    /**
     * Forløbet i at lande paa et felt af typen {@link EjeligtFelt}. Det er ens
     * hvad der sker, når man lander paa et ejeligt felt, uanset hvilket
     * felt det er - derfor er de samlet i denne ene metode.
     *
     * @author Malte
     */
    private void ejeligtFelt( SpilController spilController, Spiller spiller, EjeligtFelt felt, UserInterfaceKontrakt ui ){

        if( felt.getEjer() == null ){

            int koebsBeslutning = ui.ejendomsBud();

            if( koebsBeslutning == 0 ){
                spilController.getKoebFelt().koebFelt( felt, spiller, ui );
            }

        }else if( felt.getEjer() != null  &&  felt.getEjer() != spiller ){
            ui.betalRente();
            ui.updateSpillere( spiller );
            spilController.getHandel().indsamleLeje( spilController.getSpil(),felt, spiller, ui);

        }else if( felt.getEjer() == spiller ){
            ui.tetPaaMonopol();
        }
    }


    /**
     * Forløbet i at lande paa et felt af typen {@link StartFelt}.
     * @author Malte
     */
    private void startFelt( UserInterfaceKontrakt ui ){
        ui.landetPaaStart();
    }


    /**
     * Forløbet i at lande paa et {@link ProevLykken} felt.
     * @author Malte
     */
    private void proevLykken( Spiller spiller, SpilController spilController, UserInterfaceKontrakt ui ){
        ArrayList<Chancekort> chancekort = spilController.getSpil().getChanceKort();

        // Trækker kort
        Chancekort trukketKort = chancekort.get(0);
        chancekort.remove(0);

        ui.visChanceKort( trukketKort );

        if( trukketKort.erDirekteAktion() ){
            // Bruger kortet med det samme
            spilController.getBrugChancekort().brugChancekort( trukketKort, spiller, spilController.getSpil(),  ui,  spilController );
        }else{
            // Giver det til spilleren.
            spiller.addChancekort( trukketKort );
        }
    }


    /**
     * Forløbet i at lande paa et {@link GaaIFaengsel} felt.
     * @author Malte
     */
    private void gaaIFaengsel( SpilController spilController, UserInterfaceKontrakt ui ){
        Spiller spillerMedTur = spilController.getSpil().getSpillerMedTur();
        Faengsel faengsel = spilController.getSpil().getFaengsel();

        // Flytter spilleren
        spillerMedTur.setFaengselsStraf(true);
        spillerMedTur.setSpillerPosition( faengsel.getPlacering() );

        ui.iFaengselMedDig();
        ui.duErLandetPå( faengsel, spillerMedTur);
    }


    /**
     * Forløbet i at lande paa et {@link Faengsel} felt.
     * @author Malte
     */
    private void faengsel( UserInterfaceKontrakt ui ){
        ui.paaBesoegIFaengsel();
    }


    /**
     * Forløbet i at lande paa et {@link IndkomstSkat} felt.
     * @author Malte
     */
    private void indkomstSkat( Spiller spiller, UserInterfaceKontrakt ui ){

        int valg = ui.vaelgIndkomstSkat();

        switch (valg) {
            case 0:
                spiller.addPenge(-200);
                break;

            case 1:
                spiller.addPenge( -spiller.getPenge() * 0.1);
                break;
        }
        ui.updateSpillere( spiller );
    }


    /**
     * Forløbet i at lande paa et felt af typen {@link StatsSkat}.
     * @author Malte
     */
    private void statsSkat( StatsSkat felt,  Spiller spiller, UserInterfaceKontrakt ui ){

        ui.statsSkat( (int) felt.getSkat() );
        spiller.addPenge( -felt.getSkat() );

        ui.updateSpillere(spiller);
    }


    /**
     * Forløbet i at lande paa et felt af typen {@link FriParkering}.
     * @author Malte
     */
    private void friParkering(UserInterfaceKontrakt ui ){
        ui.friParkering();
    }

}
