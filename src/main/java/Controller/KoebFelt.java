package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.EjeligtFelt;
import ModelEnteties.felter.Ejendom;
import ModelEnteties.felter.Rederi;

public class KoebFelt {


    public void koebFelt(EjeligtFelt felt, Spiller spiller, UserInterfaceKontrakt ui ){

        if( felt instanceof Ejendom){
            koebEjendom( (Ejendom) felt, spiller, ui);

        }else if( felt instanceof Bryggeri  ){
            koebBryggeri( (Bryggeri) felt, spiller, ui );

        }else if( felt instanceof Rederi ){
            koebRederi( (Rederi) felt, spiller, ui);

        }
    }


    /**
     * Indsæt beskrivelse her
     * @param ejendom
     * @param ui
     */
    public void koebEjendom(Ejendom ejendom, Spiller spiller, UserInterfaceKontrakt ui) {

        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if ( ejendom.getEjer() == spiller ) {
            ui.tetPaaMonopol();
        }
        else if (spiller.getPenge() > ejendom.getPris()) {
            ui.gennemfoertKoeb(ejendom, spiller);
            spiller.addPenge( - ejendom.getPris() );
            ui.updateSpillere(spiller);

            //skifte ejerskab
            ejendom.setEjer(spiller);
            spiller.getSpillerEjendomme().add(ejendom);

        } else {
            ui.monetosMangel();
        }
    }


    public void koebBryggeri(Bryggeri bryggeri, Spiller spiller, UserInterfaceKontrakt userInterfaceKontrakt) {
        if ( bryggeri.getEjer() == spiller ) {
            userInterfaceKontrakt.alleredeEjer();

        } else if ( spiller.getPenge() > bryggeri.getPris()) {

            spiller.addPenge( - bryggeri.getPris() );
            userInterfaceKontrakt.ditBryggeri();
            userInterfaceKontrakt.updateSpillere( spiller );

            //skifte ejerskab
            bryggeri.setEjer( spiller );
            spiller.addBryggeri(bryggeri);


        } else {
            userInterfaceKontrakt.monetosMangel();
        }
    }

    public void koebRederi(Rederi rederi, Spiller spiller, UserInterfaceKontrakt ui) {

        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if ( rederi.getEjer() == spiller ) {
            ui.tetPaaMonopol();
        }
        else if (spiller.getPenge() > rederi.getPris()) {
            ui.ditRederi(rederi, spiller);
            spiller.addPenge( - rederi.getPris() );
            ui.updateSpillere(spiller);

            //skifte ejerskab
            rederi.setEjer(spiller);
            spiller.getSpillerRederier().add(rederi);

        } else {
            ui.monetosMangel();
        }
    }

}
