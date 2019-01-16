package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.chancekort.Chancekort;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.chancekort.JustererPenge;
import ModelEnteties.chancekort.RykTilBestemtFelt;
import ModelEnteties.chancekort.SmidIFaengsel;
import ModelEnteties.felter.Faengsel;
import ModelEnteties.felter.Felt;

public class BrugChancekort {


    public void brugChancekort(Chancekort chancekort, Spiller spiller, Spil spil, UserInterfaceKontrakt ui, SpilController spilController ){

        if( chancekort instanceof RykkerSpillerCO ){
            rykkerSpiller( spiller, ui, spilController );

        }else if( chancekort instanceof GratisUdAfFaengselCO){
            gratisUdAfFaengsel( spiller, ui );

        }else if( chancekort instanceof GiverPengeCO ){
            giverPenge( chancekort, spiller, ui );

        }else if( chancekort instanceof TagerPengeCO ) {
            tagerPenge(chancekort, spiller, ui);

        }else if( chancekort instanceof JustererPenge) {
            justererPenge((JustererPenge) chancekort, spiller, ui);

        }else if( chancekort instanceof RykTilBestemtFelt ){
            rykTilBestemtFelt( (RykTilBestemtFelt) chancekort, spiller, spilController, ui );

        }else if( chancekort instanceof SmidIFaengsel ){
            smidIFaengsel( spiller , spil, ui );

        }

        // Fjerner chancekortet fra spilleren, og lægger det i bunken.
        if( spiller.harChancekort(chancekort) ){
            spiller.fjernChancekort( chancekort );
        }
        spil.addChancekort( chancekort );

    }


    public void giverPenge(Chancekort chancekort, Spiller spiller, UserInterfaceKontrakt ui ){
        spiller.addPenge( chancekort.getPengeVerdi() );
        ui.updateSpillere( spiller );
    }

    public void gratisUdAfFaengsel( Spiller spiller, UserInterfaceKontrakt ui ){
        spiller.setFaengselsStraf(false);
        spiller.setHarSlaaetForTuren(false);
        ui.brugtUdAfFaengsel();
    }

    public void tagerPenge(Chancekort chancekort, Spiller spiller, UserInterfaceKontrakt ui){
        spiller.addPenge( -chancekort.getPengeVerdi() );
        ui.updateSpillere( spiller );
    }


    public void rykkerSpiller( Spiller spiller, UserInterfaceKontrakt ui, SpilController spilController ){
        spilController.getHandlinger().tagTaxi( spiller, spilController, ui );
        ui.chanceKortBrugt();
    }

    public void justererPenge( JustererPenge chancekort, Spiller spiller, UserInterfaceKontrakt ui ){
        spiller.addPenge( chancekort.getPengeVaerdi() );
        ui.updateSpillere( spiller );
    }

    public void rykTilBestemtFelt( RykTilBestemtFelt chancekort, Spiller spiller, SpilController spilController, UserInterfaceKontrakt ui ){
        Felt felt = spilController.getSpil().getFelter()[ chancekort.getFeltNummer() ];
        spilController.getRykSpiller().rykSpillerTilFelt(spiller, felt, 1, ui, spilController );
    }

    public void smidIFaengsel( Spiller spiller, Spil spil, UserInterfaceKontrakt ui ){
        Faengsel faengsel = spil.getFaengsel();

        spiller.setFaengselsStraf(true);
        spiller.setSpillerPosition( faengsel.getPlacering() );

        ui.iFaengselMedDig();
        ui.duErLandetPå( faengsel, spiller);
    }


}
