package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.ChanceAktionDTO;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.Ejendom;
import ModelEnteties.felter.Rederi;
import spillogik.EjendomsLogik;

import java.util.ArrayList;

public class Handlinger {


    /**
     * @auther Andreas
     * Metoden gør at en spiller kan give op.
     * Valget vises i UI. Ved valg 1,
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void givOp(Spiller spiller, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int svar;
        svar = userInterfaceKontrakt.vilDuGiveOp();
        if(svar==1) {
           spiller.setHarGivetOp(true);
            fjernEjerFraEjendom(spiller, userInterfaceKontrakt);
            spiller.getSpillerEjendomme().clear();
            userInterfaceKontrakt.takForSpillet();
            spil.slutSpillerTur();
        }
        else {
            userInterfaceKontrakt.duGavIkkeOp();
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
