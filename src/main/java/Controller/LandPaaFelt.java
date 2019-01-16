package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.chancekort.Chancekort;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;

import java.util.ArrayList;

public class LandPaaFelt {

    public void landPaaFelt(SpilController spilController, Felt felt, Spiller spiller, UserInterfaceKontrakt ui){

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


    public void ejeligtFelt(SpilController spilController, Spiller spiller, EjeligtFelt felt, UserInterfaceKontrakt ui ){

        if( felt.getEjer() == null ){

            int kobsBeslutning = ui.ejendomsBud();

            switch (kobsBeslutning){

                case 0:

                    spilController.getKoebFelt().koebFelt( felt, spiller, ui );
                    break;

                case 1:
                    ui.forsetTur();
                    break;

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


    public void proevLykken( Spiller spiller, SpilController spilController, UserInterfaceKontrakt ui ){
        ArrayList<Chancekort> chancekort = spilController.getSpil().getChanceKort();

        Chancekort trukketKort = chancekort.get(0);
        chancekort.remove(0);

        ui.visChanceKort( trukketKort );

        if( trukketKort instanceof GratisUdAfFaengselCO || trukketKort instanceof RykkerSpillerCO ){
            spiller.addChancekort( trukketKort );
        }else{
            spilController.getBrugChancekort().brugChancekort( trukketKort, spiller, spilController.getSpil(),  ui,  spilController );
        }

    }

    public void gaaIFaengsel( SpilController spilController, UserInterfaceKontrakt ui ){
        Spiller spillerMedTur = spilController.getSpil().getSpillerMedTur();
        Faengsel faengsel = spilController.getSpil().getFaengsel();

        spillerMedTur.setFaengselsStraf(true);
        spillerMedTur.setSpillerPosition( faengsel.getPlacering() );

        ui.iFaengselMedDig();
        ui.duErLandetPå( faengsel, spillerMedTur);

        spilController.slutSpillerTur();
    }

    public void faengsel( UserInterfaceKontrakt ui ){
        ui.paaBesoegIFaengsel();
    }

    public void indkomstSkat( Spiller spiller, UserInterfaceKontrakt ui ){

        double nuvPengebeholdning = spiller.getPenge();
        //Her vurderer aktionPåfelt om spilleren befinder sig på felt 4, hvor der skatten der skal betales er specifik.

        ui.skatteBesked(2);

        double skat;
        int valg = 0;

        /*HEr vælges det om spilleren vil beltale 200 ell er 10 %
         * */
        String betalingsValg = ui.skatteBetaling();

        if (betalingsValg == "At betale 200") {
            valg = 1;
        } else {
            valg = 2;
        }
        switch (valg) {
            case 1:{ skat = 200;
                spiller.setPenge(nuvPengebeholdning - skat);
                break;}

            case 2:{ spiller.setPenge(nuvPengebeholdning * 0.9);
                break;}

        }

        ui.updateSpillere( spiller );

    }

    public void statsSkat( StatsSkat felt,  Spiller spiller, UserInterfaceKontrakt ui ){

        double nuvPengebeholdning = spiller.getPenge();

        // Her vurderer aktionPåfelt om spilleren befinder sig på felt 4, hvor der skatten der skal betales er specifik.

        ui.skatteBesked(1);
        spiller.setPenge(nuvPengebeholdning - felt.getSkat() );

        ui.updateSpillere(spiller);
    }

    public void friParkering(UserInterfaceKontrakt ui ){
        ui.friParkering();
    }

}
