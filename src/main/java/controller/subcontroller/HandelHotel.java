package controller.subcontroller;

import model.Spiller;
import model.felter.ejeligefelter.Ejendom;
import spillogik.HusHotelLogik;
import view.UserInterface;

import java.util.ArrayList;

import static spillogik.BeregnLeje.beregnSalgsPrisHus;

public class HandelHotel {


    /**
     * @author Chua
     * FORLØBET i at købe et hotel på en ejendom. Dvs. den der beder UI
     * om at vise ting og tage imod inputs.
     * @param ui : hvilket UserInterface der skal bruges.
     */
    public void koebHotelForloeb( Spiller spiller, UserInterface ui ){
        Ejendom[] ejendomme = spiller.getEjendommeArray();

        if( ejendomme.length > 0 ){
            ArrayList<Ejendom> muligeEjendomme = new ArrayList<Ejendom>();


            for(int i = 0; i < ejendomme.length; i++){
                if( HusHotelLogik.kanKoebeHotel(spiller, ejendomme[i], ejendomme[i].getGruppe()) ){
                    muligeEjendomme.add(ejendomme[i]);
                }
            }

            if( muligeEjendomme.size() > 0){

                int ejendomsIndex = ui.vaelgEjendom( muligeEjendomme );
                if( ejendomsIndex < muligeEjendomme.size() ){

                    Ejendom valgtEjendom = muligeEjendomme.get( ejendomsIndex );
                    valgtEjendom.bygHotel(true);
                    valgtEjendom.setAntalHuse(0);
                    spiller.addPenge(-valgtEjendom.getHotelPris());
                    ui.byggetPaaEjendom( valgtEjendom, spiller );
                }

            }else {
                ui.kanIkkeKoebeHotel();
            }

        }else{
            ui.kanIkkeKoebeHotel();
        }
    }



// ---------------------------------------------------------------------------------------------------------------------



    /**
     * @author Andreas
     * Metoder der sælger et hotel på en ejendom.
     * Metoden taler først sammen med en metoden opretHotlekartotek, hvor der laves en ArrayList med alle de ejendomme der
     * har et hotel på sig.
     * Så vises listen i UI.
     * Og man vælger den ejendom, hvorfra der skal sælges et hus.
     * @param spiller Spilleren der vil saelge
     * @param ui
     */
    public void saelgHotelForloeb( Spiller spiller, UserInterface ui ){

        ArrayList<Ejendom> kartotek = sorterMuligeEjendomme(spiller);

        if(kartotek.size() > 0){
            int ejendomsIndex = ui.vaelgEjendom(kartotek);

            if( ejendomsIndex < kartotek.size() ) {
                Ejendom valgtEjendom = kartotek.get( ejendomsIndex );
                spiller.addPenge( beregnSalgsPrisHus(valgtEjendom,1) );
                valgtEjendom.saelgHotel(false);
                valgtEjendom.setAntalHuse(4);
                ui.solgtPaaEjendom(kartotek.get(ejendomsIndex), spiller );
            }
        }else{
            ui.kanIkkeSaelgeHotel();
        }

    }


    /**
     * Sorterer ejendomme der kan saelges et hotel paa, ved at vurdere
     * om der allerede staar et hotel.
     */
    private ArrayList<Ejendom> sorterMuligeEjendomme( Spiller spiller ){

        Ejendom[] ejendomme = spiller.getEjendommeArray();

        ArrayList<Ejendom> kartotek = new ArrayList<>();

        for( int j = 0; j < ejendomme.length; j++ ){
            if( ejendomme[j].harHotel() ){
                kartotek.add( ejendomme[j] );
            }
        }
        return kartotek;
    }

}
