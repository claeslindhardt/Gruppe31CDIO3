package controller.subcontroller;

import view.UserInterface;
import model.Spiller;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;

public class KoebFelt {


    public void koebFelt(EjeligtFelt felt, Spiller spiller, UserInterface ui ){

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
    public void koebEjendom(Ejendom ejendom, Spiller spiller, UserInterface ui) {

        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if ( ejendom.getEjer() == spiller ) {
            ui.ejerAlleredeFelt();
        }
        else if (spiller.getPenge() > ejendom.getPris()) {
            spiller.addPenge( - ejendom.getPris() );

            //skifte ejerskab
            ejendom.setEjer(spiller);
            spiller.getEjendomme().add(ejendom);

            ui.gennemfoertKoeb( ejendom, spiller);

        } else {
            ui.manglerPenge();
        }
    }


    public void koebBryggeri(Bryggeri bryggeri, Spiller spiller, UserInterface userInterface) {

        if ( spiller.getPenge() > bryggeri.getPris()) {

            spiller.addPenge( - bryggeri.getPris() );

            //skifte ejerskab
            bryggeri.setEjer( spiller );
            spiller.addBryggeri(bryggeri);

            userInterface.gennemfoertKoeb( bryggeri, spiller );


        } else {
            userInterface.manglerPenge();
        }
    }

    public void koebRederi(Rederi rederi, Spiller spiller, UserInterface ui) {

        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if ( rederi.getEjer() == spiller ) {
            ui.ejerAlleredeFelt();
        }
        else if (spiller.getPenge() > rederi.getPris()) {
            spiller.addPenge( - rederi.getPris() );

            //skifte ejerskab
            rederi.setEjer(spiller);
            spiller.getRederier().add(rederi);

            ui.gennemfoertKoeb(rederi, spiller);

        } else {
            ui.manglerPenge();
        }
    }

}
