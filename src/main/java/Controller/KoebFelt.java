package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.EjeligtFeltDTO;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.Rederi;

public class KoebFelt {


    public void koebFelt(EjeligtFeltDTO felt, Spiller spiller, UserInterfaceKontrakt ui ){

        if( felt instanceof EjendomCO ){
            koebEjendom( (EjendomCO) felt, spiller, ui);

        }else if( felt instanceof Bryggeri  ){
            koebBryggeri( (Bryggeri) felt, spiller, ui );

        }else if( felt instanceof Rederi ){
            koebRederi( (Rederi) felt, spiller, ui);

        }else if( felt instanceof JernbaneCO ){
            koebJernbane( (JernbaneCO) felt, spiller, ui );
        }


    }


    /**
     * Indsæt beskrivelse her
     * @param ejendom
     * @param ui
     */
    public void koebEjendom(EjendomCO ejendom, Spiller spiller, UserInterfaceKontrakt ui) {

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


    /**
     * Indsæt beskrivelse her
     * @param jernbane
     * @param userInterfaceKontrakt
     * @param spil
     */
    public void koebJernbane(JernbaneCO jernbane, Spiller spiller, UserInterfaceKontrakt userInterfaceKontrakt){

        //Sikkerhedsforanstaltning. Vi tjekker mod dobbeltkøb
        if ( jernbane.getEjer() == spiller ) {
            userInterfaceKontrakt.alleredeEjer();

        } else if ( spiller.getPenge() > jernbane.getPris()) {

            spiller.addPenge( -jernbane.getPris() );
            userInterfaceKontrakt.dinJernbane();
            userInterfaceKontrakt.updateSpillere( spiller );

            //skifte ejerskab
            jernbane.setEjer( spiller );
            spiller.getSpillerJernbaner().add(jernbane);

        } else {
            userInterfaceKontrakt.monetosMangel();
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
