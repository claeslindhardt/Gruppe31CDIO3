package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;

public class LandPaaFelt {

    public void landPaaFelt( SpilController spilController, FeltDTO felt, Spiller spiller, UserInterfaceKontrakt ui){

        ui.duErLandetPå(felt, spiller);

        if( felt instanceof EjeligtFeltDTO ) {
            ejeligtFelt( spilController, spiller, (EjeligtFeltDTO) felt, ui );

        }else if ( felt instanceof StartCO ){
            startFelt( ui );

        } else if( felt instanceof ChanceFeltCO ) {

        } else if( felt instanceof FriParkering ){

        } else if( felt instanceof GaaIFaengselCO ) {

        } else if( felt instanceof FaengselCO){

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


    public void proevLykken( ){

    }

    public void gaaIFaengsel( ){


    }

    public void faengsel(){

    }

    public void indkomstSkat(){

    }

    public void statsSkat( ){

    }

}
