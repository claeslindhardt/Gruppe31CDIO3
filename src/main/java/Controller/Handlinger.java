package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.chancekort.Chancekort;

import java.util.ArrayList;

public class Handlinger {


    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void givOp(Spiller spiller, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int svar;
        svar = userInterfaceKontrakt.vilDuGiveOp();
        if(svar==1) {
           spiller.setHarGivetOp(true);
            spiller.getSpillerEjendomme().clear();
            userInterfaceKontrakt.takForSpillet();
            spil.slutSpillerTur();
        }
        else {
            userInterfaceKontrakt.duGavIkkeOp();
        }

    }

    /**
     * Indsæt beskrivelse her
     * @param userInterfaceKontrakt
     */
    public void chanceKortMuligheder(Spiller spiller, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
        ArrayList<Chancekort> chancekort = spiller.getChancekort();

        if ( chancekort.size() > 0 ) {

            //Her printes de forskellige muligher:
            userInterfaceKontrakt.chanceKortHar();

            //Her er controlleren der lader en reagere på mulighederne
            int valg = userInterfaceKontrakt.chanceKortNr( spiller );

            if (valg == chancekort.size()) {
                //Der lægges en til for at er det stadig er den samme spilleres tur. I TurMenu bliver der nemlig udskrevet spillerens tur.
                spil.turMenu( );
            } else {
                Chancekort valgtChancekort = chancekort.get(valg);
                Handel handel = new Handel();
                valgtChancekort.BetingetAktion(handel, spil, userInterfaceKontrakt);
                chancekort.remove(valgtChancekort);
                spil.getSpil().addChancekort(valgtChancekort);
            }
        } else {
            userInterfaceKontrakt.ingenChanceKort();
        }
    }


    /**
     * @author Filip
     * Når spilleren lander på et taxifelt, kan vedkommende rykke til et felt efter deres valg
     * (bortset fra selve taxifeltet).
     * @param spil SpilController objekt, der der giver adgang til dens metoder, bl.a. rykSpillerTilFelt
     * @param userInterfaceKontrakt Forbindelse til UI, så metoden kan modtage input og give output tilbage
     */
    public void tagTaxi( Spiller spiller, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int destination;
        //Spiller relavantSpiller = SpilData.getSpillerMedTur();

        destination = userInterfaceKontrakt.hvorHen( spiller.getSpillerPosition(),1, spil.getSpil().getAntalFelter() );
        if(destination>spil.getSpil().getAntalFelter() || destination< 1 ){
            userInterfaceKontrakt.holdDigPaaBrettet();
        }
        else if (destination == spiller.getSpillerPosition() ){
            userInterfaceKontrakt.ikkeTaxiTilTaxi();
            tagTaxi(spiller, spil, userInterfaceKontrakt);
        }else{
            spil.getRykSpiller().rykSpillerTilFelt( spiller, spil.getSpil().getFelter()[destination], 1, userInterfaceKontrakt, spil );
        }
    }

}
