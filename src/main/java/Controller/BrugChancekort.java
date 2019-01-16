package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.ChanceAktionDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;

public class BrugChancekort {


    public void brugChancekort( ChanceAktionDTO chancekort, Spiller spiller, Spil spil, UserInterfaceKontrakt ui, SpilController spilController ){

        if( chancekort instanceof RykkerSpillerCO ){
            rykkerSpiller( spiller, ui, spilController );

        }else if( chancekort instanceof GratisUdAfFaengselCO){
            gratisUdAfFaengsel( spiller, ui );

        }else if( chancekort instanceof GiverPengeCO ){
            giverPenge( chancekort, spiller, ui );

        }else if( chancekort instanceof TagerPengeCO ){
            tagerPenge( chancekort, spiller, ui );
        }

        // Fjerner chancekortet fra spilleren, og lægger det i bunken.
        if( spiller.harChancekort(chancekort) ){
            spiller.fjernChancekort( chancekort );
        }
        spil.addChancekort( chancekort );

    }


    public void giverPenge( ChanceAktionDTO chancekort, Spiller spiller, UserInterfaceKontrakt ui ){
        spiller.addPenge( chancekort.getPengeVerdi() );
        ui.updateSpillere( spiller );
    }

    public void gratisUdAfFaengsel( Spiller spiller, UserInterfaceKontrakt ui ){
        spiller.setFaengselsStraf(false);
        spiller.setHarSlaaetForTuren(false);
        ui.brugtUdAfFaengsel();
    }

    public void tagerPenge( ChanceAktionDTO chancekort, Spiller spiller, UserInterfaceKontrakt ui){
        spiller.addPenge( -chancekort.getPengeVerdi() );
        ui.updateSpillere( spiller );
    }


    public void rykkerSpiller( Spiller spiller, UserInterfaceKontrakt ui, SpilController spilController ){
        spilController.getHandlinger().tagTaxi( spiller, spilController, ui );
        ui.chanceKortBrugt();
    }



}
