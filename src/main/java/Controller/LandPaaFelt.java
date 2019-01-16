package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;

public class LandPaaFelt {

    public void landPaaFelt(FeltDTO felt, Spiller spiller, UserInterfaceKontrakt ui){

        ui.duErLandetPå(felt, spiller);

        if( felt instanceof EjeligtFeltDTO ) {
            EjeligtFeltDTO ejeligtFeltDTO = (EjeligtFeltDTO) felt;

            if (ejeligtFeltDTO instanceof EjendomCO) {

            } else if (ejeligtFeltDTO instanceof Bryggeri) {

            } else if ( ejeligtFeltDTO instanceof Rederi) {

            }

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


    public void ejeligtFelt(  ){

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
