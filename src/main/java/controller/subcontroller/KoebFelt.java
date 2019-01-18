package controller.subcontroller;

import view.UserInterfaceKontrakt;
import model.Spiller;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;

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
            ui.ejerAlleredeFelt();
        }
        else if (spiller.getPenge() > ejendom.getPris()) {
            spiller.addPenge( - ejendom.getPris() );

            //skifte ejerskab
            ejendom.setEjer(spiller);
            spiller.getSpillerEjendomme().add(ejendom);

            ui.gennemfoertKoeb( ejendom, spiller);

        } else {
            ui.manglerPenge();
        }
    }


    public void koebBryggeri(Bryggeri bryggeri, Spiller spiller, UserInterfaceKontrakt userInterfaceKontrakt) {

        if ( spiller.getPenge() > bryggeri.getPris()) {

            spiller.addPenge( - bryggeri.getPris() );

            //skifte ejerskab
            bryggeri.setEjer( spiller );
            spiller.addBryggeri(bryggeri);

            userInterfaceKontrakt.gennemfoertKoeb( bryggeri, spiller );


        } else {
            userInterfaceKontrakt.manglerPenge();
        }
    }

    public void koebRederi(Rederi rederi, Spiller spiller, UserInterfaceKontrakt ui) {

        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if ( rederi.getEjer() == spiller ) {
            ui.ejerAlleredeFelt();
        }
        else if (spiller.getPenge() > rederi.getPris()) {
            spiller.addPenge( - rederi.getPris() );

            //skifte ejerskab
            rederi.setEjer(spiller);
            spiller.getSpillerRederier().add(rederi);

            ui.gennemfoertKoeb(rederi, spiller);

        } else {
            ui.manglerPenge();
        }
    }

}
