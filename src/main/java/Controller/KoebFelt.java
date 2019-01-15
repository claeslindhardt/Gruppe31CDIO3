package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spiller;
import ModelEnteties.felter.EjendomCO;

public class KoebFelt {


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
            ui.gennemfortKoeb(ejendom, spiller);
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
    public void koebJernbane(JernbaneCO jernbane, Spiller spiller, UserInterfaceKontrakt userInterfaceKontrakt, SpilController spil){

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
            jernbane.tagTog(spil, userInterfaceKontrakt);

        } else {
            userInterfaceKontrakt.monetosMangel();
        }
    }

}