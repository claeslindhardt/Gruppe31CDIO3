package controller.subcontroller;

import controller.SpilController;
import model.felter.ejeligefelter.EjeligtFelt;
import model.raflebaeger.RafleBaeger;
import view.UserInterface;
import model.Spiller;
import model.chancekort.Chancekort;

import java.util.ArrayList;

public class Handlinger {


    /**
     * @author Andreas
     * Metoden gør at en spiller kan give op.
     * Valget vises i UI. Ved valg 1,
     * @param userInterface
     */
    public boolean givOp( UserInterface userInterface){
        int svar;
        svar = userInterface.vilDuGiveOp();

        if(svar == 0) {
            return true;
        }
        else {
            return false;
        }
    }





    /**
     * @auther Andreas
     * Vælger og eksekverer et chancekort fra en liste der bliver vist i UI.
     * @param userInterface
     */
    public void chanceKortMuligheder(Spiller spiller, SpilController spil, UserInterface userInterface) {
        ArrayList<Chancekort> chancekort = spiller.getChancekort();

        if ( chancekort.size() > 0 ) {

            int valg = userInterface.chanceKortNr( spiller );

            if (valg < chancekort.size()) {

                Chancekort valgtChancekort = chancekort.get(valg);
                spil.getBrugChancekort().brugChancekort( valgtChancekort, spiller, spil.getSpil(), userInterface, spil );
                chancekort.remove(valgtChancekort);
                spil.getSpil().addChancekort(valgtChancekort);}

        } else {
            userInterface.ingenChanceKort();
        }
    }


    /**
     * @author Filip
     * Metode, der afgør om en faengslet spiller løslades eller skal blive i faengsel.
     */
    public void ankerDom( Spiller spiller, RafleBaeger raflebaeger, UserInterface ui ) {

        ui.ankerDom();

        raflebaeger.slaa();
        ui.terningerResultat( raflebaeger );

        boolean loesladt = raflebaeger.erEns();

        ui.anketDomResultat( loesladt );

        if( loesladt ) {
            spiller.setErIFaengsel(false);
        }
    }

}
