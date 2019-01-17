package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.ChanceAktionDTO;

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
     * @auther Andreas
     * Vælger og eksekverer et chancekort fra en liste der bliver vist i UI.
     * @param userInterfaceKontrakt
     */
    public void chanceKortMuligheder(Spiller spiller, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
        ArrayList<ChanceAktionDTO> chancekort = spiller.getChancekort();

        if ( chancekort.size() > 0 ) {

            //Her printes de forskellige muligher:
            userInterfaceKontrakt.chanceKortHar();


            int valg = userInterfaceKontrakt.chanceKortNr( spiller );

            if (valg < chancekort.size()) {

                ChanceAktionDTO valgtChancekort = chancekort.get(valg);
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
