package controller.subcontroller;

import controller.SpilController;
import view.UserInterfaceKontrakt;
import model.chancekort.*;
import model.Spil;
import model.Spiller;
import model.felter.aktionsfelter.Faengsel;
import model.felter.Felt;


/**
 * controller-klassen, der sørger for at bruge chancekort,
 * og lægge dem tilbage i bunken.
 * Den kan kun tilgås via {@link #brugChancekort }
 *
 * @author Malte
 */
public class BrugChancekort {


    /**
     * Vurderer typen af chancekort og kalder den korrekte controller metode ift.
     * kortet. Alle metoderne der kan kaldes befinder sig i klassen her.
     * Derudover putter den også chancekortet tilbage i spillet.
     * @author Malte
     *
     * @param chancekort    Chancekortet man bruger
     * @param spiller       Spilleren der bruger chancekortet
     */
    void brugChancekort(Chancekort chancekort, Spiller spiller, Spil spil, UserInterfaceKontrakt ui, SpilController spilController ){

        if( chancekort instanceof GratisUdAfFaengsel){
            gratisUdAfFaengsel( spiller, ui );

        }else if( chancekort instanceof JustererPenge) {
            justererPenge((JustererPenge) chancekort, spiller, ui);

        }else if( chancekort instanceof RykTilBestemtFelt ){
            rykTilBestemtFelt( (RykTilBestemtFelt) chancekort, spiller, spilController, ui );

        }else if( chancekort instanceof SmidIFaengsel ){
            smidIFaengsel( spiller , spil, ui );

        }else if( chancekort instanceof RykAntalFelter ){
            rykAntalFelter( (RykAntalFelter) chancekort, spiller, spil, spilController, ui);
        }

        // Fjerner chancekortet fra spilleren, og lægger det i bunken.
        if( spiller.harChancekort(chancekort) ){
            spiller.fjernChancekort( chancekort );
        }
        spil.addChancekort( chancekort );
    }


    // -----------------------------------------------------------------------------------------------------------------


    /**
     * Forløbet i når man bruger et GratisUdAfFaengslet chancekort.
     * @param spiller   Spilleren der bruger det
     */
    private void gratisUdAfFaengsel( Spiller spiller, UserInterfaceKontrakt ui ){
        spiller.setErIFaengsel(false);
        spiller.setHarSlaaetForTuren(false);
        ui.brugtUdAfFaengsel();
    }


    /**
     * Forløbet i når man bruger et JustererPenge-chancekort.
     * @param chancekort    Chancekortet der bruges
     * @param spiller       Spilleren der bruget det
     */
    private void justererPenge( JustererPenge chancekort, Spiller spiller, UserInterfaceKontrakt ui ){
        spiller.addPenge( chancekort.getPengeVaerdi() );
    }


    /**
     * Forløbet i når man bruger et RykTilBestemtFelt-chancekort.
     * @param chancekort    Chancekortet der bruges
     * @param spiller       Spilleren der bruget det
     */
    private void rykTilBestemtFelt( RykTilBestemtFelt chancekort, Spiller spiller, SpilController spilController, UserInterfaceKontrakt ui ){
        Felt felt = spilController.getSpil().getFelter()[ chancekort.getFeltNummer() ];

        int feltNr = chancekort.getFeltNummer();
        int spillerPosition = spiller.getSpillerPosition();

        if( feltNr < spillerPosition ){
            spilController.getRykSpiller().rykSpillerTilFelt(spiller, felt, 1, ui, spilController );
        } else {
            spilController.getRykSpiller().rykSpillerTilFelt(spiller, felt, 0, ui, spilController );
        }
    }


    /**
     * Forløbet i når man bruger SmidIFaengsel-kort
     * @param spiller       Spilleren der bruget det
     */
    private void smidIFaengsel( Spiller spiller, Spil spil, UserInterfaceKontrakt ui ){
        Faengsel faengsel = spil.getFaengsel();

        spiller.setErIFaengsel(true);
        spiller.setSpillerPosition( faengsel.getPlacering() );

        ui.gaaIFaengsel();
        ui.duErLandetPaa( faengsel, spiller);
    }


    /**
     * Forløbet i at bruge et RykAntalFelter-chancekort
     * @param chancekort    Chancekortet der bruges
     * @param spiller       Spilleren der bruger det
     */
    private void rykAntalFelter( RykAntalFelter chancekort, Spiller spiller, Spil spil, SpilController spilController, UserInterfaceKontrakt ui){
        spilController.getRykSpiller().rykSpillerAntalFelter( spil, spiller, chancekort.getAntalFelterAtRykke(), ui, spilController );
    }
}
