package controller.subcontroller;

import controller.SpilController;
import model.raflebaeger.RafleBaeger;
import view.UserInterfaceKontrakt;
import model.Spiller;
import model.chancekort.Chancekort;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;

import java.util.ArrayList;

public class Handlinger {


    /**
     * @author Andreas
     * Metoden gør at en spiller kan give op.
     * Valget vises i UI. Ved valg 1,
     * @param userInterfaceKontrakt
     */
    public boolean givOp( UserInterfaceKontrakt userInterfaceKontrakt ){
        int svar;
        svar = userInterfaceKontrakt.vilDuGiveOp();

        if(svar == 0) {
            userInterfaceKontrakt.harGivetOp();
            return true;
        }
        else {
            return false;
        }
    }

    public void fjernEjerFraEjendom(Spiller spiller, UserInterfaceKontrakt userInterfaceKontrakt){

        for(Ejendom x:spiller.getSpillerEjendomme()){
            x.setEjer(null);
            x.setHarHotel(false);
            x.setAntalHuse(0);
            userInterfaceKontrakt.byggetHus(x);
            userInterfaceKontrakt.byggeHotel(x);
        }

        for(Rederi x: spiller.getSpillerRederier()){
            x.setEjer(null);
        }

        for(Bryggeri x: spiller.getSpillerBryggerier()){
            x.setEjer(null);
        }

    }


    /**
     * @auther Andreas
     * Vælger og eksekverer et chancekort fra en liste der bliver vist i UI.
     * @param userInterfaceKontrakt
     */
    public void chanceKortMuligheder(Spiller spiller, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
        ArrayList<Chancekort> chancekort = spiller.getChancekort();

        if ( chancekort.size() > 0 ) {

            //Her printes de forskellige muligher:
            userInterfaceKontrakt.chanceKortHar();


            int valg = userInterfaceKontrakt.chanceKortNr( spiller );

            if (valg < chancekort.size()) {

                Chancekort valgtChancekort = chancekort.get(valg);
                spil.getBrugChancekort().brugChancekort( valgtChancekort, spiller, spil.getSpil(), userInterfaceKontrakt, spil );
                chancekort.remove(valgtChancekort);
                spil.getSpil().addChancekort(valgtChancekort);}

        } else {
            userInterfaceKontrakt.ingenChanceKort();
        }
    }


    /**
     * @author Filip
     * Metode, der afgør om en faengslet spiller løslades eller skal blive i faengsel.
     */
    public void ankerDom( Spiller spiller, RafleBaeger raflebaeger, UserInterfaceKontrakt ui ) {

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
