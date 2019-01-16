package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.ChanceAktionDTO;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;

import java.util.ArrayList;

public class LandPaaFelt {

    public void landPaaFelt( SpilController spilController, FeltDTO felt, Spiller spiller, UserInterfaceKontrakt ui){

        ui.duErLandetPå(felt, spiller);

        if( felt instanceof EjeligtFeltDTO ) {
            ejeligtFelt( spilController, spiller, (EjeligtFeltDTO) felt, ui );

        }else if ( felt instanceof StartCO ){
            startFelt( ui );

        } else if( felt instanceof ChanceFeltCO ) {
            proevLykken( spilController, ui );

        } else if( felt instanceof FriParkering ){

        } else if( felt instanceof GaaIFaengselCO ) {
            gaaIFaengsel(spilController, ui );

        } else if( felt instanceof FaengselCO){
            faengsel(ui);

        } else if (felt instanceof IndkomstSkat){

        } else if( felt instanceof StatsSkat) {

        }

    }


    public void ejeligtFelt( SpilController spilController, Spiller spiller, EjeligtFeltDTO felt, UserInterfaceKontrakt ui ){

        if( felt.getEjer() == null ){

            int kobsBeslutning = ui.ejendomsBud();

            switch (kobsBeslutning){

                case 0:

                    spilController.getKoebFelt().koebFelt( felt, spiller, ui );
                    break;

                case 1:
                    ui.forsetTur();
                    break;

                default:
                    ui.ikkeMuligt();

            }

        }else if( felt.getEjer() != null  &&  felt.getEjer() != spiller ){
            ui.betalRente();
            ui.updateSpillere( spiller );
            spilController.getHandel().indsamleLeje( spilController.getSpil(),felt, spiller, ui);

        }else if( felt.getEjer() == spiller ){
            ui.tetPaaMonopol();

        }

    }

    public void startFelt( UserInterfaceKontrakt ui ){
        ui.landetPaaStart();
    }


    public void proevLykken( SpilController spilController, UserInterfaceKontrakt ui ){
        ArrayList<ChanceAktionDTO> chancekort = spilController.getSpil().getChanceKort();

        ChanceAktionDTO trukketKort = chancekort.get(0);
        chancekort.remove(0);
        chancekort.add(trukketKort);

        trukketKort.DirketeAktion( spilController.getHandel(), spilController, ui );
    }

    public void gaaIFaengsel( SpilController spilController, UserInterfaceKontrakt ui ){
        Spiller spillerMedTur = spilController.getSpil().getSpillerMedTur();
        FaengselCO faengsel = spilController.getSpil().getFaengsel();

        spillerMedTur.setFaengselsStraf(true);
        spillerMedTur.setSpillerPosition( faengsel.getPlacering() );

        ui.iFaengselMedDig();
        ui.duErLandetPå( faengsel, spillerMedTur);

        spilController.slutSpillerTur();
    }

    public void faengsel( UserInterfaceKontrakt ui ){
        ui.paaBesoegIFaengsel();
    }

    public void indkomstSkat(){

    }

    public void statsSkat( ){

    }

}
